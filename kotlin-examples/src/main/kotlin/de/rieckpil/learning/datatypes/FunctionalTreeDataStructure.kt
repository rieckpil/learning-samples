package de.rieckpil.learning.datatypes

sealed class Tree<out A> {

  companion object {

    fun <A> size(tree: Tree<A>): Int =
      when (tree) {
        is Leaf -> 1
        is Branch -> 1 + size(tree.left) + size(tree.right)
      }

    fun maximum(tree: Tree<Int>): Int =
      when (tree) {
        is Leaf -> tree.value
        is Branch -> maxOf(maximum(tree.left), maximum(tree.right))
      }

    fun <A> depth(tree: Tree<A>): Int =
      when (tree) {
        is Leaf -> 0
        is Branch -> 1 + maxOf(depth(tree.left), depth(tree.left))
      }

    fun <A, B> map(tree: Tree<A>, f: (A) -> B): Tree<B> =
      when (tree) {
        is Leaf -> Leaf(f(tree.value))
        is Branch -> Branch(map(tree.left, f), map(tree.right, f))
      }

    fun <A, B> fold(tree: Tree<A>, l: (A) -> B, f: (B, B) -> B): B =
      when (tree) {
        is Leaf -> l(tree.value)
        is Branch -> f(fold(tree.left, l, f), fold(tree.right, l, f))
      }

    fun <A> sizeFold(tree: Tree<A>): Int = fold(tree, { 1 }, { a, b -> a + b + 1 })
    fun <A> maximumFold(tree: Tree<Int>): Int = fold(tree, { it }, { a, b -> maxOf(a, b) })
    fun <A> depthFold(tree: Tree<A>): Int = fold(tree, { 0 }, { a, b -> 1 + maxOf(a, b) })
    fun <A, B> mapFold(tree: Tree<A>, f: (A) -> B): Tree<B> = fold(tree, { a: A -> Leaf(f(a)) }, { a: Tree<B>, b: Tree<B> -> Branch(a, b) })
  }

}

data class Leaf<A>(val value: A) : Tree<A>()

data class Branch<A>(val left: Tree<A>, val right: Tree<A>) : Tree<A>()


fun main() {

  // println(Tree.size(Branch(Branch(Leaf(1), Leaf(2)), Leaf(2))))
  // println(Tree.maximum(Branch(Branch(Leaf(4), Leaf(2)), Leaf(7))))
  // println(Tree.depth(Branch(Branch(Leaf(4), Leaf(2)), Leaf(7))))
  // println(Tree.map(Branch(Branch(Leaf(4), Leaf(2)), Leaf(7))) { it * it })

  println(Tree.sizeFold(Branch(Branch(Leaf(4), Leaf(2)), Leaf(7))))

}
