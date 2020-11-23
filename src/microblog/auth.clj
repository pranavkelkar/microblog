(ns microblog.auth
  (:require [noir.session :as session]
            [ring.util.response   :as response]
            [next.jdbc            :refer [get-connection prepare execute-one! execute!]]
            [next.jdbc.result-set :as rs])
  (:use     [microblog.database :only [ds]]
            [microblog.utils :only [flash-success flash-message flash-warning flash-error]]))

(defn get-user
  [username]
  (with-open [con (get-connection ds)]
    (with-open [stmt (prepare con ["SELECT * FROM author_tbl where username=?" username] )]
      (execute! stmt {:builder-fn rs/as-arrays})
      )))

(defn put-user
  [username password]
  (try
    (with-open [con (get-connection ds)]
    (with-open [stmt (prepare con ["INSERT INTO author_tbl(username, password) values (?, ?)" username password])]
      (execute! stmt)
      ))
    (catch Exception e (.getMessage e))
    )
  )

(put-user "aragorn" "ellesar")

(defn do-register
  [username password]
  (if (empty? (get-user username))
    (if (put-user username password)
      (flash-success "/login" (str "Username " username " successfully registered !"))
      (flash-error "/register" (str "Error occurred during registering user " username ". Please retry !")))
    (flash-warning "/register" (str "Username " username " is already registered !")))
  )

(defn do-login
  "Log in a registered user by adding the user id to the session."
  [username password]
  (let [user (get-user username)]
    (if (empty? user)
      (flash-error "/login" (str "Incorrect username " username " !"))
      (do (println user)
          (response/redirect "/"))
      )
    )
  )
