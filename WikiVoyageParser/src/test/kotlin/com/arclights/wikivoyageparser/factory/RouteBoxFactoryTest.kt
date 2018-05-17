package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Route
import com.arclights.wikivoyageparser.RouteBox
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RouteBoxFactoryTest {

    @Test
    fun canParse() {
        testCases
                .forEach { (input, expectedResult) ->
                    val result = RouteBoxFactory.getRouteBox(input)
                    assertEquals(expectedResult, result)
                }
    }

    private val testCases = mapOf(
            listOf(
                    "routebox\\n",
                    " image1=Tabliczka E4.svg\\n",
                    " imagesize1=22\\n",
                    " directionl1=S\\n",
                    " majorl1=[[Norrköping]]\\n",
                    " minorl1=[[Södertälje]]\\n",
                    " directionr1=N\\n",
                    " majorr1=[[Sundsvall]]\\n",
                    " minorr1=[[Solna]]\\n\\n",
                    " image2=Tabliczka E18.svg\\n",
                    " imagesize2=22\\n",
                    " directionl2=W\\n",
                    " majorl2=[[Oslo]]\\n",
                    " minorl2=[[Enköping]]\\n",
                    " directionr2=E\\n",
                    " majorr2=[[Turku]] ([[Image:Ferry.png|18px]])\\n",
                    " minorr2=[[Norrtälje]]\\n\\n",
                    " image3=Tabliczka E20.svg\\n",
                    " imagesize3=22\\n",
                    " directionl3=W\\n",
                    " majorl3=[[Göteborg]]\\n",
                    " minorl3=[[Södertälje]]\\n",
                    " directionr3=E\\n",
                    " majorr3=[[Tallinn]]\\n",
                    " minorr3=[[Image:Ferry.png|18px]]\\n"
            )
                    to
                    RouteBox(
                            routes = listOf(
                                    Route(
                                            imageLink = "Tabliczka E4.svg",
                                            imageSize = 22,
                                            directionl = "S",
                                            majorl = "[[Norrköping]]",
                                            minorl = "[[Södertälje]]",
                                            directionr = "N",
                                            majorr = "[[Sundsvall]]",
                                            minorr = "[[Solna]]"
                                    ),
                                    Route(
                                            imageLink = "Tabliczka E18.svg",
                                            imageSize = 22,
                                            directionl = "W",
                                            majorl = "[[Oslo]]",
                                            minorl = "[[Enköping]]",
                                            directionr = "E",
                                            majorr = "[[Turku]] ([[Image:Ferry.png|18px]])",
                                            minorr = "[[Norrtälje]]"
                                    ),
                                    Route(
                                            imageLink = "Tabliczka E20.svg",
                                            imageSize = 22,
                                            directionl = "W",
                                            majorl = "[[Göteborg]]",
                                            minorl = "[[Södertälje]]",
                                            directionr = "E",
                                            majorr = "[[Tallinn]]",
                                            minorr = "[[Image:Ferry.png|18px]]"
                                    )
                            )
                    )
    )
}