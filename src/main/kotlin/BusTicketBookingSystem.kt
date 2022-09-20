import java.util.*
import kotlin.system.exitProcess
val sc = Scanner(System.`in`)

fun main() {
    while(true){
        try {
            println("Online Travel Reservation System")
            println("1. Sign In")
            println("2. Sign Up")
            println("3. Exit")
            print("Enter your Choice: ")

            when (sc.nextInt()) {
                1 -> SignIn.signIn()
                2 -> SignUp.signUp()
                3 -> exitProcess(0)
                else -> println("Invalid Input")
            }
        } catch (e: InputMismatchException) {
            println("Invalid Input")
            sc.nextLine()
            continue
        }
    }
}