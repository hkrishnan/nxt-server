(ns nxt.routes-test
  (:require [clojure.test :refer :all]
            [nxt.routes :as routes]
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

(deftest test-routes
  (testing "Routes are properly defined"
    (let [routes routes/routes]
      (is (seq routes))
      (let [home-route (first routes)]
        (is (= "/" (:path home-route)))
        (is (= :get (:method home-route)))
        (is (= :home (:route-name home-route)))))))
