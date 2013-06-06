{:carte-blanche true}

[:div
 [:div.row
  [:p.span8.lead
   "Gershwin is a stack-based, concatenative programming language with a Clojure runtime that targets the JVM. It features a fusion of Clojure data structures, namespaces and concurrency semantics with an API inspired by the Factor concatenative programming language. With seamless Clojure interop, Gershwin can leverage all the power and reach of the Clojure/JVM ecosystem while also providing the unique features of stack-based, concatenative languages: composition by code concatenation, point-free expressions, data-flow combinators, and simple, powerful code factoring."]
  [:div.span4
   [:div.well
    [:h3
     [:i.icon-download " Download"]]
    [:ul
     [:li
      [:a {:disabled "disabled" :href "javascript:void(0)"}
       "Stable Release"]]
     [:li
      [:a {:href (get-in (static.config/config) [:gershwin :urls :development-release])}
       "Development Release"]]]]]]
 [:div#next-steps
  [:div.row
   [:div.span4
    [:a.btn.btn-enormous.btn-info {:href "/getting-started.html"}
     [:i.icon-rocket]
     " Getting Started"]]
   [:div.span4
    [:a.btn.btn-enormous.btn-success {:href "/examples.html"}
     [:i.icon-play-sign]
     " Examples"]]
   [:div.span4
    [:a.btn.btn-enormous.btn-warning {:href "/documentation.html"}
     [:i.icon-book]
     " Documentation"]]]]
 [:section
  [:div.row
   [:div.span8
    [:h2 "News"]
    [:ul {:class "posts"}
     (map
      #(let [f %
             url (static.core/post-url f)
             [metadata _] (static.io/read-doc f)
             date (static.core/parse-date
                   "yyyy-MM-dd" "MMM yyyy"
                   (re-find #"\d*-\d*-\d*" (str f)))]
         [:li [:span date] " " [:a {:href url} (:title metadata)]])
      (take 25 (reverse (static.io/list-files :posts))))]]
   [:div.span4]]]]
