(ns nxt.components.config
  (:require [com.stuartsierra.component :as component]
            [aero.core :as aero]
            [clojure.java.io :as io]
            [clojure.pprint :refer [pprint]]))

(defn- get-config-path [profile]
  (format "config/nxt/%s.edn" (name profile)))

(defn read-config
  "Read config from resources/config/nxt/[profile].edn"
  ([] (read-config :development))
  ([profile]
   (let [config-path (get-config-path profile)]
     (if-let [config-resource (io/resource config-path)]
       (do
         (println "\nReading config from:" config-path)
         (let [config (aero/read-config config-resource)]
           (println "\nLoaded configuration:")
           (println "--------------------")
           (pprint config)
           config))
       (throw (ex-info (format "Config file not found: %s" config-path)
                      {:profile profile
                       :path config-path}))))))

(defrecord Config [profile config]
  component/Lifecycle
  
  (start [this]
    (println "\n;; Starting Config component")
    (if config
      this
      (assoc this :config (read-config profile))))
  
  (stop [this]
    (println "\n;; Stopping Config component")
    (assoc this :config nil)))

(defn new-config
  ([] (new-config :development))
  ([profile]
   (map->Config {:profile profile})))