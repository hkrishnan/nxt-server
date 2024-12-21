(ns nxt.config
  (:require [aero.core :as aero]
            [clojure.java.io :as io]))

(defn read-config
  ([]
   (read-config :dev))
  ([profile]
   (aero/read-config (io/resource "config/config.edn") {:profile profile})))