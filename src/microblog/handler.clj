(ns microblog.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:use [hiccup.core :only [html]]
        [hiccup.page :only [doctype include-css]]
        [hiccup.element :only [image]]
        [hiccup.form :only [form-to label text-field password-field check-box]]))

(defn generate-post
  [post]
  [:article {:class "post"}
         [:header
          [:div
           [:h1 (post :title)]
           [:div {:class "about"} "by " (post :username)  " on " (post :created)]]
          [:a {:class "action" :href "#"} "Edit"]]
   [:p {:class "post"} (post :body)] [:hr]])

(defn generate-nav
  [user]
  [:nav
   (image "quill_image40x40.png")
   [:h1
    [:a {:href "/"} "&#181Blog"]]
   (if user
     [:ul
      [:li
       [:span user]]
      [:li
       [:a {:href "#"} "Logout"]]]
     [:ul
      [:li
       [:a {:href "/login"} "Login"]]
      [:li
       [:a {:href "/register"} "Register"]]])
   ])

(defn base-template
  [user content template-size]
  (html
   (doctype :html5)
   [:html
    [:head
     [:title "&#181Blog"]
     [:link {:rel "shortcut icon" :href "quill_favicon16x16.png"}]
     (include-css "css/style.css")
     (if (= :small template-size)
       (include-css "css/small_template.css")
       (include-css "css/large_template.css"))
     ]
    [:body
     (generate-nav user)
     (content)
     ]]))

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
  (base-template nil register-content :small))

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
  (base-template nil login-content :small))

(defn index-content
  []
  (let [posts '({:id 1 :title "First Post" :username "pranav" :created "1993-01-23" :body "Body of first post"}
                {:id 2 :title "Second Post" :username "jack" :created "2019-01-14" :body "Body of second post"}
                {:id 3 :title "Third Post" :username "jill" :created "1954-06-01" :body "Body of third post"}
                {:id 3 :title "Fourth Post" :username "tom" :created "1934-12-24" :body "Body of forth post"}
                {:id 4 :title "Fifth Post" :username "shankar" :created "1995-05-25" :body "Body of fifth post"})]
    [:section {:class "content"}
     [:header
      [:h1 "Posts"]
      [:a {:class "action" :href "#"} "New"]]
     (for [post posts]
       (generate-post post))
     ]
    )
  )

(defn index-page
  []
  (base-template nil index-content :large))



(defroutes app-routes
  (GET "/" [] (index-page))
  (GET "/register" [] (register-page))
  (GET "/login" [] (login-page))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
