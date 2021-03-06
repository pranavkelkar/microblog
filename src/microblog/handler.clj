(ns microblog.handler
  (:require [compojure.core             :refer :all]
            [compojure.route            :as    route]
            [ring.middleware.defaults   :refer [wrap-defaults site-defaults]]
            [noir.session               :as    session]
            [ring.middleware.session    :refer [wrap-session]])
  (:use     [microblog.auth_template    :only  [register-page login-page]]
            [microblog.blog_template    :only  [index-page create-page update-page]]
            [microblog.auth             :only  [do-register do-login]]))

(defroutes app-routes
  (GET "/" [] (index-page))
  (GET "/register" [] (register-page))
  (POST "/register" {{:strs [username password]} :form-params}
        (do-register username password))
  (GET "/login" [] (login-page))
  (POST "/login" {{:strs [username password]} :form-params}
        (do-login username password))
  (GET "/create" [] (create-page))
  (GET "/update/:id{[0-9]+}" [id] (update-page))
  (route/not-found "Not Found"))


(def app
  (-> app-routes
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      session/wrap-noir-flash
      session/wrap-noir-session*
      wrap-session
      ))
