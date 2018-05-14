package com.arclights.wikivoyageparser.parser

import com.arclights.wikivoyageparser.IndentedText
import com.arclights.wikivoyageparser.StringIterator
import com.arclights.wikivoyageparser.Text

object TextParser {
    fun parseIndentedTextContent(iterator: StringIterator) = parseText(iterator) as IndentedText

    fun parseText(iterator: StringIterator): Text {
        val textBuilder = StringBuilder()
        while (iterator.hasNext() && iterator.peekNext() != '\n') {
            textBuilder.append(iterator.next())
        }
        if (iterator.hasNext() && iterator.peekNext() == '\n') {
            iterator.next()
        }
        return Text(textBuilder.toString())
    }
}