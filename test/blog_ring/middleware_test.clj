(ns blog-ring.middleware-test
  (:require [clojure.test :refer :all]
            [blog-ring.middleware :refer :all]))

(defn- handler [request]
  {:status  200
   :headers {}})

(deftest test-no-content
  (testing "no content via identity"
    (is (= 204 (:status ((wrap-no-content identity) {})))))

  (testing "no content with a handler"
    (is (= 204 (:status ((wrap-no-content handler) {}))))))
