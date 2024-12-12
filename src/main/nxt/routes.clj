(ns nxt.routes
  (:require [io.pedestal.http.route :as route]
            [nxt.web.layout :as layout]))

(defn home-page [request]
  {:status 200
   :headers {"Content-Type" "text/html"
            "Content-Security-Policy" 
            (str "default-src 'self';"
                 "script-src 'self' 'unsafe-inline' https://unpkg.com;"
                 "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net;"
                 "img-src 'self' data:")}
   :body (layout/home-page)})

(def routes
  (route/expand-routes
    #{["/" :get home-page :route-name :home]}))