package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Climate
import com.arclights.wikivoyageparser.Degree
import com.arclights.wikivoyageparser.Flag
import com.arclights.wikivoyageparser.IATA
import com.arclights.wikivoyageparser.Listing
import com.arclights.wikivoyageparser.MeasurementUnit
import com.arclights.wikivoyageparser.NullTemplate
import com.arclights.wikivoyageparser.Precipitation
import com.arclights.wikivoyageparser.RegionList
import com.arclights.wikivoyageparser.Region
import com.arclights.wikivoyageparser.Route
import com.arclights.wikivoyageparser.RouteBox
import com.arclights.wikivoyageparser.StringIterator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Month

internal class TemplateParserTest {

    @Test
    fun `Return Climate`() {
        climateTestCases
                .forEach { (input, expectedClimate) ->
                    val actualClimate = TemplateParser.parse(StringIterator(input))

                    assertTrue(actualClimate is Climate)
                    assertClimatesEquals(expectedClimate, actualClimate as Climate)
                }
    }

    @Test
    fun `Return Flag`() {
        flagTestCases
                .forEach { (input, expectedFlag) ->
                    val actualFlag = TemplateParser.parse(StringIterator(input))

                    assertTrue(actualFlag is Flag)
                    assertEquals(expectedFlag, actualFlag as Flag)
                }
    }

    @Test
    fun `Return IATA`() {
        IATATestCases
                .forEach { (input, expectedIATA) ->
                    val actualIATA = TemplateParser.parse(StringIterator(input))

                    assertTrue(actualIATA is IATA)
                    assertEquals(expectedIATA, actualIATA as IATA)
                }
    }

    @Test
    fun `Return Listing`() {
        listingTestCases
                .forEach { (input, expectedListing) ->
                    val actualListing = TemplateParser.parse(StringIterator(input))

                    assertTrue(actualListing is Listing)
                    assertEquals(expectedListing, actualListing as Listing)
                }
    }

    @Test
    fun `Return Regional List`() {
        regionListTestCases
                .forEach { (input, expectedRegionalList) ->
                    val actualRegionalList = TemplateParser.parse(StringIterator(input))

                    assertTrue(actualRegionalList is RegionList)
                    assertEquals(expectedRegionalList, actualRegionalList as RegionList)
                }

    }

    @Test
    fun `Return Route Box`() {
        routeBoxTestCases
                .forEach { (input, expectedRouteBox) ->
                    val actualRouteBox = TemplateParser.parse(StringIterator(input))

                    assertTrue(actualRouteBox is RouteBox)
                    assertEquals(expectedRouteBox, actualRouteBox as RouteBox)
                }
    }

    @Test
    fun `Return Null Template`() {
        nullTemplateTestCases
                .forEach { (input, expectedNullTemplate) ->
                    val actualNullTemplate = TemplateParser.parse(StringIterator(input))

                    assertTrue(actualNullTemplate is NullTemplate)
                    assertEquals(expectedNullTemplate, actualNullTemplate as NullTemplate)
                }
    }

