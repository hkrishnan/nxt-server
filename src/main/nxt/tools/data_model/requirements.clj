(ns nxt.tools.data-model.requirements
  (:require [nxt.tools.data-model.relationships :as rel]
            [clojure.spec.alpha :as s]))

(defn create-tenant-entity
  "Creates tenant entity specification"
  []
  {:description "A customer in the multi-tenant system"
   :abstract? false
   :audited? true
   :keep-history? false
   :keys {:upsert {:name :uuid-tenant
                   :type :uuid}
          :primary [:k-name]
          :alternate [[:name] 
                     [:tenant-id]]}
   :attributes {:k-name {:type :string
                        :required? true
                        :unique? true
                        :searchable? true
                        :description "Kebab-case identifier"}
               :name {:type :string
                     :required? true
                     :unique? true}
               :tenant-id {:type :string
                          :required? true
                          :unique? true}
               :web-url {:type :string
                        :required? false}
               :description {:type :string
                           :required? false
                           :searchable? true}
               :active? {:type :boolean
                        :required? true
                        :default? true}}})

(defn create-user-entity
  "Creates user entity specification"
  []
  {:description "User belonging to a tenant"
   :abstract? false
   :audited? true
   :keep-history? false
   :keys {:upsert {:name :uuid-user
                   :type :uuid}
          :primary [:ref-tenant :email]}
   :attributes {:email {:type :string
                       :required? true}
                :first-name {:type :string
                           :required? true
                           :searchable? true}
                :last-name {:type :string
                          :required? true
                          :searchable? true}
                :middle-name {:type :string
                            :required? false
                            :searchable? true}
                :position {:type :string
                         :required? false}
                :phone1 {:type :string
                       :required? false}
                :phone2 {:type :string
                       :required? false}
                :fax {:type :string
                     :required? false}
                :password-hash {:type :string
                              :required? true
                              :encrypted? true}
                :created-at {:type :instant
                           :required? true
                           :auto-generated? true}}})

(defn create-tenant-user-relationship
  "Creates the relationship between tenant and user"
  []
  (rel/create-optional-comp-belongs-to :tenant :user))

(defn create-email-uniqueness-rule
  "Creates tenant-scoped email uniqueness rule"
  []
  {:name "User email tenant scoping"
   :type :unique-constraint
   :enforced? true
   :scope :tenant
   :attributes [:email]
   :description "Email must be unique within a tenant"})

(defn requirements-to-data
  "Converts requirements to data structure"
  [tenant-kname owner-kname app-kname]
  (let [tenant-user-rel (create-tenant-user-relationship)]
    {:context {:tenant-kname tenant-kname
              :owner-kname owner-kname  
              :app-kname app-kname
              :production? false}
     
     :entities {:tenant (create-tenant-entity)
                :user (create-user-entity)}
     
     :relationships [tenant-user-rel]
     
     :business-rules [(create-email-uniqueness-rule)]}))