data class Point(val index: Int)

class MatrixIterator<T>(private val matrix: Matrix<T>) {
    var index = 0
    operator fun hasNext(): Boolean {
        return index < matrix.size
    }

    operator fun next(): T {
        return matrix.list[index++]
    }

    fun point(): Point {
        return Point(index - 1)
    }
}

class Matrix<T>(vararg _dim: Int, ini: () -> T) {
    val dim = _dim

    val size = this.dim.reduce { acc, i -> acc * i }

    val list = mutableListOf<T>()

    init {
        this.size.times {
            list.add(ini())
        }
    }

    operator fun iterator(): MatrixIterator<T> {
        return MatrixIterator(this)
    }

    fun find(target: T): Point? {
        for ((i, t) in list.withIndex()) {
            if (t == target) return Point(i)
        }
        return null
    }

    fun fromIndex(index: Int): T {
        return list[index]
    }

    fun fromPoint(point: Point): T {
        return list[point.index]
    }

    operator fun get(vararg p: Int): T {
        var index = 0
        var pres = 1
        for (i in p.size - 1 downTo 0) {
            index += pres * p[i]
            pres *= dim[i]
        }
        return list[index]
    }

    operator fun set(vararg p: Int, value: T) {
        val index = this.toIndex(*p)
        list[index] = value
    }

    fun toIndex(vararg p: Int): Int {
        var index = 0
        var pres = 1
        for (i in (dim.size - 1) downTo 0) {
            if (i < p.size) {
                index += p[i] * pres
            }
            pres *= dim[i]
        }
        return index
    }

    fun toString2dMatrix(index: Int, y: Int, x: Int): String {
        var i = index
        var str = ""
        y.times {
            x.times {
                str += list[i]
                i++
            }
            str += "\n"
        }
        return str
    }

    override fun toString(): String {
        var str = ""
        val x = dim[dim.size - 1]
        val y = dim[dim.size - 2]
        for (index in 0..(list.size - 1) step x * y) {
            str += toString2dMatrix(index, y, x)
            str += "\n"
        }
        return str
    }


}
