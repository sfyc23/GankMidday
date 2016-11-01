package com.sfyc23.gankDaily.logic.model;

import java.io.Serializable;

/**
 * Created by leilei on 2016/8/22.
 */
public class GanHuoDataBean implements Serializable {
    private static final long serialVersionUID = 3372572626243920572L;

    public String _id;
    public String createdAt;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;
    public String[] images;

//    _id: "57b1323c421aa96004f4ba39",
//    createdAt: "2016-08-15T11:08:44.341Z",
//    desc: "Android Studio plugin makes easy to search text in strings resources.",
//    images: [
//            "http://img.gank.io/ddf3b5a6-59e4-443d-82ec-4ba83b4dab2e"
//            ],
//    publishedAt: "2016-08-16T11:22:38.139Z",
//    source: "chrome",
//    type: "Android",
//    url: "https://github.com/konifar/android-strings-search-plugin",
//    used: true,
//    who: "Masayuki Suda"


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}

