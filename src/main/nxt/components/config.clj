(ns nxt.components.config
  (:require [com.stuartsierra.component :as component]
            [aero.core :as aero]
            [clojure.java.io :as io]
            [clojure.tools.logging :as log]))

(defn- get-config-path [profile]
  (format "config/nxt/%s.edn" (name profile)))

(defn read-config
  "Read config from resources/config/nxt/[profile].edn"
  ([] (read-config :development))
  ([profile]
   (let [config-path (get-config-path profile)]
     (if-let [config-resource (io/resource config-path)]
       (do
         (log/infof "Reading config from %s" config-path)
         (aero/read-config config-resource))
       (throw (ex-info (format "Config file not found: %s" config-path)
                      {:profile profile
                       :path config-path}))))))

(defrecord Config [profile config]
  component/Lifecycle
  
  (start [this]
    (println ";; Starting Config")
    (if config
      this
      (assoc this :config (read-config profile))))
  
  (stop [this]
    (println ";; Stopping Config")
    (assoc this :config nil)))

(defn new-config
  ([] (new-config :development))
  ([profile]
   (map->Config {:profile profile})))