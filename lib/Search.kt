class Search {
    // 上限開区間の探索。upper=4なら4は含まない
    // list = [1,2,3,4,5],lower = 1,upper = 4
    // 返り値 開区間のindexを返す
    // return Pair(start:0,end:3)
    // つまりend - startで個数が取れる
    // Pair(0,0) = すべての要素がupper以上
    // Pair(size,size) = すべての要素がlower未満
    // Pair(N,N) = lower以上の要素はあるが、upper未満の要素がない

    // lowerとupperで同じ値を入れると(N,N)で返る
    companion object {


        fun <T : Comparable<T>> binarySearchOpenRange(sortedList: List<T>, lower: T, upper: T): Pair<Int, Int> {
            val start = _binarySearch(sortedList, lower, 0, sortedList.size)
            val end = _binarySearch(sortedList, upper, start, sortedList.size)
            return Pair(start, end)
        }

        tailrec private fun <T : Comparable<T>> _binarySearch(sortedList: List<T>, target: T, begin: Int, end: Int): Int {
            if (begin == end) {
                return begin
            }

            val pos = (begin + end) / 2
            val cmp = target.compareTo(sortedList[pos])
            var newBegin = begin
            if (cmp > 0) {
                newBegin = Math.max(begin + 1, pos)
            }
            var newEnd = end
            if (cmp <= 0) {
                newEnd = pos
            }

            return _binarySearch(sortedList, target, newBegin, newEnd)
        }
    }
}