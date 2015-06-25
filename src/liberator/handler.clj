(ns liberator.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

;;
;; the framework
;;

(defmacro endpoint [path params swagger body]
  (let [code (map (fn [[method parameters]]
                    (let [f (symbol (str "compojure.api.core/" (.toUpperCase (-> method name)) "*"))]
                      `(~f ~path ~params :swagger ~parameters nil))) swagger)]
    `(routes
       (ANY* ~path ~params :no-doc true
         (let [~'+swagger+ ~swagger]
           ~body))
       ~@code)))

;;
;; The sample
;;

; this is liberator mock, cosuming all request and doing something with it
(defn liberate [request swagger]
  (ok {:name (-> request :params :q)
       :swagger (str swagger)}))

(defapi app
  (swagger-ui)
  (swagger-docs)
  (context* "/api" []
    (endpoint "/" req
      {:get {:parameters {:query {:q s/Str}}}
       :post {:parameters {:body {:name s/Str :sex (s/enum :male :female)}}}}
      (liberate req +swagger+))))
