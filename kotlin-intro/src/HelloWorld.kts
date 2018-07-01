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
    return "$msg $name";
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