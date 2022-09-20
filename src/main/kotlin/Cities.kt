object Cities {
    private val city = mutableListOf<String>("Chennai", "Madurai", "Coimbatore", "Trichy", "Salem")

    fun printCityList() {
        for(i in 1..city.size){
            println("$i : ${city[i - 1]}")
        }
    }

    fun getCityFromList(i: Int): String {
        if(i in 1..city.size) return city[i - 1]
        return ""
    }

    fun addCity(city: String) {
        this.city.add(city)
    }
}
