package com.arclights.wikivoyageparser.parser

import com.arclights.wikivoyageparser.Constants.Companion.headerDelimiter
import com.arclights.wikivoyageparser.Content
import com.arclights.wikivoyageparser.HorizontalSpace
import com.arclights.wikivoyageparser.StringIterator
import java.util.*

object ContentParser {
    fun parse(iterator: StringIterator): List<Content> {
        val out = ArrayList<Content>()

        while (iterator.hasNext() && iterator.peekNext() != headerDelimiter) {
            when (iterator.peekNext()) {
                '\n' -> {
                    // deal with horizontal spacing
                    out.add(HorizontalSpace())
                    iterator.next()
                }
                '*' -> out.add(ListParser.parse(iterator))
                ':' -> println("Found indented content: ${iterator.currentPlace()}")
                '{' -> out.add(TemplateParser.parse(iterator))
                else -> out.add(TextParser.parseText(iterator))
            }
        }

        return out
    }
}