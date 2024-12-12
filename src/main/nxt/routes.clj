(ns nxt.routes
  (:require [io.pedestal.http.route :as route]
            [nxt.web.layout :as layout]))

(defn home-page [_request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (layout/home-page)})

(def routes
  (route/expand-routes
    #{["/", :get, `home-page :route-name :home]}))