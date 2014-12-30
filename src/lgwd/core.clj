(ns lgwd.core
  (:require
    [aleph.tcp :as tcp]
    [manifold.stream :as s]
    [manifold.time :as time]))

(def ansireset "\u001b[0m")
(def ansiclearline "\r\033[0K")

(defn ansicolor
  "Given a color number, returns a string with an ansi escape color"
  [color]
  (str "\u001b[38;05;" color "m"))

(def lgw "Lᴇɢᴀʟɪᴢᴇ Gᴀʏ Wᴇᴇᴅ")
(def colors [196 214 226 154 46 49 51 39 21 129 201 199])

(defn generate-lgw-text
  ([offset]
    (str
      (apply str
             (interleave
               (drop offset (cycle (map ansicolor colors)))
               lgw))
      ansireset))
  ([] (generate-lgw-text 0)))


(defn loop-lgw!
  [stream loopcount]
  (if-not (s/closed? stream)
    (do
      (s/put! stream (str ansiclearline (generate-lgw-text loopcount)))
      (time/in
        (time/milliseconds 100)
        (fn []
          (loop-lgw! stream (if (= loopcount (dec (count colors)))
                              0
                              (inc loopcount))))))
    (do
      (println "we done"))))

(defn lgw-handler
  [stream info]
  (loop-lgw! stream 0))

(defn start-server
  []
  (tcp/start-server lgw-handler {:port 1337}))

