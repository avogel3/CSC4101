;list functions
(define caar (lambda (x) (car (car x))))
(define cadr (lambda (x) (car (cdr x))))
(define cddr (lambda (x) (cdr (cdr x))))
(define cdar (lambda (x) (cdr (car x))))
(define cdaar (lambda (x) (cdr (car (car x)))))
(define cdddr (lambda (x) (cdr (cdr (cdr x)))))
(define caddr (lambda (x) (car (cdr (cdr x)))))
(define cadar (lambda (x) (car (cdr (car x)))))
(define caadr (lambda (x) (car (car (cdr x)))))
(define caaar (lambda (x) (car (car (car x)))))
(define cddar (lambda (x) (cdr (cdr (car x)))))
(define cdadr (lambda (x) (cdr (car (cdr x)))))
(define cddadr (lambda (x) (cdr (cdr (car (cdr x))))))
(define cddddr (lambda (x) (cdr (cdr (cdr (cdr x))))))
(define cdddar (lambda (x) (cdr (cdr (cdr (car x))))))
(define cadaar (lambda (x) (car (cdr (car (car x))))))
(define caaaar (lambda (x) (car (car (car (car x))))))
(define caaadr (lambda (x) (car (car (car (cdr x))))))
(define caaddr (lambda (x) (car (car (cdr (cdr x))))))
(define cadadr (lambda (x) (car (cdr (car (cdr x))))))
(define caddar (lambda (x) (car (cdr (cdr (car x))))))
(define caaddr (lambda (x) (car (car (cdr (cdr x))))))
(define caadar (lambda (x) (car (car (cdr (car x))))))
(define cadddr (lambda (x) (car (cdr (cdr (cdr x))))))
(define cddaar (lambda (x) (cdr (cdr (car (car x))))))
(define cdaddr (lambda (x) (cdr (car (cdr (cdr x))))))
(define cdadar (lambda (x) (cdr (car (cdr (car x))))))
(define cdaaar (lambda (x) (cdr (car (car (car x))))))

;comparison operations
(define eqv?
  (lambda (x y) 
    (if (and (number? x) (number? y))
      (= x y)
      (eq? x y))))

(define (equal? obj1 obj2)
  (cond ((eqv? obj1 obj2)
    #t)
  ((and (pair? obj1)
    (pair? obj2)
    (equal? (car obj1) (car obj2))
    (equal? (cdr obj1) (car obj2)))
  #t)
  (else
  #f)))

;n-ary integer comparison operations
(define (< . l) 
  (if (null? l)
    (b<)
    (b< (car l)
      (cond
        ((if (null? (cdr l)) (car l))
        ((apply < (cdr l))) cdr l)))))

(define (> . l) 
  (if (null? l) 0
    (b> (car l) (apply > (cdr l)))))    

(define (= . l)
  (if (null? l) 0
    (b= (car l) (apply <= (cdr l)))))

(define (<= . l)
  (if (null? l) 0
    (b< (car l) (apply <= (cdr l)))))

(define (>= . l) 
  (if (null? l) 0
    (b< (car l) (apply >= (cdr l)))))

;boolean operations
(define not
  (lambda (x)
    (cond (x #f)
      (else #t))))

;test predicates
(define zero?
  (lambda (x)
    (if (= x 0)
      #t
      #f)))

(define positive?
  (lambda (x)
    (if (> x 0)
      #t
      #f)))

(define negative?
  (lambda (x)
    (if (< x 0)
      #t
      #f)))

(define odd?
  (lambda (x)
    (cond
      ((= x 0) #f)
      ((= x 1) #t)
      ((positive? x) (odd? (- x 2)))
      ((negative? x) (odd? (+ x 2))))))

(define even?
  (lambda (x)
    (cond
      ((= x 0) #t)
      ((= x 1) #f)
      ((positive? x) (even? (- x 2)))
      ((negative? x) (even? (+ x 2))))))

;n-ary arithemetic operations
(define (max . l)
  (if (= (length l) 1)
    (car l)
    (if (null? l)
      (write "Arg is null")
      (if (> (car l) (apply max (cdr l)))
        (car l)
        (apply max (cdr l))))))

(define (min . l)
  (if (= (length l) 1)
    (car l)
    (if (null? l)
      (write "Arg is null")
      (if (< (car l) (apply min (cdr l)))
        (car l)
        (apply min (cdr l))))))

(define (+ . l)
  (if (null? l) 0
  (b+ (car l) (apply + (cdr l)))))

(define (* . l)
  (if (null? l) 0
  (b* (car l) (apply * (cdr l)))))

(define (- . l)
  (if (null? l) 0
  (b- (car l) (apply - (cdr l)))))

;more list functions
(define (list . l)
  (if (null? l)
  '()
  l))

(define (length x)
  (cond ((null?  0)
  ((+ 1 (length (cdr x)))))))

(define (append list1 list2)
  (cond? ((null? list1) list2)
    ((cons (car list1)
      (append (cdr list1) list2)))))

(define (reverse list1)
  (if (null? list1)
    '()
    (append (reverse (cdr list1))
      (list (car list1)))))

;set and association list operations
(define (memv node list1)
  (if (null? list1)
    #f
    (or (eqv? node (car list1)) 
      (memv? node (cdr list1)))))

(define (memq node list1)
  (if (null? list1) 
    #f
    (if (eq? (car list1) node)
      node
      (memq node (cdr list1)))))

(define (member node list1)
  (if (null? list1)
    #f
    (if (eq? (car list1) node)
      node
      (member node (car list1)))))

(define (assv node list1)
  (if (null? list1)
    #f
    (if (eqv? (car (car list1)) node)
      (car list1)
      (assv node (cdr list1)))))

(define (assq node list1)
  (if (null? list1)
    #f
    (if (eq? (car (car list1)) node)
      (car list1)
      (assq node (cdr list1)))))

(define (assoc node list1)
  (if (null? list1)
    #f
    (if (equal? (car (car list1)) node)
      (car list1)
      (assoc node (cdr list1)))))

;high-order functions
(define (map f list1)
  (cond ((null? list1)
    '())
  ((pair? list1)
    (cons (f (car list1))
      (map f (cdr list1))))))

(define (for-each f list1)
  (cond ((not (null? list1))
         (f (car list1))
         (for-each f (cdr list1)))))