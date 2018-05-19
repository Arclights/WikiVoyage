package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.NullTemplate
import com.arclights.wikivoyageparser.StringIterator
import com.arclights.wikivoyageparser.Template
import com.arclights.wikivoyageparser.factory.Utils.trimSpaceAndNewline
import java.util.ArrayList

object TemplateFactory {

    fun getTemplate(content: String): Template {
        val parts = split(content.substring(2, content.length - 2), '|')
        val type = parts.first().trimSpaceAndNewline().toLowerCase()

        return when (type) {
            "do",
            "see",
            "buy",
            "eat",
            "drink",
            "sleep",
            "listing" -> ListingFactory.getListing(parts)
            "regionlist" -> RegionListFactory.getRegionList(parts)
            "flag" -> FlagFactory.getFlag(parts)
            "climate" -> ClimateFactory.getClimate(parts)
            "iata" -> IATAFactory.getIATA(parts)
            "routebox" -> RouteBoxFactory.getRouteBox(parts)
            else -> {
                println("TemplateFactory: Unknown template: $type")
                NullTemplate()
            }
        }
    }

    private fun split(string: String, delim: Char): ArrayList<String> {
        val parts = ArrayList<String>()
        var start = 0
        var end = 0
        var inBrackets = false
        var inCurlBrackets = false
        val iter = StringIterator(string)
        while (iter.hasNext()) {
            when {
                iter.next2IsStartBrackets() -> {
                    iter.next()
                    iter.next()
                    end += 2
                    inBrackets = true
                }
                iter.next2IsEndBrackets() -> {
                    iter.next()
                    iter.next()
                    end += 2
                    inBrackets = false
                }
                iter.isAtStartOfTemplate() -> {
                    iter.next()
                    iter.next()
                    end += 2
                    inCurlBrackets = true
                }
                iter.isAtEndOfTemplate() -> {
                    iter.next()
                    iter.next()
                    end += 2
                    inCurlBrackets = false
                }
            }
            if (!iter.hasNext()) {
                break
            }
            if (iter.next() == delim && !inBrackets && !inCurlBrackets) {
                parts.add(string.substring(start, end))
                start = end + 1
            }
            end++
        }
        parts.add(string.substring(start, end))

        return parts
    }
}