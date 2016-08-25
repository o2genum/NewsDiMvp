package com.news.api.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by o2genum on 24/08/16.
 */
@Root(name = "item", strict = false)
public class FeedItem implements Serializable {
    @Element(name = "pubDate")
    private String mpubDate;
    @Element(name = "title")
    private String mtitle;
    @Element(name = "link")
    private String mlink;
    @Element(name = "description")
    private String mdescription;
    @Element(name = "enclosure", required=false)
    private Enclosure menclosure;

    public FeedItem() {
    }

    public FeedItem(String description, String link, String title, String pubDate) {
        this.mdescription = description;
        this.mlink = link;
        this.mtitle = title;
        this.mpubDate = pubDate;
    }

    public String getPubDate() {
        return mpubDate;
    }

    public void setPubDate(String pubDate) {
        this.mpubDate = pubDate;
    }

    public String getTitle() {
        return mtitle;
    }

    public void setTitle(String title) {
        this.mtitle = title;
    }

    public String getLink() {
        return mlink;
    }

    public void setLink(String link) {
        this.mlink = link;
    }

    public String getDescription() {
        return mdescription;
    }

    public void setDescription(String description) {
        this.mdescription = description;
    }

    public Enclosure getEnclosure() {
        return menclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.menclosure = enclosure;
    }
}
