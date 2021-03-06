(defproject microblog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [hiccup "1.0.5"]
                 [useful "0.8.8"]
                 [seancorfield/next.jdbc "1.1.613"]
                 [org.postgresql/postgresql "42.1.3"]
                 [lib-noir "0.1.1"]
                 [org.clojure/tools.logging "1.1.0"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jmx/jmxri
                                              com.sun.jdmk/jmxtools]]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler microblog.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
