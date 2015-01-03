(ns lgwd.ansi)

(def reset "\u001b[0m")
(def clearline "\r\033[0K")

(defn color
  "Given a color number, returns a string with an ansi escape color"
  [color]
  (str "\u001b[38;05;" color "m"))