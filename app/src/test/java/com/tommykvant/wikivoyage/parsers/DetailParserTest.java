package com.tommykvant.wikivoyage.parsers;


import com.tommykvant.wikivoyage.client.Parse;
import com.tommykvant.wikivoyage.client.WikiText;
import com.tommykvant.wikivoyage.client.WikiVoyageDetails;
import com.tommykvant.wikivoyage.details.data.Details;

import junit.framework.Assert;

import org.junit.Test;

public class DetailParserTest {

    private static final String TITLE = "Stockholm";

    @Test
    public void shouldBeAbleToParseEmptyResponse(){
        WikiVoyageDetails wikiVoyageDetails = new WikiVoyageDetails(
                new Parse(
                        TITLE,
                        1,
                        new WikiText("")
                )
        );
        Details details = DetailParser.parse(wikiVoyageDetails);
        Assert.assertEquals(1, details.nbrSections());
    }

    @Test
    public void shouldBeAbleToParseHeading2()  {
        WikiVoyageDetails wikiVoyageDetails = new WikiVoyageDetails(
                new Parse(
                        TITLE,
                        1,
                        new WikiText("== HEADING ==")
                )
        );
        Details details = DetailParser.parse(wikiVoyageDetails);
        Assert.assertEquals(2, details.nbrSections());
    }


}
