package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Flag
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FlagFactoryTest {

    @Test
    fun canParse() {
        val parts = listOf("flag", "Albania")
        val expectedFlag = Flag("Albania")

        val actualFlag = FlagFactory.getFlag(parts)

        assertEquals(expectedFlag, actualFlag)
    }

}