package com.arclights.wikivoyageparser

import com.arclights.wikivoyageparser.parser.DetailsParser

object WikiVoyageParser {
    fun parse(pageContent: String, title: String): Details {
        val iterator = StringIterator(pageContent)
        return DetailsParser.parse(iterator, title)
    }
}