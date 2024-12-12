(ns nxt.system
  (:require [com.stuartsierra.component :as component]
            [nxt.components.config :as config]
            [nxt.components.api-server :as api-server]))

(defn new-system
  "Creates a new system map. Pass an optional profile keyword (:dev, :test, :prod)
   to specify which config to use."
  ([] (new-system :development))
  ([profile]
   (component/system-map
     :config (config/new-config profile)
     :api-server (component/using 
                  (api-server/new-api-server)
                  [:config]))))

(defn start [system]
  (component/start system))

(defn stop [system]
  (when system
    (component/stop system)))

(defn -main [& args]
  (let [profile (keyword (or (first args) "production"))]
    (-> (new-system profile)
        (component/start))))