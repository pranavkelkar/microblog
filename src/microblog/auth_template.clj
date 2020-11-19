(ns microblog.auth_template
  (:use [hiccup.form :only [form-to label text-field password-field]]
        [microblog.base_template :only [base-template generate-nav]]))

(defn register-form
  []
  (form-to [:post "#"]
           (label :user "Username")
           (text-field :user)
           (label :password "Password")
           (password-field :password)
           [:button {:type "submit"} "Register"]))

(defn register-content
  []
  [:section {:class "content"}
   [:header
    [:h1 "Register"]
    ]
   (register-form)
   ]
  )

(defn register-page
  []
  (base-template nil "Register" register-content :small))

(defn login-form
  []
  (form-to [:post "#"]
           (label :user "Username")
           (text-field :user)
           (label :password "Password")
           (password-field :password)
           [:button {:type "submit"} "Log In"]))

(defn login-content
  []
  [:section {:class "content"}
   [:header
    [:h1 "Login"]
    ]
   (login-form)
   ]
  )

(defn login-page
  []
  (base-template nil "Login" login-content :small))
