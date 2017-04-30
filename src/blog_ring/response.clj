(ns blog-ring.response
  (:require [ring.util.response :as res]))

(def simple-res (res/response "Hello, world!"))

simple-res
;; -> {:status 200,
;;     :headers {},
;;     :body "Hello, world!"}

(res/charset simple-res "utf-8")
;; -> {:status 200,
;;     :headers {"Content-Type" "text/plain; charset=utf-8"},
;;     :body "Hello, world!"}

(def json-res
  (res/response "{\"id\":1,\"content\":\"Hello, World!\"}"))

json-res
;; -> {:status 200,
;;     :headers {},
;;     :body "{\"id\":1,\"content\":\"Hello, World!\"}"}

(res/content-type json-res "application/json")
;; -> {:status 200,
;;     :headers {"Content-Type" "application/json"},
;;     :body "{\"id\":1,\"content\":\"Hello, World!\"}"}

(res/not-found "Ring not found!")
;; -> {:status 404,
;;     :headers {},
;;     :body "Ring not found!"}

(res/redirect "http://clojure.cz")
;; -> {:status 302,
;;     :headers {"Location" "http://clojure.cz"},
;;     :body ""}

(res/set-cookie simple-res "Clojurian" "Granted")
;; -> {:status 200,
;;     :headers {},
;;     :body "Hello, world!",
;;     :cookies {"Clojurian" {:value "Granted"}}}