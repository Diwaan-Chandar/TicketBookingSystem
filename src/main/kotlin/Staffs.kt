class Staffs(userType: String, mailID: String, name:String, age: Int, mobileNo: String): User(userType, mailID, name, age, mobileNo) {
    private var mySchedule = mutableListOf<Int>()

    fun addTrip(tripID: Int) {
        mySchedule.add(tripID)
    }

    fun getTrip(): MutableList<Int> {
        return mySchedule
    }
}