package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.RegionList
import com.arclights.wikivoyageparser.Region
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegionListFactoryTest {

    @Test
    fun canParse() {
        testCases
                .forEach { (input, expectedRegionList) ->
                    val actualRegionList = RegionListFactory.getRegionList(input)

                    assertEquals(expectedRegionList, actualRegionList)
                }
    }


    private val testCases = mapOf(
            listOf(
                    "Regionlist\\n",
                    " regionmap=Wikivoyage_map_Stockholm_PNG.png\\n",
                    " regionmaptext=Districts of Stockholm\\n",
                    " regionmapsize=500px\\n\\n",
                    " region1name=[[Stockholm/Norrmalm|Norrmalm]]\\n",
                    " region1color=#d56d76\\n",
                    " region1items=including ''Skeppsholmen''\\n",
                    " region1description= The central business district, also known as ''City'', contains several museums, hotels, restaurants, shopping venues, a casino, the Royal Opera, the Concert Hall and other performance stages, and the central rail and bus station. It includes ''Skeppsholmen'', an island known for its museum.\\n\\n",
                    " region2name=[[Stockholm/Vasastan and Hagastaden|Vasastan and Hagastaden]]\\n",
                    " region2color=#578e86\\n",
                    " region2items=Vasastan, Hagastaden, Karlberg\\n",
                    " region2description= Vasastaden contains the Stockholm Public Library, the Stockholm Observatory, and several second-hand stores. Hagastaden is a neighbourhood under construction, dominated by the Karolinska University Hospital.\\n\\n",
                    " region3name=[[Stockholm/Östermalm|Östermalm]]\\n",
                    " region3color=#d09440\\n",
                    " region3items=including Gärdet, Norra Djurgården, Värtahamnen\\n",
                    " region3description= A borough with urban boulevards, the ''National City Park'' and the Stockholm Harbour with several cruise ship terminals, the '''Stureplan''' square with upmarket shopping and nightlife, the '''Kaknäs Tower''', as well as Stockholm University, the Royal Institute of Technology, and several museums.\\n\\n",
                    " region4name=[[Stockholm/Djurgården|Djurgården]]\\n",
                    " region4color=#71b37b\\n",
                    " region4items=\\n",
                    " region4description= A park island with venues such as the '''Skansen''' open air museum, the '''Gröna Lund''' amusement park, the '''Vasa Museum''', '''ABBA The Museum''' and the '''Rosendal Palace'''.\\n\\n",
                    " region5name=[[Stockholm/Gamla Stan|Gamla Stan]]\\n",
                    " region5color=#8a84a3\\n",
                    " region5items=including Riddarholmen, Helgeandsholmen\\n",
                    " region5description=The Old Town; an island dominated by the '''Royal Palace''' and the '''Swedish Parliament'''. The rest of the island is a picturesque collection of old buildings and narrow cobblestone streets. The adjacent island, '''Riddarholmen''' has an important church and several historic government buildings.\\n\\n",
                    " region6name=[[Stockholm/Södermalm|Södermalm]]\\n",
                    " region6color=#d5dc76\\n",
                    " region6items=including Reimersholme, Långholmen\\n",
                    " region6description=A rugged island with buildings of all ages, with several viewpoints for the inner city. The more or less bohemian area nicknamed '''SoFo''' (south of Folkungagatan) has many restaurants and pubs, as well as specialist shops and boutiques. The major north-south street ''Götgatan'', has many bars and shops, especially around the ''Medborgarplatsen'' square. Our Södermalm article also includes some areas immediately south of it, featuring the Eurovision venue Globen, plus the mainland part of Nacka.\\n\\n",
                    " region7name=[[Stockholm/Kungsholmen|Kungsholmen]]\\n",
                    " region7color=#b5d29f\\n",
                    " region7items=including Stora Essingen, Lilla Essingen\\n",
                    " region7description=An island in the western inner city, with the '''Stockholm City Hall''' at its eastern edge. Further west, a collection of relaxed neighbourhood bars and restaurants can be found. West of the ''Fridhemsplan'' transport hub, the island is more suburban. There are several parks and beaches.\\n\\n",
                    " region8name=[[Stockholm/Västerort, Solna and Sundbyberg|Västerort, Solna and Sundbyberg]]\\n",
                    " region8color=#ac5c91\\n",
                    " region8items=Bromma, Kista etc\\n",
                    " region8description=The western suburbs are dominated by '''Stockholm-Bromma Airport'''. ''Vällingby'', founded in the 1950s, is one of Europe's first planned suburbs. ''Solvalla'' is a horse-race stadium. ''Kista'', a center of information technology, contains Stockholm's only two skyscrapers. Solna and Sundbyberg, just north of Stockholm, two cities in their own right. Solna is the home of the 50,000-seat '''Friends Arena''', the Royal park '''Hagaparken''', and the ''Karolinska Institute'', a medical institution.\\n\\n",
                    " region9name=[[Stockholm/Söderort|Söderort]]\\n",
                    " region9color=#69999f\\n",
                    " region9items=\\n",
                    " region9description= The southern districts of Stockholm municipality contains '''Stockholm International Fairs''' and the '''Woodland Cemetery''', a UNESCO [[World Heritage site]].\\n\\n",
                    " region10name=[[Lidingö]]\\n",
                    " region10color=#0000ff\\n",
                    " region10items=including Fjäderholmarna\\n",
                    " region10description= A suburban island just east of Stockholm, containing the ''Millesgården'' sculpture museum; the ''Ekholmsnäs'' ski slope; and ''Elfvik'': a farmland with an array of conference hotels.\\n"
            )
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
}