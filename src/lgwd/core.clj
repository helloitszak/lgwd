(ns lgwd.core
  (:require
    [aleph.tcp :as tcp]
    [manifold.stream :as s]
    [manifold.time :as time]
    [lgwd.ansi :as ansi]
    [lgwd.lgw :as lgw])
  (:gen-class))

(defn pretty-host-port
  [info]
  (str (:remote-addr info) ":" (:server-port info)))

(defn loop-lgw!
  [stream info loopcount]
  (if-not (s/closed? stream)
    (do
      (s/put! stream (str ansi/clearline (lgw/generate-lgw-text loopcount)))
      (time/in
        (time/milliseconds 100)
        (fn []
          (loop-lgw! stream info (if (= loopcount (dec (count lgw/colors)))
                              0
                              (inc loopcount))))))
    (do
      (println "disconnect " (pretty-host-port info)))))

(defn lgw-handler
  [stream info]
  (println "connect " (pretty-host-port info))
  (loop-lgw! stream info 0))

(defn start-server
  [port]
  (tcp/start-server lgw-handler {:port port})
  (println (str "Server started on " port)))

(defn -main
  []
  (start-server
    (or (System/getenv "LGWD_PORT") 1337)))