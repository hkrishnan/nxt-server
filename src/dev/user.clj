(ns user
  (:require [dev]
            [com.stuartsierra.component.repl :refer [reset set-init start stop system]]))

(println "
Development environment ready.

Available commands:
------------------
(start)          Start the system
(stop)           Stop the system
(reset)          Reset the system (stop, reload code, start)
(dev/status)     Check system status
(dev/routes)     List available routes

For more tools, explore the (dev) namespace.
")

;; Aliases for common commands
(def go start)
(def halt stop)

;; Initialize the development system
(set-init #(dev/new-dev-system))  ;; Changed to use # shorthand for fn