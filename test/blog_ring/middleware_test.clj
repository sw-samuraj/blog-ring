(ns blog-ring.middleware-test
  (:require [clojure.test :refer :all]
            [blog-ring.middleware :refer :all]))

(deftest test-no-content
  (testing "no content via identity"
    (is (= 204 (:status ((wrap-no-content identity) {}))))))
