package com.arclights.wikivoyageparser

import java.time.Month
import kotlin.collections.List

data class Details(
        val title: String,
        val sections: List<Section>
)

data class Section(
        val header: Header,
        val content: List<Content>
)

data class Header(
        val text: String
)

interface Content

class HorizontalSpace : Content

open class Text(
        val text: String
) : Content

class IndentedText(text: String) : Text(text)

interface ListPart : Content

data class WikiVoyageList(
        val subList: List<ListPart>,
        val level: Int
) : ListPart

data class WikiVoyageListItem(
        val item: Content
) : ListPart

interface Template : Content

data class Listing(
        val name: String,
        val url: String? = null,
        val alt: String? = null,
        val email: String? = null,
        val address: String? = null,
        val latitude: Double? = null,
        val longitude: Double? = null,
        val directions: String? = null,
        val phone: String? = null,
        val tollFree: String? = null,
        val fax: String? = null,
        val hours: String? = null,
        val price: String? = null,
        val content: String? = null
) : Template

data class RegionList(
        val mapImageLink: String,
        val text: String,
        val mapSize: String,
        val regions: List<Region>
) : Template

data class Region(
        val name: String,
        val color: String? = null,
        val items: String? = null,
        val description: String? = null
) : Content

data class Flag(
        val country: String
) : Template

data class Climate(
        val unit: MeasurementUnit,
        val dailyHighs: Map<Month, Degree>,
        val nightlyLows: Map<Month, Degree>,
        val precipitation: Map<Month, Precipitation>,
        val description: String
) : Template

enum class MeasurementUnit {
    METRIC,
    IMPERIAL
}

data class Degree(
        val celsius: Double,
        val fahrenheit: Double
)

data class Precipitation(
        val millimeter: Double,
        val inches: Double
)

data class IATA(
        val text: String
) : Template

class NullTemplate : Template {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode() = javaClass.hashCode()
}

data class RouteBox(
        val routes: List<Route>
) : Template

data class Route(
        val imageLink: String,
        val imageSize: Int,
        val directionl: String,
        val majorl: String,
        val minorl: String,
        val directionr: String,
        val majorr: String,
        val minorr: String
) : Template