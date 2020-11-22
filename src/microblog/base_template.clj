(ns microblog.base_template
  (:require [noir.session  :as   session])
  (:use     [hiccup.core    :only [html]]
            [hiccup.page    :only [doctype include-css]]
            [hiccup.element :only [image]]))

(defn generate-nav
  [user]
  [:nav
   (image "/quill_image40x40.png")
   [:h1
    [:a {:href "/"} "Quill"]]
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
  [user title content template-size]
  (let [flash-success (session/flash-get :success)
        flash-message (session/flash-get :message)
        flash-warning (session/flash-get :warning)
        flash-error   (session/flash-get :error)]
    (html
     (doctype :html5)
     [:html
      [:head
       [:title "Quill - " title]
       [:link {:rel "shortcut icon" :href "/quill_favicon16x16.png"}]
       (include-css "/css/style.css")
       (if (= :small template-size)
         (include-css "/css/small_template.css")
         (include-css "/css/large_template.css"))
       ]
      [:body
       (generate-nav user)
       (when flash-success [:div {:class "flash_success"} flash-success])
       (when flash-message [:div {:class "flash_message"} flash-message])
       (when flash-warning [:div {:class "flash_warning"} flash-warning])
       (when flash-error [:div {:class "flash_error"} flash-error])
       (content)
       ]])))
