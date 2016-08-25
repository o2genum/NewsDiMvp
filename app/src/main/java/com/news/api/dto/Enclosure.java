package com.news.api.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "enclosure", strict = false)
public class Enclosure implements Serializable {
    @Attribute(name = "url")
    private String mUrl;

    public String getUrl() {
        return mUrl;
    }

    public Enclosure() {
    }

    public Enclosure(String url) {
        this.mUrl = url;
    }

}
