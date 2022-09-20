object SignIn {
    fun signIn() {
        println("Sign In")
        sc.nextLine()
        print("Enter your Mail ID : ")
        val mailID = sc.nextLine()
        if(!Authenticator.isSignedAlready(mailID)) {
            println("You hadn't signed up, Please sign up to continue")
            return
        }
        print("Enter your Password: ")
        val password = sc.nextLine()
        if(Authenticator.authenticateUser(mailID, password)) {
            when(UserManagement.getUserType(mailID)) {
                "Bus Admin" -> Home.adminHome(mailID)
                "Train Admin" -> Home.adminHome(mailID)
                "Flight Admin" -> Home.adminHome(mailID)
                "Conductor" -> Home.managerHome(mailID)
                "Driver" -> Home.driverHome(mailID)
                "Flight Manager" -> Home.managerHome(mailID)
                "Pilot" -> Home.driverHome(mailID)
                "Engine Driver" -> Home.driverHome(mailID)
                "Train Manager" -> Home.managerHome(mailID)
                "Passenger" -> Home.passengerHome(mailID)
            }
        } else {
            println("Invalid Password")
        }
    }
}