(ns nxt.tools.data-model.schema
  (:require [com.rpl.specter :as specter]
            [nxt.tools.util.basic :as basic-util]
            [cuerdas.core :as strfn]
            [datomic.client.api :as d]
            [nxt.tools.data-model.erd :as erd]
            [nxt.tools.data-model.types-data :as tdata]
            ))



;;Date: October 8, 2020
#_(comment

    ;Relationship Rules
    ;------------------
    ;|Parent-Entity|-----<>-----|Child-Entity|

    1. Belongs-to

    If the function call is of the form (relationship-attr {:rel-type            "belongs-to"
                                                            :rel-app             "nxt-tools"
                                                            :rel-entity          "<parent-entity-kname>"
                                                            :attr-k-name         "ref-<parent-entity-kname>" ;Optional
                                                            :pcpk-order          1 ;>= 1 ;required
                                                            :pcaltk-order        0 ; >= 0 ;optional; default is 0
                                                            :comp-attr-required? false ;optional, default will be "false"
                                                            ;if true, it means at least one child
                                                            ;is required at the time of creating the
                                                            ;parent.
                                                            ;;this attrs defined the required? of the
                                                            ;;comp attr created on parent.
                                                            })
    a. --> component attr
    b. <-- ref-attr
    c. <-- pcpk-key and or pcalt-key
    d. --> cardinality will be many
    e. <-- cardinality will be one
    f. --> optional unless indicated
    g. <-- always required
    h. <-- :--/ref-<parent-kname>
    i. --> :--/has-many-<child-kname> + "s"

    2. Extention-of

    If the function call is of the form (relationship-attr {:rel-type            "extension-of"
                                                            :rel-app             "nxt-tools"
                                                            :rel-entity          "<parent-entity-kname>"
                                                            :attr-k-name         "<give-a-name>" ;<parent-kname> is the default
                                                            ;:pcpk-order = 1 ;optional default to 1, always 1
                                                            ;:pcaltk-order = 0 ;optinal defaults to 0, always 0
                                                            :comp-attr-required? false ;optional, default will be "false"
                                                            ;if true, it means the child
                                                            ;is required at the time of creating the
                                                            ;parent.
                                                            ;;this attrs defines the required? of the
                                                            ;;comp attr created on parent.
                                                            })
    a. --> component attr
    b. <-- ref-attr
    c. <-- pcpk-key and NOT-pcalt-key
    d. --> cardinality will be ONE
    e. <-- cardinality will be one
    f. --> optional unless indicated
    g. <-- always required attr
    h. <-- :--/<give-a-name> OR :--/ref-<parent-kname>
    i. --> :--/has-one-<child-kname>

    3. has-one-required

    If the function call is of the form (relationship-attr {:rel-type     "has-one-required"
                                                            :rel-app      "nxt-tools"
                                                            :rel-entity   "<parent-kname>"
                                                            :attr-k-name  "<give-a-name>" ;<parent-kname> is the default
                                                            :pcpk-order   0 ;>= 1 ;optional; default is 0
                                                            :pcaltk-order 0 ; >= 0 ;optional; default is 0
                                                            })

    a. --> NO-component attr
    b. <-- ref-attr
    c. <-- pcpk-key and or pcalt-key

    e. <-- cardinality will be one

    g. <-- always required attr
    h. <-- :--/<give-a-name> OR :--/ref-<parent-kname>

    4. has-one-optional

    If the function call is of the form (relationship-attr {:rel-type    "has-one-optinal"
                                                            :rel-app     "nxt-tools"
                                                            :rel-entity  "<parent-kname>"
                                                            :attr-k-name "<give-a-name>" ;<parent-kname> is the default
                                                            })

    b. <-- ref-attr

    e. <-- cardinality will be one

    g. <-- always NOT-required attr
    h. <-- :--/<give-a-name> OR :--/ref-<parent-kname>

    )
;(def conn111 env/conn111)

;;rel-comp-belongs-to
;;([will/may-have-many & has-one]
;;; This FDT will create a ref/component/non-required attr for the MAJOR entity with a cardinality of MANY
;;; The FK attr will be required.
;;; The FK attr will be PART OF PPK.)

;;rel-comp-extension-of
;;([will/may-have-one & has-one]
;;; This FDT will create a ref/component/non-required attr for the MAJOR entity with a cardinality of ONE
;;; The FK attr will be required.
;;; The FK attr will be the ONLY PPK.)

;;rel-non-comp-belongs-to
;;([will/may-have-many & has-one]
;;; This FDT will create a ref/NON-component/required attr for the MINOR entity with a cardinality of ONE
;;; The FK attr will be required.
;;; The FK attr will be PART OF PPK.)

;;rel-non-comp-extension-of
;;([will/may-have-one & has-one]
;;; This FDT will create a ref/NON-component/required attr for the MINOR entity with a cardinality of ONE
;;; The FK attr will be required.
;;; The FK attr will be the ONLY PPK.)


;Rel-optional-has-one-required (will be a required attr).
;;; This FDT will create a ref attr for the minor entity with a cardinality of ONE.
;;; The FK attr will be required.
;;; The FK attr will NOT be PART OF PPK.)
;;;;
;Rel-optional ([may-have-many & may-have-one] â‡’ has-one-optional (will be an optional attr).
; This FDT will create a ref attr for the minor entity with a cardinality of ONE.
; The FK attr will be optional.
; The FK attr will NOT be a PPK)
;;;;;
;Rel-has-valid-value


;tennat -> application
;has-many-applications
;has-<child-entity-k-name+"s"> with cardinality many and dt ref - > app, entity - component

;;has-one-user-profile
;;has-<child-entity-k-name> with cardinality one and dt ref -. app, entity, component

;;has-a-data-type
;;has-a-<to-entity-entity-k-name> with cardinaluty one and dt ref - app, entity, non-component
;[if a different ap than the current one, then there is no need for the ref attr.]

(defn component-has-many-children [app child-entity-k-name child-attr-k-name]
  (identity {
             :k-name       (str "has-many-" child-entity-k-name "s")
             :description  "has many child entity attrs"
             :data-type    {:type "ref" :entity-related-to {:app app :entity child-entity-k-name}}
             :cardinality  "many"                           ;; will be defaulted based on the type above anyway
             :primary-key  {:ppk? false :order 0}           ;; ppk? will be defaulted based on the type above anyway
             :gen-type     {:type "util" :attr-linked-to {:app app :entity child-entity-k-name :attr-k-name child-attr-k-name}} ;; will be defaulted based on the type above anyway

             :required?    false                            ;; will be defaulted based on the type above anyway
             :list-attr?   false

             :component?   true                             ;; not a component ;; will be defaulted based on the type above anyway
             :history?     true                             ;; will be defaulted based on the type above anyway

             :active?      true                             ;; will be defaulted based on the type above anyway
             :search-attr? false                            ;; will be defaulted based on the type above anyway
             }))
(defn component-has-one-child [app child-entity-k-name child-attr-k-name]
  (identity {
             :k-name       (str "has-one-" child-entity-k-name)
             :description  "has one child entity attr"
             :data-type    {:type "ref" :entity-related-to {:app app :entity child-entity-k-name}}
             :cardinality  "one"                            ;; will be defaulted based on the type above anyway
             :primary-key  {:ppk? false :order 0}           ;; ppk? will be defaulted based on the type above anyway
             :gen-type     {:type "util" :attr-linked-to {:app app :entity child-entity-k-name :attr-k-name child-attr-k-name}} ;; will be defaulted based on the type above anyway

             :required?    false                            ;; will be defaulted based on the type above anyway
             :list-attr?   false

             :component?   true
             :history?     true                             ;; will be defaulted based on the type above anyway

             :active?      true                             ;; will be defaulted based on the type above anyway
             :search-attr? false                            ;; will be defaulted based on the type above anyway
             }))


