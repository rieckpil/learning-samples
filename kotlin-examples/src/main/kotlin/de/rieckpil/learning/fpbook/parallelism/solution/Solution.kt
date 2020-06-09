package de.rieckpil.learning.fpbook.parallelism.solution

import de.rieckpil.learning.fpbook.parallelism.Par
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

typealias Par<A> = (ExecutorService) -> Future<A>

fun <A> run(es: ExecutorService, a: Par<A>): Future<A> = a(es)