    private val climateTestCases = mapOf(
            "{{Climate\n| units = Metric\n| janhigh = -0.7\n| febhigh = -0.6\n| marhigh = 3.0\n| aprhigh = 8.6\n| mayhigh = 15.7\n| junhigh = 20.7\n| julhigh = 21.9\n| aughigh = 20.4\n| sephigh = 15.1\n| octhigh = 9.9\n| novhigh = 4.5\n| dechigh = 1.1\n| janlow = -5.0\n| feblow = -5.3\n| marlow = -2.7\n| aprlow = 1.1\n| maylow = 6.3\n| junlow = 11.3\n| jullow = 13.4\n| auglow = 12.7\n| seplow = 9.0\n| octlow = 5.3\n| novlow = 0.7\n| declow = -3.2\n| janprecip = 39\n| febprecip = 27\n| marprecip = 26\n| aprprecip = 30\n| mayprecip = 30\n| junprecip = 45\n| julprecip = 72\n| augprecip = 66\n| sepprecip = 55\n| octprecip = 50\n| novprecip = 53\n| decprecip = 46\n| janh2o =\n| febh2o =\n| marh2o =\n| aprh2o =\n| mayh2o =\n| junh2o =\n| julh2o =\n| augh2o =\n| seph2o =\n| octh2o =\n| novh2o =\n| dech2o =\n| description = Average conditions for Stockholm\n}}"
                    to
                    Climate(
                            unit = MeasurementUnit.METRIC,
                            dailyHighs = mapOf(
                                    Month.JANUARY to Degree(
                                            celsius = -0.7,
                                            fahrenheit = 30.74
                                    ),
                                    Month.FEBRUARY to Degree(
                                            celsius = -0.6,
                                            fahrenheit = 30.92
                                    ),
                                    Month.MARCH to Degree(
                                            celsius = 3.0,
                                            fahrenheit = 37.4
                                    ),
                                    Month.APRIL to Degree(
                                            celsius = 8.6,
                                            fahrenheit = 47.48
                                    ),
                                    Month.MAY to Degree(
                                            celsius = 15.7,
                                            fahrenheit = 60.26
                                    ),
                                    Month.JUNE to Degree(
                                            celsius = 20.7,
                                            fahrenheit = 69.26
                                    ),
                                    Month.JULY to Degree(
                                            celsius = 21.9,
                                            fahrenheit = 71.42
                                    ),
                                    Month.AUGUST to Degree(
                                            celsius = 20.4,
                                            fahrenheit = 68.72
                                    ),
                                    Month.SEPTEMBER to Degree(
                                            celsius = 15.1,
                                            fahrenheit = 59.18
                                    ),
                                    Month.OCTOBER to Degree(
                                            celsius = 9.9,
                                            fahrenheit = 49.82
                                    ),
                                    Month.NOVEMBER to Degree(
                                            celsius = 4.5,
                                            fahrenheit = 40.1
                                    ),
                                    Month.DECEMBER to Degree(
                                            celsius = 1.1,
                                            fahrenheit = 33.98
                                    )
                            ),
                            nightlyLows = mapOf(
                                    Month.JANUARY to Degree(
                                            celsius = -5.0,
                                            fahrenheit = 23.0
                                    ),
                                    Month.FEBRUARY to Degree(
                                            celsius = -5.3,
                                            fahrenheit = 22.46
                                    ),
                                    Month.MARCH to Degree(
                                            celsius = -2.7,
                                            fahrenheit = 27.14
                                    ),
                                    Month.APRIL to Degree(
                                            celsius = 1.1,
                                            fahrenheit = 33.98
                                    ),
                                    Month.MAY to Degree(
                                            celsius = 6.3,
                                            fahrenheit = 43.34
                                    ),
                                    Month.JUNE to Degree(
                                            celsius = 11.3,
                                            fahrenheit = 52.34
                                    ),
                                    Month.JULY to Degree(
                                            celsius = 13.4,
                                            fahrenheit = 56.12
                                    ),
                                    Month.AUGUST to Degree(
                                            celsius = 12.7,
                                            fahrenheit = 54.86
                                    ),
                                    Month.SEPTEMBER to Degree(
                                            celsius = 9.0,
                                            fahrenheit = 48.2
                                    ),
                                    Month.OCTOBER to Degree(
                                            celsius = 5.3,
                                            fahrenheit = 41.54
                                    ),
                                    Month.NOVEMBER to Degree(
                                            celsius = 0.7,
                                            fahrenheit = 33.26
                                    ),
                                    Month.DECEMBER to Degree(
                                            celsius = -3.2,
                                            fahrenheit = 26.24
                                    )
                            ),
                            precipitation = mapOf(
                                    Month.JANUARY to Precipitation(
                                            millimeter = 39.0,
                                            inches = 1.53543
                                    ),
                                    Month.FEBRUARY to Precipitation(
                                            millimeter = 27.0,
                                            inches = 1.06299
                                    ),
                                    Month.MARCH to Precipitation(
                                            millimeter = 26.0,
                                            inches = 1.02362
                                    ),
                                    Month.APRIL to Precipitation(
                                            millimeter = 30.0,
                                            inches = 1.1811
                                    ),
                                    Month.MAY to Precipitation(
                                            millimeter = 30.0,
                                            inches = 1.1811
                                    ),
                                    Month.JUNE to Precipitation(
                                            millimeter = 45.0,
                                            inches = 1.77165
                                    ),
                                    Month.JULY to Precipitation(
                                            millimeter = 72.0,
                                            inches = 2.83465
                                    ),
                                    Month.AUGUST to Precipitation(
                                            millimeter = 66.0,
                                            inches = 2.59843
                                    ),
                                    Month.SEPTEMBER to Precipitation(
                                            millimeter = 55.0,
                                            inches = 2.16535
                                    ),
                                    Month.OCTOBER to Precipitation(
                                            millimeter = 50.0,
                                            inches = 1.9685
                                    ),
                                    Month.NOVEMBER to Precipitation(
                                            millimeter = 53.0,
                                            inches = 2.08661
                                    ),
                                    Month.DECEMBER to Precipitation(
                                            millimeter = 46.0,
                                            inches = 1.81102
                                    )
                            ),
                            description = "Average conditions for Stockholm"
                    )
    )

