(ns nxt.components.api-server
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]
            [nxt.routes :as routes]))

(defn service-map [config]
  {::http/routes routes/routes
   ::http/type   :jetty
   ::http/port   (get-in config [:server :port])
   ::http/join?  (get-in config [:server :join?])})

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

(defn new-api-server [config]
  (map->ApiServer {:config config}))