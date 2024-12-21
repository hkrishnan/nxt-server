(ns nxt.tools.data-model.erd
  (:require [com.rpl.specter  :as specter]
            [cuerdas.core :as cue]
            )
)



;;
;;
;;;;region Description
;;-----------------------------
;;Rel-optional-comp-belongs-to
;; rel-comp-belongs-to
;; belongs-to
;;-----------------------------
;;This is the “relationship non-required component belongs to” or “relationship optional component belongs to”.  Let’s say we have two entities, A and B. A “will/may-have-many” B’s; or A has “zero or many” B’s, and B “has-one” A.  B “belongs to” A.  There cannot be a B without A existing. A is an independent (major) entity, and B is the dependent (minor) entity. A can be created with no B’s.
;;
;;
;;When I describe this relationship between A and B, you (LLM) should automatically add an attribute “has-many-<B>s” on A with the datatype “ref” and cardinality “many”.  This attribute will not be required (optional).  And it will be a component attribute.
;;
;;The entity B will have the “ref-<A>” attribute.  This is also called a FK attr.  This attribute or attr will be a required attribute.  Its datatype will be ref, and cardinality, “one”.  This attribute will be a part of B's primary key (PART OF PPK).  The convention is that this will be the first attr in the composite primary key.
;;
;;-----------------------------
;;rel-required-comp-belongs-to
;;;-----------------------------
;;This is the “relationship required component belongs to””.  Let’s say we have two entities, A and B. A “will have one-or-many” B’s, or A has “one or many” B’s, and B “has-one” A.  B “belongs to” A.  There cannot be a B without A existing. A is an independent (major) entity, and B is the dependent (minor) entity.  When A is created, at least one B should be defined.
;;
;;
;;When I describe this relationship between A and B, you should automatically add an attribute “has-many-<B>s” on A with the datatype “ref” and cardinality “many”.  This attribute will be required.  And it will be a component attribute.
;;
;;The entity B will have the “ref-<A>” attribute.  This is also called a FK attr.  This attribute or attr will be a required attribute.  Its datatype will be ref, and cardinality, “one”.  This attribute will be a part of B's primary key (PART OF PPK).  The convention is that this will be the first attr in the composite primary key.
;;
;;
;;
;;-----------------------------
;;rel-required-comp-extension-of
;;-----------------------------
;;
;;This is the “relationship required component extension of”.  Let’s say we have two entities A and B. A “will have one” B; or A has one  B, and B “has-one” A.  B “belongs to” A.  When A is created, B also will be created. There cannot be a B without A existing. A is an independent (major) entity, and B is the dependent (minor) entity.
;;
;;
;;We may say “B is a required extension of” entity A.
;;
;;
;;When I describe this relationship between A and B, you should automatically add an attribute “has-one-<B>” on A with the datatype “ref” and cardinality “one”.  This attribute will be a required attribute.  And it will be a component attribute.
;;
;;The entity B will have the “ref-<A>” attribute.  This is also called a FK attr.  This attribute or attr will be a required attribute.  Its datatype will be ref, and cardinality, “one”.  This attribute will be the only primary key (ONLY PPK) of B.
;;
;;-----------------------------
;;rel-optional-comp-extension-of
;; rel-comp-extension-of
;; extension-of
;;-----------------------------
;;
;;This is the “relationship non-required component extension of”.  Let’s say we have two entities A and B. A “will/may-have-one” B; or A has one or zero B, and B “has-one” A.  B “belongs to” A.  There cannot be a B without A existing. But A can be created without B. A is an independent (major) entity, and B is the dependent (minor) entity.
;;
;;We may say “B is an optional extension of” entity A.
;;
;;
;;When I describe this relationship between A and B, you should automatically add an attribute “has-one-<B>” on A with the datatype “ref” and cardinality “one”.  This attribute will not be required.  And it will be a component attribute.
;;
;;The entity B will have the “ref-<A>” attribute.  This is also called a FK attr.  This attribute or attr will be a required attribute.  Its datatype will be ref, and cardinality, “one”.  This attribute will be the only primary key (ONLY PPK) of B.
;;
;;-----------------------------
;;rel-optional-has-one-required
;;-----------------------------
;;
;;This is the “relationship optional has one required””.  Let’s say we have two entities, A and B. A “will have zero-or-many” B’s, or A has “zero or many” B’s, and B “has one” A. There cannot be a B without A existing. A is an independent (major) entity. A is an attribute of B.
;;
;;
;;When I describe this relationship between A and B, the entity B will have the “ref-<A>” attribute.  This is also called a FK attr.  This attribute or attr will be a required (not optional) attribute.  Its datatype will be ref, and cardinality, “one”.  A is the prompt entity. The FK attr will NOT be PART OF PPK.
;;
;;-----------------------------
;;rel-optional-has-one-optional
;;-----------------------------
;;
;;This is the “relationship optional has one optional””.  Let’s say we have two entities, A and B. A “will have zero-or-many” B’s, or A has “zero or many” B’s, and B “has one” A. A is an independent (major) entity. A is an attribute of B.
;;
;;
;;When I describe this relationship between A and B, the entity B will have the “ref-<A>” attribute.  This is also called a FK attr.  This attribute or attr will be an optional (not required) attribute.  Its datatype will be ref, and cardinality, “one”.  A is the prompt entity. The FK attr will NOT be PART OF PPK.
;;;;endregion
;;






(defn description-attr []
  {
   :k-name        "description"
   :description   "description"
   :short-label   "Description"
   :long-label    "Description"

   :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
   :gen-type      {:type "util" :attr-linked-to nil}

   :cardinality   "one"
   :required?     true
   :component?    false
   :active?       true
   :history?      true
   :search-attr?  false

   :primary-key   {:pc-primary? false :order 0}
   :alternate-key {:pc-alternate? false :order 0}
   })
;;In nxt-tools, k-name, if present will always be part of the Composite Primary Key.  SO it needs a key order.
(defn k-name-attr [pcpk-order]
  (let [pcalt-order 0]
    (identity {
               :k-name        "k-name"
               :description   "k-name of the entity"
               :data-type     {:type "kc-string" :entity-related-to {:app nil :entity nil}}
               :cardinality   "one"
               :primary-key   {:pc-primary? true :order pcpk-order}
               :alternate-key {:pc-alternate? false :order pcalt-order}
               :gen-type      {:type "util" :attr-linked-to nil}
               ;;util - defined through the tools utility.
               ;;system - by the system, example is the linked attr.
               ;;online - a logiin user
               :required?     true
               :component?    false
               :history?      true

               ;;:attr-order 1  This will be derived from the order in this vector.
               :active?       true
               :search-attr?  true

               ;:short-label   "KName"
               ;:long-label    "K Name"
               :short-label   "K Name"
               :long-label    "K Name"
               })
    ))
(defn namespace-attr [key-type-order-map]
  (let [pcpk-order    0

        {:keys [key-type order]
         :or   {order 0 key-type "alternate"}} key-type-order-map

        pc-primary?   false
        pc-alternate? true
        ]
    (identity {
               :k-name        "namespace"
               :description   "namespace"
               :data-type     {:type "kc-string" :entity-related-to {:app nil :entity nil}}
               :cardinality   "one"
               :primary-key   {:pc-primary? pc-primary? :order order}
               :alternate-key {:pc-alternate? pc-alternate? :order order}
               :gen-type      {:type "util" :attr-linked-to nil}

               :required?     true

               :component?    false
               :history?      true

               :active?       true
               :search-attr?  false

               :short-label   "Namespace"
               :long-label    "Namespace"

               })))

;;name if present, could be primary or alternate key. k-name is always part of primary key.
;;name can be either. so it has to be indicated by a map argument
;; {:key-type "primary" order 2}
;; {:key-type "alternate" order 2}
;; order default is 0
;; name-as-key-attr {:key-type "alternate" order 1}
(defn name-as-key-attr [key-type-order-map]
  (let [pcpk-order    0
        {:keys [key-type order]
         :or   {order 0}} key-type-order-map
        pc-primary?   (if (= key-type "primary")
                        (boolean true)
                        (boolean false)
                        )
        pc-alternate? (if (= key-type "alternate")
                        (boolean true)
                        (boolean false)
                        )
        ]
    (identity {
               :k-name        "name"
               :description   "a descriptive name of entity"
               :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
               :cardinality   "one"
               :primary-key   {:pc-primary? pc-primary? :order order}
               :alternate-key {:pc-alternate? pc-alternate? :order order}
               :gen-type      {:type "util" :attr-linked-to nil}

               :required?     true

               :component?    false
               :history?      true

               :active?       true
               :search-attr?  true


               :short-label   "Name"
               :long-label    "Friendly Name"

               })))

(defn company-id-as-key-attr [key-type-order-map]
  (let [pcpk-order    0
        {:keys [key-type order]
         :or   {order 0}} key-type-order-map
        pc-primary?   (if (= key-type "primary")
                        (boolean true)
                        (boolean false)
                        )
        pc-alternate? (if (= key-type "alternate")
                        (boolean true)
                        (boolean false)
                        )
        ]
    (identity {
               :k-name        "company-id"
               :description   "a unique public identifier for a tenant"
               :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
               :cardinality   "one"
               :primary-key   {:pc-primary? pc-primary? :order order}
               :alternate-key {:pc-alternate? pc-alternate? :order order}
               :gen-type      {:type "util" :attr-linked-to nil}

               :required?     true

               :component?    false
               :history?      true

               :active?       true
               :search-attr?  true


               :short-label   "Company ID"
               :long-label    "Company ID"

               })))




#_(defmulti relationship-attr (fn [{:keys [rel-type rel-app rel-entity attr-k-name pcaltk-order]
                                    :or   {pcaltk-order 0}
                                    :as   input-map}]
                                rel-type))

(defmulti relationship-attr :rel-type)
(defmethod relationship-attr :default [{:keys [rel-type rel-app rel-entity attr-k-name pcaltk-order]
                                        :as   input-map}]
  (throw (IllegalArgumentException.
           (str "I don't know the " rel-type))))

(defmethod relationship-attr "belongs-to" [{:keys [rel-type rel-app rel-entity attr-k-name
                                                   pcaltk-order pcpk-order comp-attr-required?]
                                            :or   {pcpk-order 0 pcaltk-order 0 comp-attr-required? false}
                                            :as   input-map}]
  (identity {
             :k-name        (if (nil? attr-k-name) (str "ref-" rel-entity) attr-k-name)
             :description   "reference to the parent entity"
             :data-type     {:type "rel-optional-comp-belongs-to" :entity-related-to {:app                 rel-app :entity rel-entity
                                                                             :comp-attr-required? comp-attr-required?}}
             :cardinality   "one"
             :primary-key   {:pc-primary? true :order pcpk-order}
             :alternate-key {:pc-alternate? (if (> pcaltk-order 0) (boolean true) (boolean false)) :order pcaltk-order}
             :gen-type      {:type "util" :attr-linked-to nil}

             :required?     true
             :list-attr? false

             :component?    false
             :history?      true

             :active?       true
             :search-attr?  true

             :short-label   (cue/human rel-entity)
             :long-label    (cue/human rel-entity)
             })
  )

