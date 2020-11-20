(ns microblog.config
  (:require [clojure.java.io :refer [file]]
            [useful.config :refer [load-config]]))

(def config (load-config "config.clj"))


