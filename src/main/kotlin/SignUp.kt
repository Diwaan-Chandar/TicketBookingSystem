object SignUp {
    fun signUp() {
        println("Sign Up")
        sc.nextLine()
        var mailID:String
        while (true) {
            print("Enter your Mail ID : ")
            mailID = sc.nextLine()
            if(!Validator.isEmailValid(mailID)) {
                println("Invalid mail ID")
                continue
            }
            break
        }
        if(Authenticator.isSignedAlready(mailID)) {
            println("You had already signed up, Please Sign in to continue")
            return
        }
        val name = GetInput.getName()
        val age = GetInput.getAge()
        sc.nextLine()
        val password = GetInput.getPassword()
        val mobileNo = GetInput.getMobileNumber()
        UserManagement.addPassenger(mailID, name, age, mobileNo)
        Authenticator.addPassword(mailID, password)
        println("Signed Up successfully!! Please Log In to Continue!!")
    }
}