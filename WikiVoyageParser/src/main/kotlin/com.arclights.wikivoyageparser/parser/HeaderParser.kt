package com.arclights.wikivoyageparser.parser

import com.arclights.wikivoyageparser.Constants.Companion.headerDelimiter
import com.arclights.wikivoyageparser.Header
import com.arclights.wikivoyageparser.StringIterator

object HeaderParser {
    fun parse(iterator: StringIterator): Header {
        if (iterator.peekNext() != headerDelimiter) {
            return Header("Intro")
        }

        var headerCounter = 0
        while (iterator.peekNext() == headerDelimiter) {
            iterator.next()
            headerCounter++
        }
        val headerText = StringBuilder()
        while (iterator.peekNext() != headerDelimiter) {
            headerText.append(iterator.next())
        }
        var i = headerCounter
        while (i > 0) {
            iterator.next()
            i--
        }
        return Header(headerText.toString())
    }
}