(defn has-a-required-entity-attr [app this-entity-k-name prompt-entity-k-name rel-attr-k-name]
  (identity {
             :k-name       (str "has-a-" prompt-entity-k-name)
             :description  "has a required fk attr"
             :data-type    {:type "ref" :entity-related-to {:app app :entity prompt-entity-k-name}}
             :cardinality  "one"                            ;; will be defaulted based on the type above anyway
             :primary-key  {:ppk? false :order 0}           ;; ppk? will be defaulted based on the type above anyway
             :gen-type     {:type "util" :attr-linked-to {:app app :entity this-entity-k-name :attr-k-name rel-attr-k-name}} ;; will be defaulted based on the type above anyway

             :required?    true                             ;; will be defaulted based on the type above anyway
             :list-attr?   false

             :component?   false                            ;; not a component ;; will be defaulted based on the type above anyway
             :history?     true                             ;; will be defaulted based on the type above anyway

             :active?      true                             ;; will be defaulted based on the type above anyway
             :search-attr? true                             ;; will be defaulted based on the type above anyway
             }))

(defn has-a-entity-attr [app this-entity-k-name prompt-entity-k-name rel-attr-k-name]
  (identity {
             :k-name       (str "has-a-" prompt-entity-k-name)
             :description  "has a non-required fk attr"
             :data-type    {:type "ref" :entity-related-to {:app app :entity prompt-entity-k-name}}
             :cardinality  "one"                            ;; will be defaulted based on the type above anyway
             :primary-key  {:ppk? false :order 0}           ;; ppk? will be defaulted based on the type above anyway
             :gen-type     {:type "util" :attr-linked-to {:app app :entity this-entity-k-name :attr-k-name rel-attr-k-name}} ;; will be defaulted based on the type above anyway

             :required?    false                            ;; will be defaulted based on the type above anyway
             :list-attr?   false

             :component?   false                            ;; not a component ;; will be defaulted based on the type above anyway
             :history?     true                             ;; will be defaulted based on the type above anyway

             :active?      true                             ;; will be defaulted based on the type above anyway
             :search-attr? false                            ;; will be defaulted based on the type above anyway
             }))



(defn create-tuple-attr-vector [tuple-attr-maps]
  (specter/select [specter/ALL specter/MAP-VALS] (into [] (sort-by first tuple-attr-maps)))
  )

(defn process-primary-key [owner app entity-k-name attr-map]
  (let [{:keys [k-name primary-key]} attr-map]
    (when (true? (:pc-primary? primary-key))
      (let [attr-ident (keyword (str owner "." app "." entity-k-name "/" k-name))
            key-order  (:order primary-key)]
        (identity {key-order attr-ident})
        )
      )
    )
  )
(defn process-alternate-key [owner app entity-k-name attr-map]
  (let [{:keys [k-name alternate-key]} attr-map]
    (when (true? (:pc-alternate? alternate-key))
      (let [attr-ident (keyword (str owner "." app "." entity-k-name "/" k-name))
            key-order  (:order alternate-key)]
        (identity {key-order attr-ident})
        )
      )
    )
  )

(defn create-upsert-attr [entity-k-name]
  (identity {
             :k-name          (str "uuid-" entity-k-name)
             :description     "entity upsert key"
             :data-type       {:type "upsert-key" :entity-related-to {:app nil :entity nil}}
             :cardinality     "one"
             ;;This is automatically generated.
             ;;The data type is upsert key
             ;;tuple attrs are set as given
             ;;the following primary-key and alternate-key are set to false.
             :primary-key     {:pc-primary? false :order 0}
             :alternate-key   {:pc-alternate? false, :order 0},
             :gen-type        {:type "util" :attr-linked-to nil}

             :required?       true
             :attr-order      1

             :component?      false
             :history?        false

             :active?         true
             :search-attr?    false

             :upsert-attr?    true

             :change-allowed? false


             :short-label     "Upsert Key"
             :long-label      "Upsert Key"

             }))
(defn create-pkey-attr [primary-keys-vector]
  (identity {
             ;{ :db/valueType :db.type/tuple :db/tupleAttrs [:semester/year :semester/season] :db/cardinality :db.cardinality/one}
             :k-name        "pkey-primary"
             :description   "entity primary key"
             :data-type     {:type              "primary-key"
                             :entity-related-to {:app nil :entity nil}
                             :tuple-attrs       primary-keys-vector}
             :cardinality   "one"
             ;;This is automatically generated.
             ;;The data type is "primary-key"
             ;;tuple attrs are set as given
             ;;the following primary-key and alternate-key are set to false.
             :primary-key   {:pc-primary? false, :order 0}
             :alternate-key {:pc-alternate? false, :order 0},
             :gen-type      {:type "util" :attr-linked-to nil}

             :required?     true
             :list-attr?    false

             :component?    false
             :history?      false

             :active?       true
             :search-attr?  false


             :short-label   "Primary Key"
             :long-label    "Primary Key"

             }))
(defn create-altkey-attr [alternate-keys-vector]
  (identity {
             ;{ :db/valueType :db.type/tuple :db/tupleAttrs [:semester/year :semester/season] :db/cardinality :db.cardinality/one}
             :k-name        "pkey-alternate"
             :description   "entity alternate key"
             :data-type     {:type              "alternate-key"
                             :entity-related-to {:app nil :entity nil}
                             :tuple-attrs       alternate-keys-vector}
             :cardinality   "one"
             ;;This is automatically generated.
             ;;The data type is "alternate-key"
             ;;tuple attrs are set as given
             ;;the following primary-key and alternate-key are set to false.
             :primary-key   {:pc-primary? false, :order 0}
             :alternate-key {:pc-alternate? false, :order 0},
             :gen-type      {:type "util" :attr-linked-to nil}

             :required?     true
             :list-attr?    false

             :component?    false
             :history?      false

             :active?       true
             :search-attr?  false


             :short-label   "Alternate Key"
             :long-label    "Alternate Key"

             }))


(defn find-primary-keys [owner app k-name attrs]
  (let [primary-keys (into [] (remove nil? (specter/transform [specter/ALL] (fn [attr-map]
                                                                              (process-primary-key owner app k-name attr-map)
                                                                              ) attrs)))]
    primary-keys)
  )
(defn find-alternate-keys [owner app k-name attrs]
  (let [aternate-keys (into [] (remove nil? (specter/transform [specter/ALL] (fn [attr-map]
                                                                               (process-alternate-key owner app k-name attr-map)
                                                                               ) attrs)))]
    aternate-keys)
  )

