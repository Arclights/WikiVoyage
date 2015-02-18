package com.tommykvant.wikivoyage.details.data.templates;

import com.tommykvant.wikivoyage.creators.TextFormatter;

/**
 * Created by tommy on 2015-02-17.
 */
public class Listing extends Template {

    String name;
    String url;
    String alt;
    String email;
    String address;
    double latitude;
    double longitude;
    String directions;
    String phone;
    String tollfree;
    String fax;
    String hours;
    String price;
    String content;

    public Listing(String[] parts) {
        super(parts);
    }

    protected void parse(String[] parts) {
        for (String p : parts) {
            String[] params = p.split("=");
            if (params.length > 1 && !params[1].equals(" ")) {
                switch (params[0].trim()) {
                    case "name":
                        name = params[1].trim();
                        break;
                    case "url":
                        url = params[1].trim();
                        break;
                    case "alt":
                        alt = params[1].trim();
                        break;
                    case "email":
                        email = params[1].trim();
                        break;
                    case "address":
                        address = params[1].trim();
                        break;
                    case "lat":
                        latitude = Double.parseDouble(params[1].trim());
                        break;
                    case "long":
                        longitude = Double.parseDouble(params[1].trim());
                        break;
                    case "directions":
                        directions = TextFormatter.format(params[1].trim());
                        break;
                    case "phone":
                        phone = params[1].trim();
                        break;
                    case "tollfree":
                        tollfree = params[1].trim();
                        break;
                    case "fax":
                        fax = params[1].trim();
                        break;
                    case "hours":
                        hours = TextFormatter.format(params[1].trim());
                        break;
                    case "price":
                        price = TextFormatter.format(params[1].trim());
                        break;
                    case "content":
                        content = TextFormatter.format(params[1].trim());
                        break;
                }
            }
        }
    }

    @Override
    public String getContent() {
        StringBuilder out = new StringBuilder();
        if (url != null) {
            out.append("<b><a href=\"" + url + "\"a>" + name + "</a></b> ");
        } else {
            out.append("<b>" + name + "</b> ");
        }

        if (alt != null) {
            out.append("(").append(TextFormatter.format(alt)).append(") ");
        }

        if (directions != null) {
            out.append("(<i>" + directions + "</i>)");
        }

        if (address != null) {
            out.append(", <img src=\"map\"/><a href=\"map:" + address + "\">" + address + "</a>");
        }

        if (phone != null) {
            out.append(", ☎ <a href=\"tel:" + phone + "\">" + phone + "</a>");
        }

        if (fax != null) {
            out.append(", fax: " + fax);
        }

        if (email != null) {
            out.append(", email: <a href=\"mailto:" + email + "\">" + email + "</a>");
        }

        out.append(".");

        if (hours != null) {
            out.append(" ").append(hours);
            if (!hours.endsWith(".")) {
                out.append(".");
            }
        }

        if (content != null) {
            out.append(" ").append(content);
            if (!content.endsWith(".")) {
                out.append(".");
            }
        }

        if (price != null) {
            out.append(" ").append(price);
            if (!price.endsWith(".")) {
                out.append(".");
            }
        }

        System.out.println("Template html: " + out.toString());

        return out.toString();
    }
}
