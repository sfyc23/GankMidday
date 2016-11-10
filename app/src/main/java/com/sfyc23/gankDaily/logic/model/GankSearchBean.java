package com.sfyc23.gankDaily.logic.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author :leilei on 2016/11/9 0115.
 */
public class GankSearchBean implements Parcelable {
    private static final String TAG = "GankSearchBean";
    /**
     * error : false
     * results : [{"_id":"582147ef421aa90e799ec297","createdAt":"2016-11-08T11:35:11.5Z","desc":"11-8","publishedAt":"2016-11-08T11:51:41.578Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034gw1f9kjnm8uo1j20u00u0q5q.jpg","used":true,"who":"daimajia"},{"_id":"581fc2c0421aa91369f959f9","createdAt":"2016-11-07T07:54:40.74Z","desc":"11-7","publishedAt":"2016-11-07T11:47:36.373Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f9j7nvnwjdj20u00k0jsl.jpg","used":true,"who":"daimajia"},{"_id":"581bd560421aa91376974628","createdAt":"2016-11-04T08:25:04.30Z","desc":"11-4","publishedAt":"2016-11-04T11:48:56.654Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9frojtu31j20u00u0go9.jpg","used":true,"who":"daimajia"},{"_id":"581a838a421aa90e799ec261","createdAt":"2016-11-03T08:23:38.560Z","desc":"11-3","publishedAt":"2016-11-03T11:48:43.342Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f9em0sj3yvj20u00w4acj.jpg","used":true,"who":"daimajia"},{"_id":"58193781421aa90e6f21b49f","createdAt":"2016-11-02T08:46:57.726Z","desc":"11-2","publishedAt":"2016-11-02T11:49:08.835Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9dh2ohx2vj20u011hn0r.jpg","used":true,"who":"daimajia"},{"_id":"5817e1fa421aa913769745fe","createdAt":"2016-11-01T08:29:46.640Z","desc":"11-1","publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f9cayjaa96j20u011hqbs.jpg","used":true,"who":"daimajia"},{"_id":"5816871a421aa91369f959b6","createdAt":"2016-10-31T07:49:46.592Z","desc":"10-31","publishedAt":"2016-10-31T11:43:44.770Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f9b46kpoeoj20ku0kuwhc.jpg","used":true,"who":"daimajia"},{"_id":"581218e9421aa90e799ec222","createdAt":"2016-10-27T23:10:33.618Z","desc":"10-28","publishedAt":"2016-10-28T11:29:36.510Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f978bh1cerj20u00u0767.jpg","used":true,"who":"daimajia"},{"_id":"5811596a421aa90e6f21b45e","createdAt":"2016-10-27T09:33:30.47Z","desc":"10-27","publishedAt":"2016-10-27T11:41:45.88Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034gw1f96kp6faayj20u00jywg9.jpg","used":true,"who":"daimajia"},{"_id":"58101f83421aa90e6f21b44b","createdAt":"2016-10-26T11:14:11.143Z","desc":"10-26","publishedAt":"2016-10-26T11:28:10.759Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f95hzq3p4rj20u011htbm.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsEntity> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity implements Parcelable {

        /**
         * _id : 582147ef421aa90e799ec297
         * createdAt : 2016-11-08T11:35:11.5Z
         * desc : 11-8
         * publishedAt : 2016-11-08T11:51:41.578Z
         * source : chrome
         * type : 福利
         * url : http://ww2.sinaimg.cn/large/610dc034gw1f9kjnm8uo1j20u00u0q5q.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String get_id() {
            return _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getSource() {
            return source;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean getUsed() {
            return used;
        }

        public String getWho() {
            return who;
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
        }

        public ResultsEntity() {
        }

        protected ResultsEntity(Parcel in) {
            this._id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
        }

        public static final Creator<ResultsEntity> CREATOR = new Creator<ResultsEntity>() {
            public ResultsEntity createFromParcel(Parcel source) {
                return new ResultsEntity(source);
            }

            public ResultsEntity[] newArray(int size) {
                return new ResultsEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(error ? (byte) 1 : (byte) 0);
        dest.writeList(this.results);
    }

    public GankSearchBean() {
    }

    protected GankSearchBean(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = new ArrayList<ResultsEntity>();
        in.readList(this.results, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<GankSearchBean> CREATOR = new Parcelable.Creator<GankSearchBean>() {
        public GankSearchBean createFromParcel(Parcel source) {
            return new GankSearchBean(source);
        }

        public GankSearchBean[] newArray(int size) {
            return new GankSearchBean[size];
        }
    };
}
