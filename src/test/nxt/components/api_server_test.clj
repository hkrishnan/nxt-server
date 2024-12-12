(ns nxt.components.api-server-test
  (:require [clojure.test :refer :all]
            [io.pedestal.http :as http]
            [nxt.components.api-server :as api-server]
            [nxt.components.config :refer [map->Config start]]))

(deftest test-service-map
  (let [config-component (-> (map->Config {:profile :test})
                            (start))
        service-map (api-server/service-map config-component)]
    (testing "Service map contains required keys"
      (is (contains? service-map ::http/routes))
      (is (contains? service-map ::http/type))
      (is (contains? service-map ::http/port))
      (is (contains? service-map ::http/host)))))

(deftest test-api-server-lifecycle
  (let [config (-> (map->Config {:profile :test})
                   (start))
        api-server (api-server/new-api-server)]
    
    (testing "API server starts correctly"
      (let [started (-> api-server
                       (assoc :config config)
                       (api-server/start))]
        (is (some? (:service started)))
        
        (testing "and stops correctly"
          (let [stopped (api-server/stop started)]
            (is (nil? (:service stopped)))))))))
