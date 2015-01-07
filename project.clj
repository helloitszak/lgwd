(defproject lgwd "0.1.0"
  :description "Legalize Gay Weed Daemon"
  :url "http://github.com/ubercow/lgwd"
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [aleph "0.4.0-alpha9"]
                 [com.taoensso/timbre "3.3.1"]]

  :plugins [[com.palletops/uberimage "0.4.1"]]

  :main lgwd.core
  :aot [lgwd.core]

  :uberimage {:instructions ["ENV LGWD_PORT 420"
                             "EXPOSE 420"]
              :base-image "dockerfile/java:oracle-java8"
              :tag "ubercow/lgwd"})