#_(defmethod relationship-attr "rel-non-comp-belongs-to" [{:keys [rel-type rel-app rel-entity attr-k-name pcaltk-order]
                                                           :as   input-map}]
    (identity {
               :k-name       (if (nil? attr-k-name) (str "uuid-" rel-entity) attr-k-name)
               :description  "the parent entity key attr"
               :data-type    {:type "rel-non-comp-belongs-to" :entity-related-to {:app rel-app :entity rel-entity}}
               :cardinality  "one"                          ;; will be defaulted based on the type above anyway
               :primary-key  {:pc-primary? true :order pcaltk-order} :alternate-key {:pc-alternate? true :order pcak-order} ;; ppk? will be defaulted based on the type above anyway
               :gen-type     {:type "util" :attr-linked-to nil} ;; will be defaulted based on the type above anyway

               :required?    true                           ;; will be defaulted based on the type above anyway

               :component?   false                          ;; not a component ;; will be defaulted based on the type above anyway
               :history?     true                           ;; will be defaulted based on the type above anyway

               :active?      true                           ;; will be defaulted based on the type above anyway
               :search-attr? true                           ;; will be defaulted based on the type above anyway
               })
    )

(defmethod relationship-attr "extension-of" [{:keys [rel-type rel-app rel-entity attr-k-name
                                                     pcaltk-order pcpk-order comp-attr-required? short-label long-label]
                                              :or   {pcpk-order  0 pcaltk-order 0 comp-attr-required? false
                                                     short-label "default-labl" long-label "default-labl"}
                                              :as   input-map}]
  (identity {
             :k-name        (if (nil? attr-k-name) (str "ref-" rel-entity) attr-k-name)
             :description   "reference to the parent entity"
             :data-type     {:type "rel-optional-comp-extension-of" :entity-related-to {:app                 rel-app :entity rel-entity
                                                                               :comp-attr-required? comp-attr-required?}}
             :cardinality   "one"                           ;; will be defaulted based on the type above anyway
             :primary-key   {:pc-primary? true :order pcpk-order}
             :alternate-key {:pc-alternate? (if (> pcaltk-order 0) (boolean true) (boolean false)) :order pcaltk-order}
             :gen-type      {:type "util" :attr-linked-to nil} ;; will be defaulted based on the type above anyway

             :required?     true                            ;; will be defaulted based on the type above anyway
             :list-attr? false

             :component?    false                           ;;the corresponding ref attr will be a component.
             :history?      true                            ;; will be defaulted based on the type above anyway

             :active?       true                            ;; will be defaulted based on the type above anyway
             :search-attr?  true                            ;; will be defaulted based on the type above anyway

             :short-label   (if (= short-label "default-labl") (cue/human rel-entity) short-label)
             :long-label    (if (= long-label "default-labl") (cue/human rel-entity) long-label)
             })
  )
#_(defmethod relationship-attr "rel-non-comp-extension-of" [{:keys [rel-type rel-app rel-entity attr-k-name pcaltk-order]
                                                             :as   input-map}]
    (identity {
               :k-name       (if (nil? attr-k-name) (str "uuid-" rel-entity) attr-k-name)
               :description  "the parent entity key attr"
               :data-type    {:type "rel-non-comp-extension-of" :entity-related-to {:app rel-app :entity rel-entity}}
               :cardinality  "one"                          ;; will be defaulted based on the type above anyway
               :primary-key  {:pc-primary? true :order 1}   ;; ppk? will be defaulted based on the type above anyway
               :gen-type     {:type "util" :attr-linked-to nil} ;; will be defaulted based on the type above anyway

               :required?    true                           ;; will be defaulted based on the type above anyway

               :component?   false                          ;; not a component ;; will be defaulted based on the type above anyway
               :history?     true                           ;; will be defaulted based on the type above anyway

               :active?      true                           ;; will be defaulted based on the type above anyway
               :search-attr? true                           ;; will be defaulted based on the type above anyway
               })
    )

(defmethod relationship-attr "has-one-required" [{:keys [rel-type rel-app rel-entity attr-k-name pcaltk-order pcpk-order
                                                         dependent-on-attr-kname short-label long-label]
                                                  :or   {pcpk-order  0 pcaltk-order 0 dependent-on-attr-kname nil
                                                         short-label "default-labl" long-label "default-labl"}
                                                  :as   input-map}]
  (identity {
             :k-name                  (if (nil? attr-k-name) (str "ref-" rel-entity) attr-k-name)
             :description             "reference to the entity, and it is required for the minor entity"
             :data-type               {:type "rel-optional-has-one-required" :entity-related-to {:app rel-app :entity rel-entity}}
             :cardinality             "one"
             ;;:primary-key  {:pc-primary? (if (= rel-entity "language") (boolean true) (boolean false)) :order pcaltk-order}
             ;;In some cases this key can be a primary key.  But the reverse relationship is optional.
             ;;for example, most often, all the data-types will have a data-element.  But the html-component may not
             ;; have any relation with a data-type.
             ;;we will be looking at the pcaltk-order; if greater than 0 then it has to be a key.
             ;:primary-key  {:pc-primary? (if (> pcaltk-order 0) (boolean true) (boolean false)) :order pcaltk-order}
             :primary-key             {:pc-primary? (if (> pcpk-order 0) (boolean true) (boolean false)) :order pcpk-order}
             :alternate-key           {:pc-alternate? (if (> pcaltk-order 0) (boolean true) (boolean false)) :order pcaltk-order}
             :gen-type                {:type "util" :attr-linked-to nil}

             :required?               true
             :list-attr? false

             :component?              false
             :history?                true

             :active?                 true
             :search-attr?            true
             :dependent-on-attr-kname dependent-on-attr-kname

             :short-label             (if (= short-label "default-labl") (cue/human rel-entity) short-label)
             :long-label              (if (= long-label "default-labl") (cue/human rel-entity) long-label)
             })
  )
(defmethod relationship-attr "has-one-optional" [{:keys [rel-type rel-app rel-entity attr-k-name pcaltk-order pcpk-order
                                                         dependent-on-attr-kname short-label long-label]
                                                  :or   {pcpk-order  0 pcaltk-order 0 dependent-on-attr-kname nil
                                                         short-label "default-labl" long-label "default-labl"}
                                                  :as   input-map}]
  (identity {
             :k-name                  (if (nil? attr-k-name) (str "ref-" rel-entity) attr-k-name)
             :description             "referene to another entity, and is optional"
             :data-type               {:type "rel-optional-has-one-optional" :entity-related-to {:app rel-app :entity rel-entity}}
             :cardinality             "one"                 ;; will be defaulted based on the type above anyway
             :primary-key             {:pc-primary? false :order 0} ;; ppk? will be defaulted based on the type above anyway
             :alternate-key           {:pc-alternate? false :order 0}
             :gen-type                {:type "util" :attr-linked-to nil} ;; will be defaulted based on the type above anyway

             :required?               false                 ;; will be defaulted based on the type above anyway
             :list-attr? false

             :component?              false                 ;; not a component ;; will be defaulted based on the type above anyway
             :history?                true                  ;; will be defaulted based on the type above anyway

             :active?                 true                  ;; will be defaulted based on the type above anyway
             :search-attr?            false                 ;; will be defaulted based on the type above anyway
             :dependent-on-attr-kname dependent-on-attr-kname

             :short-label             (if (= short-label "default-labl") (cue/human rel-entity) short-label)
             :long-label              (if (= long-label "default-labl") (cue/human rel-entity) long-label)
             })
  )

(defn boolean-attr [default-value attr-kname attr-descr short-label long-label]
  (identity {
             :k-name        attr-kname
             :description   attr-descr
             :data-type     {:type "boolean" :entity-related-to {:app nil :entity nil}}
             :cardinality   "one"
             :primary-key   {:pc-primary? false :order 0}
             :alternate-key {:pc-alternate? false :order 0}
             :gen-type      {:type "util" :attr-linked-to nil}

             :required?     true

             :default-value default-value

             :component?    false
             :history?      true

             :active?       true
             :search-attr?  false


             :short-label   short-label
             :long-label    long-label

             }))
(defn string-attr [attr-kname attr-descr search? required? short-label long-label]
  (identity {
             :k-name        attr-kname
             :description   attr-descr
             :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
             :cardinality   "one"
             :primary-key   {:pc-primary? false :order 0}
             :alternate-key {:pc-alternate? false :order 0}
             :gen-type      {:type "util" :attr-linked-to nil}

             :required?     required?

             :component?    false
             :history?      true

             :active?       true
             :search-attr?  search?

             :short-label   short-label
             :long-label    long-label
             }))

(defn date-attr [attr-kname attr-descr search? required? short-label long-label]
  (identity {
             :k-name        attr-kname
             :description   attr-descr
             :data-type     {:type "date-time" :entity-related-to {:app nil :entity nil}}
             :cardinality   "one"
             :primary-key   {:pc-primary? false :order 0}
             :alternate-key {:pc-alternate? false :order 0}
             :gen-type      {:type "util" :attr-linked-to nil}

             :required?     required?

             :component?    false
             :history?      true

             :active?       true
             :search-attr?  search?

             :short-label   short-label
             :long-label    long-label
             }))

(defn default-attr [data-type-str]
  (identity {
             :k-name        (str data-type-str "-dflt")
             :description   (str data-type-str " default value")
             :data-type     {:type data-type-str :entity-related-to {:app nil :entity nil}}
             :cardinality   "one"
             :primary-key   {:pc-primary? false :order 0}
             :alternate-key {:pc-alternate? false :order 0}
             :gen-type      {:type "util" :attr-linked-to nil}

             :required?     false

             :component?    false
             :history?      true

             :active?       true
             :search-attr?  false

             :short-label   "Default Value"
             :long-label    "Default Value"
             }))


(defn tenant-owner-attr []
  (identity (relationship-attr {:rel-type     "has-one-required"
                                :rel-app      "itools"
                                :rel-entity   "tenant"
                                :attr-k-name  "ref-tenant-owner"
                                :pcpk-order   0
                                :pcaltk-order 0})))

#_(defn label-of-entity [entity-k-name]
    (identity {
               :k-name      (str entity-k-name "-label-text")
               :description (str "Labels and texts for entity " entity-k-name)
               :short-label "Label"
               :long-label  "Label"
               :attrs       [
                             (relationship-attr {:rel-type            "belongs-to"
                                                 :rel-app             "itools"
                                                 :rel-entity          entity-k-name
                                                 :pcpk-order          1
                                                 :pcaltk-order        0
                                                 :comp-attr-required? true ;enter the label while creating the main entity
                                                 })
                             (k-name-attr 2)
                             (relationship-attr {:rel-type   "has-one-required"
                                                 :rel-app    "itools"
                                                 :rel-entity "language"
                                                 :pcpk-order 2})
                             (string-attr "short-text" "short label" false true "Short Text" "Short Text")
                             (string-attr "long-text" "long label" false true "Long Text" "Long Text")
                             ]
               }))

