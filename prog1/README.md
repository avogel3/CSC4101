# Prog 1: Scheme Pretty Printer

### What does this do? 

This is a scheme lang recursive decent parser and pretty printer. You can read more about SchemeLang using the [online reference](https://www.scheme.com/tspl4/).

This iteration of the parser is not full featured. Please refer to the below outline for example output and some notes on functionality.  

According to the rubric, this Lexical Analyzer needs to be able to do the following: 

- Discard whitespace including - space, tab, newline, carriage-return, and form-feed characters
- Discard comments - everything from ; to the end of the line
- Recognize quotes, dots, and open/closing parentheses
- Recognize the boolean constants true and false
- Recognize integer constants (decimal digits without a sign)
- Recognize string constants (anything between double quotes)
- Recognize identifiers


Below is an example of the expected output of the pretty printer:
```
  123
  => 123
  #t
  => #t
  #f
  => #f
  ()
  => ()
  (+2 3) ;this is a comment
  => (+ 2 3)
  'foo
  => (quote foo)
  (define x 0)
  => (define x 0)
  (set! x (+ 2 3))
  => (set! x (+ 2 3))
  (begin (set! x 6) (set! y 7) (* x y))
  => (begin
      (set! x 6)
      (set! y 7)
      (* x y)
  )
```

### Running the Tests

JUnit is used to test some of the main parts of this project. To run the tests locally, download the `JUnit4` and `hamcrest-core-1.3` jar files and add them to the `lib` directory.

##### Andrew Vogel