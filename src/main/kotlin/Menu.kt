import java.util.Scanner

open class Menu(private val title: String) {

    private val scanner: Scanner = Scanner(System.`in`)
    private val option: MutableList<Pair<String, () -> Unit>> = mutableListOf()

    fun addOption(name: String, action: () -> Unit) {
        option.add(name to action)
    }

    fun show() {
        while (true) {
            println(title)
            option.forEachIndexed { index, option -> println("$index. ${option.first}") }
            println("Введите номер пункта:")

            val input = scanner.nextLine().toIntOrNull()
            if (input == null) {
                println("Некорректный ввод. Пожалуйста, введите номер пункта")
            } else if (input !in option.indices) {
                println("Номер пункта несуществует. Пожалуйста, введите существующий номер пункта: ${option.indices}")
            } else {
                option[input].second.invoke()
                break
            }
        }
    }
}