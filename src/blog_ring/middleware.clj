(ns blog-ring.middleware)

(defn wrap-no-content
  "Middleware that returns a 204 No Content
  from the wrapped handler."
  [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc response :status 204))))
