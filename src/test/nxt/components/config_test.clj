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
        (is (= "test" (-> started :config :env)))
        
        (testing "Double start has no effect"
          (let [double-started (component/start started)]
            (is (= (:config started) (:config double-started)))))
        
        (testing "Stop clears config"
          (let [stopped (component/stop started)]
            (is (nil? (:config stopped)))))))))

(deftest test-config-content
  (testing "Config contains required keys"
    (let [config (-> (config/new-config :test)
                    component/start
                    :config)]
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
          (is (vector? (:allowed-origins http))))))))