(defn label-of-entity [entity-k-name]
  [(identity {
              :k-name      (str entity-k-name "-label-text")
              :description (str "Labels and texts for entity " entity-k-name)
              :short-label "Label"
              :long-label  "Label"
              :attrs       [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          entity-k-name
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? true ;enter the label while creating the main entity
                                                })
                            (k-name-attr 2)
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "label-text-type"
                                                :pcpk-order 0})
                            (tenant-owner-attr)
                            (boolean-attr false "dflt-label?" "default label or text" "Default?" "Default?")
                            ]
              })
   (identity {
              :k-name      (str entity-k-name "-label-text-lang")
              :description (str "Labels and texts for entity " entity-k-name)
              :short-label "Label"
              :long-label  "Label"
              :attrs       [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          (str entity-k-name "-label-text")
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? true ;enter the label while creating the main entity
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "language"
                                                :pcpk-order 2})
                            (string-attr "short-text" "short text" false true "Short Text" "Short Text")
                            (string-attr "long-text" "long text" false true "Long Text" "Long Text")
                            ]
              })
   ]
  )

(comment
  (label-of-entity "menu-group")
  )


;;kebab-case-name
(defn tools-erd-model []
  (let [nxt-tenant "nxt"
        nxt-app    "itools"]
    (identity
      ;;Beginning of ERD
      {
       ;;what tenant? and what app?
       :tenant   nxt-tenant
       :app      nxt-app
       :entities [
                  ;;Entity - Tenant
                  {
                   :k-name      "tenant"
                   :description "Tenant Entity"
                   :short-label "Tenant"
                   :long-label  "Tenant"
                   :attrs       [
                                 (k-name-attr 1)
                                 (name-as-key-attr {:key-type "alternate" :order 1})

                                 ;;company-id-as-key-attr - used only here
                                 (company-id-as-key-attr {:key-type "alternate" :order 1})

                                 (string-attr "web-url" "The web url of the Tenant" true true "Web" "Web URL")
                                 (description-attr)
                                 (boolean-attr true "active?" "active tenant?" "Active?" "Active?")
                                 (date-attr "created-at" "Created date" false true "Created At" "Created At")
                                 ]
                   }

                  ;;Entity - Tenant User
                  {:k-name      "tenant-user"
                   :description "Tenant User"
                   :short-label "User"
                   :long-label  "Tenant User"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "tenant"
                                                     ;;part of the composite primary key - pcpk
                                                     :pcpk-order   1
                                                     :pcaltk-order 1})
                                 {
                                  :k-name        "email"
                                  :description   "Email of the user"
                                  :data-type     {:type "email" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Email"
                                  :long-label    "User Email"
                                  }
                                 #_{
                                    :k-name        "user-id"
                                    :description   "user id of the user"
                                    :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                    :cardinality   "one"

                                    :primary-key   {:pc-primary? false :order 0}
                                    :alternate-key {:pc-alternate? true :order 2}

                                    :gen-type      {:type "util" :attr-linked-to nil}

                                    :required?     true

                                    :component?    false
                                    :history?      true

                                    :active?       true
                                    :search-attr?  true

                                    :unique        false       ;;;the default is false for all attrs. Even for ppk? true attrs.
                                    ;;this is basically :db/unique value attr.  Not an upsert key.

                                    :short-label   "Userid"
                                    :long-label    "User Id"
                                    }

                                 (string-attr "password-hash" "Password Hash" false true "Pwd Hash" "Pwd Hash")

                                 (string-attr "first-name" "First Name of the User" true true "First Name" "First Name")
                                 (string-attr "last-name" "Last Name of the User" true true "Last Name" "Last Name")
                                 (string-attr "phone-1" "Primary Phone of the User" true true "Primary Phone" "Primary Phone")
                                 (string-attr "phone-2" "Secondary Phone of the User" false false "Alt Phone" "Alternate Phone")
                                 (string-attr "fax" "Fax of the User" false false "Fax" "Fax")
                                 (string-attr "position" "Position of the User" false true "Title" "Position Title")

                                 (boolean-attr false "contact?" "tenant location contact?" "Contact?" "Contact?")

                                 (boolean-attr true "active?" "active user?" "Active?" "Active?")

                                 (date-attr "created-at" "Created date" false true "Created At" "Created At")


                                 #_(relationship-attr {:rel-type   "has-one-required"
                                                       :rel-app    "itools"
                                                       :rel-entity "tenant"
                                                       ;:attr-k-name "<give-a-name>" ;<parent-kname> is the default
                                                       })
                                 #_(relationship-attr {:rel-type   "has-one-required"
                                                       :rel-app    "itools"
                                                       :rel-entity "access-group"
                                                       ;:attr-k-name "<give-a-name>" ;<parent-kname> is the default
                                                       })
                                 #_ (relationship-attr {:rel-type   "has-one-required"
                                                        :rel-app    "itools"
                                                        :rel-entity "tenant-location"
                                                        ;:attr-k-name "<give-a-name>" ;<parent-kname> is the default
                                                        })
                                 ]
                   }
                  ;;Email Verifications
                  {:k-name      "email-verification"
                   :description "Email Verification"
                   :short-label "Email Verification"
                   :long-label  "Email Verification"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "tenant-user"
                                                     ;;part of the composite primary key - pcpk
                                                     :pcpk-order   1
                                                     :pcaltk-order 0})
                                 {
                                  :k-name        "verification-token"
                                  :description   "Verification Token"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Verification Token"
                                  :long-label    "Verification Token"
                                  }
                                 (date-attr "expires-at" "Expiry date" false true "Expires At" "Expires At")
                                 (boolean-attr true "verified?" "verified email?" "Verified?" "Verified?")
                                 (date-attr "created-at" "Created date" false true "Created At" "Created At")
                                 ]
                   }
                  {:k-name      "password-reset-token"
                   :description "Password reset token"
                   :short-label "password reset token"
                   :long-label  "Password Reset Token"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "tenant-user"
                                                     ;;part of the composite primary key - pcpk
                                                     :pcpk-order   1
                                                     :pcaltk-order 0})
                                 {
                                  :k-name        "reset-token"
                                  :description   "Reset Token"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Reset Token"
                                  :long-label    "Reset Token"
                                  }
                                 (date-attr "expires-at" "Expiry date" false true "Expires At" "Expires At")
                                 (date-attr "used-at" "Used date" false true "Used At" "Used At")
                                 (date-attr "created-at" "Created date" false true "Created At" "Created At")
                                 ]
                   }
                  {:k-name      "session"
                   :description "Sessions"
                   :short-label "Session"
                   :long-label  "Session"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "tenant-user"
                                                     ;;part of the composite primary key - pcpk
                                                     :pcpk-order   1
                                                     :pcaltk-order 0})
                                 {
                                  :k-name        "session-key"
                                  :description   "Session key"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Session Key"
                                  :long-label    "Session Key"
                                  }
                                 {
                                  :k-name        "user-agent"
                                  :description   "user agent"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true

                                  :unique        false       ;;;the default is false for all attrs. Even for ppk? true attrs.
                                  ;;this is basically :db/unique value attr.  Not an upsert key.

                                  :short-label   "User Agent"
                                  :long-label    "User Agent"
                                  }
                                 {
                                  :k-name        "ip-address"
                                  :description   "ip-address"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true

                                  :unique        false       ;;;the default is false for all attrs. Even for ppk? true attrs.
                                  ;;this is basically :db/unique value attr.  Not an upsert key.

                                  :short-label   "IP Address"
                                  :long-label    "IP Address"
                                  }

                                 {
                                  :k-name        "anti-forgery-token"
                                  :description   "anti-forgery-token"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true

                                  :unique        false       ;;;the default is false for all attrs. Even for ppk? true attrs.
                                  ;;this is basically :db/unique value attr.  Not an upsert key.

                                  :short-label   "anti-forgery-token"
                                  :long-label    "anti-forgery-token"
                                  }
                                 (date-attr "expires-at" "Expiry date" false true "Expires At" "Expires At")
                                 (date-attr "created-at" "Created date" false true "Created At" "Created At")
                                 ]
                   }
                  ]
       }
      )
    )
  )


