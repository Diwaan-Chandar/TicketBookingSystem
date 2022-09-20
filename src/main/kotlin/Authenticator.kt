object Authenticator {
    private val authentication = mutableMapOf<String, String>()

    init {
        authentication["admin@bus.com"] = "123"
        authentication["admin@train.com"] = "123"
        authentication["admin@flight.com"] = "123"
    }

    fun authenticateUser(mailID: String, password: String): Boolean {
        return authentication[mailID] == password
    }

    fun addPassword(mailID: String, password: String){
        authentication[mailID] = password
    }

    fun isSignedAlready(mailID: String): Boolean {
        return mailID in authentication.keys
    }
}