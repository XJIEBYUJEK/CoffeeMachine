package machine

import java.util.*
enum class State{
    SELECT_ACTION,
    BUYING,
    FILLING
}

class CoffeeMachine(var water: Int, var milk: Int, var beans: Int, var cups: Int, var money: Int) {
    class Coffee(val Water: Int, val Milk: Int, val Beans: Int, val Money: Int)
    val espresso = Coffee(250, 0, 16, 4)
    val latte = Coffee(350, 75, 20, 7)
    val cappuccino = Coffee(200, 100, 12, 6)
    var state = State.SELECT_ACTION
    var working: Boolean = true
    var input: String = ""
    var stage: Int = 1
    fun process(inp: String){
        input = inp
        when(state) {
            State.SELECT_ACTION -> when(input){
                "buy" -> {
                    state = State.BUYING
                    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                }
                "fill" -> {
                    state = State.FILLING
                    println("Write how many ml of water do you want to add: ")
                }
                "take" -> {
                    println("I gave you \$$money\n")
                    money = 0
                    nextAction()
                }
                "remaining" -> {
                    message()
                    nextAction()
                }
                "exit" -> exit()
            }
            State.BUYING -> when(input){
                "1" -> {
                    buy(espresso)
                }
                "2" -> {
                    buy(latte)
                }
                "3" -> {
                    buy(cappuccino)
                }
                "back" -> {
                    state = State.SELECT_ACTION
                    nextAction()
                }
            }
            State.FILLING -> when(stage){
                1 -> {
                    water += input.toInt()
                    stage++
                    println("Write how many ml of milk do you want to add: ")
                }
                2 -> {
                    milk += input.toInt()
                    stage++
                    println("Write how many grams of coffee beans do you want to add: ")
                }
                3 -> {
                    beans += input.toInt()
                    stage++
                    println("Write how many disposable cups of coffee do you want to add: ")
                }
                4 -> {
                    cups += input.toInt()
                    stage = 1
                    state = State.SELECT_ACTION
                    nextAction()
                }
            }
        }
    }

    private fun exit(){
        working = false
    }

    private fun message(){
        println("The coffee machine has:\n" +
                "$water of water\n" +
                "$milk of milk\n" +
                "$beans of coffee beans\n" +
                "$cups of disposable cups\n" +
                "$money of money\n")
    }

    private fun isPos(number:Int) = number >= 0
    fun nextAction(){
        println("Write action (buy, fill, take, remaining, exit): ")
    }

    private fun buy(coffee: Coffee){
        if (isPos(water - coffee.Water) && isPos(beans - coffee.Beans) && isPos(cups - 1) && isPos(milk - coffee.Milk)){
            println("I have enough resources, making you a coffee!")
            water -= coffee.Water
            milk -= coffee.Milk
            beans -= coffee.Beans
            money += coffee.Money
            cups -= 1
        }
        else{
            if (!isPos(water - coffee.Water)){
                println("Sorry, not enough water!\n")
            }
            if (!isPos(milk - coffee.Milk)){
                println("Sorry, not enough milk!\n")
            }
            if (!isPos(beans - coffee.Beans)){
                println("Sorry, not enough coffee beans!\n")
            }
            if (!isPos(cups - 1)){
                println("Sorry, not enough disposable cups!\n")
            }
        }
        state = State.SELECT_ACTION
        nextAction()
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val coffeeMachine = CoffeeMachine(400,540,120,9,550)
    coffeeMachine.nextAction()
    do {
        coffeeMachine.process(scanner.next())
    }while (coffeeMachine.working)
}