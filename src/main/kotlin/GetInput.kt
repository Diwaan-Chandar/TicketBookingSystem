import java.util.*

object GetInput {
    fun getMailID(): String {
        while (true) {
            print("Enter Mail ID: ")
            val mailID = sc.nextLine()
            if (!Validator.isEmailValid(mailID)) {
                println("Invalid Mail ID!")
                continue
            }
            return mailID
        }
    }

    fun getName(): String {
        Loop@while (true) {
            print("Enter Name: ")
            var name = sc.nextLine()
            name = name.trim()
            if (name.length < 5) {
                println("Invalid Name!")
                continue@Loop
            }
            for (i in name.indices) {
                if ((!Character.isLetter(name[i]) && !Character.isWhitespace(name[i]))) {
                    println("Invalid Name!")
                    continue@Loop
                }
            }
            return name
        }
    }

    fun getAge(): Int {
        while (true) {
            try {
                print("Enter Age: ")
                val age = sc.nextInt()
                if (age < 18) {
                    println("You must be at least 18 to Sign Up")
                    continue
                } else if (age > 90) {
                    println("Invalid Age")
                    continue
                }
                else return age
            } catch (e: InputMismatchException) {
                println("Invalid Age!")
                sc.nextLine()
                continue
            }
        }
    }

    fun getMobileNumber(): String {
        while (true) {
            try {
                print("Enter Mobile Number: ")
                val mobileNo = sc.nextLine()
                if (!Validator.isMobileNumberValid(mobileNo)) {
                    println("Invalid Mobile Number!")
                    continue
                }
                return mobileNo
            } catch (e: InputMismatchException) {
                println("Invalid Mobile Number!")
                sc.nextLine()
                continue
            }
        }
    }

    fun getPassword(): String {
        while (true) {
            print("Enter Password: ")
            val password = sc.nextLine()
            if (!Validator.isPasswordValid(password)) {
                println("Please try a stronger password!")
                continue
            }
            return password
        }
    }
}