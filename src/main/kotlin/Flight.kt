class Flight(tripID: Int): Trip(tripID) {
    private val flightLayout: Array<Array<Boolean>> = Array(12) { Array(6) { false } }
    private var flightManager: String? = null
    private var pilot :String ?= null

    override fun addTrip(): Boolean {
        super.vehicleType = "Flight"
        super.addTrip()

        //Flight Manager Details

        while (true) {
            print("Enter Flight Manager MailID: ")
            flightManager = sc.nextLine()
            if(flightManager == "-1") {
                return false
            }

            if (!UserManagement.isValidFlightManager(flightManager?:"")) {
                println("Invalid Flight Manager Mail ID!")
                continue
            }
            break
        }

        //Driver Details

        while(true) {
            print("Enter pilot MailID: ")
            pilot = sc.nextLine()
            if(pilot == "-1") {
                return false
            }

            if (!UserManagement.isValidPilot(pilot?:"")) {
                println("Invalid Pilot Mail ID!")
                continue
            }
            break
        }

        UserManagement.addTripForStaff(tripID, flightManager?:"", pilot?:"")

        println("Trip Created Successfully!!")
        println("Trip ID: $tripID")
        println("From   : $from")
        println("To     : $to")
        println("Date   : $date")
        return true
    }

    fun printFlight() {
        var seatNo = 1
        var tempSeatNo = 1
        for(i in flightLayout) {
            for(j in i) {
                if(seatNo % 3 == 1) print("\t")
                print("${seatNo++}\t")
            }
            println()
            for(j in i) {
                tempSeatNo ++
                if(tempSeatNo % 3 == 2) print("\t")
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
            val i: Int = (t - 1) / 6
            val j: Int = (t - 1) % 6
            flightLayout[i][j] = true
        }
    }

    fun setAsUnbooked(ticket: List<Int>) {
        for(t in ticket){
            val i: Int = (t - 1) / 6
            val j: Int = (t - 1) % 6
            flightLayout[i][j] = false
        }
    }

    fun isSeatBooked(ticketNumber: Int): Boolean {
        val i: Int = (ticketNumber - 1) / 6
        val j: Int = (ticketNumber - 1) % 6
        return flightLayout[i][j]
    }
}

