(ns nxt.routes-test
  (:require [clojure.test :refer :all]
            [nxt.routes :as routes]
            [nxt.test-helpers :as helpers]
            [io.pedestal.http :as http]
            [clojure.string :as str]))

(deftest test-home-page
  (testing "Home page returns correct response"
    (let [response (routes/home-page {})]
      (is (= 200 (:status response)))
      (is (= "text/html" (get-in response [:headers "Content-Type"])))
      (is (string? (:body response)))))
  
  (testing "Response body contains required elements"
    (let [body (:body (routes/home-page {}))]
      (is (str/includes? body "<html"))
      (is (str/includes? body "Hello World"))))
  
  (testing "Response headers"
    (let [response (routes/home-page {})]
      (is (get-in response [:headers "Content-Type"]))
      (is (get-in response [:headers "Content-Security-Policy"])))

  (testing "HTML content is well-formed"
    (let [body (:body (routes/home-page {}))]
      (is (str/starts-with? body "<!DOCTYPE html>"))
      (is (str/includes? body "<head>"))
      (is (str/includes? body "</head>"))
      (is (str/includes? body "<body>"))
      (is (str/includes? body "</body>"))
      (is (str/includes? body "</html>")))))

(deftest test-routes-integration
  (helpers/with-system (helpers/test-system)
    (let [service (-> system :api-server :service)]
      
      (testing "Home route responds correctly"
        (let [response (helpers/test-request service :get "/")]
          (is (= 200 (:status response)))
          (is (= "text/html" (get-in response [:headers "Content-Type"])))
          (is (string? (:body response)))))
      
      (testing "Non-existent route returns 404"
        (let [response (helpers/test-request service :get "/not-found")]
          (is (= 404 (:status response)))))
      
      (testing "Method not allowed"
        (let [response (helpers/test-request service :post "/")]
          (is (= 405 (:status response)))))
      
      (testing "Response headers"
        (let [response (helpers/test-request service :get "/")
              headers (:headers response)]
          (is (get headers "Content-Type"))
          (is (get headers "Content-Security-Policy"))))))