package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.RegionList
import com.arclights.wikivoyageparser.Region
import com.arclights.wikivoyageparser.factory.Utils.numberSequenceForAllValidInFunction
import com.arclights.wikivoyageparser.factory.Utils.parseKeyValues

object RegionListFactory {
    fun getRegionList(parts: List<String>): RegionList {
        val mappings = parts.parseKeyValues()
        return RegionList(
                mapImageLink = mappings["regionmap"]!!,
                text = mappings["regionmaptext"]!!,
                mapSize = mappings["regionmapsize"]!!,
                regions = numberSequenceForAllValidInFunction { mappings.containsKey("region${it}name") }
                        .map {
                            Region(
                                    name = mappings["region${it}name"]!!,
                                    color = mappings["region${it}color"],
                                    items = mappings["region${it}items"],
                                    description = mappings["region${it}description"]
                            )
                        }
                        .toList()
        )
    }
}