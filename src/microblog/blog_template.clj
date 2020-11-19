(ns microblog.blog_template
  (:use [hiccup.form :only [form-to label text-field text-area]]
        [microblog.base_template :only [base-template generate-nav]]))

(defn generate-post
  [post]
  [:article {:class "post"}
   [:header
    [:div
     [:h1 (post :title)]
     [:div {:class "about"} "by " (post :username)  " on " (post :created)]]
    [:a {:class "action" :href "#"} "Edit"]]
   [:p {:class "post"} (post :body)] [:hr]])

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
      [:a {:class "action" :href "/create"} "New"]]
     (for [post posts]
       (generate-post post))
     ]
    )
  )

(defn index-page
  []
  (base-template nil "Posts" index-content :large))

(defn create-form
  []
  (form-to [:post "#"]
           (label :title "Title")
           (text-field :title)
           (label :content "Content")
           (text-area :content)
           [:button {:type "submit"} "Save"]))

(defn create-content
  []
  [:section {:class "content"}
   [:header
    [:h1 "New Post"]
    ]
   (create-form)
   ]
  )

(defn create-page
  []
  (base-template nil "New Post" create-content :large))

(defn update-form
  []
  (form-to [:post "#"]
           (label :title "Title")
           (text-field :title "Dummy title")
           (label :content "Content")
           (text-area :content "Dummy Content")
           [:button {:type "submit"} "Save"])
  )

(defn delete-form
  []
  (form-to [:post "#"]
           [:button {:class "danger" :type "submit" :onclick "return confirm('Are you sure?');"} "Delete"]))

(defn update-content
  []
  [:section {:class "content"}
   [:header
    [:h1 "Edit"]
    ]
   (update-form)
   (delete-form)
   ]
  )

(defn update-page
  []
  (base-template nil "Edit" update-content :large))






