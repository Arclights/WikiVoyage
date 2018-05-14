package com.arclights.wikivoyageparser.parser

import com.arclights.wikivoyageparser.ListPart
import com.arclights.wikivoyageparser.StringIterator
import com.arclights.wikivoyageparser.WikiVoyageList
import com.arclights.wikivoyageparser.WikiVoyageListItem

object ListParser {
//        fun parse(iterator: StringIterator): WikiVoyageList {
//            return parse(iterator, 1)
//        }

    fun parse(iterator: StringIterator, previousLevel: Int = 0): WikiVoyageList {
        val currLevel = getLevel(iterator)
        return WikiVoyageList(
                level = currLevel,
                subList = parseSubList(iterator, currLevel, previousLevel)
        )
    }

    private fun parseSubList(iterator: StringIterator, currLevel: Int, previousLevel: Int): List<ListPart> {
        val out = ArrayList<ListPart>()
        while (iterator.hasNext() && iterator.peekNext() == '*') {
            when {
                currLevel > previousLevel -> out.add(parse(iterator = iterator, previousLevel = currLevel))
                currLevel < previousLevel -> return out
                else -> {
                    skipBulletPoints(currLevel, iterator)
                    out.add(parseItem(iterator))
                    removeTrailingNewlines(iterator)
                }
            }
        }
//            out.add(parseItem(iterator))
//            removeTrailingNewlines(iterator)
        return out
    }

    private fun parseItem(iterator: StringIterator): WikiVoyageListItem {
        return WikiVoyageListItem(item = TextParser.parseText(iterator))
    }

    private fun removeTrailingNewlines(iter: StringIterator) {
        while (iter.peekNext() == '\n') {
            iter.next()
        }
    }

    private fun getLevel(iter: StringIterator): Int {
        var level = 0
        while (iter.hasXAhead(level + 1) && iter.peekXNext(level + 1) == '*') {
            level++
        }
        return level
    }

    private fun skipBulletPoints(level: Int, iterator: StringIterator) {
        iterator.skipX(level)
    }
}