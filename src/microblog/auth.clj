(ns microblog.auth
  (:require [ring.util.response :as response])
  (:use     [microblog.database :only [ds]]))

(defn do-register
  [username password]
  (println "username: " username "password:" password)
  (response/redirect "/"))

