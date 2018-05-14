package com.arclights.wikivoyageparser.parser

import com.arclights.wikivoyageparser.StringIterator
import com.arclights.wikivoyageparser.Template
import com.arclights.wikivoyageparser.factory.TemplateFactory

object TemplateParser {
    fun parse(iterator: StringIterator): Template {
        val sb = StringBuilder()
        var depth = 0
        sb.append(iterator.next())
        sb.append(iterator.next())
        while (iterator.hasNext()) {
            if (iterator.isAtEndOfTemplate() && depth == 0) {
                sb.append(iterator.next())
                sb.append(iterator.next())
                println("Template: " + sb.toString() + " :Template")
                return TemplateFactory.getTemplate(sb.toString())
            } else if (iterator.isAtStartOfTemplate()) {
                depth++
            } else if (iterator.isAtEndOfTemplate()) {
                depth--
            }
            if (iterator.peekNext() == '\n') {
                iterator.next()
            } else {
                sb.append(iterator.next())
            }
        }

        return TemplateFactory.getTemplate(sb.toString())
    }
}