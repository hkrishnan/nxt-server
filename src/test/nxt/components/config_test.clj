(ns nxt.components.config-test
  (:require [clojure.test :refer :all]
            [nxt.components.config :as config]))

(deftest test-config-lifecycle
  (let [config-component (config/new-config :test)]
    
    (testing "Initial state"
      (is (= :test (:profile config-component)))
      (is (nil? (:config config-component))))
    
    (testing "Started state"
      (let [started (config/start config-component)]
        (is (map? (:config started)))
        (is (= :test (:env (:config started))))
        
        (testing "Double start has no effect"
          (let [double-started (config/start started)]
            (is (= (:config started) (:config double-started)))))
        
        (testing "Stop clears config"
          (let [stopped (config/stop started)]
            (is (nil? (:config stopped)))))))
    
    (testing "Stop is idempotent"
      (let [stopped (config/stop config-component)]
        (is (nil? (:config stopped)))
        (let [double-stopped (config/stop stopped)]
          (is (nil? (:config double-stopped))))))))

(deftest test-config-content
  (testing "Config contains required keys"
    (let [config (config/read-config :test)]
      (is (contains? config :app-name))
      (is (contains? config :server))
      (is (contains? config :http))
      (is (contains? config :env))
      
      (testing "Server config"
        (let [server (:server config)]
          (is (contains? server :port))
          (is (contains? server :host))
          (is (contains? server :join?))
          (is (number? (:port server)))
          (is (string? (:host server)))
          (is (boolean? (:join? server)))))
      
      (testing "HTTP config"
        (let [http (:http config)]
          (is (contains? http :allowed-origins))
          (is (vector? (:allowed-origins http)))))))
  
  (testing "Development profile"
    (let [config (config/read-config :development)]
      (is (= :dev (:env config)))
      (is (false? (get-in config [:server :join?])))))
  
  (testing "Test profile"
    (let [config (config/read-config :test)]
      (is (= :test (:env config)))
      (is (= 8081 (get-in config [:server :port])))))
  
  (testing "Invalid config path"
    (is (thrown-with-msg? 
         Exception #"Config file not found"
         (config/read-config :nonexistent)))))