package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Route
import com.arclights.wikivoyageparser.RouteBox
import com.arclights.wikivoyageparser.factory.Utils.numberSequenceForAllVaildInFunction
import com.arclights.wikivoyageparser.factory.Utils.parseKeyValues

object RouteBoxFactory {
    fun getRouteBox(parts: List<String>): RouteBox {
        val mappings = parts.parseKeyValues()
        return RouteBox(
                routes = numberSequenceForAllVaildInFunction { num -> mappings.containsKey("image$num") }
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