(ns nxt.tools.data-model.seed
  (:require [com.rpl.specter :refer :all :as specter]
            [nxt.tools.util.basic :as basic-util]
            [nxt.tools.data-model.erd :as erd]
            [nxt.tools.data-model.schema :as schema]
            [datomic.client.api :as d]
            [nxt.tools.data-model.seed-data :as sdata]
            [nxt.tools.data-model.types-data :as td]))

(defn squuid [] (basic-util/squuid))
;(def conn111 (d/connect (service/get-client "nxt-server-dev5") {:db-name "nxt-dbxy-dev-tools"}))
;(def db (d/db conn111))

(defn generate-sample-map [entity-k-name]
  (schema/generate-sample-map-from-ns entity-k-name))

(defn remove-keys [pred m]
  (apply dissoc m (filter pred (keys m))))

(defn erd-map [] (schema/process-tools-erd {:req-output :erd :erd-map (erd/tools-erd)}))

(defn list-entities-with-one-composite-key []
  (into [] (remove nil? (let [erd (erd-map)]
                          (for [each erd]
                            (when (= 1 (count (first (specter/select [ALL :data-type #(= (:type %) "primary-key") :tuple-attrs] (:attrs each)))))
                              (:k-name each)
                              )
                            )
                          ))))
(defn list-entities-with-one-composite-key-with-no-tuples []
  (specter/transform [ALL] (fn [m]
                             (remove-keys #(clojure.string/includes? % "+") m))
                     (into [] (let [one-key-entities (list-entities-with-one-composite-key)]
                                (for [each one-key-entities]
                                  (generate-sample-map each)
                                  )))))


(defn- find-k-name-and-uuid
  ([entity-k-name kname]
   [(keyword (str "nxt.itools" "." entity-k-name "/" kname))
    (keyword (str "nxt.itools" "." entity-k-name "/uuid-" entity-k-name))
    ])
  ([entity-k-name kname parent-entity-k-name]
   [(keyword (str "nxt.itools" "." entity-k-name "/" kname))
    (keyword (str "nxt.itools" "." entity-k-name "/uuid-" entity-k-name))
    (keyword (str "nxt.itools" "." entity-k-name "/ref-" parent-entity-k-name))
    ])
  #_([entity-k-name kname parent-entity-k-name1 parent-entity-k-name2]
     [(keyword (str "nxt.itools" "." entity-k-name "/" kname))
      (keyword (str "nxt.itools" "." entity-k-name "/uuid-" entity-k-name))
      (keyword (str "nxt.itools" "." entity-k-name "/ref-" parent-entity-k-name1))
      (keyword (str "nxt.itools" "." entity-k-name "/ref-" parent-entity-k-name2))
      ])
  )

(defn- find-attr-vec [entity-k-name]
  (let [attr-vec (case entity-k-name
                   "tenant" (find-k-name-and-uuid "tenant" "k-name")
                   "language" (find-k-name-and-uuid "language" "code")
                   "entity-type" [:nxt.itools.entity-type/k-name :nxt.itools.entity-type/uuid-entity-type]
                   "composite-key-type" [:nxt.itools.composite-key-type/k-name :nxt.itools.composite-key-type/uuid-composite-key-type]
                   "data-type" [:nxt.itools.data-type/k-name :nxt.itools.data-type/uuid-data-type]
                   "cardinality-type" [:nxt.itools.cardinality-type/k-name :nxt.itools.cardinality-type/uuid-cardinality-type]
                   "attr-gen-type" (find-k-name-and-uuid "attr-gen-type" "k-name")
                   "access-permission" (find-k-name-and-uuid "access-permission" "k-name")
                   "release" (find-k-name-and-uuid "release" "release-id")
                   "deploy-status-type" (find-k-name-and-uuid "deploy-status-type" "k-name")
                   "release-stage-type" (find-k-name-and-uuid "release-stage-type" "k-name")
                   "aws-cf-stack" (find-k-name-and-uuid "aws-cf-stack" "k-name")
                   "plet-type" (find-k-name-and-uuid "plet-type" "k-name")
                   "device-type" (find-k-name-and-uuid "device-type" "k-name")
                   "row-col-type" (find-k-name-and-uuid "row-col-type" "k-name")
                   "row-select-type" (find-k-name-and-uuid "row-select-type" "k-name")
                   "plet-element-type" (find-k-name-and-uuid "plet-element-type" "k-name")
                   "html-component" (find-k-name-and-uuid "html-component" "k-name")
                   "label-position" (find-k-name-and-uuid "label-position" "k-name")

                   "application" (find-k-name-and-uuid "application" "k-name" "tenant") ;;parent to application optional argument
                   "access-group" (find-k-name-and-uuid "access-group" "k-name" "tenant") ;;parent to application optional argument
                   "data-type-comparator" (find-k-name-and-uuid "data-type-comparator" "k-name" "data-type") ;;parent to application optional argument
                   "current-release" (find-k-name-and-uuid "current-release" "current?" "release")
                   "datomic-db" (find-k-name-and-uuid "datomic-db" "k-name" "aws-cf-stack")

                   nil
                   )]
    attr-vec
    )
  )
(defn- find-parent-attr [entity-k-name]
  (case entity-k-name
    "application" :nxt.itools.tenant/k-name
    "access-group" :nxt.itools.tenant/k-name
    "data-type-comparator" :nxt.itools.data-type/k-name
    "current-release" :nxt.itools.release/release-id
    "datomic-db" :nxt.itools.aws-cf-stack/k-name
    nil
    ))

(defn find-upsert-key-with-parent [db2 entity-k-name parent-k-name k-name]
  (let [attr-vec (find-attr-vec entity-k-name)
        parent-attr-k-name (find-parent-attr entity-k-name)]

    (when attr-vec
      (let [attr-k-name (first attr-vec)
            attr-uuid (second attr-vec)
            attr-ref-parent (get attr-vec 2)]
        (ffirst (d/q '[:find ?uuid
                       :in $ ?parent-attr-k-name ?attr-ref-parent ?attr-k-name ?attr-uuid ?parent-k-name ?k-name
                       :where
                       [?pent ?parent-attr-k-name ?parent-k-name]
                       [?entity ?attr-ref-parent ?pent]
                       [?entity ?attr-k-name ?k-name]
                       [?entity ?attr-uuid ?uuid]
                       ;[?release :release/artists ?artist]
                       ;[?release :release/name ?release-name]
                       ]
                     db2 parent-attr-k-name attr-ref-parent attr-k-name attr-uuid parent-k-name k-name)))

      )))

(defn find-upsert-key [db2 entity-k-name k-name]
  (let [attr-vec (find-attr-vec entity-k-name)]
    (when attr-vec
      (let [attr-k-name (first attr-vec)
            attr-uuid (second attr-vec)]
        (ffirst (d/q '[:find ?uuid
                       :in $ ?attr-k-name ?attr-uuid ?k-name
                       :where [?entity ?attr-k-name ?k-name]
                       [?entity ?attr-uuid ?uuid]
                       ;[?release :release/artists ?artist]
                       ;[?release :release/name ?release-name]
                       ]
                     db2 attr-k-name attr-uuid k-name)))

      ))
  )
;;;     (find-full-entity-data-with-k-name db "current-release" true )
;;the data includes :db/id
(defn find-full-entity-data-with-k-name [db2 entity-k-name k-name]
  (let [attr-vec (find-attr-vec entity-k-name)]
    (when attr-vec
      (let [attr-k-name (first attr-vec)
            attr-uuid (second attr-vec)]
        (ffirst (d/q '[:find (pull ?entity [*])
                       :in $ ?attr ?k-name
                       :where [?entity ?attr ?k-name]
                       ;[?release :release/artists ?artist]
                       ;[?release :release/name ?release-name]
                       ]
                     db2 attr-k-name k-name))))))
;;no db/id
(defn find-entity-data-with-k-name [db2 entity-k-name k-name]
  (let [full-data (find-full-entity-data-with-k-name db2 entity-k-name k-name)]
    (dissoc full-data :db/id))
  )

#_(defn remove-keys [pred m]
    (apply dissoc m (filter pred (keys m))))


(defn generate-seed-fn-from-ns [entity-k-name]
  (let [m (atom {})
        v (atom [])
        entity-namespace (str "nxt.itools." entity-k-name)]
    ;(last (map #(swap! m assoc % "-") (list-idents-for-an-entity-from-erd-map entity-namespace)))
    (let [mm (last (map (fn [a]
                          (if (clojure.string/includes? a "has-many")
                            ;(swap! m assoc a [])
                            nil
                            (if (clojure.string/includes? a "has-a")
                              (swap! m assoc a {})
                              ;(swap! m assoc a "-")

                              (if (= a (keyword (str entity-namespace "/" "k-name")))
                                (do
                                  (swap! v conj (symbol (str entity-k-name "-k-name")))
                                  (swap! m assoc a (symbol (str entity-k-name "-k-name")))
                                  )
                                (if (= a (keyword (str entity-namespace "/" "name")))
                                  (do
                                    (swap! v conj (symbol (str entity-k-name "-name")))
                                    (swap! m assoc a (symbol (str entity-k-name "-name")))
                                    )
                                  (if (= a (keyword (str entity-namespace "/" "email")))
                                    (do
                                      (swap! v conj (symbol (str entity-k-name "-email")))
                                      (swap! m assoc a (symbol (str entity-k-name "-email")))
                                      )
                                    (if (= a (keyword (str entity-namespace "/" "release-id")))
                                      (do
                                        (swap! v conj (symbol (str entity-k-name "-release-id")))
                                        (swap! m assoc a (symbol (str entity-k-name "-release-id")))
                                        )
                                      (if (= a (keyword (str entity-namespace "/" "user-id")))
                                        (do
                                          (swap! v conj (symbol (str entity-k-name "-user-id")))
                                          (swap! m assoc a (symbol (str entity-k-name "-user-id")))
                                          )
                                        (if (= a (keyword (str entity-namespace "/" "code")))
                                          (do
                                            (swap! v conj (symbol (str entity-k-name "-code")))
                                            (swap! m assoc a (symbol (str entity-k-name "-code")))
                                            )
                                          (if (= a (keyword (str entity-namespace "/" "uuid-tenant")))
                                            (do
                                              (swap! v conj 'uuid-tenant)
                                              (swap! m assoc a 'uuid-tenant)
                                              )
                                            (if (= a (keyword (str entity-namespace "/" "uuid-" entity-k-name)))
                                              (do
                                                (swap! v conj (symbol (str "uuid-" entity-k-name)))
                                                (swap! m assoc a (symbol (str "uuid-" entity-k-name)))
                                                )

                                              (if (and (clojure.string/includes? a "+") (= (first (clojure.string/split (name a) #"\+")) "k-name"))
                                                (swap! m assoc a (vec (map (fn [key] (symbol (if (= key "k-name") (str entity-k-name "-" "k-name") key)))
                                                                           (clojure.string/split (name a) #"\+"))))

                                                (if (and (clojure.string/includes? a "+") (clojure.string/includes? (first (clojure.string/split (name a) #"\+")) "ref-"))

                                                  (swap! m assoc a (vec (map (fn [key] (symbol (if (clojure.string/includes? key "ref-") key (str entity-k-name "-" key))))
                                                                             (clojure.string/split (name a) #"\+"))))


                                                  (if (and (clojure.string/includes? a "+") (= (first (clojure.string/split (name a) #"\+")) "name"))
                                                    (swap! m assoc a (vec (map (fn [key] (symbol (if (= key "name") (str entity-k-name "-" "name") key)))
                                                                               (clojure.string/split (name a) #"\+"))))
                                                    (if (and (clojure.string/includes? a "+") (= (first (clojure.string/split (name a) #"\+")) "email"))
                                                      (swap! m assoc a (vec (map (fn [key] (symbol (if (= key "email") (str entity-k-name "-" "email") key)))
                                                                                 (clojure.string/split (name a) #"\+"))))
                                                      (if (and (clojure.string/includes? a "+") (= (first (clojure.string/split (name a) #"\+")) "release-id"))
                                                        (swap! m assoc a (vec (map (fn [key] (symbol (if (= key "release-id") (str entity-k-name "-" "release-id") key)))
                                                                                   (clojure.string/split (name a) #"\+"))))
                                                        (if (and (clojure.string/includes? a "+") (= (first (clojure.string/split (name a) #"\+")) "user-id"))
                                                          (swap! m assoc a (vec (map (fn [key] (symbol (if (= key "user-id") (str entity-k-name "-" "user-id") key)))
                                                                                     (clojure.string/split (name a) #"\+"))))
                                                          (if (and (clojure.string/includes? a "+") (= (first (clojure.string/split (name a) #"\+")) "code"))
                                                            (swap! m assoc a (vec (map (fn [key] (symbol (if (= key "code") (str entity-k-name "-" "code") key)))
                                                                                       (clojure.string/split (name a) #"\+"))))


                                                            (if (clojure.string/includes? a "?")
                                                              (swap! m assoc a true)
                                                              (if (clojure.string/includes? a (str entity-namespace "/" "uuid-"))
                                                                (do
                                                                  (swap! v conj (symbol (name a)))
                                                                  (swap! m assoc a (symbol (name a)))

                                                                  )
                                                                (if (= a (keyword (str entity-namespace "/" "description")))
                                                                  (do
                                                                    (swap! v conj (symbol (name a)))
                                                                    (swap! m assoc a 'description)
                                                                    )
                                                                  (do
                                                                    (swap! v conj (symbol (name a)))
                                                                    (swap! m assoc a (symbol (name a)))
                                                                    ))))
                                                            )
                                                          )
                                                        )))))))))))))))
                          ) (schema/list-idents-for-an-entity-from-erd-map entity-namespace)))]
      (let [ent-map (remove-keys #(clojure.string/includes? % "+") mm)
            args @v
            args-kw-vec (into [] (map keyword @v))
            dt-map (list 'def (symbol entity-k-name) (last (let [m (atom (into {}))]
                                                             (for [each args-kw-vec]
                                                               (if (clojure.string/includes? (name each) "uuid-")
                                                                 (swap! m assoc each '(squuid))
                                                                 (if (clojure.string/includes? (name each) "ref-")
                                                                   (swap! m assoc each [:nxt.itools.tenant/uuid-tenant '(find-upsert-key db "tenant" "nxt")])
                                                                   (swap! m assoc each "-")
                                                                   )
                                                                 )

                                                               ))))
            ]
        [[ent-map] args args-kw-vec [dt-map]]))))

(comment
  (generate-seed-fn-from-ns "entity")

  )

;;   [tenant-k-name tenant-name web-url description uuid-tenant]

#_(defn load-seed-language [conn vec-of-maps-data]
    (map (fn [m]
           (let [xact #(d/transact conn {:tx-data %})]
             (xact (seed-language m)))
           ) vec-of-maps-data))
#_(defn load-seed-tenant [conn vec-of-maps-data]
    (map (fn [m]
           (let [xact #(d/transact conn {:tx-data %})]
             (xact (seed-tenant m)))
           ) vec-of-maps-data))

(defn generate-map-destr-keys [ent]
  (into {} (for [each (second (generate-seed-fn-from-ns ent))]
             (let []
               {(symbol (str each "#")) (keyword (str each))}
               )
             )))
;;attr-db-id (str application-k-name "-" entity-k-name "-" k-name)
(defmacro gen-load-ent [ent]
  `(let []

     (defn ~(symbol (str "seed-" ent)) [{:keys ~(second (generate-seed-fn-from-ns ent))}]
       ~(first (generate-seed-fn-from-ns ent))
       )
     )
  )

(comment
  (generate-seed-fn-from-ns "entity")
  (macroexpand-1 '(gen-load-ent "entity"))

  )


(defmacro gen-load-label-with-parent [ent parent-ent]
  `(let []

     (defn ~(symbol (str "seed-" ent)) [{:keys ~(second (generate-seed-fn-from-ns ent))}]
       ~(let [m (first (generate-seed-fn-from-ns ent))
              db-id (str "itools-" ent)
              ent-map (merge (first m) {:db/id db-id})
              ;uuid-kw (keyword (str "uuid-" parent-ent))
              uuid-kw (keyword (str "nxt.itools." ent "/ref-" parent-ent))
              kw-parent-uuid (keyword (str "nxt.itools." parent-ent "/uuid-" parent-ent))
              kw-ref-attr (keyword (str "nxt.itools." parent-ent "/has-many-" parent-ent "-label-texts"))
              ;uuid-parent `(~uuid-kw (first  ~parent-data))
              uuid-parent `(second (~uuid-kw (first ~m)))
              parent-map (identity {kw-parent-uuid uuid-parent, kw-ref-attr [db-id]})
              ]
          [ent-map parent-map]
          )
       )
     )
  )


(defmacro gen-load-label-lang-with-parent [ent parent-ent]
  `(let []

     (defn ~(symbol (str "seed-" ent)) [{:keys ~(second (generate-seed-fn-from-ns ent))}]
       ~(let [m (first (generate-seed-fn-from-ns ent))
              db-id (str "itools-" ent)
              ent-map (merge (first m) {:db/id db-id})
              ;uuid-kw (keyword (str "uuid-" parent-ent))
              uuid-kw (keyword (str "nxt.itools." ent "/ref-" parent-ent))
              kw-parent-uuid (keyword (str "nxt.itools." parent-ent "/uuid-" parent-ent))
              kw-ref-attr (keyword (str "nxt.itools." parent-ent "/has-many-" parent-ent "-langs"))
              ;uuid-parent `(~uuid-kw (first  ~parent-data))
              uuid-parent `(second (~uuid-kw (first ~m)))
              parent-map (identity {kw-parent-uuid uuid-parent, kw-ref-attr [db-id]})
              ]
          [ent-map parent-map]
          )
       )
     )
  )


(defmacro gen-seed-children-with-parent [child-ent parent-ent]
  `(let []

     (defn ~(symbol (str "seed-" child-ent)) [{:keys ~(second (generate-seed-fn-from-ns child-ent))}]
       ~(let [m (first (generate-seed-fn-from-ns child-ent))
              db-id (str "itools-" child-ent)
              ent-map (merge (first m) {:db/id db-id})
              ;uuid-kw (keyword (str "uuid-" parent-ent))
              uuid-kw (keyword (str "nxt.itools." child-ent "/ref-" parent-ent))
              kw-parent-uuid (keyword (str "nxt.itools." parent-ent "/uuid-" parent-ent))
              kw-ref-attr (keyword (str "nxt.itools." parent-ent "/has-many-" child-ent "s"))
              ;uuid-parent `(~uuid-kw (first  ~parent-data))
              ;;getting the uuid of the country from the state map itself, using ref-country vector.
              uuid-parent `(second (~uuid-kw (first ~m)))
              parent-map (identity {kw-parent-uuid uuid-parent, kw-ref-attr [db-id]})
              ]
          [ent-map parent-map]
          )
       )
     )
  )



(defmacro gen-seed-child-with-parent [child-ent parent-ent]
  `(let []

     (defn ~(symbol (str "seed-" child-ent)) [{:keys ~(second (generate-seed-fn-from-ns child-ent))}]
       ~(let [m (first (generate-seed-fn-from-ns child-ent))
              db-id (str "itools-" child-ent)
              ent-map (merge (first m) {:db/id db-id})
              ;uuid-kw (keyword (str "uuid-" parent-ent))
              uuid-kw (keyword (str "nxt.itools." child-ent "/ref-" parent-ent))
              kw-parent-uuid (keyword (str "nxt.itools." parent-ent "/uuid-" parent-ent))
              kw-ref-attr (keyword (str "nxt.itools." parent-ent "/has-one-" child-ent)) ;;<<=====
              ;uuid-parent `(~uuid-kw (first  ~parent-data))
              ;;getting the uuid of the country from the state map itself, using ref-country vector.
              uuid-parent `(second (~uuid-kw (first ~m)))
              parent-map (identity {kw-parent-uuid uuid-parent, kw-ref-attr db-id}) ;;<<===, no vector, just db-id
              ]
          [ent-map parent-map]
          )
       )
     )
  )

(comment
  (merge {:db/id "hello"} #:itools.ent{:k-name "test" :name "nn"})
  )






;;"tenant"
;;{~(symbol "keys")



(defn seed-tenant [{:keys [tenant-k-name tenant-name web-url description uuid-tenant
                           active? created-at]}]
  [#:nxt.itools.tenant{
                        ;:has-many-applications [],
                        :name        tenant-name
                        ;:has-many-common-labels [],
                        ;:has-many-access-groups [],
                        :k-name      tenant-k-name
                        ;:has-many-app-deployment-dbs [],
                        ;:has-many-tenant-locations [],
                        :uuid-tenant uuid-tenant
                        :description description
                        ;:has-many-menu-groups [],
                        :web-url     web-url
                        :active? active?
                        :created-at created-at
                        ;:has-one-corporate-location nil
                        }
   ]
  )

;;"user"
;;"language"

(defn seed-language [{:keys [language-code language-name description uuid-language]}]
  [#:nxt.itools.language{:code          language-code,
                          :name          language-name,
                          :description   description,
                          :uuid-language uuid-language,
                          :code+code     [language-code language-code],
                          :name+name     [language-name language-name]}]
  )


;;"entity-type"

(defn seed-entity-type [{:keys [entity-type-k-name entity-type-name description uuid-entity-type]}]
  [#:nxt.itools.entity-type{:k-name           entity-type-k-name,
                             ; :name             entity-type-name,
                             :description      description,
                             :uuid-entity-type uuid-entity-type,
                             :k-name+k-name    [entity-type-k-name entity-type-k-name],
                             ;:name+name        [entity-type-name entity-type-name]
                             }])
;"composite-key-type"

;;this function is based on the data-type above and the data-type-comparators in the types-data ns.
(defn get-data-type-comparators []
  (into [] (remove nil? (flatten (specter/transform [ALL] (fn [m]
                                                            (let [uuid-parent (:uuid-data-type m)
                                                                  datomic-type (:datomic-type m)]
                                                              (let [comps (get-in td/data-type-comparators [datomic-type])]
                                                                (specter/transform [ALL] (fn [cmap]
                                                                                           {:ref-data-type               [:nxt.itools.data-type/uuid-data-type uuid-parent],
                                                                                            :data-type-comparator-k-name (:k-name cmap)
                                                                                            :data-type-comparator-name   (:name cmap)
                                                                                            :description                 (:description cmap)
                                                                                            :uuid-data-type-comparator   (squuid)}
                                                                                           ) comps)
                                                                )
                                                              )
                                                            ) sdata/data-type)))))
(defn find-dt-vec-for-html-comp [html-k-name]
  (first (specter/select [ALL #(= html-k-name (:k-name %)) :data-types] td/html-components))
  )
(defn find-uuid-dt-with-k-name [dt-k-name]
  (first (specter/select [ALL #(= dt-k-name (:data-type-k-name %)) :uuid-data-type] sdata/data-type))
  )
(defn create-data-elemnts []
  (into [] (remove nil? (flatten (specter/transform [ALL] (fn [html-map]
                                                            (let [html-k-name (:html-component-k-name html-map)
                                                                  html-uuid (:uuid-html-component html-map)]
                                                              (let [dt-vec (find-dt-vec-for-html-comp html-k-name)]
                                                                (for [each-dt dt-vec]
                                                                  (let [uuid-dt (find-uuid-dt-with-k-name each-dt)]
                                                                    {:ref-data-type      [:nxt.itools.data-type/uuid-data-type uuid-dt],
                                                                     :ref-html-component [:nxt.itools.html-component/uuid-html-component html-uuid],
                                                                     :description        html-k-name
                                                                     :uuid-data-element  (squuid)}
                                                                    )
                                                                  )
                                                                )

                                                              )
                                                            ) sdata/html-component)))))

#_(def
    data-element (create-data-elemnts))




;;     (generate-seed-fn-from-ns "tenant")

;;    (find-upsert-key-with-k-name db "tenant" "nxt")
;;    (find-full-entity-data-with-k-name db "tenant" "nxt")     ;;includes db/id
;;    (find-entity-data-with-k-name db "tenant" "nxt")         ;;no db/id
;;    (find-entity-data-with-k-name db "language" "en")


;;         (generate-seed-fn-from-ns "access-permission")
#_(specter/transform [ALL] (fn [m]
                             {:access-permission-k-name (:k-name m)
                              :access-permission-name   (:name m)
                              :description              (:description m)
                              :uuid-access-permission   (basic-util/squuid)}
                             ) td/access-permissions)

;;  (load-basic-seed conn111)

;;    ;;  (load-first-level-seed conn111)

;;Before doing the query, reload the file into the REPL.  New connection required.

#_(d/q '[:find (pull ?entity [*])
         :in $
         :where [?entity :nxt.itools.access-permission/k-name]
         ;[?release :release/artists ?artist]
         ;[?release :release/name ?release-name]
         ]
       db)




(defmacro create-next-seed-map [conn f vec-of-maps-data]
  `(doall (map (fn [m#]                                     ;;I put doall here as map returns a lazy seq.
                 (let [xact# #(d/transact ~conn {:tx-data %})]

                   ;(~f m#)                                  ;for testing
                   ;(xact# (~f m#))

                   )
                 ) ~vec-of-maps-data))
  )

(defmacro gen-load-ent-with-nxt-tenant-parent [ent]
  `(let []

     (defn ~(symbol (str "seed-" ent)) [{:keys ~(second (generate-seed-fn-from-ns ent))}]
       ~(let [m (first (generate-seed-fn-from-ns ent))
              db-id (str "itools-" ent)
              ent-map (merge (first m) {:db/id db-id})
              uuid-tenant (:uuid-tenant (first sdata/tenant))
              tenant-map (case ent
                           "corporate-location"
                           (identity {:nxt.itools.tenant/uuid-tenant                uuid-tenant
                                      :nxt.itools.tenant/has-one-corporate-location db-id})
                           "application" (identity {:nxt.itools.tenant/uuid-tenant           uuid-tenant
                                                    :nxt.itools.tenant/has-many-applications [db-id]})
                           "common-label" (identity {:nxt.itools.tenant/uuid-tenant            uuid-tenant
                                                     :nxt.itools.tenant/has-many-common-labels [db-id]})
                           "access-group" (identity {:nxt.itools.tenant/uuid-tenant            uuid-tenant
                                                     :nxt.itools.tenant/has-many-access-groups [db-id]})
                           "app-deployment-db" (identity {:nxt.itools.tenant/uuid-tenant                 uuid-tenant
                                                          :nxt.itools.tenant/has-many-app-deployment-dbs [db-id]})
                           "tenant-location" (identity {:nxt.itools.tenant/uuid-tenant               uuid-tenant
                                                        :nxt.itools.tenant/has-many-tenant-locations [db-id]})
                           "menu-group" (identity {:nxt.itools.tenant/uuid-tenant          uuid-tenant
                                                   :nxt.itools.tenant/has-many-menu-groups [db-id]})
                           )
              ]
          [ent-map
           tenant-map]
          ;('ent-map tenant-map)
          ;(first m)

          )
       )
     )

  )


#:nxt.itools.tenant{:has-many-applications       [],
                     :name                        "invigNow",
                     :has-many-common-labels      [],
                     :has-many-access-groups      [],
                     :k-name                      "nxt",
                     :has-many-app-deployment-dbs [],
                     :has-many-tenant-locations   [],
                     :uuid-tenant                 #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12",
                     :description                 "InvigNow tenant",
                     :has-many-menu-groups        [],
                     :web-url                     "www.invignow.com",
                     :has-one-corporate-location  nil}

(defmacro load-seed-x [conn f vec-of-maps-data]
  `(doall (map (fn [m#]                                     ;;I put doall here as map returns a lazy seq.
                 (let [xact# #(d/transact ~conn {:tx-data %})]

                   ;(~f m#)                                  ;for testing
                   (xact# (~f m#))

                   )
                 ) ~vec-of-maps-data))
  )

(defmacro load-seed-nox [conn f vec-of-maps-data]
  `(doall (map (fn [m#]                                     ;;I put doall here as map returns a lazy seq.
                 (let [xact# #(d/transact ~conn {:tx-data %})]

                   (~f m#)                                  ;for testing
                   ;(xact# (~f m#))

                   )
                 ) ~vec-of-maps-data))
  )

(comment
  sdata/cf-stack
  sdata/release-stage-type

  sdata/tenant

  (load-seed-x "conn" seed-tenant sdata/tenant)

  (load-seed-x "conn" (gen-load-ent "label-text-type") sdata/label-text-type)

  (load-seed-x "conn" (gen-load-ent "cf-stack") sdata/cf-stack)
  (load-seed-x "conn" (gen-load-ent "release-stage-type") sdata/release-stage-type)
  (create-next-seed-map "conn" (gen-load-ent-with-nxt-tenant-parent "app-deployment-db") sdata/app-deployment-db)
  (create-next-seed-map "conn" (gen-load-ent-with-nxt-tenant-parent "application") sdata/application)

  (load-seed-x "conn" (gen-load-ent-with-nxt-tenant-parent "app-deployment-db") sdata/app-deployment-db)
  (gen-load-ent "currency")
  (gen-load-ent "x")
  )


(defn load-basic-seed [conn]
  (do
    (load-seed-x conn seed-tenant sdata/tenant)
    ;(load-seed-x conn seed-language sdata/language)
    ;;(load-seed-x conn (gen-load-ent "currency") sdata/currency)
    ;;(load-seed-x conn (gen-load-ent "country") sdata/country)
    ;;(load-seed-x conn (gen-load-ent "entity-type") sdata/entity-type)
    ;;(load-seed-x conn (gen-load-ent "entity-event-type") sdata/entity-event-type)
    ;;(load-seed-x conn (gen-load-ent "attr-event-type") sdata/attr-event-type)
    ;;(load-seed-x conn (gen-load-ent "composite-key-type") sdata/composite-key-type)
    ;;(load-seed-x conn (gen-load-ent "data-type") sdata/data-type)
    ;;(load-seed-x conn (gen-load-ent "visual-type") sdata/visual-type)
    ;;(load-seed-x conn (gen-load-ent "label-text-type") sdata/label-text-type)
    ;;(load-seed-x conn (gen-load-ent "cardinality-type") sdata/cardinality-type)
    ;;(load-seed-x conn (gen-load-ent "attr-gen-type") sdata/attr-gen-type)
    ;;(load-seed-x conn (gen-load-ent "access-permission") sdata/access-permission)
    ;;(load-seed-x conn (gen-load-ent "release-stage-type") sdata/release-stage-type)
    ;;(load-seed-x conn (gen-load-ent "cf-stack") sdata/cf-stack)
    ;;(load-seed-x conn (gen-load-ent "plet-type") sdata/plet-type) ;
    ;;(load-seed-x conn (gen-load-ent "user-home-cell-type") sdata/user-home-cell-type)
    ;;(load-seed-x conn (gen-load-ent "device-type") sdata/device-type)
    ;;(load-seed-x conn (gen-load-ent "row-col-type") sdata/row-col-type)
    ;;(load-seed-x conn (gen-load-ent "cell-control-type") sdata/cell-control-type)
    ;;(load-seed-x conn (gen-load-ent "plet-mode-type") sdata/plet-mode-type)
    ;;(load-seed-x conn (gen-load-ent "datomic-db") sdata/datomic-db)
    ;;(load-seed-x conn (gen-load-ent "icon-type") sdata/icon-type)
    ;;(load-seed-x conn (gen-load-ent "style-context-type") sdata/style-context-type)
    ;;(load-seed-x conn (gen-load-ent "plet-cell-data-event-type") sdata/plet-cell-data-event-type)
    ;;(load-seed-x conn (gen-load-ent "severity-type") sdata/severity-type)
    ;(load-seed-x conn (gen-load-ent "visual-type") sdata/visual-type)
    )
  )

(defn load-first-level-seed [conn]
  (do
    (load-seed-x conn (gen-seed-children-with-parent "app-deployment-db" "tenant") sdata/app-deployment-db)
    (load-seed-x conn (gen-seed-children-with-parent "application" "tenant") sdata/application)
    (load-seed-x conn (gen-seed-children-with-parent "access-group" "tenant") sdata/access-group)
    (load-seed-x conn (gen-seed-children-with-parent "menu-group" "tenant") sdata/menu-group)
    (load-seed-x conn (gen-seed-children-with-parent "state" "country") sdata/state)
    (load-seed-x conn (gen-seed-children-with-parent "tenant-location" "tenant") sdata/tenant-location)
    (load-seed-x conn (gen-seed-children-with-parent "tenant-address" "tenant-location") sdata/tenant-address)
    (load-seed-x conn (gen-seed-children-with-parent "data-type-comparator" "data-type") sdata/data-type-comparator)

    (load-seed-x conn (gen-seed-child-with-parent "corporate-location" "tenant") sdata/corporate-location)
    (load-seed-x conn (gen-load-label-with-parent "menu-group-label-text" "menu-group") sdata/menu-group-label-text)
    (load-seed-x conn (gen-load-label-lang-with-parent "menu-group-label-text-lang" "menu-group-label-text") sdata/menu-group-label-text-lang)
    )
  )

(comment

  (gen-load-label-with-parent "menu-group-label-text" "menu-group")

  (gen-seed-children-with-parent "app-deployment-db" "tenant")
  )






