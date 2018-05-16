package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Climate
import com.arclights.wikivoyageparser.Degree
import com.arclights.wikivoyageparser.MeasurementUnit
import com.arclights.wikivoyageparser.Precipitation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Month

class ClimateFactoryTest {

    @Test
    fun canParse() {
        testCases
                .forEach { (input, expectedResult) ->
                    val result = ClimateFactory.getClimate(input)
                    assertClimateEquals(expectedResult, result)
                }
    }

    private fun assertClimateEquals(expectedClimate: Climate, actualClimate: Climate) {
        assertEquals(expectedClimate.unit, actualClimate.unit, "Unit does not match")
        assertEquals(expectedClimate.description, actualClimate.description, "Description does not match")

        assertEquals(expectedClimate.dailyHighs.size, actualClimate.dailyHighs.size, "The number of daily highs does not match")
        expectedClimate.dailyHighs.forEach {
            assertDegree(
                    actualDegreeMap = actualClimate.dailyHighs,
                    month = it.key,
                    expectedDegree = it.value
            )
        }

        assertEquals(expectedClimate.nightlyLows.size, actualClimate.nightlyLows.size, "The number of daily lows does not match")
        expectedClimate.nightlyLows.forEach {
            assertDegree(
                    actualDegreeMap = actualClimate.nightlyLows,
                    month = it.key,
                    expectedDegree = it.value
            )
        }

        assertEquals(expectedClimate.precipitation.size, actualClimate.precipitation.size, "The number of precipitations does not match")
        expectedClimate.precipitation.forEach {
            assertPrecipitation(
                    actualPrecipMap = actualClimate.precipitation,
                    month = it.key,
                    expectedPrecipitation = it.value
            )
        }
    }

    private fun assertDegree(actualDegreeMap: Map<Month, Degree>, month: Month, expectedDegree: Degree) {
        assertTrue(actualDegreeMap.containsKey(month))
        val degree = actualDegreeMap[month]!!
        assertEquals(expectedDegree.celsius, degree.celsius, 0.0001, "Degrees in celsius does not match for $month")
        assertEquals(expectedDegree.fahrenheit, degree.fahrenheit, 0.0001, "Degrees in fahrenheit does not match for $month")
    }

    private fun assertPrecipitation(actualPrecipMap: Map<Month, Precipitation>, month: Month, expectedPrecipitation: Precipitation) {
        assertTrue(actualPrecipMap.containsKey(month))
        val actualPrecipitation = actualPrecipMap[month]!!
        assertEquals(expectedPrecipitation.millimeter, actualPrecipitation.millimeter, 0.0001, "Precipitation in millimeter does not match for $month")
        assertEquals(expectedPrecipitation.inches, actualPrecipitation.inches, 0.0001, "Precipitation in inches does not match for $month")
    }

