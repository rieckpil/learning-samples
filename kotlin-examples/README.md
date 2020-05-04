# Handling error without exceptions

Chapter 4:

- Understanding the pitfalls of throwing exceptions, and why they break referential
transparency
- Adopting a pure functional approach for handling exceptional cases
- Using the Option data type to encode success conditions and ignore failures
- Applying the Either data type to encode both success and failure conditions

- throwing an exception is a  side-effect -> loss of control, contorl is delegated away from the program and the exception is propagated up the call stack
- program will either terminate or a different code area will handle it
- this loss of control and additional complexity should be avoided in FP
- represent failure and exception with ordinary values and provide HOF to handle them
- we loose RT when throwing an exception
- exceptions introduce context dependence and we can't simply reason about a function locally
- we could return null or magic numbers, but then we can't write generic higher order functions -> how would map look like?, this would also result in a lot of boilerplate code at the caller side, hence checking the  result with if
- Kotlin creators did not introduce Option types as they see performance overhead in instantiating wrapper classes, but introduced nullable types


- option is deferring error handling to later code
- use ordinary values instead of throwing exceptions
- lifting existing functions to work with Option does not need a rewrite to handle Some and None
- no need to extend existing non-optinal functions, map, lift, sequence, traverse, map2 (map3) should be enough
-


- for-comprehensions: syntactic sugar over  a series of flatMap and map operations -> more pleasing and concise syntax than imperative code
- works with the Arrow Datatypes

- downside of using Option, rather simplistic and does not tell what went wrong (e.g. while parsing the Strings, which one was not a integer)
- Either -> disjoint union of types, similar to Option two cases, but both cases carry a value
- By convetion, Right is success case and Left is error

- for-comprehension also available for Either
