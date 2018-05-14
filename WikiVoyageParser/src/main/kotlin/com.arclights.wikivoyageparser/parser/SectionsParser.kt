package com.arclights.wikivoyageparser.parser

import com.arclights.wikivoyageparser.Section
import com.arclights.wikivoyageparser.StringIterator

object SectionsParser {
    fun parse(iterator: StringIterator): List<Section> {
        val sections = ArrayList<Section>()
        while (iterator.hasNext()) {
            val header = HeaderParser.parse(iterator)
            sections.add(Section(
                    header = header,
                    content = ContentParser.parse(iterator)
            ))
        }
        return sections
    }
}