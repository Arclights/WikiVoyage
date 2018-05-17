package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.RegioList
import com.arclights.wikivoyageparser.Region
import com.arclights.wikivoyageparser.factory.Utils.numberSequenceForAllVaildInFunction
import com.arclights.wikivoyageparser.factory.Utils.parseKeyValues

object RegionListFactory {
    fun getRegionList(parts: List<String>): RegioList {
        val mappings = parts.parseKeyValues()
        return RegioList(
                mapImageLink = mappings["regionmap"]!!,
                text = mappings["regionmaptext"]!!,
                mapSize = mappings["regionmapsize"]!!,
                regions = numberSequenceForAllVaildInFunction { mappings.containsKey("region${it}name") }
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