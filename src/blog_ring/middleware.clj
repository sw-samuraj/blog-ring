(ns blog-ring.middleware)

(defn wrap-no-content
  "Middleware that returns a 204 No Content
  from the wrapped handler."
  [handler]
  (fn [request]
    (let [response (handler request)]
      (-> response
          (assoc :status 204)
          (dissoc :body)))))

(defn wrap-put-allowed
  "Middleware that returns a 405 Method Not Allowed
  if the request doesn't have :put method."
  [handler]
  (fn [request]
    (if (= (:request-method request) :put)
      (handler request)
      (let [response (handler request)]
        (-> response
            (assoc :status 405)
            (assoc :body "Method Not Allowed"))))))

(defn wrap-put-no-content
  "Middleware that returns a 204 No Content
  if the request method is PUT, otherwise
  a 405 Method Not Allowed."
  [handler]
  (-> handler
      wrap-no-content
      wrap-put-allowed))

(defn wrap-blank-middleware
  "Blank middleware demonstrating common structure."
  [handler]
  (fn [request]
    ; Do some magic with the request
    (let [response (handler request)]
      ; Do some magic with the response
      ; and return the response.
      response)))
