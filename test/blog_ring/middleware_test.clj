(ns blog-ring.middleware-test
  (:require [clojure.test :refer :all]
            [blog-ring.middleware :refer :all]))

(def put-request {:request-method :put})

(def get-request {:request-method :get})

(defn- handler [request]
  {:status  200
   :headers {}
   :body    "OK"})

(deftest test-no-content
  (testing "no content via identity"
    (let [response ((wrap-no-content identity) {})]
      (is (= 204 (:status response)))
      (is (nil? (:body response)))))

  (testing "no content with a handler"
    (let [response ((wrap-no-content handler) {})]
      (is (= 204 (:status response)))
      (is (nil? (:body response))))))

(deftest test-put-allowed
  (testing "put method returns 200"
    (let [response ((wrap-put-allowed handler) put-request)]
      (is (= 200 (:status response)))
      (is (= "OK" (:body response)))))

  (testing "get method returns 405"
    (let [response ((wrap-put-allowed handler) get-request)]
      (is (= 405 (:status response)))
      (is (= "Method Not Allowed" (:body response))))))

(deftest test-put-no-content
  (testing "put method returns 204"
    (let [response ((wrap-put-no-content handler) put-request)]
      (is (= 204 (:status response)))
      (is (nil? (:body response)))))

  (testing "get method returns 405"
    (let [response ((wrap-put-no-content handler) get-request)]
      (is (= 405 (:status response)))
      (is (= "Method Not Allowed" (:body response))))))

(deftest test-blank-middleware
  (testing "blank middleware via identity"
    (is (empty? ((wrap-blank-middleware identity) {}))))

  (testing "blank middleware via handler"
    (let [response ((wrap-blank-middleware handler) {})]
      (is (= 200 (:status response)))
      (is (= "OK" (:body response))))))
