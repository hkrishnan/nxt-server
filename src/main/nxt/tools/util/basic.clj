(ns nxt.tools.util.basic
  (:require [com.rpl.specter :as specter]
            [camel-snake-kebab.core :as csk]
            [java-time.api :as javatime]
            [nxt.config :as config]
            [tick.core :as t]))

(defn squuid []
  (let [uuid      (java.util.UUID/randomUUID)
        time      (System/currentTimeMillis)
        secs      (quot time 1000)
        lsb       (.getLeastSignificantBits uuid)
        msb       (.getMostSignificantBits uuid)
        timed-msb (bit-or (bit-shift-left secs 32)
                          (bit-and 0x00000000ffffffff msb))]
    (java.util.UUID. timed-msb lsb)))

(defn string->uuid [uuid-string]
  (java.util.UUID/fromString uuid-string))

(defn remove-nils
  [m]
  (clojure.walk/postwalk
    (fn [x]
      (if (map? x)
        (->> (keep (fn [[k v]] (when (nil? v) k)) x)
             (apply dissoc x))
        x))
    m))

(defn local-now []
  (t/now))

(defn now [] (new java.util.Date))

(defn find-value-of-another-key [collection key value another-key]
  (first (specter/select [specter/ALL (specter/selected? #(= (key %) value)) another-key] collection)))

(defn ->camel-case-with-spce [kebab-case-string]
  (-> kebab-case-string
      csk/->Camel_Snake_Case_String
      (clojure.string/replace #"_" " ")))

;; To java-time from #inst
(defn to-jt-from-inst [inst]
  (java.time.LocalDateTime/ofInstant 
    (java.time.Instant/ofEpochMilli (.getTime inst)) 
    java.time.ZoneOffset/UTC))

;; To #inst from jtime or jt or java-time
(defn to-inst-from-jt [local-date-time]
  (java.util.Date. 
    (.toEpochMilli 
      (.toInstant local-date-time java.time.ZoneOffset/UTC))))

(defn created-at []
  (javatime/local-date-time))

(defn created-at-jt []
  (javatime/local-date-time))

(defn created-at-inst []
  (-> (created-at)
      to-inst-from-jt))

(defn get-session-valid-minutes []
  (get-in (config/read-config) [:session :session-valid-minutes]))

(defn expires-at []
  (javatime/plus (javatime/local-date-time)
                 (javatime/minutes (get-session-valid-minutes))))

(defn expires-at-jt []
  (expires-at))

(defn expires-at-inst []
  (-> (expires-at)
      to-inst-from-jt))

;;Example CompanyID "PWZ-4831"
(defn generate-company-id []
  (str (apply str (repeatedly 3 #(rand-nth "INWSKGRHKZPYR"))) "-"
       (apply str (repeatedly 4 #(rand-nth "123456789")))))

(defn generate-k-name []
  (str "cust-" (apply str (repeatedly 4 #(rand-nth "invignowcustomer")))))

(defn split-on-space [word]
  (clojure.string/split word #"\s"))