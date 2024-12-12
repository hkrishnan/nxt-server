(ns nxt.system-test
  (:require [clojure.test :refer :all]
            [nxt.system :as system]
            [nxt.test-helpers :as helpers]
            [clojure.string :as str]
            [com.stuartsierra.component :as component]))

(deftest test-system-lifecycle
  (let [sys (system/new-system :test)]
    (testing "Initial state"
      (is (nil? (-> sys :api-server :service)))
      (is (nil? (-> sys :config :config))))
    
    (testing "Started state"
      (let [started (component/start sys)]
        (is (some? (-> started :api-server :service)))
        (is (some? (-> started :config :config)))
        
        (testing "Components are properly initialized"
          (is (= :test (-> started :config :config :env)))  ; Changed expectation to keyword
          (is (= 8081 (-> started :config :config :server :port))))
        
        (testing "Stop sequence"
          (let [stopped (component/stop started)]
            (is (nil? (-> stopped :api-server :service)))
            (is (nil? (-> stopped :config :config)))))))))