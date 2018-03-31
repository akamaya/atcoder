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

var sc: Scanner = Scanner(System.`in`)
fun main(args: Array<String>) {
    if (args.size > 0) {
        System.setIn(FileInputStream(args[0]))
    }
    sc = Scanner(System.`in`)

    problem()

}


fun problem() {

    val SIZE = getSc.nextInt()
    val B = getSc.nextInt()

    println(B)

}