    private val flagTestCases = mapOf(
            "{{flag|Albania}}"
                    to
                    Flag("Albania")
    )

    private val IATATestCases = mapOf(
            "{{IATA|Airport}}"
                    to
                    IATA("Airport")
    )

    private val listingTestCases = mapOf(
            "{{Listing\\n| name=Stockholm Tourist Center | alt= | url=http://www.stockholmtown.com/ | email=touristinfo@stockholm.se\\n| address=Kulturhuset, Sergels Torg 3-5 103 27 Stockholm | lat= | long= | directions=\\n| phone=+46 8-508 28 508 | tollfree= | fax=\\n| hours=Open M-F 09:00-19:00, Sa 09:00-16:00, Su 10:00-16:00 | price=\\n| content=The official tourist center has a lot of information in several languages and helpful staff. They also sell local transport cards and tickets to museums and sightseeing tours.\\n}}"
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
            "{{do\\n| name=Hop On - Hop Off Boat | alt= | url=http://www.stromma.se/en/stockholm/sightseeing/ | email=\\n| address= | lat= | long= | directions=\\n| phone= | tollfree= | fax=\\n| hours=daily 10:00-16:00 | price= 24-hr ticket from 180&nbsp;kr.\\n| lastedit=2017-09-18\\n| content=Audio track in 11 languages. Two of the most frequented stops are at the Palace, and at the Gamla Stan, right across the canal from ''T Slussen''. The recordings on this loop service are reasonably informative.\\n}}"
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
            "{{see\\n| name=Fotografiska | alt= | url=http://fotografiska.eu/en/ | email=\\n| address=[[Stockholm/Södermalm|Södermalm]] | lat= | long= | directions=\\n| phone= | tollfree= | fax=\\n| hours= | price=\\n| content=A photo gallery opened in 2010.\\n}}"
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
            "{{eat\\n| name=Sandys | alt= | url=http://www.sandys.se/ | email=\\n| address= | lat= | long= | directions=\\n| phone= | tollfree= | fax=\\n| hours= | price=\\n| content=Several locations throughout the city: Sergelarkaden 6 (''T T-Centralen''), Klarabergsgatan 31 (''T T-Centralen''), Stureplan 2 (''T Östermalmstorg'') and Götgatan 28 (''T Slussen''). A large Stockholm-based fast food chain focusing on submarine sandwiches, wraps and salads, Sandys offer a wide selection, reliable quality and acceptable prices, although not by any means a bargain. Sandwiches 49&nbsp;kr (excluding drinks), XL sandwiches 59&nbsp;kr, salads 65&nbsp;kr.\\n}}"
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
            "{{drink\\n| name=Systembolaget | alt= | url=http://www.systembolaget.se/english/ | email=\\n| address= | lat= | long= | directions=\\n| phone= | tollfree= | fax=\\n| hours=Generally open M-W 10:00-18:00, Th-F 10:00-19:00, Sa 10:00-15:00, all stores closed Su | price=\\n| content=Systembolaget is the government monopoly chain for selling alcohol. The stores have a wide assortment and helpful, knowledgeable staff. Tax makes beer and hard liquor expensive. Surprisingly, high-end wines can be a bargain. Ask the staff for advice. You need to be able to prove that you are over 20 years old, so be sure to bring photo ID. For more information, see the section on Systembolaget in the ''[[Sweden#Systembolaget|Sweden]]'' article. Central locations include:\\n}}"
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
            "{{listing\\n| name=Stockholm Tourist Center | alt= | url=http://www.stockholmtown.com/ | email=touristinfo@stockholm.se\\n| address=Kulturhuset, Sergels Torg 3-5 103 27 Stockholm | lat= | long= | directions=\\n| phone=+46 8-508 28 508 | tollfree= | fax=\\n| hours=Open M-F 09:00-19:00, Sa 09:00-16:00, Su 10:00-16:00 | price=\\n| content=The official tourist center has a lot of information in several languages and helpful staff. They also sell local transport cards and tickets to museums and sightseeing tours.\\n}}"
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

    private val regionListTestCases = mapOf(
            "{{Regionlist\\n| regionmap=Wikivoyage_map_Stockholm_PNG.png\\n| regionmaptext=Districts of Stockholm\\n| regionmapsize=500px\\n\\n| region1name=[[Stockholm/Norrmalm|Norrmalm]]\\n| region1color=#d56d76\\n| region1items=including ''Skeppsholmen''\\n| region1description= The central business district, also known as ''City'', contains several museums, hotels, restaurants, shopping venues, a casino, the Royal Opera, the Concert Hall and other performance stages, and the central rail and bus station. It includes ''Skeppsholmen'', an island known for its museum.\\n\\n| region2name=[[Stockholm/Vasastan and Hagastaden|Vasastan and Hagastaden]]\\n| region2color=#578e86\\n| region2items=Vasastan, Hagastaden, Karlberg\\n| region2description= Vasastaden contains the Stockholm Public Library, the Stockholm Observatory, and several second-hand stores. Hagastaden is a neighbourhood under construction, dominated by the Karolinska University Hospital.\\n\\n| region3name=[[Stockholm/Östermalm|Östermalm]]\\n| region3color=#d09440\\n| region3items=including Gärdet, Norra Djurgården, Värtahamnen\\n| region3description= A borough with urban boulevards, the ''National City Park'' and the Stockholm Harbour with several cruise ship terminals, the '''Stureplan''' square with upmarket shopping and nightlife, the '''Kaknäs Tower''', as well as Stockholm University, the Royal Institute of Technology, and several museums.\\n\\n| region4name=[[Stockholm/Djurgården|Djurgården]]\\n| region4color=#71b37b\\n| region4items=\\n| region4description= A park island with venues such as the '''Skansen''' open air museum, the '''Gröna Lund''' amusement park, the '''Vasa Museum''', '''ABBA The Museum''' and the '''Rosendal Palace'''.\\n\\n| region5name=[[Stockholm/Gamla Stan|Gamla Stan]]\\n| region5color=#8a84a3\\n| region5items=including Riddarholmen, Helgeandsholmen\\n| region5description=The Old Town; an island dominated by the '''Royal Palace''' and the '''Swedish Parliament'''. The rest of the island is a picturesque collection of old buildings and narrow cobblestone streets. The adjacent island, '''Riddarholmen''' has an important church and several historic government buildings.\\n\\n| region6name=[[Stockholm/Södermalm|Södermalm]]\\n| region6color=#d5dc76\\n| region6items=including Reimersholme, Långholmen\\n| region6description=A rugged island with buildings of all ages, with several viewpoints for the inner city. The more or less bohemian area nicknamed '''SoFo''' (south of Folkungagatan) has many restaurants and pubs, as well as specialist shops and boutiques. The major north-south street ''Götgatan'', has many bars and shops, especially around the ''Medborgarplatsen'' square. Our Södermalm article also includes some areas immediately south of it, featuring the Eurovision venue Globen, plus the mainland part of Nacka.\\n\\n| region7name=[[Stockholm/Kungsholmen|Kungsholmen]]\\n| region7color=#b5d29f\\n| region7items=including Stora Essingen, Lilla Essingen\\n| region7description=An island in the western inner city, with the '''Stockholm City Hall''' at its eastern edge. Further west, a collection of relaxed neighbourhood bars and restaurants can be found. West of the ''Fridhemsplan'' transport hub, the island is more suburban. There are several parks and beaches.\\n\\n| region8name=[[Stockholm/Västerort, Solna and Sundbyberg|Västerort, Solna and Sundbyberg]]\\n| region8color=#ac5c91\\n| region8items=Bromma, Kista etc\\n| region8description=The western suburbs are dominated by '''Stockholm-Bromma Airport'''. ''Vällingby'', founded in the 1950s, is one of Europe's first planned suburbs. ''Solvalla'' is a horse-race stadium. ''Kista'', a center of information technology, contains Stockholm's only two skyscrapers. Solna and Sundbyberg, just north of Stockholm, two cities in their own right. Solna is the home of the 50,000-seat '''Friends Arena''', the Royal park '''Hagaparken''', and the ''Karolinska Institute'', a medical institution.\\n\\n| region9name=[[Stockholm/Söderort|Söderort]]\\n| region9color=#69999f\\n| region9items=\\n| region9description= The southern districts of Stockholm municipality contains '''Stockholm International Fairs''' and the '''Woodland Cemetery''', a UNESCO [[World Heritage site]].\\n\\n| region10name=[[Lidingö]]\\n| region10color=#0000ff\\n| region10items=including Fjäderholmarna\\n| region10description= A suburban island just east of Stockholm, containing the ''Millesgården'' sculpture museum; the ''Ekholmsnäs'' ski slope; and ''Elfvik'': a farmland with an array of conference hotels.\\n}}"
                    to
                    RegionList(
                            mapImageLink = "Wikivoyage_map_Stockholm_PNG.png",
                            mapSize = "500px",
                            text = "Districts of Stockholm",
                            regions = listOf(
                                    Region(
                                            name = "[[Stockholm/Norrmalm|Norrmalm]]",
                                            color = "#d56d76",
                                            items = "including ''Skeppsholmen''",
                                            description = "The central business district, also known as ''City'', contains several museums, hotels, restaurants, shopping venues, a casino, the Royal Opera, the Concert Hall and other performance stages, and the central rail and bus station. It includes ''Skeppsholmen'', an island known for its museum."
                                    ),
                                    Region(
                                            name = "[[Stockholm/Vasastan and Hagastaden|Vasastan and Hagastaden]]",
                                            color = "#578e86",
                                            items = "Vasastan, Hagastaden, Karlberg",
                                            description = "Vasastaden contains the Stockholm Public Library, the Stockholm Observatory, and several second-hand stores. Hagastaden is a neighbourhood under construction, dominated by the Karolinska University Hospital."
                                    ),
                                    Region(
                                            name = "[[Stockholm/Östermalm|Östermalm]]",
                                            color = "#d09440",
                                            items = "including Gärdet, Norra Djurgården, Värtahamnen",
                                            description = "A borough with urban boulevards, the ''National City Park'' and the Stockholm Harbour with several cruise ship terminals, the '''Stureplan''' square with upmarket shopping and nightlife, the '''Kaknäs Tower''', as well as Stockholm University, the Royal Institute of Technology, and several museums."
                                    ),
                                    Region(
                                            name = "[[Stockholm/Djurgården|Djurgården]]",
                                            color = "#71b37b",
                                            items = "",
                                            description = "A park island with venues such as the '''Skansen''' open air museum, the '''Gröna Lund''' amusement park, the '''Vasa Museum''', '''ABBA The Museum''' and the '''Rosendal Palace'''."
                                    ),
                                    Region(
                                            name = "[[Stockholm/Gamla Stan|Gamla Stan]]",
                                            color = "#8a84a3",
                                            items = "including Riddarholmen, Helgeandsholmen",
                                            description = "The Old Town; an island dominated by the '''Royal Palace''' and the '''Swedish Parliament'''. The rest of the island is a picturesque collection of old buildings and narrow cobblestone streets. The adjacent island, '''Riddarholmen''' has an important church and several historic government buildings."
                                    ),
                                    Region(
                                            name = "[[Stockholm/Södermalm|Södermalm]]",
                                            color = "#d5dc76",
                                            items = "including Reimersholme, Långholmen",
                                            description = "A rugged island with buildings of all ages, with several viewpoints for the inner city. The more or less bohemian area nicknamed '''SoFo''' (south of Folkungagatan) has many restaurants and pubs, as well as specialist shops and boutiques. The major north-south street ''Götgatan'', has many bars and shops, especially around the ''Medborgarplatsen'' square. Our Södermalm article also includes some areas immediately south of it, featuring the Eurovision venue Globen, plus the mainland part of Nacka."
                                    ),
                                    Region(
                                            name = "[[Stockholm/Kungsholmen|Kungsholmen]]",
                                            color = "#b5d29f",
                                            items = "including Stora Essingen, Lilla Essingen",
                                            description = "An island in the western inner city, with the '''Stockholm City Hall''' at its eastern edge. Further west, a collection of relaxed neighbourhood bars and restaurants can be found. West of the ''Fridhemsplan'' transport hub, the island is more suburban. There are several parks and beaches."
                                    ),
                                    Region(
                                            name = "[[Stockholm/Västerort, Solna and Sundbyberg|Västerort, Solna and Sundbyberg]]",
                                            color = "#ac5c91",
                                            items = "Bromma, Kista etc",
                                            description = "The western suburbs are dominated by '''Stockholm-Bromma Airport'''. ''Vällingby'', founded in the 1950s, is one of Europe's first planned suburbs. ''Solvalla'' is a horse-race stadium. ''Kista'', a center of information technology, contains Stockholm's only two skyscrapers. Solna and Sundbyberg, just north of Stockholm, two cities in their own right. Solna is the home of the 50,000-seat '''Friends Arena''', the Royal park '''Hagaparken''', and the ''Karolinska Institute'', a medical institution."
                                    ),
                                    Region(
                                            name = "[[Stockholm/Söderort|Söderort]]",
                                            color = "#69999f",
                                            items = "",
                                            description = "The southern districts of Stockholm municipality contains '''Stockholm International Fairs''' and the '''Woodland Cemetery''', a UNESCO [[World Heritage site]]."
                                    ),
                                    Region(
                                            name = "[[Lidingö]]",
                                            color = "#0000ff",
                                            items = "including Fjäderholmarna",
                                            description = "A suburban island just east of Stockholm, containing the ''Millesgården'' sculpture museum; the ''Ekholmsnäs'' ski slope; and ''Elfvik'': a farmland with an array of conference hotels."
                                    )
                            )
                    )
    )

    private val routeBoxTestCases = mapOf(
            "{{routebox\\n| image1=Tabliczka E4.svg\\n| imagesize1=22\\n| directionl1=S\\n| majorl1=[[Norrköping]]\\n| minorl1=[[Södertälje]]\\n| directionr1=N\\n| majorr1=[[Sundsvall]]\\n| minorr1=[[Solna]]\\n\\n| image2=Tabliczka E18.svg\\n| imagesize2=22\\n| directionl2=W\\n| majorl2=[[Oslo]]\\n| minorl2=[[Enköping]]\\n| directionr2=E\\n| majorr2=[[Turku]] ([[Image:Ferry.png|18px]])\\n| minorr2=[[Norrtälje]]\\n\\n| image3=Tabliczka E20.svg\\n| imagesize3=22\\n| directionl3=W\\n| majorl3=[[Göteborg]]\\n| minorl3=[[Södertälje]]\\n| directionr3=E\\n| majorr3=[[Tallinn]]\\n| minorr3=[[Image:Ferry.png|18px]]\\n}}"
                    to
                    RouteBox(
                            routes = listOf(
                                    Route(
                                            imageLink = "Tabliczka E4.svg",
                                            imageSize = 22,
                                            directionl = "S",
                                            majorl = "[[Norrköping]]",
                                            minorl = "[[Södertälje]]",
                                            directionr = "N",
                                            majorr = "[[Sundsvall]]",
                                            minorr = "[[Solna]]"
                                    ),
                                    Route(
                                            imageLink = "Tabliczka E18.svg",
                                            imageSize = 22,
                                            directionl = "W",
                                            majorl = "[[Oslo]]",
                                            minorl = "[[Enköping]]",
                                            directionr = "E",
                                            majorr = "[[Turku]] ([[Image:Ferry.png|18px]])",
                                            minorr = "[[Norrtälje]]"
                                    ),
                                    Route(
                                            imageLink = "Tabliczka E20.svg",
                                            imageSize = 22,
                                            directionl = "W",
                                            majorl = "[[Göteborg]]",
                                            minorl = "[[Södertälje]]",
                                            directionr = "E",
                                            majorr = "[[Tallinn]]",
                                            minorr = "[[Image:Ferry.png|18px]]"
                                    )
                            )
                    )
    )

    private val nullTemplateTestCases = mapOf(
            "{{Seealso|Nordic history}}"
                    to
                    NullTemplate()
    )

}