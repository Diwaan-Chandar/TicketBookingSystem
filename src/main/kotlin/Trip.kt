import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.InputMismatchException
import kotlin.text.split

open class Trip(protected val tripID: Int) {
    protected var from:String? = null
    protected var to:String? = null
    protected var date: Date? = null
    private var bookings = mutableMapOf<Int, String>()
    protected var vehicleType:String? = null

    companion object {
        private var tempBookingID = 101
    }

    open fun addTrip(): Boolean {
        println("Add Trip")

        //From Place

        while (true) {
            try {
                println("From place: ")
                Cities.printCityList()
                print("Enter your choice: ")
                val city = sc.nextInt()
                from = Cities.getCityFromList(city)
                if (from == "") {
                    println("Invalid Choice!")
                    continue
                }
                break
            } catch (e: InputMismatchException) {
                println("Invalid Choice!")
                sc.nextLine()
                continue
            }
        }

        //To Place

        while (true) {
            try {
                println("To place: ")
                Cities.printCityList()
                print("Enter your choice: ")
                val city = sc.nextInt()
                to = Cities.getCityFromList(city)
                if (to == "") {
                    println("Invalid Choice!")
                    continue
                }
                break
            } catch (e: InputMismatchException) {
                println("Invalid Choice!")
                sc.nextLine()
                continue
            }
        }

        //Journey Date

        while(true) {
            try {
                print("Enter date (dd-MM-yyyy): ")
                date = SimpleDateFormat("dd-MM-yyyy").parse(sc.next())
                break
            } catch (e: ParseException) {
                println("Invalid Date Fromat!")
                continue
            }
        }
        sc.nextLine()

        return true
    }

    fun printTrip() {
        println("$tripID $from $to $date $vehicleType ")
    }

    fun getTripDetails(): String {
        return "$tripID $from $to $date $vehicleType "
    }

    fun isTripAvailable(from: String, to: String, date: Date): Boolean {
        if(this.from == from && this.to == to && this.date == date) {
            return true
        }
        return false
    }

    fun bookTicket(mailID: String): MutableList<Int> {
        when(vehicleType) {
            "Bus" -> (this as Bus).printBus()
            "Train" -> (this as Train).printTrain()
            "Flight" -> (this as Flight).printFlight()
        }

        val ticketNos = mutableListOf<Int>()
        Loop@while (true){
            try {
                ticketNos.clear()
                print("Enter tickets to book seperated by comma: ")
                val tickets = sc.nextLine()
                if(tickets == "-1") return mutableListOf()
                val tempTicket = tickets.split(',')
                for (i in tempTicket) {
                    ticketNos.add(Integer.parseInt(i))
                }
                when(vehicleType){
                    "Bus" -> for (i in ticketNos) {
                        if (i < 1 || i > 40) {
                            println("One of more of Ticket Numbers is Invalid!")
                            continue@Loop
                        }
                        if ((this as Bus).isSeatBooked(i)) {
                            println("One of more of Seats are already filled")
                            continue@Loop
                        }
                    }
                    "Train" -> for (i in ticketNos) {
                        if (i < 1 || i > 40) {
                            println("One of more of Ticket Numbers is Invalid!")
                            continue@Loop
                        }
                        if ((this as Train).isSeatBooked(i)) {
                            println("One of more of Seats are already filled")
                            continue@Loop
                        }
                    }
                    "Flight" -> for (i in ticketNos) {
                        if (i < 1 || i > 72) {
                            println("One of more of Ticket Numbers is Invalid!")
                            continue@Loop
                        }
                        if ((this as Flight).isSeatBooked(i)) {
                            println("One of more of Seats are already filled")
                            continue@Loop
                        }
                    }
                }
                break
            } catch (e: java.lang.Exception) {
                println("Invalid Ticket Format!")
                continue@Loop
            }
        }

        when(vehicleType) {
            "Bus" -> (this as Bus).setAsBooked(ticketNos)
            "Train" -> (this as Train).setAsBooked(ticketNos)
            "Flight" -> (this as Flight).setAsBooked(ticketNos)
        }

        for(i in ticketNos) {
            bookings[i] = mailID?:""
        }
        println("Ticket Booked Successfully!")

        tempBookingID++

        return ticketNos
    }

    fun cancelTicket(seatNos: MutableList<Int>) {
        for(i in seatNos) {
            bookings.remove(i)
        }
        when(vehicleType) {
            "Bus" -> (this as Bus).setAsUnbooked(seatNos)
            "Train" -> (this as Train).setAsUnbooked(seatNos)
            "Flight" -> (this as Flight).setAsUnbooked(seatNos)
        }
        println("Ticket Cancelled Successfully")
    }

    fun printBookingDetails() {
        for((seatNo, mailID) in bookings.entries){
            println("$seatNo ${UserManagement.getName(mailID)} ${UserManagement.getMobileNumber(mailID)} $mailID")
        }
    }
}

