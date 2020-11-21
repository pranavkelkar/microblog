(ns microblog.auth
  (:require [noir.session :as session]
            [ring.util.response   :as response]
            [next.jdbc            :refer [get-connection prepare execute! prepare]]
            [next.jdbc.result-set :refer [as-unqualified-maps]])
  (:use     [microblog.database :only [ds]]
            [microblog.utils :only [flash-msg flash-error]]))

(defn get-user
  [username]
  (with-open [con (get-connection ds)]
    (with-open [stmt (prepare con ["SELECT * FROM author_tbl where username=?" username])]
      (execute! stmt)
      )))

(defn put-user
  [username password]
  (with-open [con (get-connection ds)]
    (with-open [stmt (prepare con ["INSERT INTO author_tbl(username, password) values (?, ?)" username password])]
      (execute! stmt)
      )))

(defn do-register
  [username password]
  (if (empty? (get-user username))
    (if (put-user username password)
      (do
        (println "New user")
        (flash-msg "/login" (str "Username " username " successfully registered!")))
      (println "error entering user in db"))
    (do
      (println "User exists")
      (session/flash-put! :username username)
      (flash-msg "/register" (str "Username " username " is already registered!")))
    )
  )
