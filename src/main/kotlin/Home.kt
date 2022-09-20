import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Home {
    private val trips = mutableMapOf<Int, Trip>()
    private var tripID = 101

    fun adminHome(mailID: String) {
        while (true) {
            try {
                println("Welcome Admin")
                println("1. Add Trip")
                println("2. View Trips")
                println("3. Add Trip Manager")
                println("4. Add Trip Driver")
                println("5. Log Out")
                print("Enter your choice: ")
                when (sc.nextInt()) {
                    1 -> {
                        when(UserManagement.getAdminType(mailID)){
                            "Bus Admin" -> trips[tripID] = Bus(tripID)
                            "Train Admin" -> trips[tripID] = Train(tripID)
                            "Flight Admin" -> trips[tripID] = Flight(tripID)
                            else -> {
                                println("Invalid Vehicle Type!")
                                continue
                            }
                        }
                        val isAdded = trips[tripID++]?.addTrip()
                        if(isAdded == false) {
                            trips.remove(--tripID)
                        }
                    }
                    2 -> {
                        if (trips.isEmpty()) {
                            println("No trips scheduled")
                        }
                        for (trip in trips.keys) {
                            trips[trip]?.printTrip()
                        }
                    }
                    3 -> {
                        sc.nextLine()
                        val managerMailID = GetInput.getMailID()
                        val name = GetInput.getName()
                        val age = GetInput.getAge()
                        sc.nextLine()
                        val mobileNo = GetInput.getMobileNumber()
                        val password = GetInput.getPassword()

                        when(UserManagement.getAdminType(mailID)) {
                            "Bus Admin" -> {
                                UserManagement.addUser("Conductor", managerMailID, name, age, mobileNo)
                                Authenticator.addPassword(managerMailID, password)
                            }
                            "Train Admin" -> {
                                UserManagement.addUser("Train Manager", managerMailID, name, age, mobileNo)
                                Authenticator.addPassword(managerMailID, password)
                            }
                            "Flight Admin" -> {
                                UserManagement.addUser("Flight Manager", managerMailID, name, age, mobileNo)
                                Authenticator.addPassword(managerMailID, password)
                            }
                        }
                    }
                    4 -> {
                        sc.nextLine()
                        val driverMailID = GetInput.getMailID()
                        val name = GetInput.getName()
                        val age = GetInput.getAge()
                        sc.nextLine()
                        val mobileNo = GetInput.getMobileNumber()
                        val password = GetInput.getPassword()

                        when(UserManagement.getAdminType(mailID)) {
                            "Bus Admin" -> {
                                UserManagement.addUser("Driver", driverMailID, name, age, mobileNo)
                                Authenticator.addPassword(driverMailID, password)
                            }
                            "Train Admin" -> {
                                UserManagement.addUser("Engine Driver", driverMailID, name, age, mobileNo)
                                Authenticator.addPassword(driverMailID, password)
                            }
                            "Flight Admin" -> {
                                UserManagement.addUser("Pilot", driverMailID, name, age, mobileNo)
                                Authenticator.addPassword(driverMailID, password)
                            }
                        }
                    }
                    5 -> return
                    else -> println("Invalid Input")
                }
            } catch (e: InputMismatchException) {
                println("Invalid Choice!")
                sc.nextLine()
                continue
            }
        }
    }
    fun managerHome(mailID: String) {
        while (true){
            try {
                println("Welcome ${UserManagement.getName(mailID)}")
                println("1. View Schedule")
                println("2. Log Out")
                print("Enter your choice: ")
                when (sc.nextInt()) {
                    1 -> {
                        try {
                            for (trip in UserManagement.getTripFromStaff(mailID)) {
                                trips[trip]?.printTrip()
                            }
                            println("Enter -1 to Go Back")
                            print("Enter Trip ID to view more details: ")
                            val tempTripID = sc.nextInt()
                            if(tempTripID !in trips.keys) {
                                println("Invalid input!")
                                continue
                            }
                            trips[tempTripID]?.printBookingDetails()
                        } catch (e: InputMismatchException) {
                            println("Invalid Input!")
                            sc.nextLine()
                            continue
                        }
                    }
                    2 -> return
                }
            } catch (e: InputMismatchException) {
                println("Invalid Input!")
                sc.nextLine()
                continue
            }
        }
    }
    fun driverHome(mailID: String) {
        while (true){
            try {
                println("Welcome ${UserManagement.getName(mailID)}")
                println("1. View Schedule")
                println("2. Log Out")
                print("Enter your choice: ")
                when (sc.nextInt()) {
                    1 -> {
                        for (trip in UserManagement.getTripFromStaff(mailID)) {
                            trips[trip]?.printTrip()
                        }
                    }
                    2 -> return
                }
            } catch (e: InputMismatchException) {
                println("Invalid Input!")
                sc.nextLine()
                continue
            }
        }
    }
    fun passengerHome(mailID: String) {
        while (true){
            try {
                println("Welcome ${UserManagement.getName(mailID)}")
                println("1. Book Ticket")
                println("2. My Bookings")
                println("3. Cancel Ticket")
                println("4. Log Out")
                print("Enter your choice: ")

                when (sc.nextInt()) {
                    1 -> {
                        var city: Int
                        var from: String
                        sc.nextLine()
                        while (true){
                            try {
                                println("From City: ")
                                Cities.printCityList()
                                print("Enter your Choice: ")
                                city = sc.nextInt()
                                from = Cities.getCityFromList(city)
                                if (from == "") {
                                    println("Invalid Choice!")
                                    continue
                                }
                                break
                            } catch (e: InputMismatchException) {
                                println("Invalid Choice")
                                sc.nextLine()
                                continue
                            }
                        }
                        var to: String
                        while (true){
                            try {
                                println("To City: ")
                                Cities.printCityList()
                                print("Enter your Choice: ")
                                city = sc.nextInt()
                                to = Cities.getCityFromList(city)
                                if (to == "") {
                                    println("Invalid Choice!")
                                    continue
                                }
                                break
                            } catch (e: InputMismatchException) {
                                println("Invalid Choice")
                                sc.nextLine()
                                continue
                            }
                        }

                        var date: Date
                        while (true){
                            try {
                                print("Enter Journey date (dd-MM-yyyy): ")
                                date = SimpleDateFormat("dd-MM-yyyy").parse(sc.next())
                                break
                            } catch (e: ParseException) {
                                println("Invalid Date")
                                sc.nextLine()
                                continue
                            }
                        }

                        val availableTrips = mutableListOf<Int>()
                        for (trip in trips.keys) {
                            if (trips[trip]?.isTripAvailable(from, to, date) == true) {
                                availableTrips.add(trip)
                                println("${trips[trip]?.printTrip()}")
                            }
                        }

                        if(availableTrips.isEmpty()) {
                            println("No trips available for this Date / Place!")
                            continue
                        }

                        var trip: Int
                        while (true){
                            try {
                                print("Enter Trip ID to book tickets: ")
                                trip = sc.nextInt()
                                if (trip in availableTrips) {
                                    val booking = trips[trip]?.bookTicket(mailID)
                                    if (booking != null) {
                                        UserManagement.addTrip(mailID, trip, booking)
                                    }
                                } else {
                                    println("Invalid Trip ID!")
                                    continue
                                }
                                break
                            } catch (e: InputMismatchException) {
                                println("Invalid Trip ID!")
                                sc.nextLine()
                                continue
                            }
                        }


                    }
                    2 -> {
                        if(!UserManagement.printMyBookings(mailID)) {
                            println("No bookings available")
                            continue
                        }
                    }
                    3 -> {
                        val isBookingAvailable = UserManagement.printMyBookings(mailID)
                        if(!isBookingAvailable) {
                            println("No bookings available")
                            continue
                        }
                        var tripID: Int
                        val seatNos = mutableListOf<Int>()
                        while (true) {
                            try {
                                seatNos.clear()
                                print("Enter Trip ID to Cancel: ")
                                tripID = sc.nextInt()
                                if (!UserManagement.isValidTripID(tripID, mailID)) {
                                    println("Invalid Trip ID!")
                                    continue
                                }
                                sc.nextLine()
                                print("Enter Seat Numbers to cancel: ")
                                val seats = sc.nextLine()
                                val seatsStr = seats.split(",")
                                for(i in seatsStr) {
                                    seatNos.add(Integer.parseInt(i))
                                }

                                break
                            } catch (e: InputMismatchException) {
                                println("Invalid Booking ID!")
                                continue
                            }
                        }
                        UserManagement.cancelTicket(tripID, mailID, seatNos)
                    }
                    4 -> return
                }
            } catch (e: InputMismatchException) {
                println("Invalid Choice!")
                sc.nextLine()
                continue
            }
        }
    }

    fun getTripDetails(tripID: Int): String? {
        return trips[tripID]?.getTripDetails()
    }

    fun cancelTicket(tripID: Int, seatNos: MutableList<Int>) {
        trips[tripID]?.cancelTicket(seatNos)
    }
}