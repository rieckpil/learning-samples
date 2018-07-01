import java.time.LocalDateTime

println("Hello!")

fun foo(a: Int) {
    println("${a}")
}

val number = 1
var x = "Hello"

x = """
    | This is a nicely formatted message
    | ... xyz
    | 124 ss
    | LOL
"""

println(x)

foo(1)

println(" Hello $x")
println(x.trimMargin())
println(" Hello ${x.length}")


val isBigString = if (x.length > 20) "Big Sting" else "not so big"

println(isBigString)

fun greet(name: String, msg: String = "What's up? ${name.length}"): String {
    return "$msg $name"
}

val result = greet(name = "Philip", msg = "Hello")
val result2 = greet(msg = "lolol", name = "Philip")

println(result)
println(result2)


fun max(vararg  numbers: Int) =
        numbers.reduce { max, e -> if(max > e) max else e}

println(max(1,2,34234234,1221,1232312323))

val values = intArrayOf(1, 12, 1848, 2018, 1337, 42)

println(max(*values))

System.getProperties().forEach{t, u -> println("$t: $u")}

for(x in 10 downTo 1 step 2) {
    println(x)
}

val names = listOf("Phil", "Tom", "Henry", "Mike")

for(name in names) {
    println(name)
}

for(index in names.indices) {
    println("$index --- ${names.get(index)}")
}

fun process(input: Any) {

    when(input) {
        1 -> println("one")
        7,8 -> println("seven or eight")
        in 13..19 -> println("lazy as a teen")
        is String -> println("we have a string of length ${input.length}")
        else -> println("I have no clue")
    }
}

process(1)
process(7)
process(14)
process(20)
process("hello")

fun nickName(name: String) : String? {
    if(name == "Philip")
        return "nice guy"

    return null
}

println(nickName("Philip"))
println(nickName("Phil"))

fun receive(name: String?) {
    println("length is ${name?.length ?: 0}")
}

receive("Philip")
receive(null)
receive("Tom")

class Car {
    var yearOfRegistration = 2010
      private set(value) {
          if(value > LocalDateTime.now().year) throw RuntimeException("not possible ...")
          field = value
      }

    val yearSinceRegistration
      get() = 2018 - yearOfRegistration
}

val car = Car()
println(car.yearOfRegistration)
println(car.yearSinceRegistration)
println(car.toString())


object Util {
    fun getNomberOfCores() = Runtime.getRuntime().availableProcessors()
}

println(Util.getNomberOfCores())

class Pizza {
    infix fun spread(item: String) = println("spread $item")

}

val pizza = Pizza()
pizza spread "sauce"
pizza spread "salami"
