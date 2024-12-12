(ns nxt.components.api-server
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]
            [nxt.routes :as routes]))

(defn service-map [config]
  (let [server-config (-> config :config :server)]
    {::http/routes routes/routes
     ::http/type   :jetty
     ::http/host   (:host server-config)
     ::http/port   (:port server-config)
     ::http/join?  (:join? server-config)
     ;; Add CORS configuration if needed
     ::http/allowed-origins
     (constantly (get-in config [:config :http :allowed-origins]))}))

(defrecord ApiServer [config service]
  component/Lifecycle
  
  (start [component]
    (println ";; Starting API Server")
    (if service
      component
      (let [service (-> config
                       service-map
                       http/create-server
                       http/start)]
        (assoc component :service service))))

  (stop [component]
    (println ";; Stopping API Server")
    (when service
      (http/stop service))
    (assoc component :service nil)))

(defn new-api-server []
  (map->ApiServer {}))