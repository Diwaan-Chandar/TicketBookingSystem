import java.util.regex.*

object Validator {
    private fun isMatchesRegex(input: String, regex: String): Boolean{
        if (input == "") {
            return false
        }
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher (input)
        return matcher.matches()
    }

    fun isEmailValid(input: String):Boolean {
        val regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
        return isMatchesRegex(input, regex)
    }

    fun isPasswordValid(input: String):Boolean {
        val regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*])(?=\\S+$).{8,20}$"
        return isMatchesRegex(input, regex)
    }

    fun isMobileNumberValid(input: String): Boolean {
        val regex = "(\\+91)?[7-9][0-9]{9}"
        return isMatchesRegex(input, regex)
    }
}