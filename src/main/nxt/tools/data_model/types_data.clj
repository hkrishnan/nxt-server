(ns nxt.tools.data-model.types-data
(:require [com.rpl.specter :as specter]
            [nxt.tools.util.basic :as basic-util]))

(defn squuid [] (basic-util/squuid))

(def tenant-nxt
  {:k-name "nxt",
   :name "nxt",
   :web-url "www.invignow.com",
   :description "InvigNow Tenant",
   :uuid-tenant #uuid"5e98f5de-74d8-49db-bd6a-c502a0ad36b6",
   }
  )
(def application-nxt-tools
  {
   :k-name "nxt-tools",
   :name "nxt tools",
   :ref-tenant-owner "nxt"
   :description "nxt tools application",
   :uuid-application #uuid"5e98f5ef-8815-42d9-8b7a-6292b8f44384",
   }
  )

(def aws-cf-stacks [{:uuid        #uuid"5e9e3813-fbeb-4ef7-941e-0dd3cb6b666a",
                     :k-name      "nxt-server-dev5",
                     :name        "nxt-server-dev5",
                     :url         "",
                     :env         "dev",
                     :description "nxt dev cloud formation"
                     :datomic-db  [{:uuid                   #uuid"5e9e77cd-a4f5-41b7-850e-ea59459ed7da",
                                    :k-name                 "nxt-dbxy-dev-tools",
                                    :name                   "nxt-dbxy-dev-tools",
                                    :stored-at-param-store? true,
                                    :description            "dev tools database",
                                    :available?             true}]
                     }])

(def core-entity-map {:tenant-k-name       "nxt"
                      :tenant-name         "InvigNow"
                      :application-k-name  "nxt-tools"
                      :application-name    "nxt tools"
                      :language-code       "en"
                      :language-name       "English"
                      :uuid-tenant         #uuid"5e98f5de-74d8-49db-bd6a-c502a0ad36b6"
                      :uuid-application    #uuid"5e98f5ef-8815-42d9-8b7a-6292b8f44384"
                      :uuid-subscribed-app #uuid"5e98f5fa-9604-417a-9e13-54c5865aebfb"
                      :uuid-language       #uuid"5e9911a0-8ae3-48b7-a4bc-a8526398c5b7"
                      }
  )

