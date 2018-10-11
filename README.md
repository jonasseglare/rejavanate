# rejavanate

A small tool to help with the recompilation of Java source code in Leiningen projects without having to restart the REPL. Uses [virgil](https://github.com/ztellman/virgil) internally to get the job done.

Unlike virgil, it does not automatically recompile and reload the Java sources when they are changed. Instead, a Clojure function has to be called. This call can be bound to a key combination in Emacs, as explained below.

## Installation

Clone this repository, and from the inside the repository, do
```
lein install
```
In your ```~/.lein/profiles.clj``` file, make sure there is a ```:repl``` dependency on ```rejavanate```:
```clj
{

 ...

 :repl
 {
  ...

  :dependencies [

                 ...

                 [rejavanate "0.1.0-SNAPSHOT"]

                 ...
                ]

  ...
  }

 ...
}
```
Edit your ```~/.emacs.d/init.el``` file to include this sort of code:
```
(defun rejavanate ()
  (interactive)
  (cider-interactive-eval
   "(do (require 'rejavanate.core)
        (println \"Rejavanate\")
        (rejavanate.core/recompile-lein-java-source-paths))"))

(add-hook 'cider-mode-hook 
          (lambda ()

            (define-key clojure-mode-map (kbd "<C-f12>") 'rejavanate) ))
```
In the above code, we bind ```Ctrl + F12``` to mean that we want to recompile all Java code and reload it in the REPL.

## Usage

Assuming the software has been installed as explained above, hit ```Ctrl + F12``` to recompile and reimport all Java code.

## License

Copyright © 2018 Jonas Östlund

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
