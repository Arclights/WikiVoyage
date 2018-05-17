package com.arclights.wikivoyageparser.factory

object Utils {
    fun List<String>.parseKeyValues() = this
            .map {
                it.split("=")
                        .map { it.trim() }
                        .map { it.replace("\\n", "") }
            }
            .map { if (it.size > 1) it[0] to it[1] else "name" to it[0] }
            .toMap()

    fun Map<String, String>.valueOrNullForEmpty(key: String) = if (this.containsKey(key) && this[key] == "") null else this[key]

    fun numberSequenceForAllVaildInFunction(isValid: (Int) -> Boolean) =
            generateSequence(0) { if (isValid(it + 1)) it + 1 else null }
                    .drop(1)
}