(defn determine-datomic-valuetype [data-type-map]
  (let [type (:type data-type-map)]
    (second (first (specter/select [specter/ALL #(= (first %) type)] tdata/datatype-valuetype-mapping)))
    )
  )



;;creates the component attr definitions for the Parent Entity
(defn find-parents-of-this-entity [tenant app entity-map]
  (let [
        ;; the purpose is to find out the parents of this entity.
        ;; So this entity is designated as child entity.
        child-entity-kname (:k-name entity-map)]
    ;;;child-entity-kname
    (into [] (remove nil? (specter/transform [specter/ALL] (fn [attr-map]
                                                             (let [data-type-map               (:data-type attr-map)
                                                                   child-entity-rel-attr-kname (:k-name attr-map)]
                                                               (let [data-type (:type data-type-map)]
                                                                 (case data-type
                                                                   ;;this should be added to the parent entity;;
                                                                   ;; creating the attr definition here
                                                                   "rel-optional-comp-belongs-to" (let [app                        (:app (:entity-related-to data-type-map))
                                                                                               parent-entity-kname        (:entity (:entity-related-to data-type-map))
                                                                                               parent-comp-attr-required? (:comp-attr-required? (:entity-related-to data-type-map))]
                                                                                           (identity {;;both parenat and the child
                                                                                                      ;;are assumed to be of the same app.
                                                                                                      :tenant              tenant
                                                                                                      :app                 app
                                                                                                      ;;from entity
                                                                                                      :parent-entity-kname parent-entity-kname
                                                                                                      :attrs               [
                                                                                                                            (identity {
                                                                                                                                       :k-name        (str "has-many-" child-entity-kname "s")
                                                                                                                                       :description   "the relationship attr to the child entity"
                                                                                                                                       :data-type     {:type "ref" :entity-related-to {:app app :entity child-entity-kname}}
                                                                                                                                       :cardinality   "many" ;;zero or many
                                                                                                                                       :primary-key   {:pc-primary? false :order 0} ;;system generated;not a ppk
                                                                                                                                       :alternate-key {:pc-alternate? false :order 0}
                                                                                                                                       :gen-type      {:type "util" :attr-linked-to child-entity-rel-attr-kname} ;;system generated;
                                                                                                                                       ;;keeps the relation to the FK
                                                                                                                                       ;;the system generated REF attr showing this
                                                                                                                                       ;;parent-child relationship
                                                                                                                                       ;;is keyed to this relationship attr
                                                                                                                                       ;; in the erd model
                                                                                                                                       :required?     parent-comp-attr-required?
                                                                                                                                       :list-attr?    false

                                                                                                                                       :component?    true ;;Yes, yes.
                                                                                                                                       :history?      false

                                                                                                                                       :active?       true
                                                                                                                                       :search-attr?  false
                                                                                                                                       })
                                                                                                                            ]
                                                                                                      }
                                                                                                     )
                                                                                           )


                                                                   ;;this should be added to the parent entity
                                                                   "rel-optional-comp-extension-of"
                                                                   (let [app                        (:app (:entity-related-to data-type-map))
                                                                         parent-entity-kname        (:entity (:entity-related-to data-type-map))
                                                                         parent-comp-attr-required? (:comp-attr-required? (:entity-related-to data-type-map))]
                                                                     (identity {;;both parenat and the child
                                                                                ;;are assumed to be of the same app.
                                                                                :tenant              tenant
                                                                                :app                 app
                                                                                ;;from entity
                                                                                :parent-entity-kname parent-entity-kname
                                                                                :attrs               [
                                                                                                      (identity {
                                                                                                                 :k-name        (str "has-one-" child-entity-kname)
                                                                                                                 :description   "the relationship attr to the child entity"
                                                                                                                 :data-type     {:type "ref" :entity-related-to {:app app :entity child-entity-kname}}
                                                                                                                 :cardinality   "one" ;;zero or one
                                                                                                                 :primary-key   {:pc-primary? false :order 0} ;;system generated;not a ppk
                                                                                                                 :alternate-key {:pc-alternate? false :order 0}
                                                                                                                 :gen-type      {:type "util" :attr-linked-to child-entity-rel-attr-kname} ;;system generated;
                                                                                                                 ;;keeps the relation to the FK
                                                                                                                 ;;the system generated REF attr showing this
                                                                                                                 ;;parent-child relationship
                                                                                                                 ;;is keyed to this relationship attr
                                                                                                                 ;; in the erd model
                                                                                                                 :required?     parent-comp-attr-required?
                                                                                                                 :list-attr?    false

                                                                                                                 :component?    true ;;Yes, yes.
                                                                                                                 :history?      false

                                                                                                                 :active?       true
                                                                                                                 :search-attr?  false
                                                                                                                 })
                                                                                                      ]
                                                                                }
                                                                               )
                                                                     )
                                                                   nil
                                                                   ;;this should be added to the current entity
                                                                   ;"rel-optional-has-one-required" (has-a-required-entity-attr app child-entity-kname parent-entity-kname child-entity-rel-attr-kname)
                                                                   ;;this should be added to the current entity
                                                                   ;"rel-optional" (has-a-entity-attr app child-entity-kname parent-entity-kname child-entity-rel-attr-kname)
                                                                   )

                                                                 )
                                                               )
                                                             ) (:attrs entity-map))))

    )

  )


(defn create-non-comp-rel-ref-attrs [tenant app entity-map]
  (let [
        ;;the purpose is to find out the parents of this entity.  So this entity is designated as child entity.
        current-entity-kname (:k-name entity-map)]
    ;;;child-entity-kname
    (into [] (remove nil? (specter/transform [specter/ALL] (fn [attr-map]
                                                             (let [data-type-map               (:data-type attr-map)
                                                                   current-entity-fkattr-kname (:k-name attr-map)
                                                                   stripped-attr-name          (strfn/strip-prefix current-entity-fkattr-kname "uuid-")
                                                                   ]
                                                               (let [data-type (:type data-type-map)]
                                                                 (case data-type
                                                                   ;;this should be added to the parent entity;;
                                                                   ;; creating the attr definition here
                                                                   "rel-optional-has-one-required" (let [app             (:app (:entity-related-to data-type-map))
                                                                                                         to-entity-kname (:entity (:entity-related-to data-type-map))]
                                                                                                     (identity {
                                                                                                                :k-name       (str "has-a-" stripped-attr-name)
                                                                                                                :description  "the ref relationship attr to the to entity"
                                                                                                                :data-type    {:type "ref" :entity-related-to {:app app :entity to-entity-kname}}
                                                                                                                :cardinality  "one" ;;zero or many
                                                                                                                :primary-key  {:ppk? false :order 0} ;;system generated;not a ppk
                                                                                                                :gen-type     {:type "util" :attr-linked-to current-entity-fkattr-kname} ;;system generated;
                                                                                                                :required?    false ;;not required, though the keyed-to attr is required.
                                                                                                                :list-attr?   false

                                                                                                                :component?   false
                                                                                                                :history?     false

                                                                                                                :active?      true
                                                                                                                :search-attr? false
                                                                                                                }))
                                                                   "rel-optional"
                                                                   (let [app             (:app (:entity-related-to data-type-map))
                                                                         to-entity-kname (:entity (:entity-related-to data-type-map))]
                                                                     (identity {
                                                                                :k-name       (str "has-a-" stripped-attr-name)
                                                                                :description  "the ref relationship attr to the to entity"
                                                                                :data-type    {:type "ref" :entity-related-to {:app app :entity to-entity-kname}}
                                                                                :cardinality  "one" ;;zero or many
                                                                                :primary-key  {:ppk? false :order 0} ;;system generated;not a ppk
                                                                                :gen-type     {:type "util" :attr-linked-to current-entity-fkattr-kname} ;;system generated;
                                                                                :required?    false ;;not required, though the keyed-to attr is required.
                                                                                :list-attr?   false

                                                                                :component?   false
                                                                                :history?     false

                                                                                :active?      true
                                                                                :search-attr? false
                                                                                }))
                                                                   nil
                                                                   ;;this should be added to the current entity
                                                                   ;"rel-optional-has-one-required" (has-a-required-entity-attr app child-entity-kname parent-entity-kname child-entity-rel-attr-kname)
                                                                   ;;this should be added to the current entity
                                                                   ;"rel-optional" (has-a-entity-attr app child-entity-kname parent-entity-kname child-entity-rel-attr-kname)
                                                                   )
                                                                 )
                                                               )
                                                             ) (:attrs entity-map))))))

(defn generate-full-attrset-for-each-entity [tenant app entity-map]
  (let [{:keys [k-name name description short-label long-label attrs]
         :or   {name        (->> k-name
                                 (strfn/human)
                                 (cuerdas.core/capital))
                description (->> k-name
                                 (strfn/human)
                                 (strfn/capital))
                short-label (->> k-name
                                 (strfn/human)
                                 (strfn/title))
                long-label  (->> k-name
                                 (strfn/human)
                                 (strfn/title))}} entity-map]
    (let [entity-map (assoc entity-map :name name :description description :short-label short-label :long-label long-label)]
      (let [primary-keys-vector   (find-primary-keys tenant app k-name attrs)
            alternate-keys-vector (find-alternate-keys tenant app k-name attrs)
            upsert-key-attr       (create-upsert-attr k-name)
            ;tenant-attr (create-tenant-attr)  ;; not used anymore
            ;fk-ref-attrs (create-non-comp-rel-ref-attrs tenant app entity-map)
            ]
        (let [pkey-attr (create-pkey-attr primary-keys-vector)]
          ;; if :tuple-attrs [], has values in it, then we need to consider the alternate key as well.  Otherwise just take only the primary key.
          ;;alternate-keys-vector is given to the attr definition funtion.
          (if (> (count alternate-keys-vector) 0)
            (let [altkey-attr (create-altkey-attr alternate-keys-vector)]
              (let [new-full-attrset-vec (into [] (flatten (conj attrs [upsert-key-attr
                                                                        pkey-attr
                                                                        altkey-attr
                                                                        ])))]
                (let [entity-with-new-attrset (assoc entity-map :attrs new-full-attrset-vec)]
                  entity-with-new-attrset)))
            (let [new-full-attrset-vec (into [] (flatten (conj attrs [upsert-key-attr
                                                                      pkey-attr
                                                                      ])))]
              (let [entity-with-new-attrset (assoc entity-map :attrs new-full-attrset-vec)]
                entity-with-new-attrset))
            )
          ))
      )
    )
  )
;;Generate the full attr sets for each entities.
;;The newly added attrs include uuid-<tntity> pkey-000,
;;This full definitions can be used for creating the schema & the metadata.
(defn generate-full-attr-set-for-entities [toos-erd-map]
  (let [{:keys [tenant app entities]} toos-erd-map]
    (let [entity-list-with-comp-ref-attrs (let [ent-list-with-empty-vecs (specter/transform [specter/ALL] (fn [entity-map]
                                                                                                            (find-parents-of-this-entity tenant app entity-map)
                                                                                                            ) entities)]
                                            (->> ent-list-with-empty-vecs
                                                 (filter not-empty)
                                                 (flatten)
                                                 (into [])
                                                 )
                                            )]
      ;;now we have two sets of entities.  one with the given attrs, and the new one with
      ;;the component relationship attrs.
      ;;now we have to merge, basically add the component ref attrs
      ;;to the attrs list of the original entity list.  In that way,
      ;;we can give it for the next processing
      ;;i.e, shcema generation and metadata creation.
      ;;entity-list-with-comp-ref-attrs &
      ;;entities
      (let [new-entity-map-with-ref-component-attrs (specter/transform [specter/ALL] (fn [entity-map]
                                                                                       (let [ent-kname (:k-name entity-map)
                                                                                             org-attrs (:attrs entity-map)]
                                                                                         (let [new-entity-map (->> (specter/select [specter/ALL #(= (:parent-entity-kname %) ent-kname) :attrs] entity-list-with-comp-ref-attrs)
                                                                                                                   (flatten)
                                                                                                                   (into [])
                                                                                                                   ;;this may contain one to many attrs.
                                                                                                                   ;;now we need to add this new set of attrs to the original attr set.
                                                                                                                   (into org-attrs)
                                                                                                                   ;;put it back to the entity-map
                                                                                                                   (assoc entity-map :attrs)
                                                                                                                   )]
                                                                                           new-entity-map
                                                                                           )
                                                                                         )
                                                                                       ) entities)]
        ;;let's add the uuid attr, pkey and the tenant attrs to this list.
        (specter/transform [specter/ALL] (fn [entity-map3]
                                           (generate-full-attrset-for-each-entity tenant app entity-map3)
                                           )
                           new-entity-map-with-ref-component-attrs)
        )
      )
    )
  )

(defn generate-pk-key-ident [attr-vec attr-ident]
  (let [attr-ns-str (namespace attr-ident)]
    ;;the case where only one attr is ppk
    (if (= (count attr-vec) 1)
      (let [key-name (->> attr-vec
                          (first)
                          (name)
                          )]
        ;;creating an ident with the name stringed with +
        (let [new-pkey-ident           (->> (str key-name "+" key-name)
                                            (str attr-ns-str "/")
                                            (keyword)
                                            )
              ;;creating a new vec with two of the same idents.
              new-pkay-tuple-attrs-vec [(first attr-vec) (first attr-vec)]]
          ;;return a map that contains the ident and the tuple-attrs
          (identity {:new-pkey-ident       new-pkey-ident
                     :new-pkey-tuple-attrs new-pkay-tuple-attrs-vec}
                    )
          )
        )
      (let [new-pkey-ident (->> (specter/transform [specter/ALL] (fn [kw] (name kw)) attr-vec)
                                (clojure.string/join "+")
                                (str attr-ns-str "/")
                                (keyword)
                                )]
        (identity {:new-pkey-ident       new-pkey-ident
                   ;;the same given tuple-attr-vector
                   :new-pkey-tuple-attrs attr-vec}
                  )
        )
      )
    )
  )


#_#:db{:ident         :nxt.itools.label-position/pkey-000,
       :valueType     :db.type/tuple,
       :db/tupleAttrs [:reg/course :reg/semester :reg/student]
       :cardinality   :db.cardinality/one,
       :doc           "entity primary key",
       :isComponent   false,
       :noHistory     false}

(defn create-ident-from-attr-map [owner app entity-k-name attr-map]
  (let [{:keys [k-name active? description search-attr? long-label short-label required? data-type history?
                component? cardinality]
         :or   {
                ;:primary-key  {:ppk? true :order 1}
                active?      true
                short-label  "Short label"
                long-label   "Long label"
                description  "attr descr"
                search-attr? false
                required?    false
                component?   false
                cardinality  "one"}} attr-map]
    (let [card       (tdata/determine-datomic-cardinality cardinality)
          valuetype  (determine-datomic-valuetype data-type)
          attr-ident (keyword (str owner "." app "." entity-k-name "/" k-name))]
      (case (:type data-type)
        "primary-key" (let [tuple-attr-vec (:tuple-attrs data-type)]
                        (let [db-tuple-attrs (->> tuple-attr-vec
                                                  (into (sorted-map))
                                                  (vals)
                                                  (into [])
                                                  )]
                          ;;the attr-ident is in the form p-key000.  But Datomic needs to create the ident with
                          ;;concatenation using + symbol.
                          ;;For example, :nxt.itools.application/uuid-tenant+k-name as the pkey.
                          ;;in some cases, for example, Tenant entity, we have only one ident as part of the
                          ;;composite key.
                          ;;In such cases, we use :nxt.itools.tenant/k-name+k-name as the pkey
                          ;;the tupleAttrs willbe [.../k-name, ..../k-name]
                          (let [pk-key-attr-ident-map (generate-pk-key-ident db-tuple-attrs attr-ident)]
                            (identity {:db/ident       (:new-pkey-ident pk-key-attr-ident-map)
                                       :db/valueType   valuetype
                                       :db/tupleAttrs  (:new-pkey-tuple-attrs pk-key-attr-ident-map)
                                       :db/unique      :db.unique/identity
                                       :db/cardinality card
                                       :db/doc         description
                                       :db/isComponent component?
                                       :db/noHistory   history?
                                       })
                            )
                          )
                        )
        "alternate-key" (let [tuple-attr-vec (:tuple-attrs data-type)]
                          (let [db-tuple-attrs (->> tuple-attr-vec
                                                    (into (sorted-map))
                                                    (vals)
                                                    (into [])
                                                    )]
                            ;;the attr-ident is in the form p-key000.  But Datomic needs to create the ident with
                            ;;concatenation using + symbol.
                            ;;For example, :nxt.itools.application/uuid-tenant+k-name as the pkey.
                            ;;in some cases, for example, Tenant entity, we have only one ident as part of the
                            ;;composite key.
                            ;;In such cases, we use :nxt.itools.tenant/k-name+k-name as the pkey
                            ;;the tupleAttrs willbe [.../k-name, ..../k-name]
                            (let [pk-key-attr-ident-map (generate-pk-key-ident db-tuple-attrs attr-ident)]
                              (identity {:db/ident       (:new-pkey-ident pk-key-attr-ident-map)
                                         :db/valueType   valuetype
                                         :db/tupleAttrs  (:new-pkey-tuple-attrs pk-key-attr-ident-map)
                                         :db/unique      :db.unique/identity
                                         :db/cardinality card
                                         :db/doc         description
                                         :db/isComponent component?
                                         :db/noHistory   history?
                                         })
                              )
                            )
                          )
        "upsert-key" (identity {:db/ident       attr-ident
                                :db/valueType   valuetype
                                :db/cardinality card
                                :db/unique      :db.unique/identity
                                :db/doc         description
                                :db/isComponent component?
                                :db/noHistory   history?
                                }
                               )
        (identity {:db/ident       attr-ident
                   :db/valueType   valuetype
                   :db/cardinality card
                   :db/doc         description
                   :db/isComponent component?
                   :db/noHistory   history?
                   }
                  )
        )
      )
    )
  )


(defn create-schema-for-each-entity [owner app entity-map]
  (let [{:keys [k-name name description short-label long-label attrs]
         :or   {
                description "entity descr"
                short-label "short label"
                long-label  "long label"}} entity-map]
    (let [idents-for-an-entity (specter/transform [specter/ALL] (fn [attr-map]
                                                                  (create-ident-from-attr-map owner app k-name attr-map)
                                                                  ) attrs)]
      idents-for-an-entity
      )))


;;=================================================================================================================
#_(ns nxt.tools.data-model.basic-metadata
    (:require [com.rpl.specter :refer :all :as specter]
              [try-let :refer [try-let]]
              [nxt.tools.util.basic :as basic-util]
              [nxt.tools.data-model.erd :as erd]
              [nxt.tools.data-model.schema :as schema]


              [cuerdas.core :as strfn]
              [nxt.service :as service]
              [datomic.client.api :as d]

              ))

(defn find-uuid-tenant [conn tenant-k-name]
  (ffirst (d/q '[:find ?uuid-tenant
                 :in $ ?tenant-k-name
                 :where
                 [?e :nxt.itools.tenant/k-name ?tenant-k-name]
                 [?e :nxt.itools.tenant/uuid-tenant ?uuid-tenant]
                 ]
               (d/db conn) tenant-k-name))
  )

(defn find-uuid-tenant-owner [conn tenant-owner-k-name]
  (ffirst (d/q '[:find ?uuid-tenant
                 :in $ ?tenant-k-name
                 :where
                 [?e :nxt.itools.tenant/k-name ?tenant-k-name]
                 [?e :nxt.itools.tenant/uuid-tenant ?uuid-tenant]
                 ]
               (d/db conn) tenant-owner-k-name))
  )

(defn find-uuid-application [conn tenant-k-name app-k-name]
  (let [uuid-tenant (find-uuid-tenant conn tenant-k-name)]
    (ffirst (d/q '[:find ?uuid-application
                   :in $ ?uuid-tenant ?app-k-name
                   :where
                   [?t :nxt.itools.tenant/uuid-tenant ?uuid-tenant]
                   [?a :nxt.itools.application/ref-tenant ?t]
                   [?a :nxt.itools.application/k-name ?app-k-name]
                   [?a :nxt.itools.application/uuid-application ?uuid-application]
                   ]
                 (d/db conn) uuid-tenant app-k-name))
    )
  )
(defn find-uuid-entity [conn uuid-application ent-kname]
  (ffirst (d/q '[:find ?uuid-entity
                 :in $ ?uuid-application ?entity-kname
                 :where
                 [?a :nxt.itools.application/uuid-application ?uuid-application]
                 [?e :nxt.itools.entity/ref-application ?a]
                 [?e :nxt.itools.entity/k-name ?entity-kname]
                 [?e :nxt.itools.entity/uuid-entity ?uuid-entity]
                 ]
               (d/db conn) uuid-application ent-kname)))

