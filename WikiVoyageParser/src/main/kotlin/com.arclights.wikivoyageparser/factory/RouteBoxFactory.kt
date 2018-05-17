package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Route
import com.arclights.wikivoyageparser.RouteBox

object RouteBoxFactory {
    fun getRouteBox(parts: List<String>): RouteBox {
        val mappings = parts.parseKeyValues()
        return RouteBox(
                routes = generateSequence(1) { if (mappings.containsKey("direction$it")) it else null }
                        .map {
                            Route(
                                    imageLink = mappings["image$it"]!!,
                                    imageSize = mappings["imagesize$it"]!!.toInt(),
                                    directionl = mappings["directionl$it"]!!,
                                    majorl = mappings["majorl$it"]!!,
                                    minorl = mappings["minorl$it"]!!,
                                    directionr = mappings["directionr$it"]!!,
                                    majorr = mappings["majorr$it"]!!,
                                    minorr = mappings["minorr$it"]!!
                            )
                        }
                        .toList()
        )
    }
}