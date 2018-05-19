package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.NullTemplate
import com.arclights.wikivoyageparser.StringIterator
import com.arclights.wikivoyageparser.Template
import com.arclights.wikivoyageparser.factory.Utils.trimSpaceAndNewline

object TemplateParser {

    fun parse(iterator: StringIterator): Template {
        val parts = parseParts(iterator)
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

    private fun parseParts(iterator: StringIterator): List<String> {
        var partBuilder = StringBuilder()
        var parts = emptyList<String>()

        val delim = '|'
        var inBrackets = false
        var inCurlBrackets = false

        iterator.next()
        iterator.next()
        while (iterator.hasNext()) {
            when {
                iterator.next2IsStartBrackets() -> {
                    partBuilder.append(iterator.next())
                    partBuilder.append(iterator.next())
                    inBrackets = true
                }
                iterator.next2IsEndBrackets() -> {
                    partBuilder.append(iterator.next())
                    partBuilder.append(iterator.next())
                    inBrackets = false
                }
                iterator.isAtStartOfTemplate() -> {
                    iterator.next()
                    iterator.next()
                    inCurlBrackets = true
                }
                iterator.isAtEndOfTemplate() && inCurlBrackets -> {
                    partBuilder.append(iterator.next())
                    partBuilder.append(iterator.next())
                    inCurlBrackets = false
                }
            }
            if (!iterator.hasNext()) {
                return parts
            }

            if (iterator.isAtEndOfTemplate()) {
                iterator.next()
                iterator.next()
                parts = parts.plus(partBuilder.toString())
                return parts
            }

            if (iterator.peekNext() == delim && !inBrackets && !inCurlBrackets) {
                parts = parts.plus(partBuilder.toString())
                partBuilder = StringBuilder()
                iterator.next()
            } else {
                partBuilder.append(iterator.next())
            }
        }
        return parts
    }
}