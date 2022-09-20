class Train(tripID: Int): Trip(tripID) {
    private val trainLayout: Array<Array<Boolean>> = Array(8) { Array(5) { false } }
    private var trainManager: String? = null
    private var engineDriver :String ?= null

    override fun addTrip(): Boolean {
        super.vehicleType = "Train"
        super.addTrip()

        //Train Manager Details

        while (true) {
            print("Enter Train Manager MailID: ")
            trainManager = sc.nextLine()
            if(trainManager == "-1") {
                return false
            }

            if (!UserManagement.isValidTrainManager(trainManager?:"")) {
                println("Invalid Flight Manager Mail ID!")
                continue
            }
            break
        }

        //Engine Driver Details

        while(true) {
            print("Enter Engine Driver MailID: ")
            engineDriver = sc.nextLine()
            if(engineDriver == "-1") {
                return false
            }

            if (!UserManagement.isValidEngineDriver(engineDriver?:"")) {
                println("Invalid Engine Driver Mail ID!")
                continue
            }
            break
        }

        UserManagement.addTripForStaff(tripID, trainManager?:"", engineDriver?:"")

        println("Trip Created Successfully!!")
        println("Trip ID: $tripID")
        println("From   : $from")
        println("To     : $to")
        println("Date   : $date")
        return true
    }

    fun printTrain() {
        var seatNo = 1
        var tempSeatNo = 1
        for(i in trainLayout) {
            for(j in i) {
                if(seatNo % 5 == 3) print("\t")
                print("${seatNo++}\t")
            }
            println()
            for(j in i) {
                tempSeatNo ++
                if(tempSeatNo % 5 == 4) print("\t")
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
            val i: Int = (t - 1) / 5
            val j: Int = (t - 1) % 5
            trainLayout[i][j] = true
        }
    }

    fun setAsUnbooked(ticket: List<Int>) {
        for(t in ticket){
            val i: Int = (t - 1) / 5
            val j: Int = (t - 1) % 5
            trainLayout[i][j] = false
        }
    }

    fun isSeatBooked(ticketNumber: Int): Boolean {
        val i: Int = (ticketNumber - 1) / 5
        val j: Int = (ticketNumber - 1) % 5
        return trainLayout[i][j]
    }
}

