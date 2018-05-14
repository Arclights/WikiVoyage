package com.arclights.wikivoyageparser

import java.lang.Integer.max
import java.lang.Integer.min

class StringIterator(s: String) {
    private val chars = s.toCharArray()
    private val charsEnd = chars.size - 1
    private var i = -1

    fun next(): Char {
        if (nextIsComment()) {
            consumeComment()
        }
        return chars[++i]
    }

    private fun nextIsComment() = chars[i + 1] == '<' && chars[i + 2] == '!' && chars[i + 3] == '-' && chars[i + 4] == '-'

    fun peekNext() = chars[i + 1]

    fun hasXAhead(x: Int) = x < charsEnd

    fun peekXNext(x: Int) = chars[i + x]

    fun skipX(x: Int) {
        i = min(i + x, charsEnd)
    }

    fun isAtStartOfTemplate() = chars[i + 1] == '{' && chars[i + 2] == '{'

    fun isAtEndOfTemplate() = chars[i + 1] == '}' && chars[i + 2] == '}'

    fun next2IsStartBrackets() = chars[i + 1] == '[' && chars[i + 2] == '['

    fun next2IsEndBrackets() = chars[i + 1] == ']' && chars[i + 2] == ']'

    private fun consumeComment() {
        i += 4
        while (chars[i + 1] != '-' && chars[i + 2] != '-' && chars[i + 3] != '>') {
            i++
        }
        i += 3
    }

    fun hasNext() = i < charsEnd

    fun currentPlace() = (max(0, i - 5)..min(charsEnd, i + 5))
            .map { chars[i] }
            .joinToString()
}