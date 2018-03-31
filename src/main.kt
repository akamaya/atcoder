import java.io.FileInputStream
import java.util.*

fun Scanner.nextChar(): Char {
    val line = this.findInLine(".")
    return if (line == null) { // 改行のとき
        this.nextLine() // 改行読み捨て
        this.nextChar()
    }
    else {
        line[0]
    }
}

fun Scanner.nextIntList(size: Int): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 1..size) {
        list.add(this.nextInt())
    }

    return list
}

inline fun Int.times(body: (index: Int) -> Unit) {
    var index = 0
    while (index < this) {
        body(index)
        index++
    }
}

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


var sc: Scanner = Scanner(System.`in`)
fun main(args: Array<String>) {
    if (args.size > 0) {
        System.setIn(FileInputStream(args[0]))
    }
    sc = Scanner(System.`in`)

    problem()

}


fun problem() {

    //val SIZE = getSc.nextInt()
    //val B = getSc.nextInt()

    val x = 3
    val y = 4
    val z = 2

    val matrix = Matrix(z, y, x) { "0" }

    matrix[0, 0, 0] = "1"
    matrix[0, 1, 0] = "2"
    matrix[0, 2, 0] = "3"
    matrix[1, 0, 0] = "4"

    println(matrix)

}