(def release-stage-types [{:uuid #uuid"5e9e7d64-7dd9-4088-9c97-d984f81c9fba", :k-name "dev", :name "dev", :description "Development"}
                          {:uuid #uuid"5e9e7d64-268f-473d-b174-2f8164b87817", :k-name "qa", :name "qa", :description "Quality Assurance"}
                          {:uuid #uuid"5e9e7d64-4785-4684-8e76-f84e3cb2ad4c", :k-name "ext", :name "ext", :description "external testing"}
                          {:uuid #uuid"5e9e7d64-8ae0-478f-80a7-1c7b07af56a0", :k-name "perf", :name "perf", :description "Performance Testing"}
                          {:uuid #uuid"5e9e7d64-2828-4776-b5c7-944948858a7f", :k-name "prod", :name "prod", :description "Production"}])

(def app-deployment-dbs [{:uuid                    #uuid"5e9f6ca7-f289-46c7-8d1d-e8bebdaef336",
                          :uuid-tenant             (:uuid-tenant core-entity-map)
                          :uuid-release-stage-type #uuid"5e9e7d64-7dd9-4088-9c97-d984f81c9fba",
                          :uuid-datomic-db         #uuid"5e9e77cd-a4f5-41b7-850e-ea59459ed7da",
                          :uuid-release            #uuid"5e9a48f1-6c6c-4a02-8c50-da5b16207b49"}])
(def deployed-apps [{:uuid                    #uuid"5e9f6fe5-e04f-47c9-b344-a58c3a2bb1e2",
                     :uuid-app-deployment-db  #uuid"5e9f6ca7-f289-46c7-8d1d-e8bebdaef336",
                     :uuid-app-release        #uuid"5e9a5224-3148-4276-b481-0e3f3409b89a",
                     :date                    #inst"2020-04-01T00:00:00.000Z",
                     :uuid-deploy-status-type #uuid"5e9e2eab-f610-4452-8dae-7150f5cd4784"}])
(def deploy-stage-access-groups [{:uuid                   #uuid"5e9f7594-dc92-47c8-b4cf-685b2f5a7857",
                                  :uuid-app-deployment-db #uuid"5e9f6ca7-f289-46c7-8d1d-e8bebdaef336",
                                  :uuid-access-group      #uuid"5e98f683-a697-45fd-99fc-9105c05e300f", ;;admin
                                  :active?                true}
                                 {:uuid                   #uuid"5e9f7594-b018-4779-82bf-befc502228b3",
                                  :uuid-app-deployment-db #uuid"5e9f6ca7-f289-46c7-8d1d-e8bebdaef336",
                                  :uuid-access-group      #uuid"5e98f685-8dec-4b02-85c6-125945f603d4", ;;dev
                                  :active?                true}
                                 {:uuid                   #uuid"5e9f7594-e46b-41af-be51-ac9c0e7cedc3",
                                  :uuid-app-deployment-db #uuid"5e9f6ca7-f289-46c7-8d1d-e8bebdaef336",
                                  :uuid-access-group      #uuid"5e9f7611-b37b-4558-8cd5-107f6d18098a" ;;app user
                                  :active?                true}]
  )

(def releases [{:release-id "1.0", :description "release 1.0", :uuid #uuid"5e9a48f1-6c6c-4a02-8c50-da5b16207b49"}])
(def app-releases [{:uuid-application (:uuid-application core-entity-map)
                    :uuid-release     (:uuid (first releases))
                    :uuid             #uuid"5e9a5224-3148-4276-b481-0e3f3409b89a"}])
(def current-releases [{
                        :current?     true,
                        :uuid-release #uuid"5e9a5224-3148-4276-b481-0e3f3409b89a",
                        :uuid         #uuid"5e9a587d-b70a-41fe-9116-cb0c8c0a123c"}])
(def deploy-status-types [{:uuid        #uuid"5e9e2eab-795d-4acb-9023-59a4c3d4adf3",
                           :k-name      "in-process",
                           :name        "In Process",
                           :description "deployment is in process"}
                          {:uuid        #uuid"5e9e2eab-3484-40f0-9b3c-ff897bd59eb7",
                           :k-name      "deployed",
                           :name        "Deployed",
                           :description "deployment is done"}
                          {:uuid        #uuid"5e9e2eab-f610-4452-8dae-7150f5cd4784",
                           :k-name      "deployed-available",
                           :name        "Deployed and available",
                           :description "deployed and available"}])

(defn determine-datomic-cardinality [card-string]
  (case card-string
    "one" :db.cardinality/one
    "many" :db.cardinality/many
    :db.cardinality/one))

(def gen-type-mapping
  #{
    ["util" "util" "Through the Tools Utility"]
    ["online" "online" "Online data"]
    }
  )

(def attr-gen-types [{:k-name      "util",
                      :name        "util",
                      :description "generated through the tools utility",
                      :uuid        #uuid"5e9a4437-d7b7-420e-8fbc-35fd26bdb3f6"}
                     {:k-name      "online",
                      :name        "online",
                      :description "generated through online",
                      :uuid        #uuid"5e9a4437-3e7f-40da-a745-eb90b66c7c4e"}]
  )

(def cardinality-type-mapping
  #{
    ["one" :db.cardinality/one "One"]
    ["many" :db.cardinality/many "Many"]
    }
  )

(def cardinality-types [{:k-name       "one",
                         :name         "One",
                         :description  "cardinality of one",
                         :datomic-type :db.cardinality/one,
                         :uuid         #uuid"5e9a3ede-6028-4eb3-a125-155e1a7861b5"}
                        {:k-name       "many",
                         :name         "Many",
                         :description  "cardinality of many",
                         :datomic-type :db.cardinality/many,
                         :uuid         #uuid"5e9a3ede-c388-4ef8-be8e-ec40a3821cc8"}])

(def nxt-access-groups [{:uuid        #uuid"5e98f683-a697-45fd-99fc-9105c05e300f"
                          :uuid-tenant (:uuid-tenant core-entity-map)
                          :k-name      "admin"
                          :name        "Administrator"
                          :description "Administrator acccess - full access"}
                         {:uuid        #uuid"5e98f685-8dec-4b02-85c6-125945f603d4"
                          :uuid-tenant (:uuid-tenant core-entity-map)
                          :k-name      "dev"
                          :name        "Developer"
                          :description "Developer acccess - full access except users"}
                         {:uuid        #uuid"5e9f7611-b37b-4558-8cd5-107f6d18098a"
                          :uuid-tenant (:uuid-tenant core-entity-map)
                          :k-name      "app-user"
                          :name        "Application User"
                          :description "Developer acccess - full access except tools app"}
                         {:uuid        #uuid"5e98f685-a734-4ae3-b171-d6b2296bda0b"
                          :uuid-tenant (:uuid-tenant core-entity-map)
                          :k-name      "tenant-admin"
                          :name        "Tenant Admin"
                          :description "Full acccess - full access for a particular Tenant"}
                         {:uuid        #uuid"5e98f688-23d6-4ae0-885f-e5ed8148a626"
                          :uuid-tenant (:uuid-tenant core-entity-map)
                          :k-name      "tenant-app"
                          :name        "Tenant App"
                          :description "Full acccess to all applications except tools app -  for a particular Tenant"}])

(def datatype-valuetype-mapping
  #{["boolean" :db.type/boolean "Boolean"]
    ["string" :db.type/string "String"]
    ["kc-string" :db.type/string "Kabab Case String"]
    ["email" :db.type/string "Email string"]
    ["password" :db.type/string "Email string"]
    ["uuid" :db.type/uuid "UUID"]
    ["valid-values" :db.type/uuid "Translatable Valid Values"]

    ["keyword" :db.type/keyword "Key word"]
    ["number" :db.type/long "Number"]
    ["instant" :db.type/instant "Instant type - Date-time"]
    ["date-time" :db.type/instant "Date and time"]
    ["date" :db.type/instant "Date and time"]
    ["integer" :db.type/long "Integer"]
    ["ref" :db.type/ref "Ref"]
    ["primary-key" :db.type/tuple "Primary Key attribute - a tuple"]
    ["alternate-key" :db.type/tuple "Alternate Key attribute - a tuple"]
    ["upsert-key" :db.type/uuid "Upsert Key attribute"]
    ["decimal" :db.type/double "Decimal"]
    ["rel-comp-belongs-to" :db.type/ref "Belongs-to relationship, and a component relationship"]
    ["rel-comp-extension-of" :db.type/ref "Belongs-to relationship, and a component relationship; one-to-one relationship"]

    ;["rel-optional-has-one-required" :db.type/uuid "has-one relationship in the same database.  One side is required."]
    ["rel-optional-has-one-required" :db.type/ref "has-one relationship in the same database.  One side is required."]


    ["rel-optional-has-one-required-extern" :db.type/uuid "has-one relationship with an external non-datomic database.  a required attribute."]
    ;["rel-optional" :db.type/uuid "internal relationship, but optional"]
    ["rel-optional" :db.type/ref "internal relationship, but optional"]
    ["rel-optional-extern" :db.type/uuid "external relationship, and optional"]
    }

  )

#_(into [] (for [each datatype-valuetype-mapping]
             (let [dt (first each)
                   datomic-t (second each)
                   descr (nth each 2)
                   uuid (basic-util/squuid)]
               {:uuid         uuid
                :k-name       dt
                :name         (strfn/human dt)
                :datomic-type datomic-t
                :description  descr
                }
               )))


#_(into [] (for [each datatype-valuetype-mapping]
             (let [dt (first each)
                   datomic-t (second each)
                   descr (nth each 2)
                   uuid (basic-util/squuid)]
               {:uuid         uuid
                :k-name       dt
                :name         (strfn/human dt)
                :datomic-type datomic-t
                :description  descr
                }
               )))

(def data-types
  [{:uuid         #uuid"5e99f93a-f06c-40fd-8c87-92f6dd5e68e3",
    :k-name       "ref",
    :name         "ref",
    :datomic-type :db.type/ref,
    :description  "Ref"}
   {:uuid         #uuid"5e99f93a-b53c-4aa0-b8a1-d16c11d4f497",
    :k-name       "uuid",
    :name         "uuid",
    :datomic-type :db.type/uuid,
    :description  "UUID"}
   {:uuid         #uuid"5e99f93a-a1b9-43b1-aae9-1a378d1d92e1",
    :k-name       "primary-key",
    :name         "primary key",
    :datomic-type :db.type/tuple,
    :description  "Primary Key attribute - a tuple"}
   {:uuid         #uuid"5e99f93a-12e8-4e2a-b3f9-de9ca9328da8",
    :k-name       "boolean",
    :name         "boolean",
    :datomic-type :db.type/boolean,
    :description  "Boolean"}
   {:uuid         #uuid"5e99f93a-6411-4c36-a602-800750e866d3",
    :k-name       "rel-optional-extern",
    :name         "rel optional extern",
    :datomic-type :db.type/uuid,
    :description  "external relationship, and optional"}
   {:uuid         #uuid"5e99f93a-7985-4226-ae44-9abe8d28014f",
    :k-name       "decimal",
    :name         "decimal",
    :datomic-type :db.type/double,
    :description  "Decimal"}
   {:uuid         #uuid"5e99f93a-e84d-4204-bab5-99303a5afb51",
    :k-name       "email",
    :name         "email",
    :datomic-type :db.type/string,
    :description  "Email string"}
   {:uuid         #uuid"5f69559b-ad86-45a3-958f-cfeed2b1b031"
    :k-name       "password",
    :name         "password",
    :datomic-type :db.type/string,
    :description  "Email string"}
   {:uuid         #uuid"5e99f93a-7748-4302-85f7-d39a12b4926f",
    :k-name       "rel-optional",
    :name         "rel optional",
    :datomic-type :db.type/uuid,
    :description  "internal relationship, but optional"}
   {:uuid         #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3",
    :k-name       "number",
    :name         "number",
    :datomic-type :db.type/long,
    :description  "Number"}
   {:uuid         #uuid"5e99f93a-ce84-486e-9a19-06cf7f927e23",
    :k-name       "instant",
    :name         "instant",
    :datomic-type :db.type/instant,
    :description  "Instant type - Date-time"}
   {:uuid         #uuid"5e99f93a-66f7-44cd-8296-600a7107a6c5",
    :k-name       "kc-string",
    :name         "kc string",
    :datomic-type :db.type/string,
    :description  "Kabab Case String"}
   {:uuid         #uuid"5e99f93a-5bcb-48b5-b287-2caaf1dead8c",
    :k-name       "rel-comp-belongs-to",
    :name         "rel comp belongs to",
    :datomic-type :db.type/uuid,
    :description  "Belongs-to relationship, and a component relationship"}
   {:uuid         #uuid"5e99f93a-d884-4264-a914-68e0fe85ad93",
    :k-name       "valid-values",
    :name         "valid values",
    :datomic-type :db.type/uuid,
    :description  "Translatable Valid Values"}
   {:uuid         #uuid"5e99f93a-20d7-48ee-a900-ae45aa130c58",
    :k-name       "date-time",
    :name         "date time",
    :datomic-type :db.type/instant,
    :description  "Date and time"}
   {:uuid         #uuid"5e99f93a-3369-4820-8767-308af59fd475",
    :k-name       "rel-comp-extension-of",
    :name         "rel comp extension of",
    :datomic-type :db.type/uuid,
    :description  "Belongs-to relationship, and a component relationship; one-to-one relationship"}
   {:uuid         #uuid"5e99f93a-c680-4a42-a21d-d81e60300a5a",
    :k-name       "keyword",
    :name         "keyword",
    :datomic-type :db.type/keyword,
    :description  "Key word"}
   {:uuid         #uuid"5e99f93a-30ba-4b15-8430-80b378e88db4",
    :k-name       "rel-optional-has-one-required",
    :name         "rel optional has one required",
    :datomic-type :db.type/uuid,
    :description  "has-one relationship in the same database.  One side is required."}
   {:uuid         #uuid"5e99f93a-e657-4b06-81ef-915fe5b792a9",
    :k-name       "integer",
    :name         "integer",
    :datomic-type :db.type/long,
    :description  "Integer"}
   {:uuid         #uuid"5e99f93a-099e-4d40-8614-3eaa6955a690",
    :k-name       "string",
    :name         "string",
    :datomic-type :db.type/string,
    :description  "String"}
   {:uuid         #uuid"5e99f93a-64d0-4eac-8c6f-40076a8c50c1",
    :k-name       "rel-optional-has-one-required-extern",
    :name         "rel optional has one required extern",
    :datomic-type :db.type/uuid,
    :description  "has-one relationship with an external database.  a required attribute."}
   {:uuid         #uuid"5e99f93a-2af0-4b7f-b494-29e0c53c04a5",
    :k-name       "upsert-key",
    :name         "upsert key",
    :datomic-type :db.type/uuid,
    :description  "Upsert Key attribute"}]

  )



(def entity-types [{:uuid        #uuid"5e9941e1-f8c5-4223-8048-ef1a05820bcc"
                    :k-name      "entity"
                    :name        "Entity"
                    :description "entity"}
                   {:uuid        #uuid"5e9941e3-2ef2-439f-8e63-a8dc2bd3ef55"
                    :k-name      "entity-view"
                    :name        "Entity View"
                    :description "entity view"}
                   {:uuid        #uuid"5e9941e3-e078-4140-b725-c5f730a79607"
                    :k-name      "external-api"
                    :name        "External API"
                    :description "External API"}])

(def access-permissions [{:k-name      "read"
                          :name        "Read"
                          :description "Read Access"}
                         {:k-name      "write"
                          :name        "Write"
                          :description "Write Access"}
                         {:k-name      "execute"
                          :name        "Execute"
                          :description "Execute Access"}
                         {:k-name      "no-access"
                          :name        "No Access"
                          :description "No Access"}])
(def device-types [{:k-name      "web"
                    :name        "Web"
                    :description "Laptop and desktop browsers"}
                   {:k-name      "tablet"
                    :name        "Tablet"
                    :description "ipad type"}
                   {:k-name      "mobile"
                    :name        "Mobile"
                    :description "Mobile phones"}])
(def row-col-types [{:k-name      "1-col-row"
                     :name        "1 col row"
                     :description "one column row"}
                    {:k-name      "2-col-row"
                     :name        "2 col row"
                     :description "two column row"}
                    {:k-name      "3-col-row"
                     :name        "3 col row"
                     :description "three column row"}
                    {:k-name      "4-col-row"
                     :name        "4 col row"
                     :description "four column row"}])
(def pagelet-types [{:k-name      "entity-list"
                     :name        "Entity List"
                     :description "Entity List"}
                    {:k-name      "entity-detail"
                     :name        "Entity View and Edit"
                     :description "Entity View and Edit"}
                    ])
(def row-select-types [{:k-name      "single"
                        :name        "Single Row"
                        :description "single row select"}
                       {:k-name      "multiple"
                        :name        "Multiple Row"
                        :description "multiple row select"}
                       {:k-name      "no-select"
                        :name        "No Row Select"
                        :description "no row select"}])
(def pagelet-element-types [{:k-name      "tab"
                             :name        "Tab"
                             :description "Tab"}
                            {:k-name      "data"
                             :name        "Data"
                             :description "Data"}])
(def html-components
  [{:k-name      "alert",
    :name        "Alert",
    :description "Alert",
    :editable? false
    :data-types  ["string"],
    :uuid        #uuid"5e9a1b42-a81f-4491-a83d-b10a243fd764"}
   {:k-name      "single-select-drop-down",
    :name        "Single Select Drop Down",
    :description "Drop Down",
    :editable? true
    :data-types  ["rel-optional"
                  "rel-optional-extern"
                  "valid-values"
                  "rel-optional-has-one-required-extern"
                  "rel-comp-extension-of"
                  "rel-optional-has-one-required"],
    :uuid        #uuid"5e9a1b42-5726-472e-a46f-9b4957982731"}
   {:k-name      "multi--select-drop-down",
    :name        "Multi Select Drop Down",
    :description "Drop Down",
    :editable? true
    :uuid        #uuid"5e9a1b42-bf0b-4e45-9b98-b839a3b92692"}
   {:k-name      "text-input",
    :name        "Text Input",
    :description "Form Group for text input",
    :editable? true
    :data-types  ["string" "email" "uuid" "keyword" "number" "integer" "decimal"],
    :uuid        #uuid"5e9a1b42-3102-4da7-976b-ed35d4431efa"}
   {:k-name      "text-area",
    :name        "Text Area",
    :description "Form Group for paragraph",
    :editable? true
    :data-types  ["string"],
    :uuid        #uuid"5e9a1b42-76e3-4a4f-867a-60ff95034c92"}
   {:k-name      "radio-buttons",
    :name        "Radio Buttons",
    :inline?     true,
    :editable? true
    :description "Radio buttons as a single select",
    :data-types  ["valid-values"],
    :uuid        #uuid"5e9a1b42-f6d2-4c71-abca-6d8948fa6423"}
   {:k-name      "radio-boolean",
    :name        "Radio Boolean",
    :description "2 or 3 radio buttons to select true or false or none",
    :editable? true
    :data-types  ["boolean"],
    :uuid        #uuid"5e9a1b42-ad99-4be7-8edb-ef6806f33bd8"}
   {:k-name      "check-boxes",
    :name        "Check Boxes",
    :inline?     true,
    :description "Checkboxes are used if you want the user to select any number of options from a list of preset options.",
    :editable? true
    :data-types  ["valid-values" "boolean"],
    :uuid        #uuid"5e9a1b42-426a-47b7-a280-a22830425d95"}
   {:k-name      "checkbox-toggle",
    :name        "single checkbox",
    :description "A single check box as a toggle ",
    :editable? true
    :data-types  ["boolean"],
    :uuid        #uuid"5e9a1b42-4096-4d47-bfb2-2a380e759cca"}
   {:k-name      "toggle-switch",
    :name        "toggle switch",
    :description "A single check box as a toggle ",
    :editable? true
    :data-types  ["boolean"],
    :uuid        #uuid"5e9a1b42-1046-4d65-b1b8-dbe659e51deb"}
   {:k-name      "range",
    :name        "range selecter",
    :description "range selecter ",
    :editable? true
    :uuid        #uuid"5e9a1b42-31a9-4aaa-aaef-41c95b0b3465"}
   {:k-name      "date-picker",
    :name        "Date Picker",
    :description "Date Picker",
    :editable? true
    :data-types  ["date-time" "instant"],
    :uuid        #uuid"5e9a1b42-7fdd-419c-bcfb-641a7eda224c"}
   {:k-name      "date-time-picker",
    :name        "Date and Time  Picker",
    :description "Date and TImePicker",
    :editable? true
    :data-types  ["date-time" "instant"],
    :uuid        #uuid"5e9a1b42-5d67-49b1-b679-68f199c90cdb"}]

  )


(def label-positions [{:k-name      "left"
                       :name        "Left Side"
                       :description "On the left side"}
                      {:k-name      "right"
                       :name        "Right Side"
                       :description "Right Side"}
                      {:k-name      "top"
                       :name        "Top"
                       :description "Top Side"}
                      ])

;k-name name/symbol description
(def data-type-comparators
  #:db.type{:boolean [{:uuid        #uuid"5e9a04e3-036e-4740-9e2d-4dfa01b42dc1",
                       :k-name      "is",
                       :name        "=",
                       :description "true or false"}],
            :string  [{:uuid #uuid"5e9a04e3-36d3-4c66-8f09-47040def3c72", :k-name "is", :name "=", :description "equals"}
                      {:uuid        #uuid"5e9a04e3-31b6-4c21-85bb-aa5e1f82b326",
                       :k-name      "starts-with",
                       :name        "starts with",
                       :description "starts with"}
                      {:uuid        #uuid"5e9a04e3-b4b2-49df-b6cb-e26c3ea263e3",
                       :k-name      "ends-with",
                       :name        "ends with",
                       :description "ends with"}
                      {:uuid #uuid"5e9a04e3-99ce-4227-80e6-565f1ea308e3", :k-name "in", :name "in", :description "any of"}
                      {:uuid        #uuid"5e9a04e3-89aa-4aaa-a016-56155769f9e8",
                       :k-name      "contains",
                       :name        "contains",
                       :description "contains"}],
            :keyword [{:uuid #uuid"5e9a04e3-8233-44e3-8e07-5f7b97cf8d27", :k-name "is", :name "=", :description "equals"}],
            :bigint  [{:uuid #uuid"5e9a04e3-5446-4111-9b4c-2a0e6b980fa9", :k-name "is", :name "=", :description "equals"}
                      {:uuid        #uuid"5e9a04e3-bb93-4fe6-ab93-0726beb4255d",
                       :k-name      "greater-than",
                       :name        ">",
                       :description "greater than"}
                      {:uuid        #uuid"5e9a04e3-67f2-4eb0-b2e6-44c7f488629e",
                       :k-name      "greater-than-equal-to",
                       :name        ">=",
                       :description "greater than equal to"}
                      {:uuid        #uuid"5e9a04e3-1149-4a5e-96b2-dc34d838d137",
                       :k-name      "less-than",
                       :name        "<",
                       :description "less than"}
                      {:uuid        #uuid"5e9a04e3-8b9b-410a-9bc0-e7c58016b574",
                       :k-name      "less-than-equal-to",
                       :name        "<=",
                       :description "less than equal to"}
                      {:uuid        #uuid"5e9a04e3-d22c-4526-afcc-5e6594ff919e",
                       :k-name      "between",
                       :name        "><",
                       :description "between"}],
            :instant [{:uuid #uuid"5e9a04e3-89dc-4c8c-ba9b-42605572f3c4", :k-name "is", :name "=", :description "equals"}
                      {:uuid        #uuid"5e9a04e3-48d8-4536-bab8-046ed10753a1",
                       :k-name      "greater-than",
                       :name        ">",
                       :description "greater than"}
                      {:uuid        #uuid"5e9a04e3-b894-4746-92be-5d06110feee6",
                       :k-name      "greater-than-equal-to",
                       :name        ">=",
                       :description "greater than equal to"}
                      {:uuid        #uuid"5e9a04e3-ff99-4402-a5c6-0603ba22d46c",
                       :k-name      "less-than",
                       :name        "<",
                       :description "less than"}
                      {:uuid        #uuid"5e9a04e3-fefd-43e5-9697-d143ae562c83",
                       :k-name      "less-than-equal-to",
                       :name        "<=",
                       :description "less than equal to"}
                      {:uuid        #uuid"5e9a04e3-0269-41f6-bf92-b11a1ba42ba0",
                       :k-name      "between",
                       :name        "><",
                       :description "between"}],
            :double  [{:uuid #uuid"5e9a04e3-6c05-45d7-bc26-08491772372a", :k-name "is", :name "=", :description "equals"}
                      {:uuid        #uuid"5e9a04e3-3748-4eef-b654-1fc9b8202d8b",
                       :k-name      "greater-than",
                       :name        ">",
                       :description "greater than"}
                      {:uuid        #uuid"5e9a04e3-8c2c-4ba8-8b86-215b2920cef1",
                       :k-name      "greater-than-equal-to",
                       :name        ">=",
                       :description "greater than equal to"}
                      {:uuid        #uuid"5e9a04e3-df5b-4f55-a4f1-96e2c05e0acc",
                       :k-name      "less-than",
                       :name        "<",
                       :description "less than"}
                      {:uuid        #uuid"5e9a04e3-4e9f-488b-816b-35b6816ab8e0",
                       :k-name      "less-than-equal-to",
                       :name        "<=",
                       :description "less than equal to"}
                      {:uuid        #uuid"5e9a04e3-a02c-47ff-a059-906065965309",
                       :k-name      "between",
                       :name        "><",
                       :description "between"}]}
  )
#_(into {} (for [each-key (keys datatype-comparators-2)]
             {each-key (into []
                             (for [each (each-key datatype-comparators-2)]
                               (let [k-name (first each)
                                     name (second each)
                                     description (nth each 2)
                                     uuid (basic-util/squuid)]
                                 {:uuid        uuid
                                  :k-name      k-name
                                  :name        name
                                  :description description}

                                 )
                               )
                             )}

             ))
(def datatype-comparators-2 {:db.type/boolean [
                                               ["is" "=" "true or false"]
                                               ]
                             :db.type/string  [
                                               ["is" "=" "equals"]
                                               ["starts-with" "starts with" "starts with"]
                                               ["ends-with" "ends with" "ends with"]
                                               ["in" "in" "any of"]
                                               ["contains" "contains" "contains"]
                                               ]
                             :db.type/keyword [
                                               ["is" "=" "equals"]
                                               ]
                             :db.type/long  [
                                             ["is" "=" "equals"]
                                             ["greater-than" ">" "greater than"]
                                             ["greater-than-equal-to" ">=" "greater than equal to"]
                                             ["less-than" "<" "less than"]
                                             ["less-than-equal-to" "<=" "less than equal to"]
                                             ["between" "><" "between"]
                                             ]
                             :db.type/instant [
                                               ["is" "=" "equals"]
                                               ["greater-than" ">" "greater than"]
                                               ["greater-than-equal-to" ">=" "greater than equal to"]
                                               ["less-than" "<" "less than"]
                                               ["less-than-equal-to" "<=" "less than equal to"]
                                               ["between" "><" "between"]
                                               ]
                             :db.type/double  [
                                               ["is" "=" "equals"]
                                               ["greater-than" ">" "greater than"]
                                               ["greater-than-equal-to" ">=" "greater than equal to"]
                                               ["less-than" "<" "less than"]
                                               ["less-than-equal-to" "<=" "less than equal to"]
                                               ["between" "><" "between"]
                                               ]
                             }
  )
;;;;================================================================================================================
(defn assign-access-group [access-groups role-k-name]
  (first (specter/select [specter/ALL #(= (:k-name %) role-k-name) :uuid] access-groups))
  )
(defn define-user [uuid-user email user-id uuid-tenant uuid-access-group first-name last-name phone-1 phone-2 position]
  {:email                        email
   :last-name                    last-name
   :uuid-access-group            uuid-access-group
   :has-many-entity-list-filters [],
   :uuid-user                    uuid-user
   :has-a-access-group           {:nxt.tools.access-group/uuid-access-group uuid-access-group},
   :has-a-tenant                 {:nxt.tools.tenant/uuid-tenant uuid-tenant},
   :user-id                      user-id,
   :uuid-tenant                  uuid-tenant,
   :phone-1                      phone-1,
   :first-name                   first-name,
   :email+email                  [email email],
   :position                     position,
   :phone-2                      phone-2})
(defn define-application [uuid-application uuid-tenant application-k-name application-name description available-for-sub?]
  {:description        description,
   :available-for-sub? true,
   :k-name             application-k-name,
   :name               application-name,
   ;:has-many-subscribed-apps [],
   :uuid-tenant        uuid-tenant,
   ;:has-many-app-releases [],
   :uuid-application   uuid-application,
   :uuid-tenant+k-name [uuid-tenant application-k-name],
   ;:has-many-menu-groups []
   }
  )
;;;;================================================================================================================
(def customer1-tenant
  {:uuid-tenant #uuid"5ea89a95-c173-4437-b4d0-813cc9632057",
   :tenant-k-name      "cust-one",
   :tenant-name        "Customer One Inc",
   :description "Customer One",
   :web-url     "http://wwww.customer-one.com"}
  )
(def customer1-access-groups
  [{:uuid-access-group        #uuid"5ea89c58-f7b9-4384-984c-8186d1e1583a",
    :uuid-tenant (:uuid-tenant customer1-tenant)
    :access-group-k-name      "admin",
    :access-group-name        "Administrator",
    :description "Administrator acccess - full access"}
   {:uuid-access-group        #uuid"5ea89c58-a808-459a-937b-cd1899da2110",
    :uuid-tenant (:uuid-tenant customer1-tenant)
    :access-group-k-name      "dev",
    :access-group-name        "Developer",
    :description "Developer acccess - full access except users"}
   {:uuid-access-group        #uuid"5ea89c58-bfe4-4c07-9177-c6e076c043a8",
    :uuid-tenant (:uuid-tenant customer1-tenant)
    :access-group-k-name      "app-user",
    :access-group-name        "Application User",
    :description "Developer acccess - full access except tools app"}]
  )

(def customer1-uuids {:customer1-admin-user-uuid #uuid"5ea8a013-5659-48a7-b556-a1accc1b73ac"
                      :customer1-app-user-uuid   #uuid"5ea8a2ff-2821-42e7-b93f-40f936d13e3a"
                      :uuid-shipping-app         #uuid"5ea8a535-6ed2-48aa-8447-4cd365a7b0dd"
                      :uuid-invoicing-app        #uuid"5ea8a54d-9693-41b0-9c8b-456be7dc04aa"
                      }
  )

(def customer1-user (define-user (:customer1-admin-user-uuid customer1-uuids)
                                 "admin-user@cust1.com"
                                 "admin-cust1"
                                 (:uuid-tenant customer1-tenant)
                                 (assign-access-group customer1-access-groups "admin")
                                 "Francis"
                                 "Palthra"
                                 "925-666-0908"
                                 "925-666-0908"
                                 "CIO"
                                 ))
(def customer1-user (define-user (:customer1-app-user-uuid customer1-uuids)
                                 "app-user@cust1.com"
                                 "app-cust1"
                                 (:uuid-tenant customer1-tenant)
                                 (assign-access-group customer1-access-groups "app-user")
                                 "Denis"
                                 "Lawson"
                                 "925-342-0908"
                                 "925-342-0908"
                                 "Manager"
                                 ))

;; (assign-access-group customer1-access-groups "app-user")

(def customer1-users
  [{:email "cust1-admin@cust1.com",
    :last-name "Mathew",
    :uuid-access-group #uuid"5ea89c58-f7b9-4384-984c-8186d1e1583a",
    :uuid-user #uuid"5ea8c2fb-6b82-41ec-892a-ad93692c5b7f",
    :user-id "cust1-admin",
    :phone-1 "925-443-2121",
    :first-name "Bin",
    :position "CIO",
    :phone-2 "925-334-8877"}
   {:email "cust1-app-user@cust1.com",
    :last-name "Goldt",
    :uuid-access-group #uuid"5ea89c58-bfe4-4c07-9177-c6e076c043a8",
    :uuid-user #uuid"5ea8c2fb-cfe0-4c47-927c-66f7a1dcaf50",
    :user-id "cust1-app-user",
    :phone-1 "925-666-5544",
    :first-name "Mike",
    :position "Analyst",
    :phone-2 "925-777-8877"}
   {:email "nxt-app-dev@cust1.com",
    :last-name "Vattu",
    :uuid-access-group #uuid"5ea89c58-a808-459a-937b-cd1899da2110",
    :uuid-user #uuid"5ea8c2fb-cd2a-4eef-a928-b57a009af357",
    :user-id "nxt-app-dev",
    :phone-1 "925-222-2222",
    :first-name "David",
    :position "Developer",
    :phone-2 "925-000-5451"}]
  )
(def customer1-apps [(define-application (:uuid-invoicing-app customer1-uuids)
                                         (:uuid-tenant customer1-tenant)
                                         "invoicing"
                                         "Invoicing App"
                                         "Invoicing application"
                                         true)
                     (define-application (:uuid-shipping-app customer1-uuids)
                                         (:uuid-tenant customer1-tenant)
                                         "shipping"
                                         "Shipping App"
                                         "Shipping application"
                                         true)
                     ])

(def cust1-applications
  [{:uuid-application   #uuid"5ea8a54d-9693-41b0-9c8b-456be7dc04aa",
    :application-k-name "invoicing",
    :application-name   "Invoicing App",
    :description        "Invoicing application",
    :available-for-sub? true}
   {:uuid-application   #uuid"5ea8a535-6ed2-48aa-8447-4cd365a7b0dd",
    :application-k-name "shipping",
    :application-name   "Shipping App",
    :description        "Shipping application",
    :available-for-sub? true}])

(defn tenant-seed [{:keys [description,
                           tenant-k-name,
                           tenant-name,
                           uuid-tenant,
                           web-url]}]
  [#:nxt.tools.tenant{:description   description,
                       :k-name        tenant-k-name,
                       :name          tenant-name,
                       ;:has-many-subscribed-apps [],
                       :uuid-tenant   uuid-tenant,
                       ;:has-many-app-deployment-dbs [],
                       :k-name+k-name [tenant-k-name tenant-k-name],
                       ;:has-many-access-groups [],
                       :web-url       web-url,
                       ;:has-many-applications []
                       }]
  )
(defn access-groups-seed [access-groups uuid-tenant]
  (->> (specter/transform [specter/ALL] (fn [{:keys [access-group-k-name,
                                                     access-group-name,
                                                     description,
                                                     uuid-access-group,
                                                     ]}]
                                          [#:nxt.tools.access-group{:uuid-tenant uuid-tenant,
                                                                     :k-name access-group-k-name,
                                                                     :name access-group-name,
                                                                     :description description,
                                                                     ;:has-many-deploy-stage-access-groups [],
                                                                     ;:has-many-access-group-menu-items [],
                                                                     :uuid-access-group uuid-access-group,
                                                                     :uuid-tenant+k-name [uuid-tenant access-group-k-name]}
                                           #:nxt.tools.tenant{:uuid-tenant           uuid-tenant,
                                                               :has-many-access-groups [#:nxt.tools.access-group{:uuid-access-group uuid-access-group}]}]
                                          ) access-groups
                          )
       (flatten)
       (into [])
       )
  )

;;    (tenant-seed customer1-tenant)

;;;;================================================================================================================
(def nxt-uuids
  {:nxt-uuid-tenant (:uuid-tenant core-entity-map)
   }
  )
(def sample-users [
                   ["nxt"
                    ["admin" [["nxt-admin@invignow.com" #uuid"5ea8b387-9619-40e5-ac9a-639c7dff1188"]]]
                    ["app-user" [["nxt-app-user@invignow.com" #uuid"5ea8b3c0-6721-4302-b2da-4b7d28ed7e88"]]]
                    ["dev" [["nxt-app-dev@invignow.com" #uuid"5ea8b3d8-564c-4979-9097-5fa56ffeb3d5"]]]
                    ]
                   ]
  )
(def nxt-users
  [{:email "nxt-admin@invignow.com",
    :last-name "Steele",
    :uuid-access-group #uuid"5e98f683-a697-45fd-99fc-9105c05e300f",
    :uuid-user #uuid"5ea8b9bc-f310-4059-abaf-78f64907f76e",
    :user-id "nxt-admin",
    :phone-1 "925-654-7676",
    :first-name "Guy",
    :position "CTO",
    :phone-2 "925-876-5451"}
   {:email "nxt-app-user@invignow.com",
    :last-name "Krishnan",
    :uuid-access-group #uuid"5e9f7611-b37b-4558-8cd5-107f6d18098a",
    :uuid-user #uuid"5ea8b9bc-639a-4f09-bf30-51857536f668",
    :user-id "nxt-app-user",
    :phone-1 "925-453-7676",
    :first-name "Sanjay",
    :position "Analyst",
    :phone-2 "925-988-5451"}
   {:email "nxt-app-dev@invignow.com",
    :last-name "Krishnan",
    :uuid-access-group #uuid"5e98f685-8dec-4b02-85c6-125945f603d4",
    :uuid-user #uuid"5ea8b9bc-493a-44a7-b101-de8deea6ee2f",
    :user-id "nxt-app-dev",
    :phone-1 "925-888-7676",
    :first-name "Kavitha",
    :position "Developer",
    :phone-2 "925-888-5451"}]
  )
(def nxt-applications
  [{:uuid-application   #uuid"5ea8ae0f-6d06-4a06-905c-d0b6b177017f",
    :application-k-name "hotel-accounting",
    :application-name   "Hotel Accounting",
    :description        "Hotel Accounting",
    :available-for-sub? false}
   {:uuid-application   #uuid"5ea8ae0f-a3ca-4906-8342-9549158334e1",
    :application-k-name "insurance-accounting",
    :application-name   "Insurance Accounting",
    :description        "Insurance Accounting",
    :available-for-sub? false}
   {:uuid-application   #uuid"5ea8ae0f-15f3-4b02-825f-e92bba9404c1",
    :application-k-name "legal-accounting",
    :application-name   "Legal Accounting",
    :description        "Legal Accounting",
    :available-for-sub? false}
   {:uuid-application   #uuid"5ea8ae0f-16d2-4717-b1bd-7a173ec0e2ad",
    :application-k-name "etl",
    :application-name   "ETL Software",
    :description        "ETL Software",
    :available-for-sub? false}
   {:uuid-application   #uuid"5ea8ae0f-4f54-483c-bdf1-71de044a5949",
    :application-k-name "voip-software",
    :application-name   "VOIP Software",
    :description        "Voip software",
    :available-for-sub? false}
   {:uuid-application   #uuid"5ea8ae0f-b5a6-4a54-96ae-43cc62bd4f48",
    :application-k-name "online-meeting",
    :application-name   "Online Meeting",
    :description        "Online Meeting",
    :available-for-sub? false}
   {:uuid-application   #uuid"5ea8ae0f-746b-45d9-b093-06b398ba8367",
    :application-k-name "crm",
    :application-name   "crm",
    :description        "Customer relations management",
    :available-for-sub? true}
   {:uuid-application   #uuid"5ea8ae0f-6fa0-4613-b778-ccf19a9c96d7",
    :application-k-name "products",
    :application-name   "Products",
    :description        "Products",
    :available-for-sub? true}
   {:uuid-application   #uuid"5ea8ae0f-b83c-42fd-8de7-22e8e27bac4a",
    :application-k-name "item-management",
    :application-name   "Item Management",
    :description        "Item Management",
    :available-for-sub? true}
   {:uuid-application   #uuid"5ea8ae0f-27f3-4a35-b816-8589fe0e4eda",
    :application-k-name "inventory-management",
    :application-name   "Inventory Management",
    :description        "Inventory Management",
    :available-for-sub? true}
   {:uuid-application   #uuid"5ea8ae0f-3022-4346-a9ff-4cccc72f3e5c",
    :application-k-name "order-management",
    :application-name   "Order Management",
    :description        "Order Management",
    :available-for-sub? true}
   {:uuid-application   #uuid"5ea8ae0f-972f-466b-bb63-2c9672ca029a",
    :application-k-name "purchasing-management",
    :application-name   "Purchasing Management",
    :description        "Purchasing Management",
    :available-for-sub? true}
   {:uuid-application   #uuid"5ea8ae0f-57a1-4eb4-aa1c-d32f4c307bbb",
    :application-k-name "manufacturing",
    :application-name   "manufacturing",
    :description        "Manaufacturing management",
    :available-for-sub? true}
   {:uuid-application   #uuid"5ea8ae0f-ef8e-4fe3-a490-5a12836e9974",
    :application-k-name "cost-accounting",
    :application-name   "Cost Accounting",
    :description        "Cost Accounting",
    :available-for-sub? true}]

  )

(defn apps-seed [nxt-applications uuid-tenant]
  (->> (specter/transform [specter/ALL] (fn [{:keys [uuid-application
                                                     application-k-name
                                                     application-name
                                                     description
                                                     available-for-sub?]}]
                                          [#:nxt.tools.application{:description        description,
                                                                    :available-for-sub? available-for-sub?
                                                                    :k-name             application-k-name,
                                                                    :name               application-name,
                                                                    ;:has-many-subscribed-apps [],
                                                                    :uuid-tenant        uuid-tenant,
                                                                    ;:has-many-app-releases [],
                                                                    :uuid-application   uuid-application,
                                                                    :uuid-tenant+k-name [uuid-tenant application-k-name],
                                                                    ;:has-many-menu-groups []
                                                                    }
                                           #:nxt.tools.tenant{:uuid-tenant           uuid-tenant,
                                                               :has-many-applications [#:nxt.tools.application{:uuid-application uuid-application}]}]
                                          ) nxt-applications
                          )
       (flatten)
       (into [])
       )
  )
;;      (nxt-additional-apps-seed nxt-applications (:nxt-uuid-tenant nxt-uuids))
;;      (nxt-users-seed nxt-users (:nxt-uuid-tenant nxt-uuids))

(defn users-seed [nxt-users uuid-tenant]
  (specter/transform [specter/ALL] (fn [{:keys [email
                                                last-name
                                                uuid-access-group
                                                uuid-user user-id,
                                                phone-1,
                                                first-name, position, phone-2]}]
                                     #:nxt.tools.user{:email              email
                                                       :last-name          last-name
                                                       :uuid-access-group  uuid-access-group
                                                       ;:has-many-entity-list-filters [],
                                                       :uuid-user          uuid-user
                                                       :has-a-access-group {:nxt.tools.access-group/uuid-access-group uuid-access-group},
                                                       :has-a-tenant       {:nxt.tools.tenant/uuid-tenant uuid-tenant},
                                                       :user-id            user-id,
                                                       :uuid-tenant        uuid-tenant,
                                                       :phone-1            phone-1,
                                                       :first-name         first-name,
                                                       :email+email        [email email],
                                                       :position           position,
                                                       :phone-2            phone-2}
                                     ) nxt-users
                     )
  )


;;      (apps-seed nxt-applications (:nxt-uuid-tenant nxt-uuids))
;;      (users-seed nxt-users (:nxt-uuid-tenant nxt-uuids))

