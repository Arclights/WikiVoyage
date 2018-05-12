package com.tommykvant.wikivoyage.parsers;


import com.tommykvant.wikivoyage.details.data.Details;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

public class DetailParserTest {

    private static final String TITLE = "Stockholm";
    private static final String RESPONSE_START = "{\n\"parse\": {\n\"title\": \"Stockholm\",\n\"pageid\": 34294,\n\"wikitext\": {\n\"*\": \"";
    private static final String RESPONSE_END = "\"}}}";

    @Test
    public void shouldBeAbleToParseEmptyResponse() throws IOException, JSONException {
        String input = RESPONSE_START + RESPONSE_END;
        Details details = DetailParser.parse(input, TITLE);
        Assert.assertEquals(1, details.nbrSections());
    }

    @Test
    public void shouldBeAbleToParseHeading2() throws IOException, JSONException {
        String input = RESPONSE_START + "== HEADING ==" + RESPONSE_END;
        Details details = DetailParser.parse(input, TITLE);
        Assert.assertEquals(2, details.nbrSections());
    }


}
