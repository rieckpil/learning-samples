package de.rieckpil.learning.fpbook.parallelism.solution

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

typealias Par<A> = (ExecutorService) -> Future<A>

fun <A> run(es: ExecutorService, a: Par<A>): Future<A> = a(es)

object Pars {

  fun <A> unit(a: A): Par<A> = { es: ExecutorService -> UnitFuture(a) }

  data class UnitFuture<A>(val a: A) : Future<A> {
    override fun get(): A = a
    override fun get(timeout: Long, timeUnit: TimeUnit): A = a
    override fun cancel(evenIfRunnning: Boolean): Boolean = false
    override fun isDone(): Boolean = true
    override fun isCancelled(): Boolean = false
  }

  fun <A, B, C> map2(
    a: Par<A>,
    b: Par<B>,
    f: (A, B) -> C
  ): Par<C> =
    { es: ExecutorService ->
      val af: Future<A> = a(es)
      val bf: Future<B> = b(es)
      UnitFuture(f(af.get(), bf.get()))
    }

  fun <A, B> map(pa: Par<A>, f: (A) -> B): Par<B> =
    map2(pa, unit(Unit), { a, _ -> f(a) })

  fun <A> fork(a: () -> Par<A>): Par<A> =
    { es: ExecutorService ->
      es.submit(Callable<A> { a()(es).get() })
    }

  fun <A> lazyUnit(a: () -> A): Par<A> = fork { unit(a()) }

  fun <A, B> asyncF(f: (A) -> B): (A) -> Par<B> = { a: A -> lazyUnit { f(a) } }

  fun sortPar(parList: Par<List<Int>>): Par<List<Int>> = map(parList) { it.sorted() }

  fun <A> sequence(ps: List<Par<A>>): Par<List<A>> = TODO()

  fun <A, B> parMap(ps: List<A>, f: (A) -> B): Par<List<B>> = fork {
    val fbs: List<Par<B>> = ps.map(asyncF(f))
    sequence(fbs)
  }

  fun <A> parFilter(sa: List<A>, f: (A) -> Boolean): Par<List<A>> {
    val pars: List<Par<A>> = sa.map { lazyUnit { it } }
    return map(sequence(pars)) { la: List<A> ->
      la.flatMap { a ->
        if (f(a)) listOf(a) else emptyList()
      }
    }
  }

  fun <A> choice(cond: Par<Boolean>, t: Par<A>, f: Par<A>): Par<A> =
    { es: ExecutorService ->
      when (run(es, cond).get()) {
        true -> run(es, t)
        false -> run(es, f)
      }
    }

  fun <A> choice2(cond: Par<Boolean>, t: Par<A>, f: Par<A>): Par<A> =
    { es: ExecutorService ->
      choiceN(map(cond, { if (it) 1 else 0 }), listOf(t, f))(es)
    }

  fun <A> choiceChooser(cond: Par<Boolean>, t: Par<A>, f: Par<A>): Par<A> =
    { es: ExecutorService ->
      chooser(cond) { resolvedCond -> if (resolvedCond) f else t }(es)
    }


  fun <A> choiceN(n: Par<Int>, choices: List<Par<A>>): Par<A> = { es: ExecutorService ->
    choices[run(es, n).get()].invoke(es)
  }

  fun <A> choiceChooser(n: Par<Int>, choices: List<Par<A>>): Par<A> = { es: ExecutorService ->
    chooser(n) { resolvedN: Int -> choices[resolvedN] }(es)
  }

  fun <K, V> choiceMap(key: Par<K>, choices: Map<K, Par<V>>): Par<V> = { es: ExecutorService ->
    choices.getValue(key.invoke(es).get())(es)
  }

  fun <K, V> choiceMapChooser(key: Par<K>, choices: Map<K, Par<V>>): Par<V> = { es: ExecutorService ->
    chooser(key) { resolvedKey: K -> choices.getValue(resolvedKey) }(es)
  }

  fun <A, B> chooser(pa: Par<A>, choices: (A) -> Par<B>): Par<B> = { es: ExecutorService ->
    choices(pa(es).get())(es)
  }

  fun <A, B> flatMap(pa: Par<A>, f: (A) -> Par<B>): Par<B> = TODO()

  fun <A> delay(pa: () -> Par<A>): Par<A> = { es -> pa()(es) }
}

data class TimedMap2Future<A, B, C>(
  val pa: Future<A>,
  val pb: Future<B>,
  val f: (A, B) -> C
) : Future<C> {
  override fun isDone(): Boolean = TODO()
  override fun get(): C = TODO()
  override fun get(to: Long, tu: TimeUnit): C {
    val timeoutMillis = TimeUnit.MILLISECONDS.convert(to, tu)
    val start = System.currentTimeMillis()
    val a = pa.get(to, tu)
    val duration = System.currentTimeMillis() - start
    val remainder = timeoutMillis - duration
    val b = pb.get(remainder, TimeUnit.MILLISECONDS)
    return f(a, b)
  }

  override fun cancel(b: Boolean): Boolean = TODO()
  override fun isCancelled(): Boolean = TODO()
}
