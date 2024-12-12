(ns nxt.test-helpers
  (:require [clojure.test :refer :all]
            [nxt.system :as system]
            [io.pedestal.test :as test]
            [io.pedestal.http :as http]
            [clojure.java.io :as io]))

(defn with-system
  "Helper macro to run tests with a system"
  [system & body]
  `(let [s# ~system]
     (try
       ~@body
       (finally
         (system/stop s#)))))

(defn test-system
  "Creates a test system with test configuration"
  []
  (system/new-system :test))

(defn test-request
  "Makes a request to a test service"
  [service verb url & [{:as options}]]
  (test/response-for (::http/service-fn service)
                    verb url
                    options))

(defn parse-body
  "Parse a response body"
  [response]
  (update response :body slurp))