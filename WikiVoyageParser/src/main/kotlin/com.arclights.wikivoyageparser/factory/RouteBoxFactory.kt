package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Route
import com.arclights.wikivoyageparser.RouteBox

object RouteBoxFactory {
    fun getRouteBox(parts: List<String>): RouteBox {
        val mappings = parts
                .map { it.split("=") }
                .map { it[0] to it[1] }
                .toMap()
        return RouteBox(
                routes = generateSequence(1) { if (mappings.containsKey("direction$it")) it else null }
                        .map {
                            Route(
                                    imageLink = mappings["image$it"]!!,
                                    imageSize = mappings["imagesize$it"]!!.toInt(),
                                    directionl = mappings["directionl$it"]!!,
                                    majorl = mappings["majorl$it"]!!,
                                    minorl = mappings["monirl$it"]!!,
                                    directionr = mappings["directionl$it"]!!,
                                    majorr = mappings["majorr$it"]!!,
                                    minorr = mappings["minorr$it"]!!
                            )
                        }
                        .toList()
        )
    }
}