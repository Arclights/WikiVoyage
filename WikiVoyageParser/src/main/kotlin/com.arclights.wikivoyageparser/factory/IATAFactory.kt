package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.IATA

object IATAFactory {
    fun getIATA(parts: List<String>) = IATA(parts[1])
}