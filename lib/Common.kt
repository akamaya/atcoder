fun <T : Comparable<T>> Collection<T>.pairDelete(): Collection<T> {
    val map = mutableMapOf<T, Int>()
    this.forEach {
        if (map.containsKey(it)) {
            map[it] = map[it]!! + 1
        }
        else {
            map[it] = 1
        }
    }

    return map.filter { it.value % 2 == 1 }.keys
}