(ns blog-ring.wrapping)

; "Classical" middleware wrapping
(middleware-3 (middleware-2 (middleware-1 handler)))

; Middleware wrapping via threading macro
(-> handler (middleware-1) (middleware-2) (middleware-3))