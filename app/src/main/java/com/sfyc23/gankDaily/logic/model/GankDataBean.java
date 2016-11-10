package com.sfyc23.gankDaily.logic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by leilei on 2016/8/22.
 *
 */
@Table("GankDataBean")
public class GankDataBean implements Serializable, Parcelable {
    private static final long serialVersionUID = 3372572626243920572L;
    @PrimaryKey(AssignType.BY_MYSELF)
    @Column("_id")
    public String _id;
    @Column("createdAt")
    public String createdAt;
    @Column("desc")
    public String desc;
    @Column("publishedAt")
    public String publishedAt;
    @Column("source")
    public String source;
    @Column("type")
    public String type;
    @Column("url")
    public String url;
    @Column("used")
    public boolean used;
    @Column("who")
    public String who;
    @Column("images")
    public String[] images;



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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.createdAt);
        dest.writeString(this.desc);
        dest.writeString(this.publishedAt);
        dest.writeString(this.source);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeByte(used ? (byte) 1 : (byte) 0);
        dest.writeString(this.who);
        dest.writeStringArray(this.images);
    }

    public GankDataBean() {
    }

    protected GankDataBean(Parcel in) {
        this._id = in.readString();
        this.createdAt = in.readString();
        this.desc = in.readString();
        this.publishedAt = in.readString();
        this.source = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.used = in.readByte() != 0;
        this.who = in.readString();
        this.images = in.createStringArray();
    }

    public static final Parcelable.Creator<GankDataBean> CREATOR = new Parcelable.Creator<GankDataBean>() {
        public GankDataBean createFromParcel(Parcel source) {
            return new GankDataBean(source);
        }

        public GankDataBean[] newArray(int size) {
            return new GankDataBean[size];
        }
    };
}

