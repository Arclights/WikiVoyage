package com.arclights.wikivoyageparser.parser

import com.arclights.wikivoyageparser.Details
import com.arclights.wikivoyageparser.StringIterator

object DetailsParser {
    fun parse(iterator: StringIterator, title: String) = Details(
            title = title,
            sections = SectionsParser.parse(iterator)
    )
}