(defn tools-erd-model-2 []
  (let [nxt-tenant "nxt"
        nxt-app    "itools"]
    (identity
      ;;Beginning of ERD
      {
       ;;what tenant? and what app?
       :tenant   nxt-tenant
       :app      nxt-app
       :entities [
                  ;;Entity - Tenant
                  {
                   :k-name      "tenant"
                   :description "Tenant Entity"
                   :short-label "Tenant"
                   :long-label  "Tenant"
                   :attrs       [
                                 (k-name-attr 1)
                                 (name-as-key-attr {:key-type "alternate" :order 1})
                                 (string-attr "web-url" "The web url of the Tenant" true true "Web" "Web URL")
                                 (boolean-attr true "active?" "active tenant?" "Active?" "Active?")
                                 (date-attr "created-at" "Created date" false true "Created At" "Created At")
                                 (description-attr)
                                 ]
                   }

                  ;;Entity - User
                  {:k-name      "user"
                   :description "User"
                   :short-label "User"
                   :long-label  "User"
                   :attrs       [
                                 {
                                  :k-name        "email"
                                  :description   "Email of the user"
                                  :data-type     {:type "email" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Email"
                                  :long-label    "User Email"
                                  }
                                 {
                                  :k-name        "user-id"
                                  :description   "user id of the user"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? true :order 1}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true

                                  :unique        true       ;;;the default is false for all attrs. Even for ppk? true attrs.
                                  ;;this is basically :db/unique value attr.  Not an upsert key.

                                  :short-label   "Userid"
                                  :long-label    "User Id"
                                  }
                                 (date-attr "created-at" "Created date" false true "Created At" "Created At")

                                 (string-attr "first-name" "First Name of the User" true true "First Name" "First Name")
                                 (string-attr "last-name" "Last Name of the User" true true "Last Name" "Last Name")
                                 (string-attr "phone-1" "Primary Phone of the User" true true "Primary Phone" "Primary Phone")
                                 (string-attr "phone-2" "Secondary Phone of the User" false false "Alt Phone" "Alternate Phone")
                                 (string-attr "fax" "Fax of the User" false false "Fax" "Fax")
                                 (string-attr "position" "Position of the User" false true "Title" "Position Title")

                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "tenant"
                                                     ;:attr-k-name "<give-a-name>" ;<parent-kname> is the default
                                                     })
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "access-group"
                                                     ;:attr-k-name "<give-a-name>" ;<parent-kname> is the default
                                                     })
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "tenant-location"
                                                     ;:attr-k-name "<give-a-name>" ;<parent-kname> is the default
                                                     })
                                 (boolean-attr false "contact?" "tenant location contact?" "Contact?" "Contact?")
                                 (boolean-attr false "dflt-label?" "default label or text" "Default?" "Default?")
                                 ;;Just for validation purposes only
                                 (string-attr "password" "Password Hash" false true "Pwd Hash" "Pwd Hash")
                                 (string-attr "password-repeated" "Password Hash" false true "Pwd Hash" "Pwd Hash")
                                 (string-attr "password-hash" "Password Hash" false true "Pwd Hash" "Pwd Hash")
                                 ]
                   }

                  ;;Entity - user-home-cell-type
                  {
                   :k-name "user-home-cell-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - User-home-cell
                  {
                   :k-name      "user-home-cell"
                   :description "User home cell"
                   :short-label "Home"
                   :long-label  "User Home"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "user"
                                                     :attr-k-name  "ref-user-current"
                                                     :pcpk-order   1
                                                     :pcaltk-order 0})
                                 (name-as-key-attr {:key-type "primary" :order 2})
                                 (description-attr)
                                 {
                                  :k-name        "cell-order"
                                  :description   "cell order"
                                  :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Cell Order"
                                  :long-label    "Cell Order"
                                  }
                                 {
                                  :k-name        "row-order"
                                  :description   "row order"
                                  :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Row Order"
                                  :long-label    "Row Order"
                                  }
                                 (boolean-attr true "active?" "Active Cell?" "Active?" "Active?")
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "user-home-cell-type"
                                                     })
                                 (relationship-attr {:rel-type   "has-one-optional"
                                                     :rel-app    "itools"
                                                     :rel-entity "chart-item"
                                                     })
                                 ]
                   }
                  ;;Entity - user-bookmark
                  {
                   :k-name      "user-bookmark"
                   :description "User Bookmarks"
                   :short-label "Bookmark"
                   :long-label  "Bookmark"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "user"
                                                     :attr-k-name  "ref-user-current"
                                                     :pcpk-order   1
                                                     :pcaltk-order 0})
                                 (name-as-key-attr {:key-type "primary" :order 2})
                                 (description-attr)
                                 (relationship-attr {:rel-type   "has-one-optional"
                                                     :rel-app    "itools"
                                                     :rel-entity "menu-item"
                                                     })
                                 ]
                   }
                  ;;Entity - inv-item
                  {
                   :k-name      "inv-item"
                   :description "Inventory Item"
                   :short-label "Item"
                   :long-label  "Inv Item"
                   :attrs       [

                                 (name-as-key-attr {:key-type "primary" :order 1})
                                 (description-attr)
                                 ]
                   }
                  ;;Entity - inv-item-image
                  {
                   :k-name      "inv-item-image"
                   :description "Inventory Item Image"
                   :short-label "Item Image"
                   :long-label  "Inv Item Image"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "user"
                                                     :attr-k-name  "inv-item"
                                                     :pcpk-order   1
                                                     :pcaltk-order 0})

                                 {
                                  :k-name        "url"
                                  :description   "url"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true

                                  :unique        false      ;;;the default is false for all attrs. Even for ppk? true attrs.
                                  ;;this is basically :db/unique value attr.  Not an upsert key.

                                  :short-label   "url"
                                  :long-label    "url"
                                  }
                                 (boolean-attr false "dflt-image?" "default label or text" "Default?" "Default?")
                                 ]
                   }

                  ;;Entity - Language
                  {
                   :k-name      "language"
                   :description "Tenant Language"
                   :short-label "Language"
                   :long-label  "Language"
                   :attrs       [
                                 {
                                  :k-name        "code"
                                  :description   "Language Code"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Code"
                                  :long-label    "Language Code"
                                  }
                                 ;(name-attr 1)              ;;alternate key
                                 (name-as-key-attr {:key-type "alternate" :order 1})
                                 (description-attr)
                                 ]
                   }
                  ;;Entity - access-group
                  {
                   :k-name      "access-group"
                   :description "access group"
                   :short-label "Access Group"
                   :long-label  "Access Group"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "tenant"
                                                     :pcpk-order          1
                                                     :pcaltk-order        0
                                                     :comp-attr-required? false
                                                     })
                                 (k-name-attr 2)
                                 (description-attr)
                                 ]
                   }
                  ;;Entity - access-group-menu-item
                  {
                   :k-name "access-group-menu-item"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "access-group"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "menu-item"
                                                :pcpk-order 2})
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "access-permission"
                                                :pcpk-order 0})
                            ]
                   }
                  ;;Entity - access-permission
                  {
                   :k-name      "access-permission"
                   :description "access permission"
                   :short-label "Access Permission"
                   :long-label  "Access Permission"
                   :attrs       [
                                 (k-name-attr 1)
                                 (description-attr)
                                 ]
                   }
                  ;;===========================================
                  ;;Entity - Application application-entity-defn
                  {
                   :k-name      "application"
                   :description "Application Entity"
                   :short-label "Application"
                   :long-label  "Application"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "tenant"
                                                     :pcpk-order   1 ;required
                                                     :pcaltk-order 0 ;required
                                                     })
                                 (k-name-attr 2)            ; pcpk
                                 (description-attr)
                                 (tenant-owner-attr)
                                 ]
                   }

                  ;;Entity - Entity
                  {
                   :k-name      "entity"
                   :description "Entity of the application"
                   :short-label "Entity"
                   :long-label  "Entity"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "application"
                                                     :pcpk-order   1
                                                     :pcaltk-order 0})
                                 (k-name-attr 2)
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "entity-type"
                                                     ;:attr-k-name "<give-a-name>" ;<parent-kname> is the default
                                                     })
                                 (description-attr)
                                 (boolean-attr false "internal?" "do not expose it to clients or tenants" "Internal?" "Internal?")
                                 (boolean-attr false "added-by-initial-load?" "Entity added by initial load?" "Initial Load?" "Added by Initial Load?")
                                 (tenant-owner-attr)
                                 ]
                   }

                  ;;Entity - Entity-label
                  (label-of-entity "entity")
                  ;;Entity - Entity-Type
                  {
                   :k-name      "entity-type"
                   :description "Type of the Entity"
                   :short-label "Type"
                   :long-label  "Entity Type"
                   :attrs       [
                                 (k-name-attr 1)
                                 (description-attr)
                                 ]
                   }
                  ;;Entity - Entity-Constraint
                  {
                   :k-name      "entity-constraint"
                   :description "Constrained defined for entity"
                   :short-label "Constraint"
                   :long-label  "Constraint"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "entity"
                                                     :pcpk-order   1
                                                     :pcaltk-order 0
                                                     ;:comp-attr-required? true ;;forces to create a label
                                                     })
                                 (k-name-attr 2)
                                 {
                                  :k-name        "order"
                                  :description   "constraint order"
                                  :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Order"
                                  :long-label    "Constraint Order"
                                  }
                                 (boolean-attr false "active?" "is it an active constraint?" "Active?" "Active?")
                                 (tenant-owner-attr)
                                 ]
                   }
                  ;;Entity - Entity-composite-key
                  {
                   :k-name      "entity-composite-key"
                   :description "Composite Key of the entity"
                   :short-label "Key"
                   :long-label  "Composite Key"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "entity"
                                                     :pcpk-order          1
                                                     :pcaltk-order        0
                                                     :comp-attr-required? false
                                                     })

                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "attr"
                                                     :pcpk-order 2})

                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "composite-key-type"
                                                     :pcpk-order 3})
                                 {
                                  :k-name        "key-order"
                                  :description   "key order"
                                  :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Order"
                                  :long-label    "Key Order"
                                  }
                                 ]
                   }
                  ;;Entity - composite-key-type
                  {
                   :k-name      "composite-key-type"
                   :description "Type of the composite key"
                   :short-label "Key type"
                   :long-label  "Composite Key Type"
                   :attrs       [
                                 (k-name-attr 1)
                                 (description-attr)
                                 ]
                   }
                  ;;Entity - Login   ;;This shpuld be a virtual entity;  will define later.
                  {
                   :k-name      "login"
                   :description "login entity; dont store any data view only"
                   :short-label "Login"
                   :long-label  "Login"
                   :attrs       [{
                                  :k-name        "email"
                                  :description   "Email of the user"
                                  :data-type     {:type "email" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :default-value "hk@yahoo.com" ;;remove later

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Email"
                                  :long-label    "User Email"
                                  }
                                 ;;Just for validation purposes only
                                 (string-attr "password" "Password Hash" false true "Password" "Password")
                                 ;(string-attr "password-repeated" "Password Repeated" false true "Password Repeat" "Password Repeat")
                                 ]
                   }
                  ;;================================

                  ;;Entity - Data-Type
                  {
                   :k-name      "data-type"
                   :description "attr data types"
                   :short-label "Data Type"
                   :long-label  "Data Type"
                   :attrs       [
                                 (k-name-attr 1)
                                 (description-attr)
                                 {
                                  :k-name        "datomic-type"
                                  :description   "Datomic Native Data Type"
                                  :data-type     {:type "keyword" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}
                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Type"
                                  :long-label    "Datomic Data Type"
                                  }
                                 (string-attr "reg-text" "text field to store the reg ex" false true "Regx" "Text field for Regex")
                                 ]
                   }
                  ;;Entity - Visual-Type
                  {
                   :k-name      "visual-type"
                   :description "Visual Control types"
                   :short-label "Visual Type"
                   :long-label  "Visual Type"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "data-type"
                                                     :pcpk-order          1
                                                     ;:pcaltk-order        1
                                                     :comp-attr-required? false ;;forces to create a label
                                                     })
                                 (k-name-attr 2)
                                 (description-attr)

                                 ]
                   }
                  ;;Entity - data-type-comparator
                  {
                   :k-name      "data-type-comparator"
                   :description "data type comparator"
                   :short-label "comparator"
                   :long-label  "data type comparator"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "data-type"
                                                     :pcpk-order          1
                                                     :pcaltk-order        1
                                                     :comp-attr-required? false ;;forces to create a label
                                                     })
                                 (k-name-attr 2)
                                 ;(name-attr 2)
                                 (name-as-key-attr {:key-type "alternate" :order 2})
                                 (description-attr)
                                 ]
                   }
                  ;;Entity - Cardinality-type
                  {
                   :k-name      "cardinality-type"
                   :description "Cardinality types"
                   :short-label "Cardinality"
                   :long-label  "Cardinality"
                   :attrs       [
                                 (k-name-attr 1)
                                 (description-attr)
                                 {
                                  :k-name        "datomic-type"
                                  :description   "Datomic Native Cardinality Type"
                                  :data-type     {:type "keyword" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}
                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Cardinality"
                                  :long-label    "Datomic Cardinality Type"
                                  }
                                 ]
                   }
                  ;;Entity - attr-gen-type
                  {
                   :k-name      "attr-gen-type"
                   :description "how this attr is generated"
                   :short-label "Gen Type"
                   :long-label  "Generate Type"
                   :attrs       [
                                 (k-name-attr 1)
                                 (description-attr)
                                 ]
                   }

                  ;;Entity - attr-Event-Type
                  {
                   :k-name "attr-event-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - entity-event-type
                  {
                   :k-name "entity-event-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - Entity-Function
                  {
                   :k-name      "entity-function"
                   :description "Constrained defined for entity"
                   :short-label "Function"
                   :long-label  "Entity Function"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "entity"
                                                     :pcpk-order   1
                                                     :pcaltk-order 0
                                                     ;:comp-attr-required? true ;;forces to create a label
                                                     })
                                 ;;k-name is required to distinguish from customer added code with nxt code.
                                 ;;new releases from nxt can override customer code, otherwise.
                                 (k-name-attr 2)
                                 {
                                  :k-name        "order"
                                  :description   "execution order"
                                  :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Order"
                                  :long-label    "Execution Order"
                                  }
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "entity-event-type"
                                                     :pcpk-order 0})
                                 (boolean-attr false "active?" "is it an active fn?" "Active?" "Active?")
                                 {
                                  :k-name        "code"
                                  :description   "code text"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Code"
                                  :long-label    "Function Code"
                                  }
                                 (tenant-owner-attr)
                                 ]
                   }


                  ;;Entity - Attr
                  {
                   :k-name      "attr"
                   :description "the attr of an entity"
                   :short-label "attr"
                   :long-label  "attr"
                   :attrs       (into [] (flatten [
                                                   (relationship-attr {:rel-type            "belongs-to"
                                                                       :rel-app             "itools"
                                                                       :rel-entity          "entity"
                                                                       :pcpk-order          1
                                                                       :pcaltk-order        0
                                                                       :comp-attr-required? false
                                                                       })
                                                   (k-name-attr 2)
                                                   (description-attr)
                                                   (relationship-attr {:rel-type   "has-one-required"
                                                                       :rel-app    "itools"
                                                                       :rel-entity "data-type"
                                                                       })
                                                   (relationship-attr {:rel-type   "has-one-required"
                                                                       :rel-app    "itools"
                                                                       :rel-entity "cardinality-type"
                                                                       })
                                                   (boolean-attr true "active?" "is it active atttribute?" "Active?" "Active?")
                                                   {
                                                    :k-name        "attr-order"
                                                    :description   "attr order"
                                                    :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                                    :cardinality   "one"
                                                    :primary-key   {:pc-primary? false :order 0}
                                                    :alternate-key {:pc-alternate? false :order 0}
                                                    :gen-type      {:type "util" :attr-linked-to nil}

                                                    :required?     true

                                                    :component?    false
                                                    :history?      true

                                                    :active?       true
                                                    :search-attr?  false

                                                    :short-label   "Order"
                                                    :long-label    "attr Order"
                                                    }
                                                   (boolean-attr true "datomic-attr?" "is it a Storage attr?" "Storage Attr?" "Storage attr?")
                                                   (boolean-attr false "required?" "is it a required attr?" "Required?" "Required?")
                                                   (boolean-attr false "component?" "is it a component level atttribute?" "Component?" "Component?")
                                                   (boolean-attr false "history?" "is history to be maintained?" "History?" "History?")
                                                   (boolean-attr false "search-attr?" "is it a searchable attr for pages?" "Search?" "Search?")
                                                   (boolean-attr true  "list-attr?" "is it listable atttribute?" "List?" "Table List attr?")
                                                   (boolean-attr false "dd-list-attr?" "is it a drop down list atttribute?" "DDList?" "DD List attr?")
                                                   (boolean-attr false "related-attr?" "can this be shown as a related attr with the pkey attrs?" "Related Attr?" "Related Attr?")
                                                   (boolean-attr false "upsert-attr?" "is it an Upsert attr?" "Upsert?" "Upsert attr?")
                                                   (boolean-attr true "change-allowed?" "the attrs if this attr can be changed?" "Change Allowed?" "Change Allowed?")

                                                   (boolean-attr false "datomic-attr-created??" "is the ident created?" "Created?" "Created?")
                                                   (relationship-attr {:rel-type   "has-one-required"
                                                                       :rel-app    "itools"
                                                                       :rel-entity "attr-gen-type"
                                                                       })
                                                   {
                                                    :k-name        "attr-name-kw"
                                                    :description   "The full name of the attr as a key word"
                                                    :data-type     {:type "keyword" :entity-related-to {:app nil :entity nil}}
                                                    :cardinality   "one"
                                                    :primary-key   {:pc-primary? false :order 0}
                                                    :alternate-key {:pc-alternate? false :order 0}
                                                    :gen-type      {:type "util" :attr-linked-to nil}

                                                    :required?     true

                                                    :component?    false
                                                    :history?      true

                                                    :active?       true
                                                    :search-attr?  false

                                                    :short-label   "Attr KW"
                                                    :long-label    "attr fullname Keyword"
                                                    }
                                                   (string-attr "default-value" "Default value of the attr" false false "Default" "Default Value")
                                                   (relationship-attr {:rel-type    "has-one-optional"
                                                                       :rel-app     "itools"
                                                                       :rel-entity  "entity"
                                                                       :attr-k-name "ref-entity-ref-to"
                                                                       :short-label "Entity Ref To"
                                                                       })
                                                   (string-attr "default-value-to" "Default value of the ref attr" false false "Default Ref to" "Default Value Ref to")
                                                   (relationship-attr {:rel-type    "has-one-optional"
                                                                       :rel-app     "itools"
                                                                       :rel-entity  "attr"
                                                                       :attr-k-name "ref-dependent-on-attr-kname"
                                                                       :short-label "Dependent on Attr"

                                                                       ;;another attr of the same entity.
                                                                       ;; ;;For example, State is dependent on Country attr value of the same entity.
                                                                       })
                                                   (tenant-owner-attr)

                                                   ]))
                   }

                  ;;Entity - attr-label
                  (label-of-entity "attr")

                  ;;Entity - attr-Function
                  {
                   :k-name      "attr-function"
                   :description "Constrained defined for attr"
                   :short-label "Function"
                   :long-label  "Attr Function"
                   :attrs       [
                                 (relationship-attr {:rel-type     "belongs-to"
                                                     :rel-app      "itools"
                                                     :rel-entity   "attr"
                                                     :pcpk-order   1
                                                     :pcaltk-order 0
                                                     ;:comp-attr-required? true ;;forces to create a label
                                                     })
                                 (k-name-attr 2)
                                 {
                                  :k-name        "order"
                                  :description   "execution order"
                                  :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Order"
                                  :long-label    "Execution Order"
                                  }
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "attr-event-type"
                                                     :pcpk-order 0})
                                 (boolean-attr false "active?" "is it an active fn?" "Active?" "Active?")
                                 (boolean-attr false "defer-to-server?" "execute only at server?" "Defer to Server?" "Defer to Server??")
                                 (boolean-attr false "reexec-at-server?" "also execute at the server." "Reexec at Server??" "Reexec at Server?")
                                 {
                                  :k-name        "code"
                                  :description   "code text"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Code"
                                  :long-label    "Function Code"
                                  }
                                 (tenant-owner-attr)
                                 ]
                   }

                  ;;Entity - attr-valid-value
                  {
                   :k-name      "attr-valid-value"
                   :description "valid values of the attr"
                   :short-label "Valid Value"
                   :long-label  "Valid Value"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "attr"
                                                     :pcpk-order          1
                                                     :pcaltk-order        0
                                                     :comp-attr-required? false
                                                     })
                                 (k-name-attr 2)
                                 {
                                  :k-name        "key-order"
                                  :description   "key order"
                                  :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}
                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  false

                                  :short-label   "Order"
                                  :long-label    "Key Order"
                                  }
                                 (boolean-attr false "active?" "is it active atttribute?" "Active?" "Active?")
                                 (description-attr)
                                 (tenant-owner-attr)
                                 ]
                   }
                  ;;Entity - attr-valid-value-dflt
                  {
                   :k-name      "attr-valid-value-dflt"
                   :description "Default valid value"
                   :short-label "Default?"
                   :long-label  "Default?"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "attr"
                                                     :pcpk-order          1
                                                     :pcaltk-order        0
                                                     :comp-attr-required? false
                                                     })
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "attr-valid-value"
                                                     :pcpk-order 0})
                                 ]
                   }
                  ;;Entity - Valid-value-text
                  {
                   :k-name      "attr-valid-value-text"
                   :description "Translatable valid value of the attr"
                   :short-label "Value Short"
                   :long-label  "Value Long"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "attr-valid-value"
                                                     :pcpk-order          1
                                                     :pcaltk-order        0
                                                     :comp-attr-required? false
                                                     })
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "language"
                                                     :pcpk-order 2})
                                 (string-attr "short-text" "short value" false true "Short Value" "Short Value")
                                 (string-attr "long-text" "long value" false true "Long Value Text" "Long Value Text")
                                 ]
                   }

                  ;;Entity - app-deployment-db
                  {
                   :k-name      "app-deployment-db"
                   :description "app deployment db"
                   :short-label "App Deployment DB"
                   :long-label  "App Deployment DB"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "tenant"
                                                     :pcpk-order          1
                                                     :pcaltk-order        0
                                                     :comp-attr-required? false
                                                     })
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "release-stage-type"
                                                     :pcpk-order 2})
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "datomic-db"
                                                     :pcpk-order 0})

                                 ]
                   }

                  ;;Entity - release-stage-type
                  {
                   :k-name      "release-stage-type"
                   :description "release stage type"
                   :short-label "Release Stage Type"
                   :long-label  "Release Stage Type"
                   :attrs       [
                                 (k-name-attr 1)
                                 (description-attr)
                                 ]
                   }

                  ;;Entity - datomic-db
                  {
                   :k-name "datomic-db"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "cf-stack"
                                                :pcpk-order          1
                                                :pcaltk-order        1
                                                :comp-attr-required? false
                                                })
                            (k-name-attr 2)
                            (boolean-attr false "stored-at-param-store?" "stored at aws param store?" "Stored at Param Store??" "Stored At Param Store?")
                            (description-attr)
                            (boolean-attr false "available?" "is it available?" "Available?" "Available?")
                            ]
                   }

                  ;;Entity - cf-stack
                  {
                   :k-name "cf-stack"
                   :attrs  [
                            (k-name-attr 1)
                            (string-attr "url" "URL" true true "Stack Url" "Stack URL")
                            (description-attr)
                            (string-attr "env" "environment like dev, prod etc" false true "Envt" "Environment")
                            ]
                   }

                  ;;Entity - menu-group
                  {
                   :k-name "menu-group"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "tenant"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (k-name-attr 2)
                            (relationship-attr {:rel-type     "has-one-required"
                                                :rel-app      "itools"
                                                :rel-entity   "application"
                                                :pcpk-order   0
                                                :pcaltk-order 0})

                            (description-attr)
                            (tenant-owner-attr)
                            ]
                   }


                  ;;Entity - menu-group-item-default
                  {
                   :k-name "menu-group-item-dflt"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "menu-group"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "menu-item"
                                                :pcpk-order 0})
                            ]
                   }


                  ;;Entity - menu-item
                  {
                   :k-name "menu-item"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "menu-group"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (k-name-attr 2)

                            (relationship-attr {:rel-type     "has-one-required"
                                                :rel-app      "itools"
                                                :rel-entity   "plet"
                                                :pcpk-order   0
                                                :pcaltk-order 0})

                            (description-attr)
                            (tenant-owner-attr)
                            ]
                   }
                  ;;Entity - chart-item
                  {
                   :k-name "chart-item"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "menu-group"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (k-name-attr 2)
                            (description-attr)
                            (string-attr "chart-function" "chart-function" false true "Function Name" "Chart Function Name")
                            (tenant-owner-attr)
                            ]
                   }
                  ;;Entity - Menu-group-label-text
                  (label-of-entity "menu-group")
                  ;;Entity - menu-item-label-text
                  (label-of-entity "menu-item")

                  (label-of-entity "chart-item")

                  ;;Entity - "label-text-type"
                  {
                   :k-name "label-text-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - "severity-type"
                  {
                   :k-name "severity-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }

                  ;;Entity - message-Text
                  {
                   :k-name      "message-text"
                   :description "Commonly used text or labels"
                   :short-label "Message Text"
                   :long-label  "Message Text"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "tenant"
                                                     :pcpk-order          1
                                                     :pcaltk-order        0
                                                     :comp-attr-required? false
                                                     })
                                 (k-name-attr 2)
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "severity-type"
                                                     :pcpk-order 0})
                                 #_(relationship-attr {:rel-type   "has-one-required"
                                                       :rel-app    "itools"
                                                       :rel-entity "language"
                                                       :pcpk-order 3})

                                 (description-attr)
                                 ;(string-attr "short-text" "short value" false true "Short Value" "Short Value")
                                 ;(string-attr "long-text" "long value" false true "Long Value Text" "Long Value Text")
                                 (tenant-owner-attr)
                                 ]
                   }
                  ;;Entity - message-Text-lang
                  {
                   :k-name "message-text-lang"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "message-text"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "language"
                                                :pcpk-order 2})
                            (string-attr "short-text" "short value" false true "Short Value" "Short Value")
                            (string-attr "long-text" "long value" false true "Long Value Text" "Long Value Text")
                            ]
                   }
                  ;;Entity - Device-type
                  {
                   :k-name "device-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - plet-type
                  {
                   :k-name "plet-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - row-col-type
                  {
                   :k-name "row-col-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - Cell-Control-type
                  {
                   :k-name "cell-control-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - pagelet-mode-type
                  {
                   :k-name "plet-mode-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - icon-type
                  {
                   :k-name "icon-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - style-context-type
                  {
                   :k-name "style-context-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - plet-cell-data-event-type
                  {
                   :k-name "plet-cell-data-event-type"
                   :attrs  [
                            (k-name-attr 1)
                            (description-attr)
                            ]
                   }
                  ;;Entity - plet
                  {
                   :k-name "plet"
                   :attrs  [
                            (relationship-attr {:rel-type            "has-one-required"
                                                :rel-app             "itools"
                                                :rel-entity          "entity"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (k-name-attr 2)
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "device-type"})
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "plet-type"})
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "plet-mode-type"})
                            (description-attr)
                            (boolean-attr false "show-breadcrumb?" "show breadcrumb on the page?" "Show Breadcrumb?" "Show Breadcrumb?")
                            (boolean-attr true "show-plet-label?" "show breadcrumb on the page?" "Show Label?" "Show Pagelet Label?")
                            (tenant-owner-attr)
                            ]
                   }

                  ;;Entity - plet-label-text
                  (label-of-entity "plet")


                  ;;Entity - plet-entity-list
                  {
                   :k-name "plet-entity-list"
                   :attrs  [
                            (relationship-attr {:rel-type            "extension-of"
                                                :rel-app             "itools"
                                                :rel-entity          "plet"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (boolean-attr false "show-number-of-rows?" "show number of rows" "No of Rows?" "Number of Rows?")
                            (boolean-attr false "show-filter?" "show the filter window" "Show Filter?" "Show Filter?")
                            (boolean-attr false "show-user-config?" "show the config button?" "Show Config?" "Show User Config?")
                            (boolean-attr false "show-row-select?" "show the number of rows to be selecter?" "Show Row Select" "Show Row Select?")
                            (boolean-attr false "allow-multi-row-select?" "multi-row select?" "Multi Row?" "Multi Row?")
                            (boolean-attr false "show-col-heading?" "show the column heading for the table" "Show Col Heading?" "Show Table Col Heading?")
                            (boolean-attr false "allow-word-wrap?" "show the column heading for the table" "Word Wrap?" "Word wrap?")
                            (boolean-attr false "allow-save-filter?" "show the save filter button?" "Allow saving filter?" "Allow save filter?")
                            (boolean-attr false "show-plet-add?" "show entity add?" "Show Add?" "Show Add?")
                            (boolean-attr false "show-plet-view?" "show entity view?" "Show View?" "Show View?")
                            {
                             :k-name        "rows-tobe-shown-dflt"
                             :description   "rows to be shown default"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Default no of rows"
                             :long-label    "Rows to be shown default"
                             }
                            {
                             :k-name        "rows-tobe-shown"
                             :description   "rows to be shown"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Rows"
                             :long-label    "Rows to be shown"
                             }
                            (relationship-attr {:rel-type    "has-one-required"
                                                :rel-app     "itools"
                                                :rel-entity  "plet"
                                                :attr-k-name "ref-plet-add"
                                                :short-label "Add Plet"})
                            (relationship-attr {:rel-type    "has-one-required"
                                                :rel-app     "itools"
                                                :rel-entity  "plet"
                                                :attr-k-name "ref-plet-view"
                                                :short-label "View Plet"})
                            ]
                   }
                  ;;Entity - plet-thumbnail
                  {
                   :k-name "plet-thumbnail"
                   :attrs  [
                            (relationship-attr {:rel-type            "extension-of"
                                                :rel-app             "itools"
                                                :rel-entity          "plet"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (boolean-attr false "show-plet-add?" "show entity add?" "Show Add?" "Show Add?")
                            (boolean-attr false "show-plet-view?" "show entity view?" "Show View?" "Show View?")
                            (relationship-attr {:rel-type    "has-one-required"
                                                :rel-app     "itools"
                                                :rel-entity  "plet"
                                                :attr-k-name "ref-plet-add"
                                                :short-label "Add Plet"})
                            (relationship-attr {:rel-type    "has-one-required"
                                                :rel-app     "itools"
                                                :rel-entity  "plet"
                                                :attr-k-name "ref-plet-view"
                                                :short-label "View Plet"})
                            ]
                   }
                  ;;Entity - plet-next
                  {
                   :k-name "plet-next"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type    "has-one-required"
                                                :rel-app     "itools"
                                                :rel-entity  "plet"
                                                :attr-k-name "ref-plet-next"
                                                :pcpk-order  2})

                            {
                             :k-name        "order"
                             :description   "plet order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}
                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Plet Order"
                             :long-label    "Plet Order"
                             }
                            ]
                   }
                  ;;Entity - plet-children
                  {
                   :k-name "plet-children"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type    "has-one-required"
                                                :rel-app     "itools"
                                                :rel-entity  "plet"
                                                :attr-k-name "ref-plet-child"
                                                :pcpk-order  2})

                            {
                             :k-name        "order"
                             :description   "plet order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}
                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Plet Order"
                             :long-label    "Plet Order"
                             }
                            ]
                   }
                  ;;Entity - plet-entity-list-data
                  {
                   :k-name "plet-entity-list-data"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet-entity-list"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "attr"
                                                :pcpk-order 2})
                            {
                             :k-name        "col-order-dflt"
                             :description   "default-col order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}
                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Dflt Col Order"
                             :long-label    "Default Col Order"
                             }
                            {
                             :k-name        "col-order"
                             :description   "default-col order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}
                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Col Order"
                             :long-label    "Col Order"
                             }
                            (boolean-attr false "hidden?" "Always Hidden attr??" "Hidden?" "Hidden?")
                            (boolean-attr false "sortable?" "Sortable??" "Sortable?" "Sortable?")
                            (boolean-attr true "show-label?" "show label??" "Show Label?" "Show?")
                            (boolean-attr false "show-related-attr?" "Related attr??" "Show Rel Attr?" "Related?")
                            (boolean-attr false "listable?" "Is this listable?" "Listable?" "Listable?")
                            (boolean-attr false "list-by-dflt?" "Listed by default" "Dflt List?" "Dflt List?")
                            (boolean-attr false "link-to-entity-view?" "Link to entity-view" "Link to View" "Link to View")
                            ]
                   }
                  ;;Entity - plet-thumbnail-data
                  {:k-name "plet-thumbnail-data"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet-thumbnail"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "attr"
                                                :pcpk-order 2})
                            {
                             :k-name        "col-order-dflt"
                             :description   "default-col order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}
                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Dflt Col Order"
                             :long-label    "Default Col Order"
                             }
                            {
                             :k-name        "col-order"
                             :description   "default-col order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}
                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Col Order"
                             :long-label    "Col Order"
                             }
                            (boolean-attr false "hidden?" "Always Hidden attr??" "Hidden?" "Hidden?")
                            ;(boolean-attr false "sortable?" "Sortable??" "Sortable?" "Sortable?")
                            (boolean-attr false "show-label?" "show label??" "Show Label?" "Show?")
                            ;(boolean-attr false "show-related-attr?" "Related attr??" "Show Rel Attr?" "Related?")
                            ;(boolean-attr false "listable?" "Is this listable?" "Listable?" "Listable?")
                            ;(boolean-attr false "list-by-dflt?" "Listed by default" "Dflt List?" "Dflt List?")
                            (boolean-attr false "link-to-entity-view?" "Link to entity-view" "Link to View" "Link to View")
                            ]
                   }
                  ;;Entity - plet-entity-list-action
                  {
                   :k-name "plet-entity-list-action"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet-entity-list"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (k-name-attr 2)
                            {
                             :k-name        "action-order"
                             :description   "action order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Order"
                             :long-label    "Action Order"
                             }
                            (description-attr)
                            (string-attr "div-type" "div type" false true "Div Type" "Div Type")
                            (string-attr "div-class" "div class" false true "Div Class" "Div Class")
                            (string-attr "fname" "action function" false true "Function name" "Function Name")
                            (string-attr "ns" "function namespace" false true "Namespace" "Function Namespace")
                            (string-attr "input-map" "function input map" false true "Input" "Function Input Map")
                            (string-attr "output-map" "function output map" false true "Output" "Function Output")
                            (boolean-attr false "show-on-bar?" "Show on the bar?" "Show on the bar?" "Show on the bar?")
                            (boolean-attr false "list-action?" "Part of the drop down list" "List?" "List Action?")
                            ]
                   }
                  ;;Entity - entity-list-pagelet-action-label
                  (label-of-entity "plet-entity-list-action")

                  ;;Entity - Entity-list-filter
                  {
                   :k-name "entity-list-filter"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet-entity-list"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "user"
                                                :pcpk-order 2})
                            ;(name-attr 1)
                            (name-as-key-attr {:key-type "alternate" :order 1})
                            (description-attr)
                            (string-attr "filter-string" "filter-string" false true "Filter" "Filter")
                            ]
                   }



                  ;;Entity - plet-entity-thumb-link-list
                  {
                   :k-name "plet-thumb-link-list"
                   :attrs  [
                            (relationship-attr {:rel-type            "extension-of"
                                                :rel-app             "itools"
                                                :rel-entity          "plet"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            ;(boolean-attr false  "show-number-of-rows?" "show number of rows" "No of Rows?" "Number of Rows?")

                            {
                             :k-name        "rows-tobe-shown-dflt"
                             :description   "rows to be shown default"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? true :order 2}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Default no of rows"
                             :long-label    "Rows to be shown default"
                             }
                            {
                             :k-name        "rows-tobe-shown"
                             :description   "rows to be shown"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? true :order 2}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Rows"
                             :long-label    "Rows to be shown"
                             }
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "row-col-type"})
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "attr"
                                                :pcpk-order 0})

                            (boolean-attr true "control-attr?" "is it a control attribute?" "Control Attr?" "Control?")
                            (boolean-attr true "hidden?" "Hidden?" "Hidden?" "Hidden?")
                            (relationship-attr {:rel-type    "has-one-required"
                                                :rel-app     "itools"
                                                :rel-entity  "attr"
                                                :attr-k-name "ref-attr-related"
                                                :pcpk-order  0})
                            (boolean-attr false "hidden-related?" "Hidden?" "Hidden Related?" "Hidden Related?")

                            ]
                   }

                  ;;Entity - "plet-thumb-link-list-with-children"
                  {
                   :k-name "plet-thumb-link-child"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet-thumb-link-list"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "attr"
                                                :pcpk-order 2})
                            (boolean-attr true "control-attr?" "is it a control attribute?" "Control Attr?" "Control?")
                            (boolean-attr true "hidden?" "Hidden?" "Hidden?" "Hidden?")
                            (relationship-attr {:rel-type    "has-one-required"
                                                :rel-app     "itools"
                                                :rel-entity  "attr"
                                                :attr-k-name "ref-attr-related"
                                                :pcpk-order  0})
                            (boolean-attr false "hidden-related?" "Hidden?" "Hidden Related?" "Hidden Related?")

                            ]
                   }

                  ;;Entity - "visited-service"
                  {
                   :k-name "visited-service"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "user"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "menu-item"
                                                :pcpk-order 2})
                            {:k-name        "visit-date-time"
                             :description   "visited date and time"
                             :data-type     {:type "date-time" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"

                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      false

                             :active?       true
                             :search-attr?  true

                             :short-label   "Visit Time"
                             :long-label    "Visit time"
                             }

                            ]
                   }

                  ;;Entity - plet-entity-detail
                  {
                   :k-name "plet-entity-detail"
                   :attrs  [
                            (relationship-attr {:rel-type            "extension-of"
                                                :rel-app             "itools"
                                                :rel-entity          "plet"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "row-col-type"})
                            (boolean-attr true "show-plet-edit?" "show entity edit?" "Allow Edit?" "Allow Edit?")
                            (boolean-attr false "label-on-side?" "Label on the side of the controls?" "Side Label?" "Label on side?")
                            (boolean-attr true "show-required-attr-msg?" "Show required attribute message?" "Show required message?" "Show required message?")
                            (relationship-attr {:rel-type    "has-one-optional"
                                                :rel-app     "itools"
                                                :rel-entity  "plet"
                                                :attr-k-name "ref-plet-edit"
                                                :short-label "Edit Plet"})
                            (relationship-attr {:rel-type    "has-one-optional"
                                                :rel-app     "itools"
                                                :rel-entity  "plet"
                                                :attr-k-name "ref-plet-view"
                                                :short-label "View Plet"})
                            ]
                   }
                  ;;Entity - plet-cell
                  {
                   :k-name "plet-cell"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet-entity-detail"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            {
                             :k-name        "cell-order"
                             :description   "cell order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? true :order 2}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Cell Order"
                             :long-label    "Cell Order"
                             }
                            {
                             :k-name        "row-order"
                             :description   "cell order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     false

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Row Order"
                             :long-label    "Row Order"
                             }
                            (boolean-attr false "full-row?" "Full Row" "Full Row?" "Full Row?")
                            (relationship-attr {:rel-type    "has-one-required"
                                                :rel-app     "itools"
                                                :rel-entity  "cell-control-type"
                                                :short-label "Cell Control Type"})
                            ;;This following attribute is relevant in the case of Tabs.
                            ;;Stating you are placing this cell when the plet is of type "list" or "detail", and so on.
                            ;;Sinc only one tab is allowed, the system will pick the right tab, based on the pklet type.
                            (string-attr "polymorphic-type-kname" "Type KName" false false "Polymorphic Type Kname" "Type KName")
                            {
                             :k-name        "active-tab-order"
                             :description   "active tab order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Active Tab Order"
                             :long-label    "Active Tab Order"
                             }
                            (relationship-attr {:rel-type    "has-one-optional"
                                                :rel-app     "itools"
                                                :rel-entity  "plet"
                                                :short-label "Insert Plet"
                                                ;:attr-k-name "ref-entity-ref-to"
                                                })

                            ]
                   }
                  ;;Entity - plet-cell-data
                  {
                   :k-name "plet-cell-data"
                   :attrs  [
                            (relationship-attr {:rel-type            "extension-of"
                                                :rel-app             "itools"
                                                :rel-entity          "plet-cell"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "attr"
                                                :pcpk-order 0})
                            (relationship-attr {:rel-type   "has-one-optional"
                                                :rel-app    "itools"
                                                :rel-entity "visual-type"
                                                :pcpk-order 0})
                            {
                             :k-name        "setup-map"
                             :description   "Setup Map"
                             :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? false :order 0}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     false

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Setup Map"
                             :long-label    "Setup Map"
                             }
                            (boolean-attr false "active?" "active by the User?" "Active?" "Active?")

                            (boolean-attr false "focus?" "Focus?" "Focus?" "Focus?")
                            (boolean-attr false "hidden?" "Always Hidden attr??" "Hidden?" "Hidden?")

                            (boolean-attr true "show-label?" "show label??" "Show Label?" "Show?")
                            (boolean-attr false "display-only?" "Display Only??" "Display Only?" "Display Only?")
                            (boolean-attr false "show-related-attr?" "Related attr??" "Show Rel Attr?" "Related?")
                            (string-attr "element-style" "element style" false false "Element Style" "Element Style")
                            (string-attr "label-div-style" "label style" false false "Label Style" "Label Style")
                            (boolean-attr false "polymorphic-type-attr?" "Polymorphic attr?" "Polymorphic Type Attr?" "Polymorphic Type Attr?")
                            (boolean-attr false "visited?" "Visited by the User?" "Visited?" "Visited?")
                            ;(boolean-attr false  "dirty?"   "Dirty by the User?" "Dirty?" "Dirty?")
                            ]
                   }

                  ;;Entity - plet-cell-data-function
                  {
                   :k-name "plet-cell-data-function"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet-cell-data"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })

                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "plet-cell-data-event-type"
                                                :pcpk-order 2})
                            (boolean-attr false "active?" "Always Hidden attr??" "Hidden?" "Hidden?")
                            (string-attr "code" "element style" false true "Element Style" "Element Style")
                            (tenant-owner-attr)
                            ]
                   }
                  ;;Entity - plet-cell-button
                  {
                   :k-name "plet-cell-button"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet-cell"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            {
                             :k-name        "button-order"
                             :description   "button order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? true :order 2}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Button Order"
                             :long-label    "Button Order"
                             }
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "style-context-type"
                                                :pcpk-order 0})
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "icon-type"
                                                :pcpk-order 0})
                            (string-attr "button-type" "Button type" false true "Button Type" "Button Type")
                            ]
                   }
                  ;;Entity - plet-cell-button-label-text
                  (label-of-entity "plet-cell-button")
                  ;;Entity - plet-cell-tab
                  {
                   :k-name "plet-cell-tab"
                   :attrs  [
                            (relationship-attr {:rel-type            "belongs-to"
                                                :rel-app             "itools"
                                                :rel-entity          "plet-cell"
                                                :pcpk-order          1
                                                :pcaltk-order        0
                                                :comp-attr-required? false
                                                })
                            {
                             :k-name        "tab-order"
                             :description   "tab order"
                             :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                             :cardinality   "one"
                             :primary-key   {:pc-primary? true :order 2}
                             :alternate-key {:pc-alternate? false :order 0}

                             :gen-type      {:type "util" :attr-linked-to nil}

                             :required?     true

                             :component?    false
                             :history?      true

                             :active?       true
                             :search-attr?  false

                             :short-label   "Order"
                             :long-label    "Cell Order"
                             }
                            (relationship-attr {:rel-type   "has-one-required"
                                                :rel-app    "itools"
                                                :rel-entity "plet"
                                                :pcpk-order 0})
                            ;(boolean-attr false  "active?" "active?" "active tab?" "Active?")
                            ]
                   }
                  ;;Entity - plet-cell-tab-label-text
                  (label-of-entity "plet-cell-tab")




                  ;;Entity - country
                  {
                   :k-name      "country"
                   :short-label "Country"
                   :attrs       [
                                 {:k-name        "code"
                                  :description   "Country name code"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Country"
                                  :long-label    "Country Code"
                                  }
                                 {:k-name        "name"
                                  :description   "Country name "
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 1}
                                  :alternate-key {:pc-alternate? true :order 1}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Name"
                                  :long-label    "Country Name"
                                  }
                                 (description-attr)

                                 ]
                   }
                  ;;Entity - state
                  {
                   :k-name      "state"
                   :short-label "State"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "country"
                                                     :pcpk-order          1 ;;default anyway
                                                     :pcaltk-order        1
                                                     :comp-attr-required? false ;;making it mandatory while creating employee
                                                     })
                                 {:k-name        "code"
                                  :description   "state name code"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "State"
                                  :long-label    "State"
                                  }
                                 {:k-name        "name"
                                  :description   "State name "
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? true :order 2}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Name"
                                  :long-label    "State Name"
                                  }
                                 ]
                   }

                  ;;Entity - Currency
                  {
                   :k-name      "currency"
                   :short-label "Currency"
                   :attrs       [
                                 {:k-name        "code"
                                  :description   "state name code"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Code"
                                  :long-label    "Code"
                                  }
                                 {:k-name        "name"
                                  :description   "Currncy name "
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? true :order 1}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Name"
                                  :long-label    "Currency Name"
                                  }
                                 {:k-name        "symbol"
                                  :description   "symbol "
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Symbol"
                                  :long-label    "Currency Symbol"
                                  }
                                 (boolean-attr false "before-number?" "Before Number?" "Before Number?" "Before Number?")
                                 ]
                   }

                  ;;Entity - Tenant-Location
                  {
                   :k-name      "tenant-location"
                   :short-label "Location"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "tenant"
                                                     :pcpk-order          1
                                                     :pcaltk-order        0
                                                     :comp-attr-required? false
                                                     })
                                 {:k-name        "location-name"
                                  :description   "name"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Name"
                                  :long-label    "Name"
                                  }
                                 (description-attr)
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "language"})
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "currency"})

                                 ]
                   }
                  ;;Entity - corporate-Location
                  {
                   :k-name      "corporate-location"
                   :short-label "Corporate?"
                   :attrs       [
                                 (relationship-attr {:rel-type            "extension-of"
                                                     :rel-app             "itools"
                                                     :rel-entity          "tenant"
                                                     :pcpk-order          1
                                                     :pcaltk-order        0
                                                     :comp-attr-required? false
                                                     })
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "tenant-location"})

                                 ]
                   }
                  ;;Entity - Tenant-address
                  {
                   :k-name      "tenant-address"
                   :short-label "Address"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "tenant-location"
                                                     :pcpk-order          1
                                                     :pcaltk-order        0
                                                     :comp-attr-required? false
                                                     })
                                 {:k-name        "line1"
                                  :description   "line1"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Line1"
                                  :long-label    "Line1"
                                  }
                                 (string-attr "line2" "Line 2" false false "Line 2" "Line 2")
                                 (string-attr "city" "City" true true "City" "City")
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "country"})
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "state"})
                                 (string-attr "zip" "Zip" true true "Zip" "Zip")

                                 ]
                   }
                  ;;part

                  ;;Entity - Part
                  {
                   :k-name      "part"
                   :short-label "Part"
                   :attrs       [
                                 {
                                  :k-name        "part-id"
                                  :description   "part id"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? true :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}
                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "PartID"
                                  :long-label    "PartID"
                                  }
                                 (name-as-key-attr {:key-type "alternate" :order 1})
                                 (description-attr)


                                 (relationship-attr {:rel-type   "has-one-optional"
                                                     :rel-app    "itools"
                                                     :rel-entity "part-image"})
                                 ]
                   }
                  ;;Entity - Part-Image
                  {
                   :k-name      "part-image"
                   :short-label "Image"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "part"
                                                     :pcpk-order          1 ;;default anyway
                                                     ;:pcaltk-order        0
                                                     :comp-attr-required? false ;;making it mandatory while creating employee
                                                     })
                                 (name-as-key-attr {:key-type "primary" :order 2})
                                 {:k-name        "file-key"
                                  :description   "file-key"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     false      ;;--

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "File Key"
                                  :long-label    "File Key"
                                  }
                                 ]
                   }






                  ;;;;;; Employee Management System Entities

                  ;;Entity - Employee
                  {
                   :k-name      "employee"
                   :short-label "Employee"
                   :attrs       [
                                 {
                                  :k-name        "emplid"
                                  :description   "emplid"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? true :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}
                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Emplid"
                                  :long-label    "Emplid"
                                  }
                                 ;date-attr [attr-kname attr-descr search? required? short-label long-label]
                                 (string-attr "first-name" "first name" true true "First Name" "First Name")
                                 (string-attr "last-name" "last name" true true "Last Name" "Last Name")
                                 (date-attr "date-of-birth" "date of birth" true true "Date of Birth" "Date of Birth")
                                 (string-attr "ssn" "ssn name" true true "SSN" "SSN")

                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "gender-type"
                                                     :pcpk-order 0})
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "employee-type"
                                                     :pcpk-order 0})
                                 (boolean-attr false "full-time?" "Fulltme employee?" "Fulltime??" "Fulltime??")
                                 {:k-name        "email"
                                  :description   "Email"
                                  :data-type     {:type "email" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Email"
                                  :long-label    "Email"
                                  }
                                 {:k-name        "password"
                                  :description   "password"
                                  :data-type     {:type "password" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Password"
                                  :long-label    "Password"
                                  }
                                 (string-attr "comment" "comment" false false "Comment" "Comment")
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "job"
                                                     :pcpk-order 0})
                                 ]
                   }
                  ;;Entity - employee-type
                  {
                   :k-name      "employee-type"
                   :short-label "Employee Type"
                   :attrs       [
                                 {:k-name        "name"
                                  :description   "Employee Type"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Type"
                                  :long-label    "Employee Type"
                                  }
                                 (description-attr)
                                 ]
                   }
                  (label-of-entity "employee-type")
                  ;;Entity - gender-type
                  {
                   :k-name      "gender-type"
                   :short-label "Employee Gender"
                   :attrs       [
                                 {:k-name        "name"
                                  :description   "Gender name"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Gender"
                                  :long-label    "Gender"
                                  }
                                 (description-attr)
                                 ]
                   }
                  (label-of-entity "gender-type")
                  ;;Entity - employee address  ;;only one address per employee
                  {
                   :k-name      "employee-address"
                   :short-label "Employee Address"
                   :attrs       [
                                 (relationship-attr {:rel-type            "extension-of" ;;"extension-of"
                                                     :rel-app             "itools"
                                                     :rel-entity          "employee"
                                                     ;:pcpk-order          1  ;;default anyway
                                                     ;:pcaltk-order        0
                                                     :comp-attr-required? true ;;making it mandatory while creating employee
                                                     })
                                 (string-attr "line1" "address line 1" true true "Line 1" "Line 1 Address")
                                 (string-attr "line2" "address line 2" false false "Line 2" "Line 2 Adress")
                                 (relationship-attr {:rel-type   "has-one-required"
                                                     :rel-app    "itools"
                                                     :rel-entity "country"
                                                     :pcpk-order 0})
                                 (relationship-attr {:rel-type                "has-one-required"
                                                     :rel-app                 "itools"
                                                     :rel-entity              "state"
                                                     :pcpk-order              0
                                                     :dependent-on-attr-kname "ref-country"})
                                 (string-attr "city" "City" true true "City" "City")
                                 (string-attr "zip" "address zip 1" true true "Zip" "Zip")
                                 ]
                   }
                  ;;Entity - image
                  {
                   :k-name      "image"
                   :short-label "Employee Image"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "employee"
                                                     :pcpk-order          1 ;;default anyway
                                                     ;:pcaltk-order        0
                                                     :comp-attr-required? false ;;making it mandatory while creating employee
                                                     })
                                 {
                                  :k-name        "image-name"
                                  :description   "a descriptive name of image"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}
                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true


                                  :short-label   "Name"
                                  :long-label    "Image Name"

                                  }
                                 (string-attr "url" "url 2" false false "Url" "Url")
                                 ]
                   }
                  ;;Entity - doc
                  {
                   :k-name      "doc"
                   :short-label "Employee Doc"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "employee"
                                                     :pcpk-order          1 ;;default anyway
                                                     ;:pcaltk-order        0
                                                     :comp-attr-required? false ;;making it mandatory while creating employee
                                                     })
                                 {
                                  :k-name        "doc-name"
                                  :description   "a descriptive name of doc"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}
                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true


                                  :short-label   "Name"
                                  :long-label    "Doc Name"
                                  }
                                 (string-attr "url" "url 2" false false "Url" "Url")
                                 ]
                   }

                  ;;Entity - employee dependents
                  {
                   :k-name      "dependent"
                   :short-label "Employee dependent"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "employee"
                                                     :pcpk-order          1 ;;default anyway
                                                     ;:pcaltk-order        0
                                                     :comp-attr-required? false ;;making it mandatory while creating employee
                                                     })
                                 {:k-name        "first-name"
                                  :description   "first name"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "First Name"
                                  :long-label    "First Name"
                                  }
                                 {:k-name        "last-name"
                                  :description   "last name"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 3}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Last Name"
                                  :long-label    "Last Name"
                                  }
                                 {:k-name        "date-of-birth"
                                  :description   "date of birth"
                                  :data-type     {:type "date-time" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 4}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "DOB"
                                  :long-label    "Date of Birth"
                                  }
                                 {:k-name        "date-added"
                                  :description   "date added"
                                  :data-type     {:type "date-time" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 0}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Date Added"
                                  :long-label    "Date Added"
                                  }
                                 ]
                   }


                  ;;Entity - business-unit
                  {
                   :k-name      "business-unit"
                   :short-label "Business Unit"
                   :attrs       [
                                 {
                                  :k-name        "business-unit-name"
                                  :description   "a descriptive name of unit"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"
                                  :primary-key   {:pc-primary? true :order 1}
                                  :alternate-key {:pc-alternate? false :order 0}
                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      true

                                  :active?       true
                                  :search-attr?  true


                                  :short-label   "Unit"
                                  :long-label    "Business Unit"
                                  }
                                 (description-attr)
                                 ]
                   }
                  ;;Entity - BMI
                  #_{
                     :k-name      "bmi-calc"
                     :short-label "BMI Calculator"
                     :attrs       [
                                   {
                                    :k-name        "height"
                                    :description   "Height"
                                    :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                    :cardinality   "one"
                                    :primary-key   {:pc-primary? false :order 0}
                                    :alternate-key {:pc-alternate? false :order 0}
                                    :gen-type      {:type "util" :attr-linked-to nil}

                                    :required?     true

                                    :component?    false
                                    :history?      true

                                    :active?       true
                                    :search-attr?  true


                                    :short-label   "Height"
                                    :long-label    "Height"
                                    }

                                   {
                                    :k-name        "weight"
                                    :description   "Weight"
                                    :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                    :cardinality   "one"
                                    :primary-key   {:pc-primary? false :order 0}
                                    :alternate-key {:pc-alternate? false :order 0}
                                    :gen-type      {:type "util" :attr-linked-to nil}

                                    :required?     true

                                    :component?    false
                                    :history?      true

                                    :active?       true
                                    :search-attr?  true


                                    :short-label   "Weight"
                                    :long-label    "Weight"
                                    }
                                   {
                                    :k-name        "bmi"
                                    :description   "BMI"
                                    :data-type     {:type "number" :entity-related-to {:app nil :entity nil}}
                                    :cardinality   "one"
                                    :primary-key   {:pc-primary? false :order 0}
                                    :alternate-key {:pc-alternate? false :order 0}
                                    :gen-type      {:type "util" :attr-linked-to nil}

                                    :required?     true

                                    :component?    false
                                    :history?      true

                                    :active?       true
                                    :search-attr?  true


                                    :short-label   "BMI"
                                    :long-label    "BMI"
                                    }
                                   ]
                     }
                  ;;Entity - job
                  {
                   :k-name      "job"
                   :short-label "Job"
                   :attrs       [
                                 (relationship-attr {:rel-type            "belongs-to"
                                                     :rel-app             "itools"
                                                     :rel-entity          "business-unit"
                                                     :pcpk-order          1 ;;default anyway
                                                     ;:pcaltk-order        0
                                                     :comp-attr-required? false ;;making it mandatory while creating employee
                                                     })
                                 {:k-name        "job-code"
                                  :description   "job name code"
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? true :order 2}
                                  :alternate-key {:pc-alternate? false :order 0}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true

                                  :short-label   "Job Code"
                                  :long-label    "Job Code"
                                  }
                                 {:k-name        "title"
                                  :description   "Job Name "
                                  :data-type     {:type "string" :entity-related-to {:app nil :entity nil}}
                                  :cardinality   "one"

                                  :primary-key   {:pc-primary? false :order 1}
                                  :alternate-key {:pc-alternate? false :order 1}

                                  :gen-type      {:type "util" :attr-linked-to nil}

                                  :required?     true

                                  :component?    false
                                  :history?      false

                                  :active?       true
                                  :search-attr?  true
                                  :related-attr? true

                                  :short-label   "Title"
                                  :long-label    "Job Title"
                                  }
                                 (description-attr)
                                 ]
                   }

                  ]
       }
      )
    )
  )

(defn tools-erd []
  (let [nxt-tenant "nxt"
        nxt-app    "itools"]
    (identity
      ;;Beginning of ERD
      {
       :tenant   nxt-tenant
       :app      nxt-app
       :entities (into [] (nthrest (specter/select [specter/ALL] (into [] (flatten (:entities (tools-erd-model))))) 0))

       ;:entities  [(nth (specter/select [ALL ] (:entities (tools-erd-model))))]

       ;:entities (into []  (:entities (tools-erd-model)))

       })))

(comment
  (tools-erd)
  )

