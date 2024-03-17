val input = mutableListOf("A", "A", "100","B", "10","20","30","40","50","60","70","80","90","100")
fun readLine(): String? {
    val line = input.firstOrNull()
    input.removeAt(0)
    return line
}
class Game{
    init{
        println("Welcome to the game!")
        val player = Player()
        var riddle : Int
        var guess : Int
        var isRiddleHigher : Boolean? = null
        var isPlayerGuesser : Boolean? = player.choose()
        val computer = Computer_Player()

        while(isPlayerGuesser != null){
            when(isPlayerGuesser){
                true ->{
                    riddle = computer.playTurnRiddler()
                    while(true){
                        guess = player.guess()
                        if(guess == riddle){
                            println("Congratulations! You got it!")
                            isPlayerGuesser = player.choose()
                            break
                        }
                        if(guess < riddle){
                            println("You guessed number that is lower than riddle")
                        } else {
                            println("You guessed number that is higher than riddle")
                        }
                    }
                }
                false ->{
                    riddle = player.riddle()!!
                    while(true){
                        guess = computer.playTurnGuesser(isRiddleHigher)
                        if(guess == riddle) {
                            println("Computer got it!")
                            isPlayerGuesser = player.choose()
                            break
                        }
                        isRiddleHigher = guess < riddle
                    }
                }
            }
        }
    }
}
class Computer_Player(){
    private var guessNumber : Int = 0
    private var low: Int = 0
    private var high: Int = 100
    fun playTurnRiddler() : Int {
        println("Computer riddled some number")
        return (0..100).random()
    }
    fun playTurnGuesser(numberHigher: Boolean?): Int {
        when(numberHigher){
            true -> {
                low = guessNumber + 1
                guessNumber = low + (high - low) / 2
            }
            false -> {
                high = guessNumber - 1
                guessNumber = low + (high - low) / 2
            }
            null -> {
                guessNumber = (0..100).random()
            }
        }
        println("Computer guessed: $guessNumber")
        return guessNumber
    }
}

class Player {

    fun choose(): Boolean? {
        println(
            "Pick a side\n" +
            "a) Riddler\n" +
            "b) Guesser\n" +
            "c) Quit the game"
        )
        var choice: String = readLine()!!.lowercase()

        return when (choice) {
            "a" -> false
            "b" -> true
            else -> null
        }
    }
    fun guess() : Int{
        print("Guess a number(range is 0-100): ")
        while(true){
            val guessNumber : Int? = readLine()!!.toIntOrNull()
            if(guessNumber in 0..100){
                return guessNumber!!
            } else{
                println("Number you type should be in range of 0-100")
            }
        }
    }
    fun riddle(): Int? {
        println("What number do you riddle?(0 to 100)")
        while (true) {
            when (val riddleNumber = readLine()!!.toIntOrNull()) {
                in 0..100 -> {
                    println("Saved!")
                    return riddleNumber
                }

                else -> println("Riddled number should be a number in range of 0-100")
            }
        }
    }
}

fun main() {
    val instance = Game()
}