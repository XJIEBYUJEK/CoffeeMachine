fun main() {
    val input = readLine()!!
    if (input.isNotEmpty()){
        when(input.first()){
            'i' -> println(input.drop(1).toInt()+1)
            's' -> println(input.drop(1).reversed())
            else -> println(input)
        }
    }

}