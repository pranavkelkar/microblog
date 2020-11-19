(ns microblog.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:use     [microblog.auth_template :only [register-page login-page]]
            [microblog.blog_template :only [index-page create-page update-page]]))

(defroutes app-routes
  (GET "/" [] (index-page))
  (GET "/register" [] (register-page))
  (GET "/login" [] (login-page))
  (GET "/create" [] (create-page))
  (GET "/update/:id{[0-9]+}" [id] (update-page))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
