(ns blog-ring.middleware
  (:require [ring.util.response :as response]))

(defn wrap-no-content
  "Middleware that returns a 204 No Content
  from the wrapped handler."
  [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc response :status 204))))
