(ns blog-ring.middleware
  (:require [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]))

((wrap-params identity)
  {:query-string "clojure=yes&lisp=maybe"})
;; -> {:query-string "clojure=yes&lisp=maybe",
;;     :form-params {},
;;     :params {"clojure" "yes", "lisp" "maybe"},
;;     :query-params {"clojure" "yes", "lisp" "maybe"}}

((wrap-keyword-params identity)
  {:params {"clojure" "yes" "lisp" "maybe"}})
;; -> {:params {:clojure "yes", :lisp "maybe"}}
