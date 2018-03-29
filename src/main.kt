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

data class Point(val y: Int, val x: Int) {
    companion object {
        fun fromIndex(index: Int, ySize: Int): Point {
            return Point(index / ySize, index % ySize)
        }
    }
}

class MatrixIterator<T>(val matrix: Matrix<T>) {
    var index = 0
    operator fun hasNext(): Boolean {
        return index < matrix.list.size
    }

    operator fun next(): T {
        return matrix.list[index++]
    }

    fun point(): Point {
        return Point.fromIndex(index - 1, matrix.ySize)
    }


}

data class Matrix<T>(val ySize: Int, val xSize: Int, val init: T, val separator: Char? = null) {
    val list = mutableListOf<T>()

    init {
        for (i in 1..(ySize * xSize)) {
            list.add(init)
        }
    }

    operator fun iterator(): MatrixIterator<T> {
        return MatrixIterator(this)
    }

    fun read(reader: () -> T) {
        list.forEachIndexed { i, t -> list[i] = reader() }
    }

    fun find(target: T): Point? {
        for ((i, t) in list.withIndex()) {
            if (t == target) return Point.fromIndex(i, ySize)
        }
        return null
    }

    fun depthSearch() {

    }

    fun breadthSearch() {

    }

    operator fun get(index: Int): T {
        return list[index]
    }

    operator fun get(y: Int, x: Int): T {
        return list[y * ySize + x]
    }

    override fun toString(): String {
        var str = ""
        for ((i, t) in list.withIndex()) {
            str += t
            if (separator != null && i % ySize != ySize - 1) {
                str += separator
            }
            if (i % ySize == ySize - 1) {
                str += "\n"
            }

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

    val A = sc.nextInt()
    val B = sc.nextInt()

    val x = 0
    val y = 0

    val mat = mutableListOf<MutableList<Char>>()
    for (a in 1..100) {
        mat.add(mutableListOf<Char>())
    }
    for (y in 0..99) {
        for (x in 0..99) {
            val c = if ((y + x) % 2 == 0) {
                '.'
            }
            else {
                '#'
            }
            val xline = mat[y]
            xline.add(c)

        }
    }


    //println(brues)
    //println(sum)

}

