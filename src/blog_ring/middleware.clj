(ns blog-ring.middleware)

(defn wrap-no-content
  "Middleware that returns a 204 No Content
  from the wrapped handler."
  [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc response :status 204))))

(defn wrap-put-allowed
  "Middleware that returns a 405 Method Not Allowed
  if the request doesn't have :put method."
  [handler]
  (fn [request]
    (if (= (:request-method request) :put)
      (handler request)
      (let [response (handler request)]
        (assoc response :status 405)))))