    private val testCases = mapOf(
            listOf(
                    "Climate\n",
                    " units = Metric\n",
                    " janhigh = -0.7\n",
                    " febhigh = -0.6\n",
                    " marhigh = 3.0\n",
                    " aprhigh = 8.6\n",
                    " mayhigh = 15.7\n",
                    " junhigh = 20.7\n",
                    " julhigh = 21.9\n",
                    " aughigh = 20.4\n",
                    " sephigh = 15.1\n",
                    " octhigh = 9.9\n",
                    " novhigh = 4.5\n",
                    " dechigh = 1.1\n",
                    " janlow = -5.0\n",
                    " feblow = -5.3\n",
                    " marlow = -2.7\n",
                    " aprlow = 1.1\n",
                    " maylow = 6.3\n",
                    " junlow = 11.3\n",
                    " jullow = 13.4\n",
                    " auglow = 12.7\n",
                    " seplow = 9.0\n",
                    " octlow = 5.3\n",
                    " novlow = 0.7\n",
                    " declow = -3.2\n",
                    " janprecip = 39\n",
                    " febprecip = 27\n",
                    " marprecip = 26\n",
                    " aprprecip = 30\n",
                    " mayprecip = 30\n",
                    " junprecip = 45\n",
                    " julprecip = 72\n",
                    " augprecip = 66\n",
                    " sepprecip = 55\n",
                    " octprecip = 50\n",
                    " novprecip = 53\n",
                    " decprecip = 46\n",
                    " janh2o =\n",
                    " febh2o =\n",
                    " marh2o =\n",
                    " aprh2o =\n",
                    " mayh2o =\n",
                    " junh2o =\n",
                    " julh2o =\n",
                    " augh2o =\n",
                    " seph2o =\n",
                    " octh2o =\n",
                    " novh2o =\n",
                    " dech2o =\n",
                    " description = Average conditions for Stockholm\n"
            )
                    to
                    Climate(
                            unit = MeasurementUnit.METRIC,
                            dailyHighs = mapOf(
                                    Month.JANUARY to Degree(
                                            celsius = -0.7,
                                            fahrenheit = 30.74
                                    ),
                                    Month.FEBRUARY to Degree(
                                            celsius = -0.6,
                                            fahrenheit = 30.92
                                    ),
                                    Month.MARCH to Degree(
                                            celsius = 3.0,
                                            fahrenheit = 37.4
                                    ),
                                    Month.APRIL to Degree(
                                            celsius = 8.6,
                                            fahrenheit = 47.48
                                    ),
                                    Month.MAY to Degree(
                                            celsius = 15.7,
                                            fahrenheit = 60.26
                                    ),
                                    Month.JUNE to Degree(
                                            celsius = 20.7,
                                            fahrenheit = 69.26
                                    ),
                                    Month.JULY to Degree(
                                            celsius = 21.9,
                                            fahrenheit = 71.42
                                    ),
                                    Month.AUGUST to Degree(
                                            celsius = 20.4,
                                            fahrenheit = 68.72
                                    ),
                                    Month.SEPTEMBER to Degree(
                                            celsius = 15.1,
                                            fahrenheit = 59.18
                                    ),
                                    Month.OCTOBER to Degree(
                                            celsius = 9.9,
                                            fahrenheit = 49.82
                                    ),
                                    Month.NOVEMBER to Degree(
                                            celsius = 4.5,
                                            fahrenheit = 40.1
                                    ),
                                    Month.DECEMBER to Degree(
                                            celsius = 1.1,
                                            fahrenheit = 33.98
                                    )
                            ),
                            nightlyLows = mapOf(
                                    Month.JANUARY to Degree(
                                            celsius = -5.0,
                                            fahrenheit = 23.0
                                    ),
                                    Month.FEBRUARY to Degree(
                                            celsius = -5.3,
                                            fahrenheit = 22.46
                                    ),
                                    Month.MARCH to Degree(
                                            celsius = -2.7,
                                            fahrenheit = 27.14
                                    ),
                                    Month.APRIL to Degree(
                                            celsius = 1.1,
                                            fahrenheit = 33.98
                                    ),
                                    Month.MAY to Degree(
                                            celsius = 6.3,
                                            fahrenheit = 43.34
                                    ),
                                    Month.JUNE to Degree(
                                            celsius = 11.3,
                                            fahrenheit = 52.34
                                    ),
                                    Month.JULY to Degree(
                                            celsius = 13.4,
                                            fahrenheit = 56.12
                                    ),
                                    Month.AUGUST to Degree(
                                            celsius = 12.7,
                                            fahrenheit = 54.86
                                    ),
                                    Month.SEPTEMBER to Degree(
                                            celsius = 9.0,
                                            fahrenheit = 48.2
                                    ),
                                    Month.OCTOBER to Degree(
                                            celsius = 5.3,
                                            fahrenheit = 41.54
                                    ),
                                    Month.NOVEMBER to Degree(
                                            celsius = 0.7,
                                            fahrenheit = 33.26
                                    ),
                                    Month.DECEMBER to Degree(
                                            celsius = -3.2,
                                            fahrenheit = 26.24
                                    )
                            ),
                            precipitation = mapOf(
                                    Month.JANUARY to Precipitation(
                                            millimeter = 39.0,
                                            inches = 1.53543
                                    ),
                                    Month.FEBRUARY to Precipitation(
                                            millimeter = 27.0,
                                            inches = 1.06299
                                    ),
                                    Month.MARCH to Precipitation(
                                            millimeter = 26.0,
                                            inches = 1.02362
                                    ),
                                    Month.APRIL to Precipitation(
                                            millimeter = 30.0,
                                            inches = 1.1811
                                    ),
                                    Month.MAY to Precipitation(
                                            millimeter = 30.0,
                                            inches = 1.1811
                                    ),
                                    Month.JUNE to Precipitation(
                                            millimeter = 45.0,
                                            inches = 1.77165
                                    ),
                                    Month.JULY to Precipitation(
                                            millimeter = 72.0,
                                            inches = 2.83465
                                    ),
                                    Month.AUGUST to Precipitation(
                                            millimeter = 66.0,
                                            inches = 2.59843
                                    ),
                                    Month.SEPTEMBER to Precipitation(
                                            millimeter = 55.0,
                                            inches = 2.16535
                                    ),
                                    Month.OCTOBER to Precipitation(
                                            millimeter = 50.0,
                                            inches = 1.9685
                                    ),
                                    Month.NOVEMBER to Precipitation(
                                            millimeter = 53.0,
                                            inches = 2.08661
                                    ),
                                    Month.DECEMBER to Precipitation(
                                            millimeter = 46.0,
                                            inches = 1.81102
                                    )
                            ),
                            description = "Average conditions for Stockholm"
                    )
    )
}