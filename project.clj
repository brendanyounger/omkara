(defproject omkara "0.0.1"
  :description  "A starting point for Clojure web apps based on the Om/React.js library"
  :url          "https://github.com/brendanyounger/omkara"
  :min-lein-version "2.0.0"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2127"]
                 [om "0.1.5"]
                 [org.marianoguerra/clj-rhino "0.2.1"]
                 [ring "1.2.0"]
                 [compojure "1.1.5"]
                 [enlive "1.1.4"]
                 [org.clojure/tools.logging "0.2.6"]
                 [liberator "0.9.0"]
                 [ch.qos.logback/logback-classic "1.0.13"]
                 [com.netflix.hystrix/hystrix-clj "1.3.2"
                    :exclusions [org.slf4j/slf4j-api]]
                ]

  :plugins [[lein-cljsbuild "1.0.1"]
            [lein-ring "0.8.8"]]

  :source-paths   ["src"]
  :resource-paths ["public" "javascripts" "html"]

  :ring {
    :handler omkara.core/app
  }

  :cljsbuild {
    :builds [
      { :id "development"
        :source-paths ["src-cljs" "dev"]
        :compiler {
          :output-to "public/main.dev.js"
          :output-dir "out/development"
          :externs ["javascripts/react-externs.js"]
          ;; :preamble uses clojure.java.io/resource to load files. be sure that this path can be found via the classloader
          :preamble ["react-0.8.0.js"]
          :optimizations :simple
          :closure-warnings { :non-standard-jsdoc :off }
        }
      }

      { :id "production"
        :source-paths ["src-cljs"]
        :compiler {
          :output-to "public/main.min.js"
          :output-dir "out/production"
          :externs ["javascripts/react-externs.js"]
          :preamble ["react-0.8.0.js"]
          :optimizations :simple
          :closure-warnings { :non-standard-jsdoc :off }
        }
      }

      { :id "embedded"
        :source-paths ["src-cljs"]
        :compiler {
          :output-to "public/main.embed.js"
          :output-dir "out/embed"
          :externs ["javascripts/react-externs.js"]
          :preamble ["react-prelude.js" "react.min.js" "react-postlude.js"]
          :optimizations :simple
          ; :pretty-print true
          :closure-warnings { :non-standard-jsdoc :off }
        }
      }
      ]
    }
  )
