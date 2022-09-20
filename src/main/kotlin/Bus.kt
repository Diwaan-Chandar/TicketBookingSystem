class Bus(tripID: Int): Trip(tripID) {
    private val busLayout: Array<Array<Boolean>> = Array(10) { Array(4) { false } }
    private var conductor: String? = null
    private var driver :String ?= null

    override fun addTrip(): Boolean {
        super.vehicleType = "Bus"
        super.addTrip()

        //Conductor Details

        while (true) {
            print("Enter Conductor MailID: ")
            conductor = sc.nextLine()
            if(conductor == "-1") {
                return false
            }

            if (!UserManagement.isValidConductor(conductor?:"")) {
                println("Invalid Conductor Mail ID!")
                continue
            }
            break
        }

        //Driver Details

        while(true) {
            print("Enter Driver MailID: ")
            driver = sc.nextLine()
            if(driver == "-1") {
                return false
            }

            if (!UserManagement.isValidDriver(driver?:"")) {
                println("Invalid Driver Mail ID!")
                continue
            }
            break
        }

        UserManagement.addTripForStaff(tripID, conductor?:"", driver?:"")

        println("Trip Created Successfully!!")
        println("Trip ID: $tripID")
        println("From   : $from")
        println("To     : $to")
        println("Date   : $date")
        return true
    }

    fun printBus() {
        var seatNo = 1
        var tempSeatNo = 1
        for(i in busLayout) {
            for(j in i) {
                if(seatNo % 2 == 1) print("\t")
                print("${seatNo++}\t")
            }
            println()
            for(j in i) {
                tempSeatNo ++
                if(tempSeatNo % 2 == 0) print("\t")
                if(j) print("✅\t")
                else print("🔳\t")
            }
            println()
            println()
        }
        println("✅ - Booked Already")
        println("🔳 - Available for Booking")
        println()
    }

    fun setAsBooked(ticket: List<Int>) {
        for(t in ticket) {
            val i: Int = (t - 1) / 4
            val j: Int = (t - 1) % 4
            busLayout[i][j] = true
        }
    }

    fun setAsUnbooked(ticket: List<Int>) {
        for(t in ticket){
            val i: Int = (t - 1) / 4
            val j: Int = (t - 1) % 4
            busLayout[i][j] = false
        }
    }

    fun isSeatBooked(ticketNumber: Int): Boolean {
        val i: Int = (ticketNumber - 1) / 4
        val j: Int = (ticketNumber - 1) % 4
        return busLayout[i][j]
    }
}
