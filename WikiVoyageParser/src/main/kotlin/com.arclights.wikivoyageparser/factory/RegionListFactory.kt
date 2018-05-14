package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.RegioList
import com.arclights.wikivoyageparser.Region

object RegionListFactory {
    fun getRegionList(parts: List<String>): RegioList {
        val mappings = parts
                .map { it.split("=") }
                .map { it[0] to it[1] }
                .toMap()
        return RegioList(
                mapImageLink = mappings["regionmap"]!!,
                text = mappings["regionmaptext"]!!,
                mapSize = mappings["regionmapSize"]!!.toInt(),
                regions = generateSequence(1) { if (mappings.containsKey("region${it}name")) it else null }
                        .map {
                            Region(
                                    name = mappings["region${it}name"]!!.trim(),
                                    color = mappings["region${it}color"]?.trim(),
                                    items = mappings["region${it}items"]?.trim(),
                                    description = mappings["region${it}description"]?.trim()
                            )
                        }
                        .toList()
        )
    }
}