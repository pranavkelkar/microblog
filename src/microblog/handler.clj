(ns microblog.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.util.response       :as   response])
  (:use     [microblog.auth_template :only [register-page login-page]]
            [microblog.blog_template :only [index-page create-page update-page]]))

(defn do-register
  [username password]
  (println "username: " username "password:" password)
  (response/redirect "/"))

(defroutes app-routes
  (GET "/" [] (index-page))
  (GET "/register" [] (register-page))
  (POST "/register" {{:strs [username password]} :form-params}
        (do-register username password))
  (GET "/login" [] (login-page))
  (GET "/create" [] (create-page))
  (GET "/update/:id{[0-9]+}" [id] (update-page))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
