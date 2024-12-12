(ns nxt.web.layout-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [nxt.web.layout :as layout]))

(deftest test-page-template
  (testing "Basic page structure"
    (let [html (layout/page-template {:title "Test Page"} [:p "Hello"])]
      (is (string? html))
      (is (str/includes? html "<title>Test Page</title>"))
      (is (str/includes? html "<p>Hello</p>"))
      (is (str/includes? html "htmx.org")) ; HTMX script included
      (is (str/includes? html "bulma.min.css")))) ; Bulma CSS included
  
  (testing "Default title when not provided"
    (let [html (layout/page-template {} [:p "Content"])]
      (is (str/includes? html "<title>NXT Application</title>"))))
  
  (testing "Multiple content elements"
    (let [html (layout/page-template 
                {:title "Multiple"}
                [:h1 "Header"]
                [:p "Para 1"]
                [:p "Para 2"])]
      (is (str/includes? html "<h1>Header</h1>"))
      (is (str/includes? html "<p>Para 1</p>"))
      (is (str/includes? html "<p>Para 2</p>")))))

(deftest test-home-page
  (testing "Home page content"
    (let [html (layout/home-page)]
      (is (string? html))
      (is (str/includes? html "Hello World"))
      (is (str/includes? html "title")) ; Has a title
      (is (str/includes? html "subtitle")))) ; Has a subtitle
  
  (testing "Home page has required CSS classes"
    (let [html (layout/home-page)]
      (is (str/includes? html "class=\"title\""))
      (is (str/includes? html "class=\"subtitle\""))
      (is (str/includes? html "class=\"section\""))
      (is (str/includes? html "class=\"container\""))))