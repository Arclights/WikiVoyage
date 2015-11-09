package com.tommykvant.wikivoyage.fetchers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.tommykvant.wikivoyage.details.data.Image;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class ImageFetcher {
    static String urlBase = "http://tools.wmflabs.org/magnus-toolserver/commonsapi.php?image=";

    public static Image fetch(String image, int height, int width) {
        Log.d("ImageFetcher", "Image: " + image);
        DefaultHttpClient client = new DefaultHttpClient();
        Image out = new Image();
        try {
            HttpGet httpGet = new HttpGet(new URI(urlBase + replaceChars(image) + "&thumbwidth=" + width + "&thumbheight=" + height));
            HttpResponse execute = client.execute(httpGet);
            InputStream content = execute.getEntity().getContent();
            SAXParserFactory parserFactor = SAXParserFactory.newInstance();
            SAXParser parser = parserFactor.newSAXParser();
            ImageHandler handler = new ImageHandler(out);
            parser.parse(content, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ImageFetcher: " + out);
        return out;
    }

    private static String replaceChars(String in) {
        return in.replace(' ', '_');
    }


    private static class ImageHandler extends DefaultHandler {

        Image image;
        String content = null;

        public ImageHandler(Image image) {
            this.image = image;
        }

        @Override
        public void endElement(String uri, String localName,
                               String qName) throws SAXException {
            switch (qName) {
                case "author":
                    image.setAuthor(parseAuthor(content));
                    break;
                case "thumbnail":
                    image.setImageUrl(content);
                    image.setImage(downloadThumbnail(content));
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            content = String.copyValueOf(ch, start, length).trim();
        }


        private String parseAuthor(String code) {
            Matcher m = Pattern.compile("title=\"User:([\\w\\s]+)\"").matcher(code);
            if (m.find()) {
                System.out.println("Parsed author: " + m.group(1));
                return m.group(1);
            }
            return "";
        }

        private Bitmap downloadThumbnail(String url) {
            Log.d("ImageFetcher", "Bitmap: " + url);
            DefaultHttpClient client = new DefaultHttpClient();
            Bitmap out = null;
            try {
                HttpGet httpGet = new HttpGet(new URI(url));
                HttpResponse execute = client.execute(httpGet);
                InputStream content = execute.getEntity().getContent();
                out = BitmapFactory.decodeStream(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("ImageFetcher: " + out);
            return out;
        }

    }
}
