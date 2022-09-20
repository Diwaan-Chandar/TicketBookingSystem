class Passenger(mailID: String, name:String, age: Int, mobileNo: String): User("Passenger", mailID, name, age, mobileNo) {
    private var myBookings = mutableMapOf<Int, MutableList<Int>>() //Trip ID, Seat Nos

    fun addNewBooking(tripID: Int, seats: MutableList<Int>) {
        if (tripID in myBookings.keys) myBookings[tripID]?.addAll(seats)
        else myBookings[tripID] = seats
    }

    fun printBookingDetails(): Boolean {
        if(myBookings.isEmpty()) return false
        for((tripID, seats) in myBookings.entries) {
            println("${Home.getTripDetails(tripID?:0)} ${seats.size} $seats")
        }
        return true
    }

    fun cancelTicket(tripID: Int, seats: MutableList<Int>) {
        for(i in seats) {
            myBookings[tripID]?.remove(i)
        }
        if(myBookings[tripID]?.isEmpty() == true) myBookings.remove(tripID)
    }

    fun getBooking(tripID: Int): MutableList<Int>? {
        return myBookings[tripID]
    }

    fun isValidTripID(tripID: Int): Boolean{
        return tripID in myBookings.keys
    }
}