(ns blog-ring.middleware-test
  (:require [clojure.test :refer :all]
            [blog-ring.middleware :refer :all]))

(def put-request {:request-method :put})

(def get-request {:request-method :get})

(defn- handler [request]
  {:status  200
   :headers {}})

(deftest test-no-content
  (testing "no content via identity"
    (is (= 204 (:status ((wrap-no-content identity) {})))))

  (testing "no content with a handler"
    (is (= 204 (:status ((wrap-no-content handler) {}))))))

(deftest test-put-allowed
  (testing "put method returns 200"
    (is (= 200 (:status ((wrap-put-allowed handler) put-request)))))

  (testing "get method returns 405"
    (is (= 405 (:status ((wrap-put-allowed handler) get-request))))))

(deftest test-put-no-content
  (testing "put method returns 204"
    (is (= 204 (:status ((wrap-put-no-content handler) put-request)))))

  (testing "get method returns 405"
    (is (= 405 (:status ((wrap-put-no-content handler) get-request))))))
