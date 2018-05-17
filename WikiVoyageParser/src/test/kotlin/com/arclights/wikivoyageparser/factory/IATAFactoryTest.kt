package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.IATA
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IATAFactoryTest {

    @Test
    fun parseFlag() {
        val parts = listOf("IATA", "Airport")
        val expectedIATA = IATA("Airport")

        val actualIATA = IATAFactory.getIATA(parts)

        assertEquals(expectedIATA, actualIATA)
    }

}