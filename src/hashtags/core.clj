(ns hashtags.core
  (:gen-class)
  (:require [clojure.edn :as edn]))

(defn exit [msg & {:keys [status] :or {status 0}}]
  (println msg)
  (System/exit status))

(defn -main [& args]
  (let [db (slurp (str (System/getProperty "user.home") "/.config/hashtags.edn"))
        hashtags (try (edn/read-string db)
                      (catch RuntimeException e (exit (.getMessage e) :status 1)))
        xs (take 30 (shuffle hashtags))]
    (when args (println "Found" (count hashtags) "hashtags"))
    (println (apply str (map #(str #"#" % " ") xs)))))
