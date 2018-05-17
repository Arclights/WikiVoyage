package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Listing
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ListingFactoryTest {

    @Test
    fun canGet() {
        testCases
                .forEach { (input, expectedListing) ->
                    val actualListing = ListingFactory.getListing(input)

                    assertEquals(expectedListing, actualListing)
                }
    }

    private val testCases = mapOf(
            listOf(
                    "Listing\\n",
                    " name=Stockholm Tourist Center ",
                    " alt= ",
                    " url=http://www.stockholmtown.com/ ",
                    " email=touristinfo@stockholm.se\\n",
                    " address=Kulturhuset, Sergels Torg 3-5 103 27 Stockholm ",
                    " lat= ",
                    " long= ",
                    " directions=\\n",
                    " phone=+46 8-508 28 508 ",
                    " tollfree= ",
                    " fax=\\n",
                    " hours=Open M-F 09:00-19:00, Sa 09:00-16:00, Su 10:00-16:00 ",
                    " price=\\n",
                    " content=The official tourist center has a lot of information in several languages and helpful staff. They also sell local transport cards and tickets to museums and sightseeing tours.\\n"
            )
                    to
                    Listing(
                            name = "Stockholm Tourist Center",
                            alt = null,
                            url = "http://www.stockholmtown.com/",
                            email = "touristinfo@stockholm.se",
                            address = "Kulturhuset, Sergels Torg 3-5 103 27 Stockholm",
                            latitude = null,
                            longitude = null,
                            directions = null,
                            phone = "+46 8-508 28 508",
                            tollFree = null,
                            fax = null,
                            hours = "Open M-F 09:00-19:00, Sa 09:00-16:00, Su 10:00-16:00",
                            price = null,
                            content = "The official tourist center has a lot of information in several languages and helpful staff. They also sell local transport cards and tickets to museums and sightseeing tours."
                    ),
            listOf(
                    "do\\n",
                    " name=Hop On - Hop Off Boat ",
                    " alt= ",
                    " url=http://www.stromma.se/en/stockholm/sightseeing/ ",
                    " email=\\n",
                    " address= ",
                    " lat= ",
                    " long= ",
                    " directions=\\n",
                    " phone= ",
                    " tollfree= ",
                    " fax=\\n",
                    " hours=daily 10:00-16:00 ",
                    " price= 24-hr ticket from 180&nbsp;kr.\\n",
                    " lastedit=2017-09-18\\n",
                    " content=Audio track in 11 languages. Two of the most frequented stops are at the Palace, and at the Gamla Stan, right across the canal from ''T Slussen''. The recordings on this loop service are reasonably informative.\\n"
            )
                    to
                    Listing(
                            name = "Hop On - Hop Off Boat",
                            alt = null,
                            url = "http://www.stromma.se/en/stockholm/sightseeing/",
                            email = null,
                            address = null,
                            latitude = null,
                            longitude = null,
                            directions = null,
                            phone = null,
                            tollFree = null,
                            fax = null,
                            hours = "daily 10:00-16:00",
                            price = "24-hr ticket from 180&nbsp;kr.",
                            content = "Audio track in 11 languages. Two of the most frequented stops are at the Palace, and at the Gamla Stan, right across the canal from ''T Slussen''. The recordings on this loop service are reasonably informative."
                    ),
            listOf(
                    "see\\n",
                    " name=Fotografiska ",
                    " alt= ",
                    " url=http://fotografiska.eu/en/ ",
                    " email=\\n",
                    " address=[[Stockholm/Södermalm|Södermalm]] ",
                    " lat= ",
                    " long= ",
                    " directions=\\n",
                    " phone= ",
                    " tollfree= ",
                    " fax=\\n",
                    " hours= ",
                    " price=\\n",
                    " content=A photo gallery opened in 2010.\\n"
            )
                    to
                    Listing(
                            name = "Fotografiska",
                            alt = null,
                            url = "http://fotografiska.eu/en/",
                            email = null,
                            address = "[[Stockholm/Södermalm|Södermalm]]",
                            latitude = null,
                            longitude = null,
                            directions = null,
                            phone = null,
                            tollFree = null,
                            fax = null,
                            hours = null,
                            price = null,
                            content = "A photo gallery opened in 2010."
                    ),
            listOf(
                    "eat\\n",
                    " name=Sandys ",
                    " alt= ",
                    " url=http://www.sandys.se/ ",
                    " email=\\n",
                    " address= ",
                    " lat= ",
                    " long= ",
                    " directions=\\n",
                    " phone= ",
                    " tollfree= ",
                    " fax=\\n",
                    " hours= ",
                    " price=\\n",
                    " content=Several locations throughout the city: Sergelarkaden 6 (''T T-Centralen''), Klarabergsgatan 31 (''T T-Centralen''), Stureplan 2 (''T Östermalmstorg'') and Götgatan 28 (''T Slussen''). A large Stockholm-based fast food chain focusing on submarine sandwiches, wraps and salads, Sandys offer a wide selection, reliable quality and acceptable prices, although not by any means a bargain. Sandwiches 49&nbsp;kr (excluding drinks), XL sandwiches 59&nbsp;kr, salads 65&nbsp;kr.\\n"
            )
                    to
                    Listing(
                            name = "Sandys",
                            alt = null,
                            url = "http://www.sandys.se/",
                            email = null,
                            address = null,
                            latitude = null,
                            longitude = null,
                            directions = null,
                            phone = null,
                            tollFree = null,
                            fax = null,
                            hours = null,
                            price = null,
                            content = "Several locations throughout the city: Sergelarkaden 6 (''T T-Centralen''), Klarabergsgatan 31 (''T T-Centralen''), Stureplan 2 (''T Östermalmstorg'') and Götgatan 28 (''T Slussen''). A large Stockholm-based fast food chain focusing on submarine sandwiches, wraps and salads, Sandys offer a wide selection, reliable quality and acceptable prices, although not by any means a bargain. Sandwiches 49&nbsp;kr (excluding drinks), XL sandwiches 59&nbsp;kr, salads 65&nbsp;kr."
                    ),
            listOf(
                    "drink\\n",
                    " name=Systembolaget ",
                    " alt= ",
                    " url=http://www.systembolaget.se/english/ ",
                    " email=\\n",
                    " address= ",
                    " lat= ",
                    " long= ",
                    " directions=\\n",
                    " phone= ",
                    " tollfree= ",
                    " fax=\\n",
                    " hours=Generally open M-W 10:00-18:00, Th-F 10:00-19:00, Sa 10:00-15:00, all stores closed Su ",
                    " price=\\n",
                    " content=Systembolaget is the government monopoly chain for selling alcohol. The stores have a wide assortment and helpful, knowledgeable staff. Tax makes beer and hard liquor expensive. Surprisingly, high-end wines can be a bargain. Ask the staff for advice. You need to be able to prove that you are over 20 years old, so be sure to bring photo ID. For more information, see the section on Systembolaget in the ''[[Sweden#Systembolaget|Sweden]]'' article. Central locations include:\\n"
            )
                    to
                    Listing(
                            name = "Systembolaget",
                            alt = null,
                            url = "http://www.systembolaget.se/english/",
                            email = null,
                            address = null,
                            latitude = null,
                            longitude = null,
                            directions = null,
                            phone = null,
                            tollFree = null,
                            fax = null,
                            hours = "Generally open M-W 10:00-18:00, Th-F 10:00-19:00, Sa 10:00-15:00, all stores closed Su",
                            price = null,
                            content = "Systembolaget is the government monopoly chain for selling alcohol. The stores have a wide assortment and helpful, knowledgeable staff. Tax makes beer and hard liquor expensive. Surprisingly, high-end wines can be a bargain. Ask the staff for advice. You need to be able to prove that you are over 20 years old, so be sure to bring photo ID. For more information, see the section on Systembolaget in the ''[[Sweden#Systembolaget|Sweden]]'' article. Central locations include:"
                    ),
            listOf(
                    "listing\\n",
                    " name=Stockholm Tourist Center ",
                    " alt= ",
                    " url=http://www.stockholmtown.com/ ",
                    " email=touristinfo@stockholm.se\\n",
                    " address=Kulturhuset, Sergels Torg 3-5 103 27 Stockholm ",
                    " lat= ",
                    " long= ",
                    " directions=\\n",
                    " phone=+46 8-508 28 508 ",
                    " tollfree= ",
                    " fax=\\n",
                    " hours=Open M-F 09:00-19:00, Sa 09:00-16:00, Su 10:00-16:00 ",
                    " price=\\n",
                    " content=The official tourist center has a lot of information in several languages and helpful staff. They also sell local transport cards and tickets to museums and sightseeing tours.\\n"
            )
                    to
                    Listing(
                            name = "Stockholm Tourist Center",
                            alt = null,
                            url = "http://www.stockholmtown.com/",
                            email = "touristinfo@stockholm.se",
                            address = "Kulturhuset, Sergels Torg 3-5 103 27 Stockholm",
                            latitude = null,
                            longitude = null,
                            directions = null,
                            phone = "+46 8-508 28 508",
                            tollFree = null,
                            fax = null,
                            hours = "Open M-F 09:00-19:00, Sa 09:00-16:00, Su 10:00-16:00",
                            price = null,
                            content = "The official tourist center has a lot of information in several languages and helpful staff. They also sell local transport cards and tickets to museums and sightseeing tours."
                    )
    )

}