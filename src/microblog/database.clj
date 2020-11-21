(ns microblog.database
  (:require [next.jdbc        :refer [get-datasource]])
  (:use     [microblog.config :only  [config]]))

(def db {:dbtype "postgresql"
         :host (config :db-host)
         :port (config :db-port)
         :dbname (config :db-name)
         :user (config :db-user)
         :password (config :db-pwd)})

(def ds (get-datasource db))
