(ns microblog.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:use [hiccup.core :only [html]]
        [hiccup.page :only [doctype include-css]]
        [hiccup.element :only [image]]))

(defn generate-post
  [post]
  [:article {:class "post"}
         [:header
          [:div
           [:h1 (post :title)]
           [:div {:class "about"} "by " (post :username)  " on " (post :created)]]
          [:a {:class "action" :href "#"} "Edit"]]
         [:p {:class "post"} (post :body)] [:hr]])

(defn home-page
  []
  (let [posts '({:id 1 :title "First Post" :username "pranav" :created "1993-01-23" :body "Body of first post"}
                {:id 2 :title "Second Post" :username "jack" :created "2019-01-14" :body "Body of second post"}
                {:id 3 :title "Third Post" :username "jill" :created "1954-06-01" :body "Body of third post"}
                {:id 3 :title "Fourth Post" :username "tom" :created "1934-12-24" :body "Body of forth post"}
                {:id 4 :title "Fifth Post" :username "shankar" :created "1995-05-25" :body "Body of fifth post"})]
    (html
   (doctype :html5)
   [:html
    [:head
     [:title "&#181Blog"]
     [:link {:rel "shortcut icon" :href "quill_favicon16x16.png"}]
     (include-css "css/style.css")]
    [:body
     [:nav
      (image "quill_image40x40.png")
      [:h1
       [:a {:href "#"} "&#181Blog"]]
      [:ul
       [:li
        [:span "username"]]
       [:li
        [:a {:href "#"} "Logout"]]]]
     [:section {:class "content"}
      [:header
       [:h1 "Posts"]
       [:a {:class "action" :href "#"} "New"]]
      (for [post posts]
        (generate-post post))
      ]]])))



(defroutes app-routes
  (GET "/" [] (home-page))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
