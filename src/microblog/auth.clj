(ns microblog.auth
  (:require [noir.session :as session]
            [ring.util.response   :as response]
            [next.jdbc            :refer [get-connection prepare execute! prepare]]
            [next.jdbc.result-set :refer [as-unqualified-maps]])
  (:use     [microblog.database :only [ds]]
            [microblog.utils :only [flash-success flash-message flash-warning flash-error]]))

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
      (flash-success "/login" (str "Username " username " successfully registered!"))
      (flash-error "/register" (str "Error occurred during registering user " username ". Please retry!")))
    (flash-warning "/register" (str "Username " username " is already registered!")))
  )
