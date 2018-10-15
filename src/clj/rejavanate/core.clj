(ns rejavanate.core
  (:require [clojure.tools.namespace.repl :refer [refresh-all]]
            [virgil.compile :refer [compile-all-java java-file?]]
            [clojure.java.io :as io]))

(defn lein-project-file []
  (io/file "project.clj"))

(defn java-source-paths []
  (let [lpf (lein-project-file)]
    (when (.exists lpf)
      (->> lpf
           slurp
           read-string
           (drop-while (partial not= :java-source-paths))
           second))))

(def default-java-source-paths ["src/java"])

(defn recompile-directories [directories]

  ;; Code stolen from virgil
  
  (println (str "\nRecompiling all files in " (vec directories)))
  (compile-all-java directories)
  (binding [*ns* *ns*]
    (refresh-all)))

(defn recompile-lein-java-source-paths []
  (recompile-directories (or (java-source-paths)
                             default-java-source-paths)))
