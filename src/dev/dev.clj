(ns dev
  (:require [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [io.pedestal.http :as http]
            [io.pedestal.test :as test]
            [nxt.system :as system]
            [clojure.pprint :refer [pprint]]))

(defonce system nil)

(defn new-system []
  (system/new-system :development))

(defn init []
  (alter-var-root #'system (constantly (new-system))))

(defn start []
  (alter-var-root #'system system/start))

(defn stop []
  (alter-var-root #'system system/stop))

(defn go []
  (init)
  (start))

(defn reset []
  (stop)
  (refresh :after 'dev/go))

;; Helper functions
(defn status []
  (println "\nSystem Status:")
  (println "------------------")
  (println "Environment:" (-> system :config :config :env name))
  (println "Server Config:")
  (pprint (-> system :config :config :server))
  (let [service (-> system :api-server :service)]
    (println "Server Running:" (boolean service))))

(defn routes []
  (when-let [routes (-> system :api-server :service ::http/routes)]
    (doseq [{:keys [path method route-name]} routes]
      (println (format "%-7s %-20s %s" (name method) path route-name)))))

(defn config []
  (println "\nCurrent Configuration:")
  (println "----------------------")
  (pprint (-> system :config :config)))

(comment
  (go)
  (status)
  )