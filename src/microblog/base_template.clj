(ns microblog.base_template
  (:use [hiccup.core :only [html]]
        [hiccup.page :only [doctype include-css]]
        [hiccup.element :only [image]]))

(defn generate-nav
  [user]
  [:nav
   (image "/quill_image40x40.png")
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
  [user title content template-size]
  (html
   (doctype :html5)
   [:html
    [:head
     [:title "&#181Blog - " title]
     [:link {:rel "shortcut icon" :href "/quill_favicon16x16.png"}]
     (include-css "/css/style.css")
     (if (= :small template-size)
       (include-css "/css/small_template.css")
       (include-css "/css/large_template.css"))
     ]
    [:body
     (generate-nav user)
     (content)
     ]]))