(defn find-uuid-entity-type [conn entity-type-k-name]
  (ffirst (d/q '[:find ?uuid-entity-type
                 :in $ ?entity-type-k-name
                 :where
                 [?e :nxt.itools.entity-type/k-name ?entity-type-k-name]
                 [?e :nxt.itools.entity-type/uuid-entity-type ?uuid-entity-type]
                 ]
               (d/db conn) entity-type-k-name))
  )

(defn find-uuid-label-text-type [conn label-text-type-k-name]
  (ffirst (d/q '[:find ?uuid-label-text-type
                 :in $ ?label-text-type-k-name
                 :where
                 [?e :nxt.itools.label-text-type/k-name ?label-text-type-k-name]
                 [?e :nxt.itools.label-text-type/uuid-label-text-type ?uuid-label-text-type]
                 ]
               (d/db conn) label-text-type-k-name))
  )
;;Ta
(defn find-uuid-language [conn code]
  (ffirst (d/q '[:find ?uuid-language
                 :in $ ?code
                 :where
                 [?e :nxt.itools.language/code ?code]
                 [?e :nxt.itools.language/uuid-language ?uuid-language]
                 ]
               (d/db conn) code)))
(defn find-uuid-data-type [conn friendly-data-type]
  (ffirst (d/q '[:find ?uuid-data-type
                 :in $ ?friendly-data-type
                 :where
                 [?e :nxt.itools.data-type/k-name ?friendly-data-type]
                 [?e :nxt.itools.data-type/uuid-data-type ?uuid-data-type]
                 ]
               (d/db conn) friendly-data-type))
  )
