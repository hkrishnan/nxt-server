(ns dev
  (:require [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [io.pedestal.http :as http]
            [io.pedestal.test :as test]
            [com.stuartsierra.component.repl :refer [reset set-init start stop system]]
            [nxt.system :as system]
            [clojure.pprint :refer [pprint]]))

;; System initialization
(defn new-dev-system []
  (system/new-system :development))

(set-init new-dev-system)

;; Config helpers
(defn config []
  (-> system :config :config))

(defn print-config []
  (println "\nCurrent Configuration:")
  (println "----------------------")
  (pprint (config)))

;; Testing helpers
(defn test-request [verb url & [{:as options}]]
  (let [service (-> system :api-server :service ::http/service-fn)]
    (test/response-for service verb url options)))

;; Print the available endpoints
(defn print-routes []
  (let [routes (-> system :api-server :service ::http/routes)]
    (doseq [{:keys [path method route-name]} routes]
      (println (format "%-7s %-20s %s" (name method) path route-name)))))

;; System status
(defn system-status []
  (println "\nSystem Status:")
  (println "------------------")
  (println "Environment:" (-> (config) :env name))
  (println "Server Config:")
  (pprint (-> (config) :server))
  (let [service (-> system :api-server :service)]
    (println "Server Running:" (boolean service))))

(comment
  ;; Basic system lifecycle
  (start)  ; Start the system
  (stop)   ; Stop the system
  (reset)  ; Stop, reload, and start the system
  
  ;; Development helpers
  (system-status)   ; Check system status
  (print-config)    ; View current config
  (print-routes)    ; See available routes
  
  ;; Test endpoints
  (test-request :get "/")
  
  ;; Example of making changes and reloading
  (do 
    (refresh)    ; Reload changed namespaces
    (reset)))    ; Restart the system