package dev.ajthom.covid

fun <T, L : Iterable<T>> L.doForEach(block: (T) -> Unit): L {
	forEach(block)
	return this
}
