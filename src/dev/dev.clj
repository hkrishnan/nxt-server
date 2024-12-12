(ns dev
  (:require [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [clojure.edn :as edn]
            [io.pedestal.http :as http]
            [io.pedestal.test :as test]
            [com.stuartsierra.component.repl :refer [reset set-init start stop system]]
            [nxt.system :as system]))

;; System initialization
(defn new-dev-system
  "Creates a system map for local development"
  []
  (-> (system/config)
      (system/new-system)))

;; Set the development system as the one to be used by the REPL tools
(set-init new-dev-system)

;; Convenience functions for REPL use
(defn db
  "Returns the database from the running system"
  []
  (-> system :database))

(defn config
  "Returns the config from the running system"
  []
  (-> system :config))

;; Testing helpers
(defn test-request
  "Makes a test HTTP request to the running system. Example:
   (test-request :get \"/\")
   (test-request :post \"/api/data\" {:body {:name \"test\"}})"
  [verb url & [{:as options}]]
  (let [service (-> system :api-server :service ::http/service-fn)]
    (test/response-for service verb url options)))

;; Print the available endpoints
(defn print-routes
  "Print all routes available in the system"
  []
  (let [routes (-> system :api-server :service ::http/routes)]
    (doseq [{:keys [path method route-name]} routes]
      (println (format "%-7s %-20s %s" (name method) path route-name)))))

;; Development time server control
(defn restart-server
  "Restart just the web server part of the system"
  []
  (let [api-server (:api-server system)]
    (when-let [service (:service api-server)]
      (http/stop service))
    (let [new-service (-> api-server :config http/create-server http/start)]
      (alter-var-root #'system assoc-in [:api-server :service] new-service))))

;; REPL Convenience functions
(defn system-status
  "Print basic system diagnostics"
  []
  (println "\nSystem Status:")
  (println "------------------")
  (println "Config:" (boolean (config)))
  (let [api-server (:api-server system)]
    (println "Server running:" (boolean (:service api-server)))
    (when-let [service (:service api-server)]
      (println "Server port:" (-> service ::http/server :port)))))

(comment
  ;; Basic system lifecycle
  (start)  ; Start the system
  (stop)   ; Stop the system
  (reset)  ; Stop, reload, and start the system
  
  ;; Development helpers
  (system-status)  ; Check system status
  (print-routes)   ; See available routes
  
  ;; Test endpoints
  (test-request :get "/")
  
  ;; Restart server without restarting whole system
  (restart-server)
  
  ;; Example of making changes and reloading
  (do 
    (refresh)    ; Reload changed namespaces
    (reset))     ; Restart the system
  )