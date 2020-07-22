package dev.ajthom.covid

import com.soywiz.klock.Date

class StateInfos {
	fun getName(abbreviation: String): String {
		return stateMap[abbreviation]?.name ?: abbreviation
	}

	fun getState(abbreviation: String): StateInfo {
		return stateMap[abbreviation] ?: throw RuntimeException("State not found")
	}

	private val stateList = listOf(
		StateInfo("US", "United States", 	population = 331808807),
		StateInfo("AK", "Alaska", 			population = 731545),
		StateInfo("AL", "Alabama", 			population = 4903185, 		maskMandateStart = Date(2020, 7, 15), maskMandateEnd = Date(2020, 7, 31)),
		StateInfo("AS", "American Samoa", 	population = 49437),
		StateInfo("AZ", "Arizona", 			population = 7278717),
		StateInfo("AR", "Arkansas", 		population = 3017804,		maskMandateStart = Date(2020, 7, 20)),
		StateInfo("CA", "California", 39512223, maskMandateStart = Date(2020, 6, 18)),
		StateInfo("CO", "Colorado", 5758736, maskMandateStart = Date(2020, 7, 17)),
		StateInfo("CT", "Connecticut", 3565287, maskMandateStart = Date(2020, 4, 20)),
		StateInfo("DE", "Delaware", 973764, maskMandateStart = Date(2020, 4, 28)),
		StateInfo("DC", "District of Columbia", 705749, maskMandateStart = Date(2020, 6, 16)),
		StateInfo("FL", "Florida", 21477737),
		StateInfo("GA", "Georgia", 10617423),
		StateInfo("GU", "Guam", 168485),
		StateInfo("HI", "Hawaii", 1415872, maskMandateStart = Date(2020, 4, 20)),
		StateInfo("ID", "Idaho", 1787065),
		StateInfo("IL", "Illinois", 12671821, maskMandateStart = Date(2020, 5, 1)),
		StateInfo("IN", "Indiana", 6732219),
		StateInfo("IA", "Iowa", 3155070),
		StateInfo("KS", "Kansas", 2913314, maskMandateStart = Date(2020, 7, 3)),
		StateInfo("KY", "Kentucky", 4467673, maskMandateStart = Date(2020, 5, 11)),
		StateInfo("LA", "Louisiana", 4648794, maskMandateStart = Date(2020, 7, 13)),
		StateInfo("ME", "Maine", 1344212, maskMandateStart = Date(2020, 5, 1)),
		StateInfo("MD", "Maryland", 6045680, maskMandateStart = Date(2020, 4, 18)),
		StateInfo("MA", "Massachusetts", 6892503, maskMandateStart = Date(2020, 5, 6)),
		StateInfo("MI", "Michigan", 9986857, maskMandateStart = Date(2020, 6, 18)),
		StateInfo("MN", "Minnesota", 5639632, maskMandateStart = Date(2020, 7, 25)),
		StateInfo("MS", "Mississippi", 2679149),
		StateInfo("MO", "Missouri", 6137428),
		StateInfo("MT", "Montana", 1068778, maskMandateStart = Date(2020, 7, 16)),
		StateInfo("NE", "Nebraska", 1934408),
		StateInfo("NV", "Nevada", 3080156, maskMandateStart = Date(2020, 6, 24)),
		StateInfo("NH", "New Hampshire", 1359711),
		StateInfo("NJ", "New Jersey", 8882190, maskMandateStart = Date(2020, 4, 8)),
		StateInfo("NM", "New Mexico", 2096829, maskMandateStart = Date(2020, 5, 16)),
		StateInfo("NY", "New York", 19453561, maskMandateStart = Date(2020, 4, 17)),
		StateInfo("NC", "North Carolina", 10448084, maskMandateStart = Date(2020, 6, 26)),
		StateInfo("ND", "North Dakota", 762062),
		StateInfo("MP", "Northern Mariana Islands", 51433),
		StateInfo("OH", "Ohio", 11689100),
		StateInfo("OK", "Oklahoma", 3956971),
		StateInfo("OR", "Oregon", 4217737, maskMandateStart = Date(2020, 7, 1)),
		StateInfo("PA", "Pennsylvania", 12801989, maskMandateStart = Date(2020, 4, 19)),
		StateInfo("PR", "Puerto Rico", 3193694),
		StateInfo("RI", "Rhode Island", 1059361, maskMandateStart = Date(2020, 5, 8)),
		StateInfo("SC", "South Carolina", 5148714),
		StateInfo("SD", "South Dakota", 884659),
		StateInfo("TN", "Tennessee", 6829174),
		StateInfo("TX", "Texas", 28995881, maskMandateStart = Date(2020, 7, 3)),
		StateInfo("VI", "Virgin Islands", 106235),
		StateInfo("UT", "Utah", 3205958),
		StateInfo("VT", "Vermont", 623989),
		StateInfo("VA", "Virginia", 8535519, maskMandateStart = Date(2020, 5, 29)),
		StateInfo("WA", "Washington", 7614893, maskMandateStart = Date(2020, 6, 26)),
		StateInfo("WV", "West Virginia", 1792147, maskMandateStart = Date(2020, 7, 6)),
		StateInfo("WI", "Wisconsin", 5822434),
		StateInfo("WY", "Wyoming", 578759)
	).doForEach { it.doFreeze() }.doFreeze()

	private val stateMap = stateList.associateBy { it.abbreviation }.doFreeze()
}

data class StateInfo(val abbreviation: String, val name: String, val population: Long, val maskMandateStart: Date? = null, val maskMandateEnd: Date? = null)