;;(defn find-uuid-cardinality-type [friendly-cardinality])
(defn find-uuid-cardinality-type [conn friendly-cardinality]
  (ffirst (d/q '[:find ?uuid-cardinality-type
                 :in $ ?friendly-cardinality
                 :where
                 [?e :nxt.itools.cardinality-type/k-name ?friendly-cardinality]
                 [?e :nxt.itools.cardinality-type/uuid-cardinality-type ?uuid-cardinality-type]
                 ]
               (d/db conn) friendly-cardinality))
  )
;;(defn find-uuid-attr-gen-type [friendly-gen-type])
(defn find-uuid-attr-gen-type [conn friendly-gen-type]
  (ffirst (d/q '[:find ?uuid-attr-gen-type
                 :in $ ?friendly-gen-type
                 :where
                 [?e :nxt.itools.attr-gen-type/k-name ?friendly-gen-type]
                 [?e :nxt.itools.attr-gen-type/uuid-attr-gen-type ?uuid-attr-gen-type]
                 ]
               (d/db conn) friendly-gen-type))
  )

(defn find-uuid-composite-key-type [conn comp-key-k-name]
  (ffirst (d/q '[:find ?uuid-composite-key-type
                 :in $ ?comp-key-k-name
                 :where
                 [?e :nxt.itools.composite-key-type/k-name ?comp-key-k-name]
                 [?e :nxt.itools.composite-key-type/uuid-composite-key-type ?uuid-composite-key-type]
                 ]
               (d/db conn) comp-key-k-name))
  )

(comment

  (erd/tools-erd)

  (count (:entities (erd/tools-erd)))

  (take 74 (:entities (erd/tools-erd)))

  (generate-full-attr-set-for-entities (:entities (erd/tools-erd)))
  )

;;=================================================================================================================

;;   (process-tools-erd {:req-output :schema :erd-map (erd/tools-erd)})

(defmulti process-tools-erd :req-output)

;;Gets the Vec with all entities with full attrs
;;such as upsert attr, alternate-pkey, primary-pkey
;;component-attr for the Parent
(defmethod process-tools-erd :erd
  [input-map]
  (let [erd (:erd-map input-map)]
    (let [{:keys [tenant app]} erd]
      ;;First let's get the full set of entities and their attrs
      (let [entities-with-full-attr-sets (generate-full-attr-set-for-entities erd)]
        entities-with-full-attr-sets
        )
      )
    )
  )

;;the Required Out is SCHEMA
(defmethod process-tools-erd :schema
  [input-map]
  (let [erd (:erd-map input-map)]                           ;erd
    (let [{:keys [tenant app]} erd]
      ;;First let's get the full set of entities and their attrs
      (let [entities-with-full-attr-sets (generate-full-attr-set-for-entities erd)]
        (let [vec-of-ident-vecs-of-all-entities (specter/transform [specter/ALL] (fn [entity-map]
                                                                                   (create-schema-for-each-entity tenant app entity-map)
                                                                                   )
                                                                   entities-with-full-attr-sets
                                                                   )]
          (->> vec-of-ident-vecs-of-all-entities
               (flatten)
               (into [])
               )
          )
        )
      )
    )
  )

#_(specter/select [specter/ALL #(or (= (:k-name %) "tenant") (= (:k-name %) "application"))]
                  (process-tools-erd {:req-output :meta :erd-map (erd/tools-erd)}))

;;the Required Out is Metadata
;;see the basic-metadata namespace


#_(process-tools-erd {:req-output         :meta :erd-map (erd/tools-erd)
                      :conn               conn111
                      :language-code      "en"
                      :tenant-k-name      "nxt"
                      :application-k-name "nxt-tools"})
;;Tools metadata.
;;This function can run only after running the schema install & the basic seed data.
;;This will create the Entity/attrs for all the entities defined in the ERD
;;that can be transacted into the schema idents.

;;   ["pagelet" "pagelet-label" "entity-detail-plet-tab" "entity-detail-plet-tab-label" "entity-list-filter"]

;;   "entity-list-filter"

;;   (vec (nth (partition 5 (process-tools-erd {:req-output :erd :erd-map (erd/tools-erd)}) ) 10))

