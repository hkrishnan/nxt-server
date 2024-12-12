(ns nxt.components.config-test
  (:require [clojure.test :refer :all]
            [nxt.components.config :as config]
            [com.stuartsierra.component :as component]))

(deftest test-config-lifecycle
  (let [config-component (config/new-config :test)]
    
    (testing "Initial state"
      (is (= :test (:profile config-component)))
      (is (nil? (:config config-component))))
    
    (testing "Started state"
      (let [started (component/start config-component)]
        (is (map? (:config started)))
        (is (= :test (-> started :config :env)))  ; Changed expectation to keyword
        
        (testing "Double start has no effect"
          (let [double-started (component/start started)]
            (is (= (:config started) (:config double-started)))))
        
        (testing "Stop clears config"
          (let [stopped (component/stop started)]
            (is (nil? (:config stopped)))))))))

(deftest test-config-content
  (testing "Config contains required keys"
    (let [base-config (-> (config/new-config :test)
                         component/start
                         :config
                         :base)]  ; Access base config for app-name
      (is (contains? base-config :app-name))
      (is (string? (:app-name base-config)))
      (is (contains? base-config :server))
      (is (contains? base-config :http))
      (is (contains? base-config :env))
      
      (testing "Server config"
        (let [server (:server base-config)]
          (is (contains? server :port))
          (is (contains? server :host))
          (is (contains? server :join?))
          (is (number? (:port server)))
          (is (string? (:host server)))
          (is (boolean? (:join? server)))))
      
      (testing "HTTP config"
        (let [http (:http base-config)]
          (is (contains? http :allowed-origins))
          (is (map? (:secure-headers http))))))))