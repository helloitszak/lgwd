(ns lgwd.lgw
  (:require [lgwd.ansi :as ansi]))

(def lgw "Lᴇɢᴀʟɪᴢᴇ Gᴀʏ Wᴇᴇᴅ")
(def colors [196 214 226 154 46 49 51 39 21 129 201 199])

(defn generate-lgw-text
  ([offset]
    (str
      (apply str
             (interleave
               (drop offset (cycle (map ansi/color colors)))
               lgw))
      ansi/reset))
  ([] (generate-lgw-text 0)))
