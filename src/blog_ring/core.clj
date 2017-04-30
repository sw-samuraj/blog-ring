(ns blog-ring.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :refer [response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]))

(defn handler [request]
  (response
    (str "<h1>One Ring rules them all!</h1>"
         "<ul><li>Request from IP: "
         (:remote-addr request)
         "</li><li>Request method: "
         (:request-method request)
         "</li><li>Headers: "
         (select-keys
           (:headers request)
           ["accept" "user-agent" "accept-encoding"])
         "</li><li>URI: "
         (:uri request)
         "</li><li>Query string: "
         (:query-string request)
         "</li><li>Params: "
         (:params request)
         "</li></ul>")))

(defn -main []
  (jetty/run-jetty (-> #'handler
                       (wrap-reload )
                       (wrap-keyword-params )
                       (wrap-params))
    {:port 3000}))
