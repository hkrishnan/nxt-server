(ns nxt.components.api-server-test
  (:require [clojure.test :refer :all]
            [io.pedestal.http :as http]
            [nxt.components.api-server :as api-server]
            [nxt.components.config :as config]
            [com.stuartsierra.component :as component]))

(deftest test-service-map
  (let [config-component (-> (config/new-config :test)
                            (component/start))
        service-map (api-server/service-map config-component)]
    (testing "Service map contains required keys"
      (is (contains? service-map ::http/routes))
      (is (contains? service-map ::http/type))
      (is (contains? service-map ::http/port))
      (is (contains? service-map ::http/host)))))

(deftest test-api-server-lifecycle
  (let [config (-> (config/new-config :test)
                   (component/start))
        api-server (api-server/new-api-server)]
    
    (testing "API server starts correctly"
      (let [started (-> api-server
                       (assoc :config config)
                       (component/start))]
        (is (some? (:service started)))
        
        (testing "and stops correctly"
          (let [stopped (component/stop started)]
            (is (nil? (:service stopped)))))))))