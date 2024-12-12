(ns nxt.system
  (:require [com.stuartsierra.component :as component]
            [nxt.components.api-server :as api-server]
            [aero.core :as aero]
            [clojure.java.io :as io]))

(defn config []
  (aero/read-config (io/resource "config.edn")))

(defn new-system [config]
  (component/system-map
    :api-server (api-server/new-api-server config)))

(defn -main [& _args]
  (component/start (new-system (config))))