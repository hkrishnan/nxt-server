(ns nxt.tools.data-model.relationships
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]))

;; Specs for validation
(s/def ::entity-name keyword?)
(s/def ::kebab-case? 
  (fn [k] 
    (and (keyword? k)
         (let [n (name k)]
           (and (= n (str/lower-case n))
                (not (str/includes? n "_"))
                (or (not (str/includes? n "-"))
                    (not (re-find #"-{2,}" n))))))))

(s/def ::valid-entity
  (s/and ::entity-name ::kebab-case?))

(s/def ::relationship-type 
  #{:optional-comp-belongs-to
    :required-comp-belongs-to
    :required-comp-extension-of
    :non-required-comp-extension-of
    :optional-has-one-required
    :optional-has-one-optional})

;; Validation functions
(defn validate-entity-names! 
  [major-entity minor-entity]
  (when-not (s/valid? ::valid-entity major-entity)
    (throw (ex-info "Invalid major entity name - must be kebab-case keyword" 
                   {:major-entity major-entity
                    :spec-explain (s/explain-str ::valid-entity major-entity)})))
  (when-not (s/valid? ::valid-entity minor-entity)
    (throw (ex-info "Invalid minor entity name - must be kebab-case keyword"
                   {:minor-entity minor-entity
                    :spec-explain (s/explain-str ::valid-entity minor-entity)})))
  (when (= major-entity minor-entity)
    (throw (ex-info "Major and minor entities must be different"
                   {:major-entity major-entity
                    :minor-entity minor-entity}))))

;; Core relationship functions
(defn create-optional-comp-belongs-to
  "Creates optional component belongs-to relationship.
   Example: A may-have-many Bs, B belongs-to A"
  [major-entity minor-entity]
  (validate-entity-names! major-entity minor-entity)
  {:type :optional-comp-belongs-to
   :major-entity major-entity
   :minor-entity minor-entity
   :description (str minor-entity " belongs to " major-entity)
   :relationship-attrs {major-entity {:name (str "has-many-" (name minor-entity) "s")
                                    :required? false
                                    :component? true}
                       minor-entity {:name (str "ref-" (name major-entity))
                                   :required? true
                                   :part-of-pk? true}}})

(defn create-required-comp-belongs-to
  "Creates required component belongs-to relationship.
   Example: A will-have-one-or-many Bs, B belongs-to A"
  [major-entity minor-entity]
  (validate-entity-names! major-entity minor-entity)
  {:type :required-comp-belongs-to
   :major-entity major-entity
   :minor-entity minor-entity
   :description (str minor-entity " belongs to " major-entity)
   :relationship-attrs {major-entity {:name (str "has-many-" (name minor-entity) "s")
                                    :required? true
                                    :component? true}
                       minor-entity {:name (str "ref-" (name major-entity))
                                   :required? true
                                   :part-of-pk? true}}})

(defn create-required-comp-extension-of
  "Creates required component extension relationship.
   Example: A will-have-one B, B is extension of A"
  [major-entity minor-entity]
  (validate-entity-names! major-entity minor-entity)
  {:type :required-comp-extension-of
   :major-entity major-entity
   :minor-entity minor-entity
   :description (str minor-entity " is extension of " major-entity)
   :relationship-attrs {major-entity {:name (str "has-one-" (name minor-entity))
                                    :required? true
                                    :component? true}
                       minor-entity {:name (str "ref-" (name major-entity))
                                   :required? true
                                   :is-pk? true}}})

(defn create-relationship
  "Creates relationship of specified type between entities"
  [relationship-type major-entity minor-entity]
  (when-not (s/valid? ::relationship-type relationship-type)
    (throw (ex-info "Invalid relationship type"
                   {:type relationship-type
                    :valid-types (s/describe ::relationship-type)})))
  (case relationship-type
    :optional-comp-belongs-to (create-optional-comp-belongs-to major-entity minor-entity)
    :required-comp-belongs-to (create-required-comp-belongs-to major-entity minor-entity)
    :required-comp-extension-of (create-required-comp-extension-of major-entity minor-entity)
    ;; Add other relationship types as needed
    ))