(ns nxt.web.layout
  (:require [hiccup.page :as page]))

(defn page-template [{:keys [title]} & content]
  (page/html5
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1"}]
     [:title (or title "NXT Application")]
     ;; HTMX
     [:script {:src "https://unpkg.com/htmx.org@1.9.10"}]
     ;; Bulma CSS
     [:link {:rel "stylesheet" 
            :href "https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css"}]]
    [:body
     [:section.section
      [:div.container
       content]]]))

(defn home-page []
  (page-template
    {:title "Hello World"}
    [:h1.title "Hello World"]
    [:p.subtitle "Welcome to NXT Application"]))