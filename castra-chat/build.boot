#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.3.1"

(set-env!
  :dependencies (read-string (slurp "../deps.edn"))
  :out-path     "resources/public"
  :src-paths    #{"src/hoplon" "src/castra" "src/cljs"})

;; Static assets
(add-sync! (get-env :out-path) #{"src/static"})

(require
  '[tailrecursion.hoplon.boot :refer :all]
  '[tailrecursion.castra.task :as c])

(deftask development
  "Build the castra chat demo. Server on port 8000."
  []
  (comp (watch) (hoplon {:prerender false}) (c/castra-dev-server 'demo.api.chat)))

(deftask production
  "Build the castra chat demo for production."
  []
  (hoplon {:optimizations :advanced}))
