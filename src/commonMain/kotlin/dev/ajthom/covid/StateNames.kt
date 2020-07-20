package dev.ajthom.covid

class StateNames {
    fun getName(abbreviation: String): String {
        return stateMap[abbreviation]?.name ?: abbreviation
    }

    fun getState(abbreviation: String): StateInfo {
        return stateMap[abbreviation] ?: throw RuntimeException("State not found")
    }

    private val stateList = listOf(
            StateInfo("AK", "Alaska", 731545),
            StateInfo("AL", "Alabama", 4903185),
            StateInfo("AS", "American Samoa", 49437),
            StateInfo("AZ", "Arizona", 7278717),
            StateInfo("AR", "Arkansas", 3017804),
            StateInfo("CA", "California", 39512223),
            StateInfo("CO", "Colorado", 5758736),
            StateInfo("CT", "Connecticut", 3565287),
            StateInfo("DE", "Delaware", 973764),
            StateInfo("DC", "District of Columbia", 705749),
            StateInfo("FL", "Florida", 21477737),
            StateInfo("GA", "Georgia", 10617423),
            StateInfo("GU", "Guam", 168485),
            StateInfo("HI", "Hawaii", 1415872),
            StateInfo("ID", "Idaho", 1787065),
            StateInfo("IL", "Illinois", 12671821),
            StateInfo("IN", "Indiana", 6732219),
            StateInfo("IA", "Iowa", 3155070),
            StateInfo("KS", "Kansas", 2913314),
            StateInfo("KY", "Kentucky", 4467673),
            StateInfo("LA", "Louisiana", 4648794),
            StateInfo("ME", "Maine", 1344212),
            StateInfo("MD", "Maryland", 6045680),
            StateInfo("MA", "Massachusetts", 6892503),
            StateInfo("MI", "Michigan", 9986857),
            StateInfo("MN", "Minnesota", 5639632),
            StateInfo("MS", "Mississippi", 2679149),
            StateInfo("MO", "Missouri", 6137428),
            StateInfo("MT", "Montana", 1068778),
            StateInfo("NE", "Nebraska", 1934408),
            StateInfo("NV", "Nevada", 3080156),
            StateInfo("NH", "New Hampshire", 1359711),
            StateInfo("NJ", "New Jersey", 8882190),
            StateInfo("NM", "New Mexico", 2096829),
            StateInfo("NY", "New York", 19453561),
            StateInfo("NC", "North Carolina", 10448084),
            StateInfo("ND", "North Dakota", 762062),
            StateInfo("MP", "Northern Mariana Islands", 51433),
            StateInfo("OH", "Ohio", 11689100),
            StateInfo("OK", "Oklahoma", 3956971),
            StateInfo("OR", "Oregon", 4217737),
            StateInfo("PA", "Pennsylvania", 12801989),
            StateInfo("PR", "Puerto Rico", 3193694),
            StateInfo("RI", "Rhode Island", 1059361),
            StateInfo("SC", "South Carolina", 5148714),
            StateInfo("SD", "South Dakota", 884659),
            StateInfo("TN", "Tennessee", 6829174),
            StateInfo("TX", "Texas", 28995881),
            StateInfo("VI", "Virgin Islands", 106235),
            StateInfo("UT", "Utah", 3205958),
            StateInfo("VT", "Vermont", 623989),
            StateInfo("VA", "Virginia", 8535519),
            StateInfo("WA", "Washington", 7614893),
            StateInfo("WV", "West Virginia", 1792147),
            StateInfo("WI", "Wisconsin", 5822434),
            StateInfo("WY", "Wyoming", 578759)
    ).doFreeze()

    private val stateMap = stateList.associateBy { it.abbreviation }.doFreeze()
}

data class StateInfo(val abbreviation: String, val name: String, val population: Long)
