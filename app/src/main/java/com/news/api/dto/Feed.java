package com.news.api.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "rss", strict = false)
public class Feed implements Serializable {
    @Element(name = "channel")
    private Channel mChannel;

    public Channel getChannel() {
        return mChannel;
    }

    public Feed() {
    }

    public Feed(Channel channel) {
        this.mChannel = channel;
    }

}
