(ns nxt.system-test
  (:require [clojure.test :refer :all]
            [nxt.system :as system]
            [nxt.test-helpers :as helpers]
            [clojure.string :as str]))

(deftest test-system-lifecycle
  (testing "System starts and stops correctly"
    (let [sys (helpers/test-system)]
      (testing "Initial state"
        (is (nil? (-> sys :api-server :service)))
        (is (nil? (-> sys :config :config))))
      
      (testing "Started state"
        (let [started (system/start sys)]
          (is (some? (-> started :api-server :service)))
          (is (some? (-> started :config :config)))
          
          (testing "Components are properly initialized"
            (is (= :test (-> started :config :config :env)))
            (is (= 8081 (-> started :config :config :server :port))))
          
          (testing "Stop sequence"
            (let [stopped (system/stop started)]
              (is (nil? (-> stopped :api-server :service)))
              (is (nil? (-> stopped :config :config)))))))))

(deftest test-system-integration
  (helpers/with-system (helpers/test-system)
    (let [service (-> system :api-server :service)]
      
      (testing "Basic HTTP functionality"
        (let [response (helpers/test-request service :get "/")]
          (is (= 200 (:status response)))
          (is (str/includes? (:body response) "Hello World"))))
      
      (testing "Static assets"
        (testing "HTMX script is included"
          (let [response (helpers/test-request service :get "/")]
            (is (str/includes? (:body response) "htmx.org"))))
        
        (testing "Bulma CSS is included"
          (let [response (helpers/test-request service :get "/")]
            (is (str/includes? (:body response) "bulma.min.css")))))
      
      (testing "Security headers"
        (let [response (helpers/test-request service :get "/")
              headers (:headers response)]
          (is (headers "Content-Security-Policy"))
          (is (headers "X-Frame-Options"))
          (is (headers "X-Content-Type-Options"))))
      
      (testing "Error handling"
        (testing "404 Not Found"
          (let [response (helpers/test-request service :get "/nonexistent")]
            (is (= 404 (:status response)))
            (is (str/includes? (:body response) "Not Found"))))
        
        (testing "405 Method Not Allowed"
          (let [response (helpers/test-request service :post "/")]
            (is (= 405 (:status response))))))))