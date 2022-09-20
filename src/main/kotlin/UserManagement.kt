object UserManagement {
    private val users = mutableMapOf<String, User>()

    init {
        users["admin@bus.com"] = User("Bus Admin", "admin@bus.com", "Bus Admin", 30, "+911234567890")
        users["admin@train.com"] = User("Train Admin", "admin@train.com", "Train Admin", 30, "+911234567890")
        users["admin@flight.com"] = User("Flight Admin", "admin@flight.com", "Flight Admin", 30, "+911234567890")
    }

    val getUserType: (String) -> String? = { mailID -> users[mailID]?.userType }

    val getName: (String) -> String? = { mailID -> users[mailID]?.name }

    fun addPassenger(mailID: String , name: String , age: Int, mobileNo: String) {
        users[mailID] = Passenger(mailID, name, age, mobileNo)
    }

    fun addUser(userType: String, mailID: String , name: String , age: Int, mobileNo: String) {
        users[mailID] = Staffs(userType, mailID, name, age, mobileNo)
    }

    fun addTripForStaff(tripID: Int, conductor: String, driver: String) {
        (users[conductor] as Staffs).addTrip(tripID)
        (users[driver] as Staffs).addTrip(tripID)
    }

    fun getTripFromStaff(mailID: String): MutableList<Int> {
        return (users[mailID] as Staffs).getTrip()
    }

    fun addTrip(mailID: String, tripID: Int, tickets: MutableList<Int>) {
        (users[mailID] as Passenger).addNewBooking(tripID, tickets)
    }

    fun printMyBookings(mailID: String): Boolean {
        return (users[mailID] as Passenger).printBookingDetails()
    }

    fun cancelTicket(tripID: Int, mailID: String, seatNos: MutableList<Int>) {
        val booking = (users[mailID] as Passenger).getBooking(tripID)
        if (booking != null) {
            for(i in seatNos) {
                if (i !in booking) {
                    println("One or more invalid Seat Numbers!")
                    return
                }
            }
        }
        Home.cancelTicket(tripID, seatNos)
        (users[mailID] as Passenger).cancelTicket(tripID, seatNos)

    }

    fun getMobileNumber(mailID: String): String? {
        return users[mailID]?.mobileNumber
    }

    fun isValidTripID(tripID: Int, mailID: String): Boolean {
        return (users[mailID] as Passenger).isValidTripID(tripID)
    }

    fun isValidConductor(mailID: String): Boolean {
        return users[mailID]?.userType == "Conductor"
    }

    fun isValidDriver(mailID: String): Boolean {
        return users[mailID]?.userType == "Driver"
    }

    fun getAdminType(mailID: String): String? {
        return users[mailID]?.userType
    }

    fun isValidFlightManager(mailID: String): Boolean {
        return users[mailID]?.userType == "Flight Manager"
    }

    fun isValidPilot(mailID: String): Boolean {
        return users[mailID]?.userType == "Pilot"
    }

    fun isValidTrainManager(mailID: String): Boolean {
        return users[mailID]?.userType == "Train Manager"
    }

    fun isValidEngineDriver(mailID: String): Boolean {
        return users[mailID]?.userType == "Engine Driver"
    }
}