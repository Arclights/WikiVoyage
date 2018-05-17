package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Listing
import com.arclights.wikivoyageparser.factory.Utils.parseKeyValues
import com.arclights.wikivoyageparser.factory.Utils.valueOrNullForEmpty

object ListingFactory {
    fun getListing(parts: List<String>): Listing {
        val mappings = parts.parseKeyValues()
        return Listing(
                name = mappings["name"]!!,
                url = mappings.valueOrNullForEmpty("url"),
                alt = mappings.valueOrNullForEmpty("alt"),
                email = mappings.valueOrNullForEmpty("email"),
                address = mappings.valueOrNullForEmpty("address"),
                latitude = mappings.valueOrNullForEmpty("lat")?.toDouble(),
                longitude = mappings.valueOrNullForEmpty("long")?.toDouble(),
                directions = mappings.valueOrNullForEmpty("directions"),
                phone = mappings.valueOrNullForEmpty("phone"),
                tollFree = mappings.valueOrNullForEmpty("tollfree"),
                fax = mappings.valueOrNullForEmpty("fax"),
                hours = mappings.valueOrNullForEmpty("hours"),
                price = mappings.valueOrNullForEmpty("price"),
                content = mappings.valueOrNullForEmpty("content")
        )
    }
}