(ns microblog.utils
  (:require [noir.session       :as   session]
            [ring.util.response :as response]))

(defn flash-fn [type]
  (fn [url msg]
    (println url msg)
    (println (session/flash-put! type msg))
    (response/redirect url)))

(def flash-error (flash-fn :error))

(def flash-msg (flash-fn :message))