;;(specter/select [ALL :k-name] (vec (nth (partition 5 (process-tools-erd {:req-output :erd :erd-map (erd/tools-erd)}) ) 0)))
;=> ["tenant" "user" "language" "entity-type" "composite-key-type"]

(defmethod process-tools-erd :meta
  [input-map]
  (let [{:keys [conn language-code tenant-k-name application-k-name]} input-map
        entities-with-full-attrs (specter/select [specter/ALL
                                                  #_#(or
                                                       (= (:k-name %) "user")
                                                       ;(= (:k-name %) "application")
                                                       ;(= (:k-name %) "entity-list-filter")
                                                       )
                                                  ]
                                                 ;(vec (nth (partition 5 (process-tools-erd {:req-output :erd :erd-map (erd/tools-erd)}) ) 0))
                                                 (process-tools-erd {:req-output :erd :erd-map (erd/tools-erd)})
                                                 )
        ]                                                   ;entities-with-full-attrs
    (let [uuid-language           (find-uuid-language conn language-code)
          uuid-tenant             (find-uuid-tenant conn tenant-k-name)
          uuid-tenant-owner       (find-uuid-tenant-owner conn tenant-k-name) ;;owner and the tenant are the same now.
          uuid-application        (find-uuid-application conn tenant-k-name application-k-name)
          uuid-primary-comp-key   (find-uuid-composite-key-type conn "primary")
          uuid-alternate-comp-key (find-uuid-composite-key-type conn "alternate")
          ]                                                 ;[uuid-language uuid-tenant uuid-tenant-owner uuid-application uuid-primary-comp-key uuid-alternate-comp-key]
      (let [entity-attrs (specter/transform [specter/ALL] (fn [entity-map]
                                                            (let [
                                                                  entity-primary-keys   (atom [])
                                                                  entity-alternate-keys (atom [])
                                                                  new-entity-map        (let [{entity-k-name      :k-name
                                                                                               entity-name        :name
                                                                                               entity-type        :entity-type ;;will be dfaulted below
                                                                                               label-text-type    :label-text-type
                                                                                               entity-description :description
                                                                                               entity-short-label :short-label
                                                                                               entity-long-label  :long-label
                                                                                               :or                {entity-description "entity description"
                                                                                                                   entity-type        "entity"
                                                                                                                   label-text-type    "label"
                                                                                                                   entity-short-label "Short Label"
                                                                                                                   entity-long-label  "Long Label"}
                                                                                               } entity-map

                                                                                              entity-uuid                 (basic-util/squuid)


                                                                                              app-db-id                   (str application-k-name "-db-id")
                                                                                              entity-db-id                (str application-k-name "-" entity-k-name)

                                                                                              entity-label-uuid           (basic-util/squuid)
                                                                                              entity-label-text-lang-uuid (basic-util/squuid)
                                                                                              entity-label-db-id          (str application-k-name "-" entity-k-name "-label")
                                                                                              entity-label-lang-db-id     (str application-k-name "-" entity-k-name "-label-lang")
                                                                                              uuid-entity-type            (find-uuid-entity-type conn entity-type)
                                                                                              uuid-label-text-type        (find-uuid-label-text-type conn label-text-type)
                                                                                              ]
                                                                                          (let [app-map
                                                                                                               {:db/id                                    app-db-id
                                                                                                                :nxt.itools.application/uuid-application uuid-application
                                                                                                                :nxt.itools.application/has-many-entitys [entity-db-id]
                                                                                                                }

                                                                                                ent-map
                                                                                                               {:db/id                                          entity-db-id
                                                                                                                :nxt.itools.entity/uuid-entity                 entity-uuid
                                                                                                                :nxt.itools.entity/ref-application             app-db-id ;[:nxt.itools.application/uuid-application uuid-application]
                                                                                                                :nxt.itools.entity/k-name                      entity-k-name
                                                                                                                ;:nxt.itools.entity/name                   entity-name
                                                                                                                :nxt.itools.entity/description                 entity-description
                                                                                                                :nxt.itools.entity/ref-entity-type             [:nxt.itools.entity-type/uuid-entity-type uuid-entity-type]
                                                                                                                :nxt.itools.entity/ref-tenant-owner            [:nxt.itools.tenant/uuid-tenant uuid-tenant-owner]
                                                                                                                :nxt.itools.entity/internal?                   true,
                                                                                                                :nxt.itools.entity/added-by-initial-load?      true,

                                                                                                                :nxt.itools.entity/has-many-entity-label-texts [entity-label-db-id]
                                                                                                                }
                                                                                                ent-label-map
                                                                                                               {:db/id                                                          entity-label-db-id
                                                                                                                :nxt.itools.entity-label-text/uuid-entity-label-text           entity-label-uuid
                                                                                                                :nxt.itools.entity-label-text/ref-entity                       entity-db-id
                                                                                                                :nxt.itools.entity-label-text/k-name                           entity-label-db-id
                                                                                                                :nxt.itools.entity-label-text/ref-label-text-type              [:nxt.itools.label-text-type/uuid-label-text-type uuid-label-text-type]
                                                                                                                :nxt.itools.entity-label-text/ref-tenant-owner                 [:nxt.itools.tenant/uuid-tenant uuid-tenant-owner]
                                                                                                                :nxt.itools.entity-label-text/dflt-label?                      true
                                                                                                                :nxt.itools.entity-label-text/has-many-entity-label-text-langs [entity-label-lang-db-id]
                                                                                                                }
                                                                                                ent-label-map-lang
                                                                                                               {:db/id                                                          entity-label-lang-db-id
                                                                                                                :nxt.itools.entity-label-text-lang/uuid-entity-label-text-lang entity-label-text-lang-uuid
                                                                                                                :nxt.itools.entity-label-text-lang/ref-entity-label-text       entity-label-db-id
                                                                                                                :nxt.itools.entity-label-text-lang/ref-language                [:nxt.itools.language/uuid-language uuid-language]
                                                                                                                :nxt.itools.entity-label-text-lang/short-text                  entity-short-label
                                                                                                                :nxt.itools.entity-label-text-lang/long-text                   entity-long-label
                                                                                                                }

                                                                                                attrs          (:attrs entity-map)
                                                                                                new-attr-order (atom 1)] ;ent-map ;;The upsert-key will be given an attr-order of 1 by default.
                                                                                            ;attrs

                                                                                            (let [attr-maps (specter/transform [specter/ALL] (fn [attr-map]
                                                                                                                                               ;attr-map
                                                                                                                                               (let [{:keys [active?
                                                                                                                                                             description
                                                                                                                                                             search-attr?

                                                                                                                                                             long-label

                                                                                                                                                             dependent-on-attr-kname

                                                                                                                                                             required?
                                                                                                                                                             attr-order

                                                                                                                                                             data-type ;map
                                                                                                                                                             k-name

                                                                                                                                                             alternate-key
                                                                                                                                                             history?
                                                                                                                                                             gen-type ;map
                                                                                                                                                             short-label
                                                                                                                                                             component?
                                                                                                                                                             cardinality
                                                                                                                                                             primary-key

                                                                                                                                                             upsert-attr?
                                                                                                                                                             change-allowed?
                                                                                                                                                             datomic-attr?
                                                                                                                                                             dd-list-attr?
                                                                                                                                                             list-attr?
                                                                                                                                                             related-attr?
                                                                                                                                                             dflt-label?
                                                                                                                                                             label-text-type
                                                                                                                                                             default-value
                                                                                                                                                             ]
                                                                                                                                                      :or   {upsert-attr?            false
                                                                                                                                                             change-allowed?         false
                                                                                                                                                             datomic-attr?           true ; all are datomic attrs here
                                                                                                                                                             dd-list-attr?           false
                                                                                                                                                             list-attr?              true
                                                                                                                                                             related-attr?           false

                                                                                                                                                             dependent-on-attr-kname nil

                                                                                                                                                             search-attr?            false
                                                                                                                                                             active?                 true
                                                                                                                                                             description             "attr description"
                                                                                                                                                             required?               false
                                                                                                                                                             attr-order              0
                                                                                                                                                             component?              false
                                                                                                                                                             cardinality             "one"
                                                                                                                                                             primary-key             {:pc-primary? false, :order 0}
                                                                                                                                                             alternate-key           {:pc-alternate? false, :order 0}
                                                                                                                                                             long-label              "Attr Long Label"
                                                                                                                                                             short-label             "Attr Short Label"

                                                                                                                                                             label-text-type         "label"
                                                                                                                                                             dflt-label?             true
                                                                                                                                                             default-value           nil
                                                                                                                                                             }} attr-map
                                                                                                                                                     short-label           (if (= k-name "k-name")
                                                                                                                                                                             (let [s-label (if (= entity-short-label "Tenant")
                                                                                                                                                                                             ;;hard coded.  Needs to be fixed later.
                                                                                                                                                                                             (str "Owner" " " short-label)
                                                                                                                                                                                             (str entity-short-label " " short-label))
                                                                                                                                                                                   ]
                                                                                                                                                                               s-label
                                                                                                                                                                               )
                                                                                                                                                                             (let [s-label short-label]
                                                                                                                                                                               s-label
                                                                                                                                                                               )
                                                                                                                                                                             )

                                                                                                                                                     owner-k-name          tenant-k-name
                                                                                                                                                     attr-name-kw          (keyword (str owner-k-name "." application-k-name "." entity-k-name "/" k-name))
                                                                                                                                                     this-attr-order       (if (= attr-order 0) (swap! new-attr-order inc) attr-order) ;;thie will set the Upsert Key at the beginning later, when we
                                                                                                                                                     ;;use attr-order as a default for column order.
                                                                                                                                                     uuid-data-type        (find-uuid-data-type conn (:type data-type))
                                                                                                                                                     uuid-cardinality-type (find-uuid-cardinality-type conn cardinality)
                                                                                                                                                     uuid-attr-gen-type    (find-uuid-attr-gen-type conn (:type gen-type))
                                                                                                                                                     attr-uuid             (basic-util/squuid)
                                                                                                                                                     attr-db-id            (str application-k-name "-" entity-k-name "-" k-name)
                                                                                                                                                     attr-label-db-id      (str application-k-name "-" entity-k-name "-" k-name "-label")
                                                                                                                                                     attr-label-lang-db-id (str application-k-name "-" entity-k-name "-" k-name "-label-lang")
                                                                                                                                                     attr-label-uuid       (basic-util/squuid)
                                                                                                                                                     attr-label-lang-uuid  (basic-util/squuid)

                                                                                                                                                     refd-entity-db-id     (when (some #(= (:type data-type) %) ["rel-optional-has-one-optional"
                                                                                                                                                                                                                 "rel-optional-has-one-required"
                                                                                                                                                                                                                 "rel-optional-comp-extension-of"
                                                                                                                                                                                                                 "rel-optional-comp-belongs-to"]
                                                                                                                                                                                       )
                                                                                                                                                                             (let [rel-ent          (:entity-related-to data-type)
                                                                                                                                                                                   tenant-kname     "nxt" ;;only nxt is supported.
                                                                                                                                                                                   app-kname        (:app rel-ent)
                                                                                                                                                                                   ;uuid-ref-app (find-uuid-application conn tenant-kname app-kname)
                                                                                                                                                                                   ; only "nxt-tools" app is supported.
                                                                                                                                                                                   ;; we will use the same algorith here to create the db-id, as used for the refed entity.
                                                                                                                                                                                   ref-ent-kname    (:entity rel-ent)
                                                                                                                                                                                   ;;this will not work as there are no entities added yet. So we have to use db-id string for ref.
                                                                                                                                                                                   ;;uuid-ref-ent (find-uuid-entity conn uuid-ref-app ent-kname)
                                                                                                                                                                                   ref-entity-db-id (str application-k-name "-" ref-ent-kname)
                                                                                                                                                                                   ]
                                                                                                                                                                               ;uuid-ref-ent
                                                                                                                                                                               ;[ref-ent-kname ref-entity-db-id]
                                                                                                                                                                               ref-entity-db-id
                                                                                                                                                                               )
                                                                                                                                                                             )
                                                                                                                                                     ]
                                                                                                                                                 (let [new-attr-map-with-nil {:db/id                                      attr-db-id
                                                                                                                                                                              :nxt.itools.attr/uuid-attr                 attr-uuid
                                                                                                                                                                              :nxt.itools.attr/ref-entity                entity-db-id ;[:nxt.itools.entity/uuid-entity entity-uuid]
                                                                                                                                                                              :nxt.itools.attr/k-name                    k-name
                                                                                                                                                                              :nxt.itools.attr/ref-tenant-owner          [:nxt.itools.tenant/uuid-tenant uuid-tenant-owner]
                                                                                                                                                                              :nxt.itools.attr/description               description

                                                                                                                                                                              :nxt.itools.attr/ref-data-type             [:nxt.itools.data-type/uuid-data-type uuid-data-type]
                                                                                                                                                                              :nxt.itools.attr/ref-cardinality-type      [:nxt.itools.cardinality-type/uuid-cardinality-type uuid-cardinality-type]
                                                                                                                                                                              :nxt.itools.attr/ref-attr-gen-type         [:nxt.itools.attr-gen-type/uuid-attr-gen-type uuid-attr-gen-type]


                                                                                                                                                                              :nxt.itools.attr/component?                component?
                                                                                                                                                                              :nxt.itools.attr/history?                  history?
                                                                                                                                                                              :nxt.itools.attr/required?                 required?
                                                                                                                                                                              :nxt.itools.attr/attr-order                this-attr-order ;(bigint this-attr-order)
                                                                                                                                                                              :nxt.itools.attr/active?                   active?
                                                                                                                                                                              :nxt.itools.attr/search-attr?              search-attr?
                                                                                                                                                                              :nxt.itools.attr/dd-list-attr?             dd-list-attr?
                                                                                                                                                                              :nxt.itools.attr/list-attr?                list-attr?
                                                                                                                                                                              :nxt.itools.attr/related-attr?             related-attr?
                                                                                                                                                                              :nxt.itools.attr/upsert-attr?              upsert-attr?
                                                                                                                                                                              :nxt.itools.attr/change-allowed?           change-allowed?
                                                                                                                                                                              :nxt.itools.attr/datomic-attr?             datomic-attr?
                                                                                                                                                                              :nxt.itools.attr/attr-name-kw              attr-name-kw

                                                                                                                                                                              :nxt.itools.attr/has-many-attr-label-texts [attr-label-db-id]

                                                                                                                                                                              :nxt.itools.attr/default-value             (str default-value)
                                                                                                                                                                              }

                                                                                                                                                       new-attr-map          (basic-util/remove-nils new-attr-map-with-nil)

                                                                                                                                                       ]
                                                                                                                                                   (let [mofified-attr-mapx            (if (nil? refd-entity-db-id)
                                                                                                                                                                                         new-attr-map
                                                                                                                                                                                         (assoc new-attr-map :nxt.itools.attr/ref-entity-ref-to refd-entity-db-id)
                                                                                                                                                                                         )
                                                                                                                                                         mofified-attr-map             (if (nil? dependent-on-attr-kname)
                                                                                                                                                                                         mofified-attr-mapx
                                                                                                                                                                                         (let [independent-attr-ref (str application-k-name "-" entity-k-name "-" dependent-on-attr-kname)
                                                                                                                                                                                               ;;The db-id string of the independent attr.
                                                                                                                                                                                               ;;using the same Convention here
                                                                                                                                                                                               ]
                                                                                                                                                                                           (assoc mofified-attr-mapx :nxt.itools.attr/ref-dependent-on-attr-kname independent-attr-ref)
                                                                                                                                                                                           )
                                                                                                                                                                                         )
                                                                                                                                                         mofified-attr-map-with-entity [
                                                                                                                                                                                        {:db/id                             entity-db-id
                                                                                                                                                                                         :nxt.itools.entity/uuid-entity    entity-uuid
                                                                                                                                                                                         :nxt.itools.entity/has-many-attrs [attr-db-id]
                                                                                                                                                                                         }
                                                                                                                                                                                        mofified-attr-map
                                                                                                                                                                                        ]
                                                                                                                                                         attr-label-map
                                                                                                                                                                                       {:db/id                                                      attr-label-db-id
                                                                                                                                                                                        :nxt.itools.attr-label-text/uuid-attr-label-text           attr-label-uuid
                                                                                                                                                                                        :nxt.itools.attr-label-text/ref-attr                       attr-db-id
                                                                                                                                                                                        :nxt.itools.attr-label-text/k-name                         attr-label-db-id
                                                                                                                                                                                        :nxt.itools.attr-label-text/ref-label-text-type            [:nxt.itools.label-text-type/uuid-label-text-type uuid-label-text-type]
                                                                                                                                                                                        :nxt.itools.attr-label-text/ref-tenant-owner               [:nxt.itools.tenant/uuid-tenant uuid-tenant-owner]
                                                                                                                                                                                        :nxt.itools.attr-label-text/dflt-label?                    dflt-label?

                                                                                                                                                                                        :nxt.itools.attr-label-text/has-many-attr-label-text-langs [attr-label-lang-db-id]
                                                                                                                                                                                        }
                                                                                                                                                         attr-label-map-lang
                                                                                                                                                                                       {:db/id                                                      attr-label-lang-db-id
                                                                                                                                                                                        :nxt.itools.attr-label-text-lang/uuid-attr-label-text-lang attr-label-lang-uuid
                                                                                                                                                                                        :nxt.itools.attr-label-text-lang/ref-attr-label-text       attr-label-db-id
                                                                                                                                                                                        :nxt.itools.attr-label-text-lang/ref-language              [:nxt.itools.language/uuid-language uuid-language]
                                                                                                                                                                                        :nxt.itools.attr-label-text-lang/short-text                short-label
                                                                                                                                                                                        :nxt.itools.attr-label-text-lang/long-text                 long-label
                                                                                                                                                                                        }
                                                                                                                                                         entity-composite-key-db-id    (str application-k-name "-" entity-k-name "-" k-name "-pkey")
                                                                                                                                                         entity-composite-key          (basic-util/squuid)
                                                                                                                                                         entity-primary-key-map        (when (true? (:pc-primary? primary-key))
                                                                                                                                                                                         [
                                                                                                                                                                                          {:db/id                                             entity-db-id
                                                                                                                                                                                           :nxt.itools.entity/uuid-entity                    entity-uuid
                                                                                                                                                                                           :nxt.itools.entity/has-many-entity-composite-keys [entity-composite-key-db-id]
                                                                                                                                                                                           }
                                                                                                                                                                                          {:db/id                                                      entity-composite-key-db-id
                                                                                                                                                                                           :nxt.itools.entity-composite-key/uuid-entity-composite-key entity-composite-key
                                                                                                                                                                                           :nxt.itools.entity-composite-key/ref-entity                entity-db-id
                                                                                                                                                                                           :nxt.itools.entity-composite-key/ref-composite-key-type    [:nxt.itools.composite-key-type/uuid-composite-key-type uuid-primary-comp-key]
                                                                                                                                                                                           :nxt.itools.entity-composite-key/ref-attr                  attr-db-id
                                                                                                                                                                                           :nxt.itools.entity-composite-key/key-order                 (:order primary-key) ;(bigint (:order primary-key))
                                                                                                                                                                                           }
                                                                                                                                                                                          ]
                                                                                                                                                                                         )
                                                                                                                                                         entity-composite-key-db-id    (str application-k-name "-" entity-k-name "-" k-name "-altkey")
                                                                                                                                                         entity-composite-key          (basic-util/squuid)
                                                                                                                                                         entity-alternate-key-map      (when (true? (:pc-alternate? alternate-key))
                                                                                                                                                                                         [{:db/id                                             entity-db-id
                                                                                                                                                                                           :nxt.itools.entity/uuid-entity                    entity-uuid
                                                                                                                                                                                           :nxt.itools.entity/has-many-entity-composite-keys [entity-composite-key-db-id]
                                                                                                                                                                                           }
                                                                                                                                                                                          {:db/id                                                      entity-composite-key-db-id
                                                                                                                                                                                           :nxt.itools.entity-composite-key/uuid-entity-composite-key entity-composite-key
                                                                                                                                                                                           :nxt.itools.entity-composite-key/ref-entity                entity-db-id
                                                                                                                                                                                           :nxt.itools.entity-composite-key/ref-composite-key-type    [:nxt.itools.composite-key-type/uuid-composite-key-type uuid-alternate-comp-key]
                                                                                                                                                                                           :nxt.itools.entity-composite-key/ref-attr                  attr-db-id
                                                                                                                                                                                           :nxt.itools.entity-composite-key/key-order                 (:order alternate-key) ;(bigint (:order alternate-key))
                                                                                                                                                                                           }
                                                                                                                                                                                          ]
                                                                                                                                                                                         )

                                                                                                                                                         ]
                                                                                                                                                     [mofified-attr-map-with-entity attr-label-map attr-label-map-lang entity-primary-key-map entity-alternate-key-map]
                                                                                                                                                     )
                                                                                                                                                   )
                                                                                                                                                 )
                                                                                                                                               ) attrs)
                                                                                                  ]
                                                                                              [app-map ent-map ent-label-map ent-label-map-lang attr-maps]
                                                                                              )
                                                                                            )
                                                                                          )
                                                                  ]
                                                              new-entity-map
                                                              )
                                                            ) entities-with-full-attrs)
            ]
        (vec (remove nil? (flatten entity-attrs)))
        )
      )
    )
  )
