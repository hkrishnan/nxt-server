(ns nxt.tools.data-model.seed-data
  (:require
    [nxt.tools.util.basic :as basic-util]
    [tick.core :as t]
    [cuerdas.core :as strfn]
    )
  )

(comment

  (nxt.tools.data-model.erd/description-attr)
  (basic-util/squuid)

  (strfn/kebab "cust one")

  (let [t [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17]
        f (fn [n]
            {:tenant-k-name (strfn/kebab (str "cust " n))
             :tenant-name   (str "cust " n)
             :web-url       (str "www." n ".com")
             :active?       true
             :created-at    (t/inst (t/now))
             :description   (str "customer " n)
             :uuid-tenant   (basic-util/squuid)}
            )
        ]
    (vec (map f t))
    )
  )

;;"tenant"
(def
  tenant
  [{:tenant-k-name "nxt",
    :tenant-name   "invigNow",
    :company-id "nxt-123"
    :web-url       "www.invignow.com",
    :active?       true
    :created-at    (-> (t/date "1900-01-01") (t/at "11:00") (t/inst))
    :description   "InvigNow tenant",
    :uuid-tenant   #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"}
   {:tenant-k-name "cust-1",
    :tenant-name   "cust 1",
    :web-url       "www.1.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 1",
    :uuid-tenant   #uuid"64655d22-250a-4292-b905-6f8dbe842b26"}
   {:tenant-k-name "cust-2",
    :tenant-name   "cust 2",
    :web-url       "www.2.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 2",
    :uuid-tenant   #uuid"64655d22-eb1e-43ce-9edf-a2509358bee9"}
   {:tenant-k-name "cust-3",
    :tenant-name   "cust 3",
    :web-url       "www.3.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 3",
    :uuid-tenant   #uuid"64655d22-ddd6-4c9e-8f92-2220cf72f0f9"}
   {:tenant-k-name "cust-4",
    :tenant-name   "cust 4",
    :web-url       "www.4.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 4",
    :uuid-tenant   #uuid"64655d22-81c5-4a72-90c3-fa122922899b"}
   {:tenant-k-name "cust-5",
    :tenant-name   "cust 5",
    :web-url       "www.5.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 5",
    :uuid-tenant   #uuid"64655d22-70c6-4570-a7e5-ef82f2d58fce"}
   {:tenant-k-name "cust-6",
    :tenant-name   "cust 6",
    :web-url       "www.6.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 6",
    :uuid-tenant   #uuid"64655d22-18c6-4b2b-8112-656fe82534c1"}
   {:tenant-k-name "cust-7",
    :tenant-name   "cust 7",
    :web-url       "www.7.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 7",
    :uuid-tenant   #uuid"64655d22-3dbc-462f-ba77-c4ce342a1195"}
   {:tenant-k-name "cust-8",
    :tenant-name   "cust 8",
    :web-url       "www.8.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 8",
    :uuid-tenant   #uuid"64655d22-25e5-4714-a94f-c27ebb9e4042"}
   {:tenant-k-name "cust-9",
    :tenant-name   "cust 9",
    :web-url       "www.9.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 9",
    :uuid-tenant   #uuid"64655d22-5c2e-47f9-a25b-9dd09fd13864"}
   {:tenant-k-name "cust-10",
    :tenant-name   "cust 10",
    :web-url       "www.10.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 10",
    :uuid-tenant   #uuid"64655d22-c368-4752-8523-3a919bff16e9"}
   {:tenant-k-name "cust-11",
    :tenant-name   "cust 11",
    :web-url       "www.11.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 11",
    :uuid-tenant   #uuid"64655d22-7148-4539-b633-04b27a336e1c"}
   {:tenant-k-name "cust-12",
    :tenant-name   "cust 12",
    :web-url       "www.12.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 12",
    :uuid-tenant   #uuid"64655d22-ef69-4cbc-a474-11a35f00653e"}
   {:tenant-k-name "cust-13",
    :tenant-name   "cust 13",
    :web-url       "www.13.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 13",
    :uuid-tenant   #uuid"64655d22-6bf9-4c49-9fcc-2adfedc0fa8a"}
   {:tenant-k-name "cust-14",
    :tenant-name   "cust 14",
    :web-url       "www.14.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 14",
    :uuid-tenant   #uuid"64655d22-5a9b-4bd9-8046-29d684cd9c76"}
   {:tenant-k-name "cust-15",
    :tenant-name   "cust 15",
    :web-url       "www.15.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 15",
    :uuid-tenant   #uuid"64655d22-75cb-410f-8250-41892e79c08e"}
   {:tenant-k-name "cust-16",
    :tenant-name   "cust 16",
    :web-url       "www.16.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 16",
    :uuid-tenant   #uuid"64655d22-4df7-4858-81c9-b102ab709b06"}
   {:tenant-k-name "cust-17",
    :tenant-name   "cust 17",
    :web-url       "www.17.com",
    :active?       true,
    :created-at    #inst"2023-05-17T23:02:58.079-00:00",
    :description   "customer 17",
    :uuid-tenant   #uuid"64655d22-1988-47a5-92f8-bacdd8f1d444"}
   ])


(comment

  [{:tenant-k-name "cust-one",
    :tenant-name   "cust-one",
    :web-url       "www.cust-one.com",
    :active?       true
    :created-at    (-> (t/date "1900-01-01") (t/at "11:00") (t/inst))
    :description   "InvigNow tenant",
    :uuid-tenant   #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"}]

  )
;;"user"
;;"language"
(def
  language
  [{:language-code "en",
    :language-name "English",
    :description   "English language",
    :uuid-language #uuid"5ebb1c9b-9423-4815-a27f-abe502a25383"}
   {:language-code "hi",
    :language-name "Hindi",
    :description   "Hindi language",
    :uuid-language #uuid"5f8e103b-a036-4c38-b08b-4ce98fa9e2af"}])

;;"entity-type"
(def
  entity-type
  [
   {:entity-type-k-name "entity",
    :description        "Entity",
    :uuid-entity-type   #uuid"5ebb1d74-8af7-4834-8041-3ac6a4e3e76d"}
   {:entity-type-k-name "view",
    :description        "View Entity",
    :uuid-entity-type   #uuid"5ebb1e14-74ae-4aa7-9d7a-986e47f60cf6"}
   ]
  )
(comment
  (nxt.tools.util.basic/squuid)
  )
;;"label-text-type
(def
  label-text-type
  [
   {:label-text-type-k-name "page-heading",
    :description            "Page Heading",
    :uuid-label-text-type   #uuid"6126c87d-3ebd-459f-9449-a97b023ff0ac"}
   {:label-text-type-k-name "label",
    :description            "Label",
    :uuid-label-text-type   #uuid"6109d1be-f427-4d65-bc3a-8e4aee4edf33"}
   {:label-text-type-k-name "text",
    :description            "Some text",
    :uuid-label-text-type   #uuid"6109d1ce-0fbe-4b86-8060-d29264e33407"}
   ]
  )

;;"entity-event-type"
(def
  entity-event-type
  [
   {:uuid-entity-event-type   #uuid"6350a2bc-31b4-4727-bf3d-82ba8650c3dd",
    :entity-event-type-k-name "entity-common",
    :description              "Common functions"}
   {:uuid-entity-event-type   #uuid"5fb7425b-c6da-4549-a729-024518446e01",
    :entity-event-type-k-name "entity-save-validate",
    :description              "Save Validate",
    }
   {:uuid-entity-event-type   #uuid"5fb7426f-e0d9-470b-9a89-290bc6100d65",
    :entity-event-type-k-name "entity-save-pre-change",
    :description              "Save Pre Change",
    }
   {:uuid-entity-event-type   #uuid"5fb74293-74dc-475c-8616-b247a107aece",
    :entity-event-type-k-name "entity-save-post-change",
    :description              "Save Post Change",
    }
   ]
  )


(comment (basic-util/squuid))
;;"attr-event-type"
(def
  attr-event-type
  [
   {:uuid-attr-event-type   #uuid"5fb74301-37a3-410f-8e2a-67cb09ebb1b8",
    :attr-event-type-k-name "attr-default",
    :description            "attr default",
    }
   {:uuid-attr-event-type   #uuid"616f042b-17e6-422e-8905-944653acd13d"
    :attr-event-type-k-name "attr-validate",
    :description            "attr validate",
    }
   {:uuid-attr-event-type   #uuid"5fb742fd-469e-4c50-8582-bf2ff3933cc8",
    :attr-event-type-k-name "attr-change",
    :description            "attr change",
    }
   ]
  )

;"composite-key-type"
(def
  composite-key-type
  [{:composite-key-type-k-name "primary",
    :description               "Primary Key",
    :uuid-composite-key-type   #uuid"5ebc8a48-8387-4f5d-9d98-05e9f61193ba"}
   {:composite-key-type-k-name "alternate",
    :description               "Alternate key",
    :uuid-composite-key-type   #uuid"5ebc8b40-e0ae-4ea2-bb9b-dc3a6572c312"}])
;"data-type"
(def
  data-type
  [{:uuid-data-type   #uuid"5e99f93a-f06c-40fd-8c87-92f6dd5e68e3",
    :data-type-k-name "ref",
    :datomic-type     :db.type/ref,
    :reg-text         "-"
    :description      "Ref"}
   {:uuid-data-type   #uuid"5e99f93a-b53c-4aa0-b8a1-d16c11d4f497",
    :data-type-k-name "uuid",
    :datomic-type     :db.type/uuid,
    :reg-text         "-"
    :description      "UUID"}
   {:uuid-data-type   #uuid"5e99f93a-a1b9-43b1-aae9-1a378d1d92e1",
    :data-type-k-name "primary-key",
    :datomic-type     :db.type/tuple,
    :reg-text         "-"
    :description      "Primary Key attr - a tuple"}
   {:uuid-data-type   #uuid"5eec11d8-ff20-4b9f-ad23-a87234526ac1"
    :data-type-k-name "alternate-key",
    :data-type-name   "alternate key",
    :datomic-type     :db.type/tuple,
    :reg-text         "-"
    :description      "Alternate Primary Key attr - a tuple"}
   {:uuid-data-type   #uuid"5e99f93a-12e8-4e2a-b3f9-de9ca9328da8",
    :data-type-k-name "boolean",
    :data-type-name   "boolean",
    :datomic-type     :db.type/boolean,
    :reg-text         "-"
    :description      "Boolean"}
   {:uuid-data-type   #uuid"5e99f93a-6411-4c36-a602-800750e866d3",
    :data-type-k-name "rel-optional-extern",
    :data-type-name   "rel optional extern",
    :datomic-type     :db.type/uuid,
    :reg-text         "-"
    :description      "external relationship, and optional"}
   {:uuid-data-type   #uuid"5e99f93a-7985-4226-ae44-9abe8d28014f",
    :data-type-k-name "decimal",
    :data-type-name   "decimal",
    :datomic-type     :db.type/double,
    :reg-text         "-"
    :description      "Decimal"}
   {:uuid-data-type   #uuid"5e99f93a-e84d-4204-bab5-99303a5afb51",
    :data-type-k-name "email",
    :data-type-name   "email",
    :datomic-type     :db.type/string,
    :description      "Email string"
    :reg-text         (str #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")}
   ;; https://regexlib.com/Search.aspx?k=password&c=0&m=0&ps=20&p=1
   ;;This regular expression match can be used for validating strong password.
   ;; It expects atleast 1 small-case letter,
   ;; 1 Capital letter,
   ;; 1 digit,
   ;; 1 special character
   ;; and the length should be between 6-any characters.
   ;; The sequence of the characters is not important.
   ;; This expression follows the above 4 norms specified by microsoft for a strong password.
   {:uuid-data-type   #uuid"5f221310-2992-4b18-994c-2505f7f4343f"
    :data-type-k-name "password",
    :data-type-name   "Password",
    :datomic-type     :db.type/string,
    :description      "password string"
    :reg-text         (str #"(?=^.{6,}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\s).*$")}
   {:uuid-data-type   #uuid"5e99f93a-7748-4302-85f7-d39a12b4926f",
    :data-type-k-name "rel-optional",
    :data-type-name   "rel optional",
    :datomic-type     :db.type/ref,
    :reg-text         "-"
    :description      "internal relationship, but optional"}
   {:uuid-data-type   #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3",
    :data-type-k-name "number",
    :data-type-name   "number",
    :datomic-type     :db.type/long,
    :reg-text         "-"
    :description      "Number"}
   {:uuid-data-type   #uuid"5e99f93a-ce84-486e-9a19-06cf7f927e23",
    :data-type-k-name "instant",
    :data-type-name   "instant",
    :datomic-type     :db.type/instant,
    :reg-text         "-"
    :description      "Instant type - Date-time"}
   {:uuid-data-type   #uuid"5e99f93a-66f7-44cd-8296-600a7107a6c5",
    :data-type-k-name "kc-string",
    :data-type-name   "kc string",
    :datomic-type     :db.type/string,
    :reg-text         "-"
    :description      "Kabab Case String"}
   {:uuid-data-type   #uuid"5e99f93a-5bcb-48b5-b287-2caaf1dead8c",
    :data-type-k-name "rel-comp-belongs-to",
    :data-type-name   "rel comp belongs to",
    :datomic-type     :db.type/ref,
    :reg-text         "-"
    :description      "Belongs-to relationship, and a component relationship"}
   {:uuid-data-type   #uuid"5e99f93a-d884-4264-a914-68e0fe85ad93",
    :data-type-k-name "valid-values",
    :data-type-name   "valid values",
    :datomic-type     :db.type/uuid,
    :reg-text         "-"
    :description      "Translatable Valid Values"}
   {:uuid-data-type   #uuid"5e99f93a-20d7-48ee-a900-ae45aa130c58",
    :data-type-k-name "date-time",
    :data-type-name   "date time",
    :datomic-type     :db.type/instant,
    :reg-text         "-"
    :description      "Date and time"}
   {:uuid-data-type   #uuid"5e99f93a-3369-4820-8767-308af59fd475",
    :data-type-k-name "rel-comp-extension-of",
    :data-type-name   "rel comp extension of",
    :datomic-type     :db.type/ref,
    :reg-text         "-"
    :description      "Belongs-to relationship, and a component relationship; one-to-one relationship"}
   {:uuid-data-type   #uuid"5e99f93a-c680-4a42-a21d-d81e60300a5a",
    :data-type-k-name "keyword",
    :data-type-name   "keyword",
    :datomic-type     :db.type/keyword,
    :reg-text         "-"
    :description      "Key word"}
   {:uuid-data-type   #uuid"5e99f93a-30ba-4b15-8430-80b378e88db4",
    :data-type-k-name "rel-optional-has-one-required",
    :data-type-name   "rel optional has one required",
    :datomic-type     :db.type/ref,
    :reg-text         "-"
    :description      "has-one relationship in the same database.  One side is required."}
   {:uuid-data-type   #uuid"5e99f93a-e657-4b06-81ef-915fe5b792a9",
    :data-type-k-name "integer",
    :data-type-name   "integer",
    :datomic-type     :db.type/bigint,
    :reg-text         "-"
    :description      "Integer"}
   {:uuid-data-type   #uuid"5e99f93a-099e-4d40-8614-3eaa6955a690",
    :data-type-k-name "string",
    :data-type-name   "string",
    :datomic-type     :db.type/string,
    :reg-text         "-"
    :description      "String"}
   {:uuid-data-type   #uuid"5e99f93a-64d0-4eac-8c6f-40076a8c50c1",
    :data-type-k-name "rel-optional-has-one-required-extern",
    :data-type-name   "rel optional has one required extern",
    :datomic-type     :db.type/uuid,
    :reg-text         "-"
    :description      "has-one relationship with an external database.  a required attr."}
   {:uuid-data-type   #uuid"5e99f93a-2af0-4b7f-b494-29e0c53c04a5",
    :data-type-k-name "upsert-key",
    :data-type-name   "upsert key",
    :datomic-type     :db.type/uuid,
    :reg-text         "-"
    :description      "Upsert Key attr"}

   ;;"file-upload-button"

   {:uuid-data-type   #uuid"62915b9c-27e1-4e5f-bfb3-d362d140b448",
    :data-type-k-name "file-upload-button",
    :data-type-name   "file-upload-button",
    :datomic-type     :db.type/string,
    :reg-text         "-",
    :description      "file-upload-button"}

   ]
  )
(def
  visual-type
  [{:uuid-visual-type   #uuid"63509f0f-2440-4c66-b300-af2b17a238bd",
    :ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3"],
    :visual-type-k-name "text-box",
    :description        "Text Box to enter the value"}
   {:uuid-visual-type   #uuid"63509f0f-fb5c-442d-b99d-56fc27831e79",
    :ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3"],
    :visual-type-k-name "slider",
    :description        "Slider to enter the value"}]
  )

;"cardinality-type"
(def
  cardinality-type
  [{:uuid-cardinality-type   #uuid"5e9a3ede-6028-4eb3-a125-155e1a7861b5",
    :cardinality-type-k-name "one",
    :cardinality-type-name   "One",
    :datomic-type            :db.cardinality/one,
    :description             "cardinality of one"}
   {:uuid-cardinality-type   #uuid"5e9a3ede-c388-4ef8-be8e-ec40a3821cc8",
    :cardinality-type-k-name "many",
    :cardinality-type-name   "Many",
    :datomic-type            :db.cardinality/many,
    :description             "cardinality of many"}])
;"attr-gen-type"
(def
  attr-gen-type
  [{:uuid-attr-gen-type   #uuid"5e9a4437-d7b7-420e-8fbc-35fd26bdb3f6",
    :attr-gen-type-k-name "util",
    :attr-gen-type-name   "util",
    :description          "generated through the tools utility"}
   {:uuid-attr-gen-type   #uuid"5e9a4437-3e7f-40da-a745-eb90b66c7c4e",
    :attr-gen-type-k-name "online",
    :attr-gen-type-name   "online",
    :description          "generated through online"}])
;"access-permission"
(def
  access-permission
  [{:access-permission-k-name "read",
    :access-permission-name   "Read",
    :description              "Read Access",
    :uuid-access-permission   #uuid"5ebda1dd-541f-4b9b-bb70-a3f70e1d6c1d"}
   {:access-permission-k-name "write",
    :access-permission-name   "Write",
    :description              "Write Access",
    :uuid-access-permission   #uuid"5ebda1dd-58c1-4d13-a0dd-529c7077a2f8"}
   {:access-permission-k-name "execute",
    :access-permission-name   "Execute",
    :description              "Execute Access",
    :uuid-access-permission   #uuid"5ebda1dd-e925-47d2-b19c-eb5b3abe43d5"}
   {:access-permission-k-name "no-access",
    :access-permission-name   "No Access",
    :description              "No Access",
    :uuid-access-permission   #uuid"5ebda1dd-3991-427d-8756-f1ce9477d9bc"}])
;"release"
(def
  release
  [{:release-release-id "release 1.0.0",
    :description        "release 1.0.0",
    :uuid-release       #uuid"5ebda8f4-9de9-4542-b79c-8a6b3a06ddf5"}]
  )

;"deploy-status-type"
(def
  deploy-status-type
  [{:deploy-status-type-k-name "in-process",
    :deploy-status-type-name   "In Process",
    :description               "deployment is in process",
    :uuid-deploy-status-type   #uuid"5ebdac72-225b-4456-b68e-0fd6adaaff3d"}
   {:deploy-status-type-k-name "deployed",
    :deploy-status-type-name   "Deployed",
    :description               "deployment is done",
    :uuid-deploy-status-type   #uuid"5ebdac72-8668-4d70-8464-df66a3f99eb4"}
   {:deploy-status-type-k-name "deployed-available",
    :deploy-status-type-name   "Deployed and available",
    :description               "deployed and available",
    :uuid-deploy-status-type   #uuid"5ebdac72-37a6-418d-a8d6-2cbdb66e60aa"}])
;"release-stage-type"
(def
  release-stage-type
  [{:release-stage-type-k-name "dev",
    :release-stage-type-name   "dev",
    :description               "Development",
    :uuid-release-stage-type   #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"}
   {:release-stage-type-k-name "qa",
    :release-stage-type-name   "qa",
    :description               "Quality Assurance",
    :uuid-release-stage-type   #uuid"5ebdb6d0-8484-4dc6-9786-74ed1ec79768"}
   {:release-stage-type-k-name "ext",
    :release-stage-type-name   "ext",
    :description               "external testing",
    :uuid-release-stage-type   #uuid"5ebdb6d0-6e37-45e5-ad48-fe296025c312"}
   {:release-stage-type-k-name "perf",
    :release-stage-type-name   "perf",
    :description               "Performance Testing",
    :uuid-release-stage-type   #uuid"5ebdb6d0-f7c1-4686-ba5a-00f372a65d4b"}
   {:release-stage-type-k-name "prod",
    :release-stage-type-name   "prod",
    :description               "Production",
    :uuid-release-stage-type   #uuid"5ebdb6d0-7182-4b26-82ba-a0be5cbcdfc0"}])
;"cf-stack"
(def
  cf-stack
  [{:cf-stack-k-name "nxt-server-dev5",
    :cf-stack-name   "nxt-server-dev5",
    :url             "-",
    :description     "nxt dev cloud formation",
    :env             "dev",
    :uuid-cf-stack   #uuid"5ebdb841-ddde-4edd-bf44-e20fe64f1a41"}
   ])

(comment (basic-util/squuid))


(def
  tenant-location
  [{:ref-tenant           [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :location-name        "United States Office"
    :description          "US Office"
    :ref-language         [:nxt.itools.language/uuid-language #uuid"5ebb1c9b-9423-4815-a27f-abe502a25383"],
    :ref-currency         [:nxt.itools.currency/uuid-currency #uuid"60c1631f-e439-4785-b541-f512b273a1d7"],
    :uuid-tenant-location #uuid"60c164e4-f00d-45fe-bbc5-523ab19350a5"}
   {:ref-tenant           [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :location-name        "India Office"
    :description          "India Office"
    :ref-language         [:nxt.itools.language/uuid-language #uuid"5ebb1c9b-9423-4815-a27f-abe502a25383"],
    :ref-currency         [:nxt.itools.currency/uuid-currency #uuid"60c163c4-161d-40c6-be03-c90ee84d50e9"],
    :uuid-tenant-location #uuid"60c16ada-de55-41c2-9ecc-924cf4a292af"}]
  )
(def
  corporate-location
  [{:ref-tenant              [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :ref-tenant-location     [:nxt.itools.tenant-location/uuid-tenant-location #uuid"60c164e4-f00d-45fe-bbc5-523ab19350a5"],
    :uuid-corporate-location #uuid"60c29a56-1ce5-4618-9b12-80221d294138"}
   ]
  )
;;corporate address
(def
  tenant-address
  [{:ref-tenant-location [:nxt.itools.tenant-location/uuid-tenant-location #uuid"60c164e4-f00d-45fe-bbc5-523ab19350a5"]
    :uuid-tenant-address #uuid"60c29b3f-3ca3-4273-a687-43a5d8401fab"
    :line1               "8787 Dublin Blvd"
    :line2               ""
    :city                "Dublin"
    :ref-country         [:nxt.itools.country/uuid-country #uuid"60c29cc1-9def-442b-80cd-9c6a3ba45091"]
    :ref-state           [:nxt.itools.state/uuid-state #uuid"60c29dfd-9fcd-4df0-a066-8ac7bfc4d065"]
    :zip                 "94568"
    }

   {:ref-tenant-location [:nxt.itools.tenant-location/uuid-tenant-location #uuid"60c16ada-de55-41c2-9ecc-924cf4a292af"] ;India location
    :uuid-tenant-address #uuid"60c29e9b-d86f-4c60-ab95-9f5869672d96"
    :line1               "1212 MG Road"
    :line2               ""
    :city                "Ernakulam"
    :ref-country         [:nxt.itools.country/uuid-country #uuid"60c29cd3-237e-45ea-b37a-89b52a3a7366"] ;India
    :ref-state           [:nxt.itools.state/uuid-state #uuid"60c29e01-82bb-4bc7-b0c4-0b77989826b8"] ;kerala
    :zip                 "688 787"
    }
   ]
  )

;;country
(def
  country
  [{:country-code "USA"
    :country-name "United States of America",
    :description  "United States"
    :uuid-country #uuid"60c29cc1-9def-442b-80cd-9c6a3ba45091"}
   {:country-code "IND"
    :country-name "India",
    :description  "India"
    :uuid-country #uuid"60c29cd3-237e-45ea-b37a-89b52a3a7366"}
   ])

;;state
(comment (basic-util/squuid))
(def
  state
  [{:state-code  "CA"
    :state-name  "California",
    :ref-country [:nxt.itools.country/uuid-country #uuid"60c29cc1-9def-442b-80cd-9c6a3ba45091"]
    :uuid-state  #uuid"60c29dfd-9fcd-4df0-a066-8ac7bfc4d065"}
   {:state-code  "OR"
    :state-name  "Oregon",
    :ref-country [:nxt.itools.country/uuid-country #uuid"60c29cc1-9def-442b-80cd-9c6a3ba45091"]
    :uuid-state  #uuid"60c29dff-e37e-4fea-8dc7-9d2452bd8ef7"}
   {:state-code  "WA"
    :state-name  "Washington",
    :ref-country [:nxt.itools.country/uuid-country #uuid"60c29cc1-9def-442b-80cd-9c6a3ba45091"]
    :uuid-state  #uuid"60c29e00-4de0-4b23-a4cb-8b6c4126ebb9"}
   {:state-code  "TX"
    :state-name  "Texas",
    :ref-country [:nxt.itools.country/uuid-country #uuid"60c29cc1-9def-442b-80cd-9c6a3ba45091"]
    :uuid-state  #uuid"60c29e00-b770-4207-b4ba-b9bfe36eb7ca"}

   {:state-code  "KEL"
    :state-name  "Kerala",
    :ref-country [:nxt.itools.country/uuid-country #uuid"60c29cd3-237e-45ea-b37a-89b52a3a7366"]
    :uuid-state  #uuid"60c29e01-82bb-4bc7-b0c4-0b77989826b8"}

   {:state-code  "TAN"
    :state-name  "Tamil Nadu",
    :ref-country [:nxt.itools.country/uuid-country #uuid"60c29cd3-237e-45ea-b37a-89b52a3a7366"]
    :uuid-state  #uuid"60c29e9c-af43-4a15-a886-ac459fd193fa"}
   {:state-code  "KAR"
    :state-name  "Karnataka",
    :ref-country [:nxt.itools.country/uuid-country #uuid"60c29cd3-237e-45ea-b37a-89b52a3a7366"]
    :uuid-state  #uuid"60c29e9c-d16e-4ff3-81c6-17180550ff1b"}
   {:state-code  "MAH"
    :state-name  "Maharashtra",
    :ref-country [:nxt.itools.country/uuid-country #uuid"60c29cd3-237e-45ea-b37a-89b52a3a7366"]
    :uuid-state  #uuid"60c29e9b-bf6c-48dd-87bf-5699eff414f2"}
   ])

(def
  currency
  [{:currency-code  "USD",
    :currency-name  "United States Dollar",
    :symbol         "$",
    :before-number? true
    :digits         2
    :uuid-currency  #uuid"60c1631f-e439-4785-b541-f512b273a1d7"}
   {:currency-code  "INR",
    :currency-name  "India Rupee",
    :symbol         "R",
    :before-number? true
    :digits         2
    :uuid-currency  #uuid"60c163c4-161d-40c6-be03-c90ee84d50e9"}
   ])


;"user-home-cell-type"
(def
  user-home-cell-type
  [{:user-home-cell-type-k-name "chart",
    :user-home-cell-type-name   "Chart",
    :description                "Chart Analytics",
    :uuid-user-home-cell-type   #uuid"62689039-f4b6-48d7-b955-943c38e6e40e"}
   {:user-home-cell-type-k-name "indicator",
    :user-home-cell-type-name   "Indicator",
    :description                "Indicator",
    :uuid-user-home-cell-type   #uuid"62689039-2c88-4e41-b184-07bcd474f095"}
   {:user-home-cell-type-k-name "image",
    :user-home-cell-type-name   "image",
    :description                "Image",
    :uuid-user-home-cell-type   #uuid"62689039-1ce6-4b40-84f8-8fc8d07b1ab3"}
   {:user-home-cell-type-k-name "bookmark",
    :user-home-cell-type-name   "Bookmark",
    :description                "Bookmarked Pages",
    :uuid-user-home-cell-type   #uuid"6271b191-11a2-49e8-9cf9-040ae46490ae"}
   {:user-home-cell-type-k-name "dash-config",
    :user-home-cell-type-name   "Dashboard Config",
    :description                "Dashboard Configuration",
    :uuid-user-home-cell-type   #uuid"6271b1a3-8495-49a2-be51-5eb54ef5b97c"}]
  )


;"plet-type"
(def
  plet-type
  [{:plet-type-k-name "entity-list",
    :plet-type-name   "Entity List",
    :description      "Entity List",
    :uuid-plet-type   #uuid"5ebdc119-906e-4842-8d1f-434cb57dcc50"}
   {:plet-type-k-name "entity-detail",
    :plet-type-name   "Entity View and Edit",
    :description      "Entity View and Edit",
    :uuid-plet-type   #uuid"5ebdc119-0526-438a-89c4-7f29a1ba9399"}
   ;;thumbnails
   {:plet-type-k-name "thumbnail",
    :plet-type-name   "Entity Thumbnail",
    :description      "Entity Thumbnail",
    :uuid-plet-type   #uuid"624e267f-d8af-4674-9177-9e0ae76e450b"}
   {:plet-type-k-name "udash",
    :plet-type-name   "User Dashboard",
    :description      "User Dashboard",
    :uuid-plet-type   #uuid"626c6dcd-e65f-4f6c-a605-863bbb37fcfa"}
   ]
  )

;"plet-cell-dat-event-type"
(def
  plet-cell-data-event-type
  [{:plet-cell-data-event-type-k-name "page-data-init",
    :plet-cell-data-event-type-name   "Page Data Init",
    :description                      "Page Cell Data Init",
    :uuid-plet-cell-data-event-type   #uuid"613ffc6e-c81c-4f31-8790-145068d95e77"}

   {:plet-cell-data-event-type-k-name "page-data-change",
    :plet-cell-data-event-type-name   "Page Data Change",
    :description                      "Page Data Change",
    :uuid-plet-cell-data-event-type   #uuid"613ffc98-d324-4c86-8ea6-f7815d5dd2ed"}
   ]
  )
;"device-type"
(def
  device-type
  [{:device-type-k-name "web",
    :device-type-name   "Web",
    :description        "Laptop and desktop browsers",
    :uuid-device-type   #uuid"5ebdd37c-8095-4eb6-86c6-cd2ce6344d78"}
   {:device-type-k-name "web-login",
    :device-type-name   "Web Login",
    :description        "Login for web  - Laptop and desktop browsers",
    :uuid-device-type   #uuid"61859a50-3423-405f-86ee-8360ef524a1a"}
   {:device-type-k-name "tablet",
    :device-type-name   "Tablet",
    :description        "ipad type",
    :uuid-device-type   #uuid"5ebdd37c-dc4d-4e9d-a1f2-e0fa652d20cd"}
   {:device-type-k-name "mobile",
    :device-type-name   "Mobile",
    :description        "Mobile phones",
    :uuid-device-type   #uuid"5ebdd37c-909c-4634-9207-8f073256819a"}])

(comment
  (basic-util/squuid)
  )


;"row-col-type"
(def
  row-col-type
  [{:row-col-type-k-name "variable-col-row",
    :row-col-type-name   "variable col row",
    :description         "variable col row",
    :uuid-row-col-type   #uuid"626c35d4-f7fe-41fa-a835-24ad738e3c0c"}
   {:row-col-type-k-name "1-col-4-row-central",
    :row-col-type-name   "1 col-4 row central",
    :description         "one column-4 row alighned centrally",
    :uuid-row-col-type   #uuid"611af677-e446-4c9c-b058-92017e5f6774"}
   {:row-col-type-k-name "1-col-row",
    :row-col-type-name   "1 col row",
    :description         "one column row",
    :uuid-row-col-type   #uuid"5ebdd3eb-b613-4111-a295-9d8053d676c7"}

   {:row-col-type-k-name "1-col-row-with-image",
    :row-col-type-name   "1 col row with image",
    :description         "one column row, but row is in half-page, the other half is for the image",
    :uuid-row-col-type   #uuid"628d457c-71c7-4d8a-9794-dc89fbe50a16"}

   {:row-col-type-k-name "2-col-row",
    :row-col-type-name   "2 col row",
    :description         "two column row",
    :uuid-row-col-type   #uuid"5ebdd3eb-a757-4d9e-bbe5-017176b600a7"}
   {:row-col-type-k-name "3-col-row",
    :row-col-type-name   "3 col row",
    :description         "three column row",
    :uuid-row-col-type   #uuid"5ebdd3eb-7598-4a60-9e3f-69b1af8033da"}
   {:row-col-type-k-name "4-col-row",
    :row-col-type-name   "4 col row",
    :description         "four column row",
    :uuid-row-col-type   #uuid"5ebdd3eb-6ac3-43b4-9ed5-183ce5fd1155"}
   {:row-col-type-k-name "12-col-row",
    :row-col-type-name   "12 col row",
    :description         "12 column row",
    :uuid-row-col-type   #uuid"5f22282e-3e7c-4c26-9354-c2a0d466ac55"}])
;"row-select-type"
(def
  row-select-type
  [{:row-select-type-k-name "single",
    :row-select-type-name   "Single Row",
    :description            "single row select",
    :uuid-row-select-type   #uuid"5ebdd453-e46c-4110-9b32-0844ebb3ef4c"}
   {:row-select-type-k-name "multiple",
    :row-select-type-name   "Multiple Row",
    :description            "multiple row select",
    :uuid-row-select-type   #uuid"5ebdd453-c233-4b23-9d4a-998697592173"}
   {:row-select-type-k-name "no-select",
    :row-select-type-name   "No Row Select",
    :description            "no row select",
    :uuid-row-select-type   #uuid"5ebdd453-bac5-4ff7-9fbc-874e82bc26b4"}])
(comment

  {:cell-control-type-k-name "plet",
   :cell-control-type-name   "Pagelet",
   :description              "Ref to a apgelet",
   :uuid-cell-control-type   (basic-util/squuid)}
  )


;"cell-control-type"
(def
  cell-control-type
  [{:cell-control-type-k-name "tab",
    :cell-control-type-name   "Tab",
    :description              "Tab",
    :uuid-cell-control-type   #uuid"5ebdd4c7-69f5-4913-8fc7-3b402fc13b3e"}
   {:cell-control-type-k-name "data",
    :cell-control-type-name   "Data",
    :description              "Data",
    :uuid-cell-control-type   #uuid"5ebdd4c7-adeb-446b-a3b8-c86a5833c6e4"}
   {:cell-control-type-k-name "line",
    :cell-control-type-name   "Line",
    :description              "Horizontal Line",
    :uuid-cell-control-type   #uuid"5f28c1dd-f59d-46cd-b5e1-822c711e77d0"}
   {:cell-control-type-k-name "alert",
    :cell-control-type-name   "Alert",
    :description              "Messages and alerts",
    :uuid-cell-control-type   #uuid"611af986-732b-4126-942c-d629b934038e"}
   {:cell-control-type-k-name "button",
    :cell-control-type-name   "Button",
    :description              "Button as part of a button group",
    :uuid-cell-control-type   #uuid"611af9ec-7720-4758-aa05-96b65bf2768d"}
   {:cell-control-type-k-name "plet",
    :cell-control-type-name   "Pagelet",
    :description              "Ref to a apgelet",
    :uuid-cell-control-type   #uuid"611b481a-a568-4c5c-8dbe-b08c057ba650"}
   ])

;"plet-mode-type"
(def plet-mode-type
  [{:plet-mode-type-k-name "login-add",
    :plet-mode-type-name   "Login Add",
    :description           "Login Add plet.  This is similar to add , but no save button group.  ",
    :uuid-plet-mode-type   #uuid"6125c159-dc5b-4dd7-a5de-cc085d69225d"}
   {:plet-mode-type-k-name "add",
    :plet-mode-type-name   "Add",
    :description           "Add plet",
    :uuid-plet-mode-type   #uuid"5fe3e2e7-d670-421f-be95-c7030ce38d06"}
   {:plet-mode-type-k-name "view",
    :plet-mode-type-name   "View",
    :description           "View Mode",
    :uuid-plet-mode-type   #uuid"5fe3e2e9-2cb3-4757-b189-9f0d50fa139a"}
   {:plet-mode-type-k-name "edit",
    :plet-mode-type-name   "Edit",
    :description           "Edit Mode",
    :uuid-plet-mode-type   #uuid"5fe3e2ea-4b6b-4cfc-99d5-01d67f9c0cad"}
   {:plet-mode-type-k-name "list",
    :plet-mode-type-name   "List",
    :description           "List Mode",
    :uuid-plet-mode-type   #uuid"624e88f1-e5d9-4d37-a6c1-11e927088bf8"}
   {:plet-mode-type-k-name "thumbnail",
    :plet-mode-type-name   "Thumbnail List",
    :description           "Thumbnail List Mode",
    :uuid-plet-mode-type   #uuid"6267494e-3b90-45fc-9e36-1172802ae325"}

   {:plet-mode-type-k-name "view-with-image",
    :plet-mode-type-name   "View with image",
    :description           "View with image",
    :uuid-plet-mode-type   #uuid"628d5418-4a61-48aa-967c-d4ddb9a39024"}


   {:plet-mode-type-k-name "udash",
    :plet-mode-type-name   "User Dashboard",
    :description           "User Dashboard mode",
    :uuid-plet-mode-type   #uuid"626cc925-3306-4535-ac0e-93a4e23c7077"}
   ])

(comment
  nxt.tools.util.basic
  (nxt.tools.util.basic/squuid)
  [{:style-context-type-k-name "primary",
    :style-context-type-name   "Primary",
    :description               "Primary",
    :uuid-style-context-type   (basic-util/squuid)}
   {:style-context-type-k-name "secondary",
    :style-context-type-name   "Secondary",
    :description               "Secondary",
    :uuid-style-context-type   (basic-util/squuid)}
   {:style-context-type-k-name "success",
    :style-context-type-name   "Success",
    :description               "Success",
    :uuid-style-context-type   (basic-util/squuid)}
   {:style-context-type-k-name "warning",
    :style-context-type-name   "Warning",
    :description               "Warning",
    :uuid-style-context-type   (basic-util/squuid)}
   {:style-context-type-k-name "danger",
    :style-context-type-name   "Danger",
    :description               "Danger",
    :uuid-style-context-type   (basic-util/squuid)}
   {:style-context-type-k-name "info",
    :style-context-type-name   "Info",
    :description               "Info",
    :uuid-style-context-type   (basic-util/squuid)}
   {:style-context-type-k-name "light",
    :style-context-type-name   "Light",
    :description               "Light",
    :uuid-style-context-type   (basic-util/squuid)}
   {:style-context-type-k-name "dark",
    :style-context-type-name   "Dark",
    :description               "Dark",
    :uuid-style-context-type   (basic-util/squuid)}
   ]
  )

(comment
  [
   {:icon-type-k-name "no-icon",
    :icon-type-name   "No Icon",
    :description      "No icon",
    :uuid-icon-type   (basic-util/squuid)}
   {:icon-type-k-name "info",
    :icon-type-name   "Info Icon",
    :description      "Info icon",
    :uuid-icon-type   (basic-util/squuid)}
   {:icon-type-k-name "warning",
    :icon-type-name   "Warning Icon",
    :description      "Warning icon",
    :uuid-icon-type   (basic-util/squuid)}
   {:icon-type-k-name "danger",
    :icon-type-name   "Danger Icon",
    :description      "Danger icon",
    :uuid-icon-type   (basic-util/squuid)}
   ]
  )

;"icon-type--type"
(def
  icon-type
  [{:icon-type-k-name "no-icon",
    :icon-type-name   "No Icon",
    :description      "No icon",
    :uuid-icon-type   #uuid"611afe91-0bf0-42b8-8dee-680be001e8fe"}
   {:icon-type-k-name "info",
    :icon-type-name   "Info Icon",
    :description      "Info icon",
    :uuid-icon-type   #uuid"611afe91-25b8-4425-adfc-799a978ccf06"}
   {:icon-type-k-name "warning",
    :icon-type-name   "Warning Icon",
    :description      "Warning icon",
    :uuid-icon-type   #uuid"611afe91-022d-424d-a955-0a44274e08b9"}
   {:icon-type-k-name "danger",
    :icon-type-name   "Danger Icon",
    :description      "Danger icon",
    :uuid-icon-type   #uuid"611afe91-213e-4478-af9c-70a174c40bec"}]
  )
;"style-context-type--type"
(def
  style-context-type
  [{:style-context-type-k-name "primary",
    :style-context-type-name   "Primary",
    :description               "Primary",
    :uuid-style-context-type   #uuid"611af84a-695a-4234-9a38-81458ef470e6"}
   {:style-context-type-k-name "secondary",
    :style-context-type-name   "Secondary",
    :description               "Secondary",
    :uuid-style-context-type   #uuid"611af84a-2a52-48dc-a3c6-d9f71ac9126a"}
   {:style-context-type-k-name "success",
    :style-context-type-name   "Success",
    :description               "Success",
    :uuid-style-context-type   #uuid"611af84a-d2dd-4361-b939-2939c30a7695"}
   {:style-context-type-k-name "warning",
    :style-context-type-name   "Warning",
    :description               "Warning",
    :uuid-style-context-type   #uuid"611af84a-2fa2-437e-bd6c-cd80d0b809c0"}
   {:style-context-type-k-name "danger",
    :style-context-type-name   "Danger",
    :description               "Danger",
    :uuid-style-context-type   #uuid"611af84a-a94e-46d4-87fc-30574d44d179"}
   {:style-context-type-k-name "info",
    :style-context-type-name   "Info",
    :description               "Info",
    :uuid-style-context-type   #uuid"611af84a-f306-4284-86f0-062bf4385533"}
   {:style-context-type-k-name "light",
    :style-context-type-name   "Light",
    :description               "Light",
    :uuid-style-context-type   #uuid"611af84a-c86f-46b7-84bf-d29c1d034905"}
   {:style-context-type-k-name "dark",
    :style-context-type-name   "Dark",
    :description               "Dark",
    :uuid-style-context-type   #uuid"611af84a-314d-459c-af22-01ba0ba2133b"}]
  )
;"html-component"
(def
  html-component
  [{:html-component-k-name "alert",
    :html-component-name   "Alert",
    :description           "Alert",
    :editable?             false
    :uuid-html-component   #uuid"5ebdd53b-65bd-4a62-ae3a-4796cea6d1b0"}
   {:html-component-k-name "single-select-drop-down",
    :html-component-name   "Single Select Drop Down",
    :description           "Drop Down",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-33fe-4c31-9c36-2d891fc45a87"}
   {:html-component-k-name "multi--select-drop-down",
    :html-component-name   "Multi Select Drop Down",
    :description           "Drop Down",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-cb46-4221-b7c2-9a3ac5ab6335"}
   {:html-component-k-name "text-input",
    :html-component-name   "Text Input",
    :description           "Form Group for text input",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-3397-4f39-bee6-1eac4e22ad29"}
   {:html-component-k-name "text-area",
    :html-component-name   "Text Area",
    :description           "Form Group for paragraph",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-f5a5-4fe1-885f-b0ae8160ec1b"}
   {:html-component-k-name "radio-buttons",
    :html-component-name   "Radio Buttons",
    :description           "Radio buttons as a single select",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-55b8-4f70-ad4d-db85bfd95f52"}
   {:html-component-k-name "radio-boolean",
    :html-component-name   "Radio Boolean",
    :description           "2 or 3 radio buttons to select true or false or none",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-07a4-460b-911e-59817b4340d1"}
   {:html-component-k-name "check-boxes",
    :html-component-name   "Check Boxes",
    :description           "Checkboxes are used if you want the user to select any number of options from a list of preset options.",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-a6a3-48a5-839c-372f05c4d23d"}
   {:html-component-k-name "checkbox-toggle",
    :html-component-name   "single checkbox",
    :description           "A single check box as a toggle ",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-0992-43ac-be20-4be6d923499d"}
   {:html-component-k-name "toggle-switch",
    :html-component-name   "toggle switch",
    :description           "A single check box as a toggle ",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-828b-457f-8736-2efdc7573e4c"}
   {:html-component-k-name "range",
    :html-component-name   "range selecter",
    :description           "range selecter ",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-7d4b-43f6-9db6-bf3c7d177440"}
   {:html-component-k-name "date-picker",
    :html-component-name   "Date Picker",
    :description           "Date Picker",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-9f36-464b-9158-275e76d3188e"}
   {:html-component-k-name "date-time-picker",
    :html-component-name   "Date and Time  Picker",
    :description           "Date and TImePicker",
    :editable?             true
    :uuid-html-component   #uuid"5ebdd53b-dd4f-4956-8c89-2e9695c352e0"}])
;"label-position"
(def
  label-position
  [{:label-position-k-name "left",
    :label-position-name   "Left Side",
    :description           "On the left side",
    :uuid-label-position   #uuid"5ebdd5d4-953d-420b-9239-49c4db8b9e06"}
   {:label-position-k-name "right",
    :label-position-name   "Right Side",
    :description           "Right Side",
    :uuid-label-position   #uuid"5ebdd5d4-dafb-4c22-82d2-f69078570381"}
   {:label-position-k-name "top",
    :label-position-name   "Top",
    :description           "Top Side",
    :uuid-label-position   #uuid"5ebdd5d4-adbc-4e30-87a1-1566fa9941e9"}])
;;;
;;;
;;application
(def
  application
  [{:ref-tenant         [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :application-k-name "itools",
    :application-name   "nxt Tools",
    :ref-tenant-owner   [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :description        "nxt Tools Application",
    ;:ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    :uuid-application   #uuid"5ebe1935-ee76-4ff1-92c4-18bc9d743031"}


   {:ref-tenant         [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :application-k-name "legal-accounting",
    :application-name   "Legal Accounting",
    :ref-tenant-owner   [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :description        "Legal Accounting",
    ;:ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    :uuid-application   #uuid"5ebdf1d4-66ae-4f39-b291-73d190f578c4"}

   {:ref-tenant         [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :application-k-name "voip-software",
    :application-name   "VOIP Software",
    ;:ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    :ref-tenant-owner   [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :description        "Voip software",
    :uuid-application   #uuid"5ebdf1d4-37c6-4f63-87ec-5dc24257667c"}

   {:ref-tenant         [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :application-k-name "crm",
    :application-name   "crm",
    :ref-tenant-owner   [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :description        "Customer relations management",
    ;:ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    :uuid-application   #uuid"5ebdf1d4-a8fe-44de-b6f6-aea44dc9a5e3"}

   {:ref-tenant         [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :application-k-name "item-management",
    :application-name   "Item Management",
    :ref-tenant-owner   [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :description        "Item Management",
    ;:ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    :uuid-application   #uuid"5ebdf1d4-ab97-44f6-a790-61b5588df972"}
   {:ref-tenant         [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :application-k-name "inventory-management",
    :application-name   "Inventory Management",
    :ref-tenant-owner   [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :description        "Inventory Management",
    ;:ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    :uuid-application   #uuid"5ebdf1d4-2779-4b58-9cec-8b5ffb656868"}
   {:ref-tenant         [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :application-k-name "order-management",
    :application-name   "Order Management",
    :ref-tenant-owner   [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :description        "Order Management",
    ;:ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    :uuid-application   #uuid"5ebdf1d4-9f21-489b-bca7-548db24c4670"}
   {:ref-tenant         [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :application-k-name "procurement",
    :application-name   "Procurement",
    :ref-tenant-owner   [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :description        "Purchasing Management",
    ;:ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    :uuid-application   #uuid"5ebdf1d4-afb8-494b-a40c-579524e49905"}
   {:ref-tenant         [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :application-k-name "manufacturing",
    :application-name   "manufacturing",
    :ref-tenant-owner   [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :description        "Manaufacturing management",
    ;:ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    :uuid-application   #uuid"5ebdf1d5-cddf-44ec-b5f4-19f70a7e3c70"}
   {:ref-tenant         [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :application-k-name "cost-accounting",
    :application-name   "Cost Accounting",
    :ref-tenant-owner   [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :description        "Cost Accounting",
    ;:ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    :uuid-application   #uuid"5ebdf1d5-eed3-421b-b20f-aa7f41e37e94"}])

;;access-group
(def
  access-group
  [{:ref-tenant          [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :access-group-k-name "admin",
    :access-group-name   "Administrator",
    :description         "Administrator acccess - full access",
    :uuid-access-group   #uuid"5ebe278c-3054-40c1-b58d-067cc5b9f64c"}
   {:ref-tenant          [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :access-group-k-name "dev",
    :access-group-name   "Developer",
    :description         "Developer acccess - full access except users",
    :uuid-access-group   #uuid"5ebe278c-5e5b-4f59-92bc-85b90b23bef8"}
   {:ref-tenant          [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :access-group-k-name "app-user",
    :access-group-name   "Application User",
    :description         "Developer acccess - full access except tools app",
    :uuid-access-group   #uuid"5ebe278c-e36a-47ff-9dab-d6b58bcace78"}
   {:ref-tenant          [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :access-group-k-name "tenant-admin",
    :access-group-name   "Tenant Admin",
    :description         "Full acccess - full access for a particular Tenant",
    :uuid-access-group   #uuid"5ebe278c-d2dc-43d2-8e90-18dfe7341f15"}
   {:ref-tenant          [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :access-group-k-name "tenant-app",
    :access-group-name   "Tenant App",
    :description         "Full acccess to all applications except tools app -  for a particular Tenant",
    :uuid-access-group   #uuid"5ebe278c-5270-4bb6-9bc4-8cd4c762aedc"}]
  )


(def
  data-type-comparator
  [{:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-12e8-4e2a-b3f9-de9ca9328da8"],
    :data-type-comparator-k-name "is",
    :data-type-comparator-name   "=",
    :description                 "true or false",
    :uuid-data-type-comparator   #uuid"5ebeea42-0e3a-4908-9dec-1b45a7f80611"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-7985-4226-ae44-9abe8d28014f"],
    :data-type-comparator-k-name "is",
    :data-type-comparator-name   "=",
    :description                 "equals",
    :uuid-data-type-comparator   #uuid"5ebeea42-71c6-4547-9cb2-b001ee0953f8"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-7985-4226-ae44-9abe8d28014f"],
    :data-type-comparator-k-name "greater-than",
    :data-type-comparator-name   ">",
    :description                 "greater than",
    :uuid-data-type-comparator   #uuid"5ebeea42-4083-4000-8a0a-8c4e33d138ac"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-7985-4226-ae44-9abe8d28014f"],
    :data-type-comparator-k-name "greater-than-equal-to",
    :data-type-comparator-name   ">=",
    :description                 "greater than equal to",
    :uuid-data-type-comparator   #uuid"5ebeea42-f40d-40bc-95d0-4c1a9dc01d85"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-7985-4226-ae44-9abe8d28014f"],
    :data-type-comparator-k-name "less-than",
    :data-type-comparator-name   "<",
    :description                 "less than",
    :uuid-data-type-comparator   #uuid"5ebeea42-2b8c-4d22-a70e-48729ecf5d68"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-7985-4226-ae44-9abe8d28014f"],
    :data-type-comparator-k-name "less-than-equal-to",
    :data-type-comparator-name   "<=",
    :description                 "less than equal to",
    :uuid-data-type-comparator   #uuid"5ebeea42-3862-459c-a1c1-b38def7495c9"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-7985-4226-ae44-9abe8d28014f"],
    :data-type-comparator-k-name "between",
    :data-type-comparator-name   "><",
    :description                 "between",
    :uuid-data-type-comparator   #uuid"5ebeea42-934d-4863-bc7e-14782d165ab5"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e84d-4204-bab5-99303a5afb51"],
    :data-type-comparator-k-name "is",
    :data-type-comparator-name   "=",
    :description                 "equals",
    :uuid-data-type-comparator   #uuid"5ebeea42-c770-4b8e-a58d-cc74f8aeae5e"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e84d-4204-bab5-99303a5afb51"],
    :data-type-comparator-k-name "starts-with",
    :data-type-comparator-name   "starts with",
    :description                 "starts with",
    :uuid-data-type-comparator   #uuid"5ebeea42-1544-4503-bb23-eef1ec6875ea"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e84d-4204-bab5-99303a5afb51"],
    :data-type-comparator-k-name "ends-with",
    :data-type-comparator-name   "ends with",
    :description                 "ends with",
    :uuid-data-type-comparator   #uuid"5ebeea42-c20c-4185-9191-557b5148363a"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e84d-4204-bab5-99303a5afb51"],
    :data-type-comparator-k-name "in",
    :data-type-comparator-name   "in",
    :description                 "any of",
    :uuid-data-type-comparator   #uuid"5ebeea42-4b88-4916-a5e1-96798384851b"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e84d-4204-bab5-99303a5afb51"],
    :data-type-comparator-k-name "contains",
    :data-type-comparator-name   "contains",
    :description                 "contains",
    :uuid-data-type-comparator   #uuid"5ebeea42-8ea2-4084-9ae9-77b8339f63a3"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3"],
    :data-type-comparator-k-name "is",
    :data-type-comparator-name   "=",
    :description                 "equals",
    :uuid-data-type-comparator   #uuid"5ebeea42-df9a-4ab9-be32-0e0072df8a6d"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3"],
    :data-type-comparator-k-name "greater-than",
    :data-type-comparator-name   ">",
    :description                 "greater than",
    :uuid-data-type-comparator   #uuid"5ebeea42-454e-4c92-a21c-ab9d897801bb"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3"],
    :data-type-comparator-k-name "greater-than-equal-to",
    :data-type-comparator-name   ">=",
    :description                 "greater than equal to",
    :uuid-data-type-comparator   #uuid"5ebeea42-f14e-43f8-b5dd-35c8a648ee24"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3"],
    :data-type-comparator-k-name "less-than",
    :data-type-comparator-name   "<",
    :description                 "less than",
    :uuid-data-type-comparator   #uuid"5ebeea42-6613-4e63-8c4a-cc069bbd3ccb"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3"],
    :data-type-comparator-k-name "less-than-equal-to",
    :data-type-comparator-name   "<=",
    :description                 "less than equal to",
    :uuid-data-type-comparator   #uuid"5ebeea42-8868-4b6c-a86a-e1923b841a61"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3"],
    :data-type-comparator-k-name "between",
    :data-type-comparator-name   "><",
    :description                 "between",
    :uuid-data-type-comparator   #uuid"5ebeea42-05ac-465c-88ae-44f60216561c"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-ce84-486e-9a19-06cf7f927e23"],
    :data-type-comparator-k-name "is",
    :data-type-comparator-name   "=",
    :description                 "equals",
    :uuid-data-type-comparator   #uuid"5ebeea42-98ad-43c2-9393-f008ac313dcf"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-ce84-486e-9a19-06cf7f927e23"],
    :data-type-comparator-k-name "greater-than",
    :data-type-comparator-name   ">",
    :description                 "greater than",
    :uuid-data-type-comparator   #uuid"5ebeea42-e9a2-4467-959e-28628b03f28a"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-ce84-486e-9a19-06cf7f927e23"],
    :data-type-comparator-k-name "greater-than-equal-to",
    :data-type-comparator-name   ">=",
    :description                 "greater than equal to",
    :description-2               "greater than equal to",
    :uuid-data-type-comparator   #uuid"5ebeea42-0342-4713-ae1f-f3c5c03b1bd7"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-ce84-486e-9a19-06cf7f927e23"],
    :data-type-comparator-k-name "less-than",
    :data-type-comparator-name   "<",
    :description                 "less than",
    :uuid-data-type-comparator   #uuid"5ebeea42-d2b5-43f0-b81e-b01ff274005e"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-ce84-486e-9a19-06cf7f927e23"],
    :data-type-comparator-k-name "less-than-equal-to",
    :data-type-comparator-name   "<=",
    :description                 "less than equal to",
    :uuid-data-type-comparator   #uuid"5ebeea42-eb68-4c5a-b071-840e0498a853"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-ce84-486e-9a19-06cf7f927e23"],
    :data-type-comparator-k-name "between",
    :data-type-comparator-name   "><",
    :description                 "between",
    :uuid-data-type-comparator   #uuid"5ebeea42-4f04-4a87-b73f-57e1a64e9fd6"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-66f7-44cd-8296-600a7107a6c5"],
    :data-type-comparator-k-name "is",
    :data-type-comparator-name   "=",
    :description                 "equals",
    :uuid-data-type-comparator   #uuid"5ebeea42-050c-494b-9369-ab9a4cf11b45"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-66f7-44cd-8296-600a7107a6c5"],
    :data-type-comparator-k-name "starts-with",
    :data-type-comparator-name   "starts with",
    :description                 "starts with",
    :uuid-data-type-comparator   #uuid"5ebeea42-b92d-44b7-adc2-6748bc18639e"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-66f7-44cd-8296-600a7107a6c5"],
    :data-type-comparator-k-name "ends-with",
    :data-type-comparator-name   "ends with",
    :description                 "ends with",
    :uuid-data-type-comparator   #uuid"5ebeea42-abac-4c5b-96c5-e8ebd39567b2"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-66f7-44cd-8296-600a7107a6c5"],
    :data-type-comparator-k-name "in",
    :data-type-comparator-name   "in",
    :description                 "any of",
    :uuid-data-type-comparator   #uuid"5ebeea42-970a-4df9-893b-6d439adbd97e"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-66f7-44cd-8296-600a7107a6c5"],
    :data-type-comparator-k-name "contains",
    :data-type-comparator-name   "contains",
    :description                 "contains",
    :uuid-data-type-comparator   #uuid"5ebeea42-21c1-4c45-bdc7-9bf4cd21934c"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-20d7-48ee-a900-ae45aa130c58"],
    :data-type-comparator-k-name "is",
    :data-type-comparator-name   "=",
    :description                 "equals",
    :uuid-data-type-comparator   #uuid"5ebeea42-9890-469d-9af5-1b259ae07c33"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-20d7-48ee-a900-ae45aa130c58"],
    :data-type-comparator-k-name "greater-than",
    :data-type-comparator-name   ">",
    :description                 "greater than",
    :uuid-data-type-comparator   #uuid"5ebeea42-497d-42c6-ab09-356b3d057736"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-20d7-48ee-a900-ae45aa130c58"],
    :data-type-comparator-k-name "greater-than-equal-to",
    :data-type-comparator-name   ">=",
    :description                 "greater than equal to",
    :uuid-data-type-comparator   #uuid"5ebeea42-766d-4803-be72-660b06eac803"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-20d7-48ee-a900-ae45aa130c58"],
    :data-type-comparator-k-name "less-than",
    :data-type-comparator-name   "<",
    :description                 "less than",
    :uuid-data-type-comparator   #uuid"5ebeea42-f51f-4bd7-b2a9-b0d7b3ebec24"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-20d7-48ee-a900-ae45aa130c58"],
    :data-type-comparator-k-name "less-than-equal-to",
    :data-type-comparator-name   "<=",
    :description                 "less than equal to",
    :uuid-data-type-comparator   #uuid"5ebeea42-05e4-4c72-8d58-35a2e82434e8"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-20d7-48ee-a900-ae45aa130c58"],
    :data-type-comparator-k-name "between",
    :data-type-comparator-name   "><",
    :description                 "between",
    :uuid-data-type-comparator   #uuid"5ebeea42-c090-4a01-b3bc-8c3213d7e720"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-c680-4a42-a21d-d81e60300a5a"],
    :data-type-comparator-k-name "is",
    :data-type-comparator-name   "=",
    :description                 "equals",
    :uuid-data-type-comparator   #uuid"5ebeea42-5341-4de3-b246-7bcc3e8bbb96"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e657-4b06-81ef-915fe5b792a9"],
    :data-type-comparator-k-name "is",
    :data-type-comparator-name   "=",
    :description                 "equals",
    :uuid-data-type-comparator   #uuid"5ebeea42-6031-4c57-b05a-ed94f0a1f2ef"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e657-4b06-81ef-915fe5b792a9"],
    :data-type-comparator-k-name "greater-than",
    :data-type-comparator-name   ">",
    :description                 "greater than",
    :uuid-data-type-comparator   #uuid"5ebeea42-a43b-4de3-95c1-e4a340f257e8"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e657-4b06-81ef-915fe5b792a9"],
    :data-type-comparator-k-name "greater-than-equal-to",
    :data-type-comparator-name   ">=",
    :description                 "greater than equal to",
    :uuid-data-type-comparator   #uuid"5ebeea42-dc2d-4598-bb72-73968b3d9eb1"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e657-4b06-81ef-915fe5b792a9"],
    :data-type-comparator-k-name "less-than",
    :data-type-comparator-name   "<",
    :description                 "less than",
    :uuid-data-type-comparator   #uuid"5ebeea42-27dc-4d4f-ab74-2106bcdd5e34"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e657-4b06-81ef-915fe5b792a9"],
    :data-type-comparator-k-name "less-than-equal-to",
    :data-type-comparator-name   "<=",
    :description                 "less than equal to",
    :uuid-data-type-comparator   #uuid"5ebeea42-d860-4a3c-8b2d-326cc4e86219"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e657-4b06-81ef-915fe5b792a9"],
    :data-type-comparator-k-name "between",
    :data-type-comparator-name   "><",
    :description                 "between",
    :uuid-data-type-comparator   #uuid"5ebeea42-814b-4ea0-944d-513c590e4a8b"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-099e-4d40-8614-3eaa6955a690"],
    :data-type-comparator-k-name "is",
    :data-type-comparator-name   "=",
    :description                 "equals",
    :uuid-data-type-comparator   #uuid"5ebeea42-b0e3-41a8-b6ce-5edda0460576"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-099e-4d40-8614-3eaa6955a690"],
    :data-type-comparator-k-name "starts-with",
    :data-type-comparator-name   "starts with",
    :description                 "starts with",
    :uuid-data-type-comparator   #uuid"5ebeea42-4d58-47a6-a351-6b90bac0a449"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-099e-4d40-8614-3eaa6955a690"],
    :data-type-comparator-k-name "ends-with",
    :data-type-comparator-name   "ends with",
    :description                 "ends with",
    :uuid-data-type-comparator   #uuid"5ebeea42-79c9-4dd7-b9bd-6cc5cdc5179b"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-099e-4d40-8614-3eaa6955a690"],
    :data-type-comparator-k-name "in",
    :data-type-comparator-name   "in",
    :description                 "any of",
    :uuid-data-type-comparator   #uuid"5ebeea42-1d9d-4657-ba7a-622b40becab2"}
   {:ref-data-type               [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-099e-4d40-8614-3eaa6955a690"],
    :data-type-comparator-k-name "contains",
    :data-type-comparator-name   "contains",
    :description                 "contains",
    :uuid-data-type-comparator   #uuid"5ebeea42-1976-4452-ace5-dbe272680c55"}]

  )

(def
  current-release
  [{:ref-release          [:nxt.itools.release/uuid-release #uuid"5ebda8f4-9de9-4542-b79c-8a6b3a06ddf5"],
    :uuid-current-release #uuid"5ebf0543-1f37-4b27-acf9-9a809ef7af50"}])
(def
  datomic-db
  [{:ref-cf-stack      [:nxt.itools.cf-stack/uuid-cf-stack #uuid"5ebdb841-ddde-4edd-bf44-e20fe64f1a41"],
    :datomic-db-k-name "nxt-dbxy-dev-tools"
    :datomic-db-name   "nxt-dbxy-dev-tools"
    :description       "dev database",
    :uuid-datomic-db   #uuid"5ebf04ba-d621-427b-a633-d98bc956df32"}])
(def
  app-deployment-db
  [{
    :uuid-app-deployment-db #uuid"60bab0a0-a4cf-4485-82b7-c19b7a7fbe24"
    :ref-tenant             [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :ref-release-stage-type [:nxt.itools.release-stage-type/uuid-release-stage-type #uuid"5ebdb6d0-3f70-4289-8e16-eb22e3529293"]
    ;:ref-release-stage-type-from [:nxt.itools.app-deployment-db/uuid-app-deployment-db  #uuid"60bab0a0-a4cf-4485-82b7-c19b7a7fbe24"]
    :ref-datomic-db         [:nxt.itools.datomic-db/uuid-datomic-db #uuid"5ebf04ba-d621-427b-a633-d98bc956df32"]
    }])

(comment
  (basic-util/squuid)

  )

(def
  menu-group
  [{:ref-application   [:nxt.itools.application/uuid-application #uuid"5ebe1935-ee76-4ff1-92c4-18bc9d743031"],
    :ref-tenant        [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :ref-tenant-owner  [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :menu-group-k-name "itools",
    :menu-group-name   "nxt Tools",
    :description       "nxt Tool application menu",
    :uuid-menu-group   #uuid"5ebf2b81-9496-4be3-b0b0-dc84a847ca49"}

   {:ref-application   [:nxt.itools.application/uuid-application #uuid"5ebe1935-ee76-4ff1-92c4-18bc9d743031"],
    :ref-tenant        [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :ref-tenant-owner  [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :menu-group-k-name "user",
    :menu-group-name   "nxt Current User",
    :description       "nxt Current User",
    :uuid-menu-group   #uuid"6268ee2a-4d60-4b77-8285-885c1f8ec7c6"}

   {:ref-application   [:nxt.itools.application/uuid-application #uuid"5ebdf1d4-afb8-494b-a40c-579524e49905"],
    :ref-tenant        [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :ref-tenant-owner  [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :menu-group-k-name "nxt-procurement",
    :menu-group-name   "nxt Procurement",
    :description       "nxt Procurement",
    :uuid-menu-group   #uuid"618dff75-fd2c-4a08-8999-23ed2be828c7"}

   ])

(def
  menu-group-label-text
  [{:ref-menu-group               [:nxt.itools.menu-group/uuid-menu-group #uuid"5ebf2b81-9496-4be3-b0b0-dc84a847ca49"],
    :menu-group-label-text-k-name "nxt-tools"
    :ref-label-text-type          [:nxt.itools.label-text-type/uuid-label-text-type #uuid"6109d1be-f427-4d65-bc3a-8e4aee4edf33"]
    :ref-tenant-owner             [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :dflt-label?                  true
    :uuid-menu-group-label-text   #uuid"5ec0768a-63e8-4659-ba79-86759b80484e"}

   {:ref-menu-group               [:nxt.itools.menu-group/uuid-menu-group #uuid"618dff75-fd2c-4a08-8999-23ed2be828c7"],
    :menu-group-label-text-k-name "nxt-procurement"
    :ref-label-text-type          [:nxt.itools.label-text-type/uuid-label-text-type #uuid"6109d1be-f427-4d65-bc3a-8e4aee4edf33"]
    :ref-tenant-owner             [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :dflt-label?                  true
    :uuid-menu-group-label-text   #uuid"618dfffd-6514-4676-bb29-a4ec6511ed0b"}

   {:ref-menu-group               [:nxt.itools.menu-group/uuid-menu-group #uuid"6268ee2a-4d60-4b77-8285-885c1f8ec7c6"],
    :menu-group-label-text-k-name "nxt-user",
    :ref-label-text-type          [:nxt.itools.label-text-type/uuid-label-text-type #uuid"6109d1be-f427-4d65-bc3a-8e4aee4edf33"],
    :ref-tenant-owner             [:nxt.itools.tenant/uuid-tenant #uuid"5eb65636-2e24-4ed5-8533-a5107c740c12"],
    :dflt-label?                  true,
    :uuid-menu-group-label-text   #uuid"6351b94d-298a-4a78-ac8e-56cbee09cc15"}

   ]
  )
(def
  menu-group-label-text-lang
  [{:ref-menu-group-label-text       [:nxt.itools.menu-group-label-text/uuid-menu-group-label-text #uuid"5ec0768a-63e8-4659-ba79-86759b80484e"],
    :ref-language                    [:nxt.itools.language/uuid-language #uuid"5ebb1c9b-9423-4815-a27f-abe502a25383"],
    :short-text                      "nxt Builder",
    :long-text                       "nxt Builder",
    :uuid-menu-group-label-text-lang #uuid"610b4c6f-747b-4f72-b423-b84327174ee9"}
   {:ref-menu-group-label-text       [:nxt.itools.menu-group-label-text/uuid-menu-group-label-text
                                      #uuid"6351b94d-298a-4a78-ac8e-56cbee09cc15"],
    :ref-language                    [:nxt.itools.language/uuid-language #uuid"5ebb1c9b-9423-4815-a27f-abe502a25383"],
    :short-text                      "User",
    :long-text                       "User",
    :uuid-menu-group-label-text-lang #uuid"6351b9a4-f811-4e67-9c9a-19d4cf6dec3b"}

   {:ref-menu-group-label-text       [:nxt.itools.menu-group-label-text/uuid-menu-group-label-text #uuid"618dfffd-6514-4676-bb29-a4ec6511ed0b"],
    :ref-language                    [:nxt.itools.language/uuid-language #uuid"5ebb1c9b-9423-4815-a27f-abe502a25383"],
    :short-text                      "nxt Procurement",
    :long-text                       "nxt Procurement",
    :uuid-menu-group-label-text-lang #uuid"618e007b-8f07-4fc8-b31c-22c157eb0db2"}

   ]
  )

(comment (basic-util/squuid))

(def
  severity-type
  [{:severity-type-k-name "text",
    :severity-type-name   "Text",
    :description          "Text",
    :uuid-severity-type   #uuid"616f4dd4-2739-49b5-be00-043876b00863"}
   {:severity-type-k-name "message",
    :severity-type-name   "Message",
    :description          "Message common messages",
    :uuid-severity-type   #uuid"616f4d55-7b2c-4f59-938d-2d19ed07cbd3"}
   {:severity-type-k-name "warning",
    :severity-type-name   "Warning",
    :description          "Warning messages",
    :uuid-severity-type   #uuid"616f4d55-61f5-4d97-8e50-053dd732903b"}
   {:severity-type-k-name "error",
    :severity-type-name   "Error",
    :description          "Error messages",
    :uuid-severity-type   #uuid"616f4d55-0916-4c42-8688-a440566fc38b"}]
  )

(comment severity-type)



#_{:uuid-data-type   #uuid"5e99f93a-e84d-4204-bab5-99303a5afb51",
   :data-type-k-name "email",
   :data-type-name   "email",
   :datomic-type     :db.type/string,
   :description      "Email string"
   :reg-text         (str #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")}
;; https://regexlib.com/Search.aspx?k=password&c=0&m=0&ps=20&p=1
;;This regular expression match can be used for validating strong password.
;; It expects atleast 1 small-case letter,
;; 1 Capital letter,
;; 1 digit,
;; 1 special character
;; and the length should be between 6-any characters.
;; The sequence of the characters is not important.
;; This expression follows the above 4 norms specified by microsoft for a strong password.
#_{:uuid-data-type   #uuid"5f221310-2992-4b18-994c-2505f7f4343f"
   :data-type-k-name "password",
   :data-type-name   "Password",
   :datomic-type     :db.type/string,
   :description      "password string"
   :reg-text         (str #"(?=^.{6,}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\s).*$")}

#_{:html-component-k-name "text-input",
   :html-component-name   "Text Input",
   :description           "Form Group for text input",
   :uuid-html-component   #uuid"5ebdd53b-3397-4f39-bee6-1eac4e22ad29"}
(def
  data-element
  [
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5f221310-2992-4b18-994c-2505f7f4343f"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-3397-4f39-bee6-1eac4e22ad29"],
    :description        "password text-input",

    :uuid-data-element  #uuid"5f2493a5-646a-4dfb-beb6-035dbb1ee9c7"}


   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-099e-4d40-8614-3eaa6955a690"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-65bd-4a62-ae3a-4796cea6d1b0"],
    :description        "alert",

    :uuid-data-element  #uuid"5ec07edb-3586-408e-b00c-0f6fbc8fb233"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-7748-4302-85f7-d39a12b4926f"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-33fe-4c31-9c36-2d891fc45a87"],
    :description        "single-select-drop-down",

    :uuid-data-element  #uuid"5ec07edb-e4a2-4986-85ba-ce1676db85c3"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-6411-4c36-a602-800750e866d3"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-33fe-4c31-9c36-2d891fc45a87"],
    :description        "single-select-drop-down",

    :uuid-data-element  #uuid"5ec07edb-f4ae-44bd-b654-0fe1d748b388"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-d884-4264-a914-68e0fe85ad93"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-33fe-4c31-9c36-2d891fc45a87"],
    :description        "single-select-drop-down",

    :uuid-data-element  #uuid"5ec07edb-eb27-468c-908b-5348996306e6"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-64d0-4eac-8c6f-40076a8c50c1"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-33fe-4c31-9c36-2d891fc45a87"],
    :description        "single-select-drop-down",

    :uuid-data-element  #uuid"5ec07edb-6425-4ea2-833f-faf29eb404f4"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-3369-4820-8767-308af59fd475"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-33fe-4c31-9c36-2d891fc45a87"],
    :description        "single-select-drop-down",

    :uuid-data-element  #uuid"5ec07edb-4068-4f57-8bac-28097b93976d"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-30ba-4b15-8430-80b378e88db4"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-33fe-4c31-9c36-2d891fc45a87"],
    :description        "single-select-drop-down",

    :uuid-data-element  #uuid"5ec07edb-c21d-4205-973e-fb050dfab196"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-099e-4d40-8614-3eaa6955a690"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-3397-4f39-bee6-1eac4e22ad29"],
    :description        "text-input",
    :uuid-data-element  #uuid"5ec07edb-1531-42a7-b06f-7987fd0147c8"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e84d-4204-bab5-99303a5afb51"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-3397-4f39-bee6-1eac4e22ad29"],
    :description        "text-input",
    :uuid-data-element  #uuid"5ec07edb-af53-4ff8-8bac-9a07fa8a3d3b"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-b53c-4aa0-b8a1-d16c11d4f497"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-3397-4f39-bee6-1eac4e22ad29"],
    :description        "text-input",
    :uuid-data-element  #uuid"5ec07edb-840a-4207-83ca-b790586c63eb"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-c680-4a42-a21d-d81e60300a5a"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-3397-4f39-bee6-1eac4e22ad29"],
    :description        "text-input",
    :uuid-data-element  #uuid"5ec07edb-72e5-4c30-8f63-8a3344c0b126"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-2aa7-4b76-838b-736ea2f14ae3"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-3397-4f39-bee6-1eac4e22ad29"],
    :description        "text-input",
    :uuid-data-element  #uuid"5ec07edb-fddd-439e-bb6f-d20d3223d476"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-e657-4b06-81ef-915fe5b792a9"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-3397-4f39-bee6-1eac4e22ad29"],
    :description        "text-input",
    :uuid-data-element  #uuid"5ec07edb-e3cc-4ab3-8141-bf69cc684c0e"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-7985-4226-ae44-9abe8d28014f"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-3397-4f39-bee6-1eac4e22ad29"],
    :description        "text-input",
    :uuid-data-element  #uuid"5ec07edb-92a9-419b-bb8a-0d43b6cc733a"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-099e-4d40-8614-3eaa6955a690"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-f5a5-4fe1-885f-b0ae8160ec1b"],
    :description        "text-area",
    :uuid-data-element  #uuid"5ec07edb-996c-4038-8e4e-33dd601d3af2"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-d884-4264-a914-68e0fe85ad93"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-55b8-4f70-ad4d-db85bfd95f52"],
    :description        "radio-buttons",
    :uuid-data-element  #uuid"5ec07edb-8ec9-464c-8f71-dde3b61a6699"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-12e8-4e2a-b3f9-de9ca9328da8"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-07a4-460b-911e-59817b4340d1"],
    :description        "radio-boolean",
    :uuid-data-element  #uuid"5ec07edb-b226-4fc0-bfe9-d53f6f72c16b"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-d884-4264-a914-68e0fe85ad93"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-a6a3-48a5-839c-372f05c4d23d"],
    :description        "check-boxes",
    :uuid-data-element  #uuid"5ec07edb-0d38-4199-925a-c9e86875ac4e"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-12e8-4e2a-b3f9-de9ca9328da8"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-a6a3-48a5-839c-372f05c4d23d"],
    :description        "check-boxes",
    :uuid-data-element  #uuid"5ec07edb-9409-4808-b5ae-221b32b1f691"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-12e8-4e2a-b3f9-de9ca9328da8"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-0992-43ac-be20-4be6d923499d"],
    :description        "checkbox-toggle",
    :uuid-data-element  #uuid"5ec07edb-ffd2-435a-adcf-acb468edfd21"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-12e8-4e2a-b3f9-de9ca9328da8"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-828b-457f-8736-2efdc7573e4c"],
    :description        "toggle-switch",
    :uuid-data-element  #uuid"5ec07edb-d133-420b-8824-b88963212c93"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-20d7-48ee-a900-ae45aa130c58"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-9f36-464b-9158-275e76d3188e"],
    :description        "date-picker",
    :uuid-data-element  #uuid"5ec07edb-7230-4340-afe0-5b207069d906"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-ce84-486e-9a19-06cf7f927e23"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-9f36-464b-9158-275e76d3188e"],
    :description        "date-picker",
    :uuid-data-element  #uuid"5ec07edb-ec2d-4ab7-a3ec-2373c0427f3c"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-20d7-48ee-a900-ae45aa130c58"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-dd4f-4956-8c89-2e9695c352e0"],
    :description        "date-time-picker",
    :uuid-data-element  #uuid"5ec07edb-57ee-48a1-a284-295a6cb89bd1"}
   {:ref-data-type      [:nxt.itools.data-type/uuid-data-type #uuid"5e99f93a-ce84-486e-9a19-06cf7f927e23"],
    :ref-html-component [:nxt.itools.html-component/uuid-html-component #uuid"5ebdd53b-dd4f-4956-8c89-2e9695c352e0"],
    :description        "date-time-picker",
    :uuid-data-element  #uuid"5ec07edb-8eeb-4044-afd2-1f30d8024e24"}]
  )






