(ns microblog.utils
  (:require [noir.session       :as   session]
            [ring.util.response :as response]))

(defn flash-fn [type]
  (fn [url msg]
    (session/flash-put! type msg)
    (response/redirect url)))

(def flash-success (flash-fn :success))

(def flash-message (flash-fn :message))

(def flash-warning (flash-fn :warning))

(def flash-error (flash-fn :error))