;;=================================================================================================================
;;usage->   (list-idents-for-an-entity-from-erd-map "nxt.tools.entity")
;;Lists the idents from the ERD for an entity.
(defn list-idents-for-an-entity-from-erd-map [entity-ns-string]
  (->> (specter/transform [specter/ALL] (fn [ident]
                                          (let [ns (->> ident
                                                        (:db/ident)
                                                        (namespace)
                                                        )]
                                            (when (= ns entity-ns-string)
                                              (:db/ident ident)
                                              )))
                          (process-tools-erd {:req-output :schema :erd-map (erd/tools-erd)}))
       (remove nil?)
       (into [])
       )
  )
;;this function creates a sample map from an entity name.
;;this can be used for creating the seed data
(defn generate-sample-map-from-ns [entity-k-name]
  (let [m                (atom {})
        entity-namespace (str "nxt.nxt-tools." entity-k-name)]
    ;(last (map #(swap! m assoc % "-") (list-idents-for-an-entity-from-erd-map entity-namespace)))
    (last (map (fn [a]
                 (if (clojure.string/includes? a "has-many")
                   (swap! m assoc a [])
                   (if (clojure.string/includes? a "has-a")
                     (swap! m assoc a {})
                     ;(swap! m assoc a "-")

                     (if (= a (keyword (str entity-namespace "/" "k-name")))
                       (swap! m assoc a (symbol (str entity-k-name "-k-name")))
                       (if (= a (keyword (str entity-namespace "/" "name")))
                         (swap! m assoc a (symbol (str entity-k-name "-name")))
                         (if (= a (keyword (str entity-namespace "/" "uuid-tenant")))
                           (swap! m assoc a 'uuid-tenant)
                           (if (= a (keyword (str entity-namespace "/" "uuid-" entity-k-name)))
                             (swap! m assoc a (symbol (str "uuid-" entity-k-name)))
                             (if (clojure.string/includes? a "+")
                               (swap! m assoc a (vec (map (fn [key] (symbol (if (= key "k-name") (str entity-k-name "-k-name") key)))
                                                          (clojure.string/split (name a) #"\+"))))
                               (if (clojure.string/includes? a "?")
                                 (swap! m assoc a true)
                                 (if (clojure.string/includes? a (str entity-namespace "/" "uuid-"))
                                   (swap! m assoc a (symbol (name a)))
                                   (if (= a (keyword (str entity-namespace "/" "description")))
                                     (swap! m assoc a 'description)
                                     (swap! m assoc a (symbol (name a)))
                                     )
                                   )
                                 )
                               )
                             )
                           )
                         )
                       )
                     )
                   )
                 ) (list-idents-for-an-entity-from-erd-map entity-namespace)))
    )
  )

(defn generate-sample-map-from-ns-with-dbid [entity-k-name]
  (assoc (generate-sample-map-from-ns entity-k-name) :db/id entity-k-name)
  )

(comment
  (generate-sample-map-from-ns-with-dbid "application")

  )





