package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Listing

object ListingFactory {
    fun getListing(parts: List<String>): Listing {
        val mappings = parts.map { it.split("=").let { it[0] to it[1] } }.toMap()
        return Listing(
                name = mappings["name"]!!,
                url = mappings["url"],
                alt = mappings["alt"],
                email = mappings["email"],
                address = mappings["address"],
                latitude = mappings["lat"]?.toDouble(),
                longitude = mappings["long"]?.toDouble(),
                directions = mappings["directions"],
                phone = mappings["phone"],
                tollFree = mappings["tollfree"],
                fax = mappings["fax"],
                hours = mappings["hours"],
                price = mappings["price"],
                content = mappings["content"]
        )
    }
}