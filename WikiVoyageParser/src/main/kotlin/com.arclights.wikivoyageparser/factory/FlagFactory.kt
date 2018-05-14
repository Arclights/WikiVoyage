package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Flag

object FlagFactory {
    fun getFlag(parts: List<String>) = Flag(country = parts[1])
}