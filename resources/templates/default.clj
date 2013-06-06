;;(doctype :html)
[:html
 {:lang "en"}
 [:head
  [:meta {:charset "utf-8"}]
  [:title (when-let [title (:title metadata)] (str title " -- ")) "Gershwin Programming Language"]
  [:meta
   {:content "width=device-width, initial-scale=1.0",
    :name "viewport"}]
  [:meta
   {:content (or (:description metadata)
                 "Gershwin: Stack-based, Concatenative Clojure")
    :name "description"}]
  [:meta {:name "keywords", :content (or (:tags metadata)
                                         "Gershwin programming language Clojure Factor concatenative stack-based")}]
  ;;[:meta {:content "Daniel Gregoire", :name "author"}]
  "<!--[if lt IE 9]>\n      <script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>\n    <![endif]-->"
  [:link
   {:rel "stylesheet",
    :href "/stylesheets/bootstrap/css/bootstrap.min.css"}]
  [:link
   {:rel "stylesheet",
    :href "/stylesheets/bootstrap/css/bootstrap-responsive.min.css"}]
  [:link
   {:rel "stylesheet",
    :href "/font-awesome/css/font-awesome.min.css"}]
  [:link
   {:rel "stylesheet",
    :href "/stylesheets/bootswatch/bootswatch.css"}]
  [:link {:rel "stylesheet", :href "/stylesheets/app.css"}]]
 [:body#top.preview
  {:data-offset "80", :data-target ".subnav", :data-spy "scroll"}
  "<!-- Navbar\n    ================================================== -->"
  [:div.navbar.navbar-fixed-top
   [:div.navbar-inner
    [:div.container
     [:a.btn.btn-navbar
      {:data-target ".nav-collapse", :data-toggle "collapse"}
      [:span.icon-bar]
      [:span.icon-bar]
      [:span.icon-bar]]
     [:div.brand
      "<!-- <img id=\"mini-logo\" src=\"/images/musical_note.png\" /> -->"
      [:a {:href "/"}
       [:img#mini-logo {:src "/images/logo.png"}]]
      " "
      [:a {:href "/"} "Gershwin"]]
     [:div#main-menu.nav-collapse.collapse
      [:ul#main-menu-left.nav
       [:li [:a {:href "/news.html"} "News"]]
       ;; [:li [:a {:href "/getting-started.html"} "Getting Started"]]
       ;; [:li [:a {:href "/examples.html"} "Examples"]]
       [:li [:a {:href "/documentation.html"} "Documentation"]]
       [:li#download-menu.dropdown
        [:a.dropdown-toggle {:data-toggle "dropdown"
                             :href "#"}
         "Download " [:b.caret]]
        [:ul.dropdown-menu
         [:li
          [:a {:disabled "disabled" :href "javascript:void(0)"}
           "Stable Release"]]
         [:li
          [:a {:target "_blank" :href (get-in (static.config/config) [:gershwin :urls :development-release])}
           "Development Release"]]]]]
      [:ul#main-menu-right.nav.pull-right
       [:li
        [:a
         {:title "Gershwin Github source repository",
          :href "https://github.com/gershwin/gershwin",
          :target "_blank",
          :rel "tooltip"}
         [:i.icon-large.icon-github]
         " Project on Github"]]]]]]]
  [:div.container
   "<!-- Masthead\n================================================== -->"
   [:header#overview.jumbotron.subhead
    [:div.row
     [:div.span6
      [:img#logo
       {:alt "Gershwin Programming Language Logo",
        :title "Gershwin Programming Language",
        :src "/images/logo.png"}]
      [:h1 "Gershwin"]
      [:p.lead "Stack-based, Concatenative Clojure"]]
     [:div.span6]]]
   (if (:carte-blanche metadata)
     content
     [:div
      [:div.row
       [:div.span8
        content]
       [:div.span4
        (when (= (:type metadata) :site)
          [:div.well
           [:h3
            [:i.icon-download " Download"]]
           [:ul
            [:li
             [:a {:disabled "disabled" :href "javascript:void(0)"}
              "Stable Release"]]
            [:li
             [:a {:href (get-in (static.config/config) [:gershwin :urls :development-release])}
              "Development Release"]]]])]]
      (when (= (:type metadata) :post)
        [:div.row
         [:br]
         [:br]
         [:div.span8
          [:p [:i.icon-large.icon-comments] " Have feedback? Start a conversation with "
           [:a {:href "https://twitter.com/gershwinlang"}
            "@gershwinlang"]
           " on Twitter."]]])])
   [:br]
   [:br]
   [:br]
   [:br]
   [:hr]
   [:footer#footer
    [:p.pull-right [:a {:href "#top"} "Back to top"]]
    [:div.links
     [:p
      [:a
       {:href "https://github.com/gershwin/gershwin"}
       [:i.icon-large.icon-github]
       " Source Repository"]
      [:a {:href "/documentation.html"}
       [:i.icon-book]
       " Documentation"]
      [:a
       {:href "https://github.com/gershwin/gershwin/issues"}
       [:i.icon-tasks]
       " Issues/Bugs"]]
     [:p
      [:a {:href "/rss-feed"}
       [:i.icon-rss]
       " RSS Feed"]
      [:a {:href "https://twitter.com/gershwinlang"}
       [:i.icon-twitter]
       " @gershwinlang"]]]
    [:p
     "Web design created by "
     [:a {:href "http://thomaspark.me"} "Thomas Park"]
     ".\n        Web design code licensed under the "
     [:a
      {:href "http://www.apache.org/licenses/LICENSE-2.0"}
      "Apache License v2.0"]
     "."
     [:br]
     "\n        Web design based on "
     [:a {:href "http://twitter.github.com/bootstrap/"} "Bootstrap"]
     ". Icons from "
     [:a
      {:href "http://fortawesome.github.com/Font-Awesome/"}
      "Font Awesome"]
     ". Web fonts from "
     [:a {:href "http://www.google.com/webfonts"} "Google"]
     ". Logo by Daniel Gregoire."]
    [:br]]]
  "<!-- /container -->"
  "<!-- Le javascript\n    ================================================== -->"
  "<!-- Placed at the end of the document so the pages load faster -->"
  [:script
   {:src
    "http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"}]
  [:script
   {:src
    "//cdnjs.cloudflare.com/ajax/libs/jquery-smooth-scroll/1.4.10/jquery.smooth-scroll.min.js"}]
  [:script {:src "/javascripts/bootstrap/bootstrap.min.js"}]]]
