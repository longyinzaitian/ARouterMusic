package com.husy.network.bingimage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

/**
 * @author husy
 * @date 2019/8/31
 */
public class LaunchResponse implements Parcelable {
    private List<LaunchImage> images;
    private ToolTips tooltips;

    public List<LaunchImage> getImages() {
        return images;
    }

    public void setImages(List<LaunchImage> images) {
        this.images = images;
    }

    public ToolTips getTooltips() {
        return tooltips;
    }

    public void setTooltips(ToolTips tooltips) {
        this.tooltips = tooltips;
    }

    @Override
    public String toString() {
        return "LaunchResponse{" +
                "images=" + Arrays.toString(images.toArray()) +
                ", tooltips=" + tooltips +
                '}';
    }

    public static class LaunchImage implements Parcelable {
        private String startdate;
        private String enddate;
        private String fullstartdate;
        private String url;
        private String urlbase;
        private String copyright;
        private String copyrightlink;
        private String title;
        private String quiz;
        private boolean wp;
        private int drk;
        private int top;
        private int bot;

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getFullstartdate() {
            return fullstartdate;
        }

        public void setFullstartdate(String fullstartdate) {
            this.fullstartdate = fullstartdate;
        }

        public String getUrl() {
            return "https://cn.bing.com" + url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlbase() {
            return urlbase;
        }

        public void setUrlbase(String urlbase) {
            this.urlbase = urlbase;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCopyrightlink() {
            return copyrightlink;
        }

        public void setCopyrightlink(String copyrightlink) {
            this.copyrightlink = copyrightlink;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getQuiz() {
            return quiz;
        }

        public void setQuiz(String quiz) {
            this.quiz = quiz;
        }

        public boolean isWp() {
            return wp;
        }

        public void setWp(boolean wp) {
            this.wp = wp;
        }

        public int getDrk() {
            return drk;
        }

        public void setDrk(int drk) {
            this.drk = drk;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getBot() {
            return bot;
        }

        public void setBot(int bot) {
            this.bot = bot;
        }

        @Override
        public String toString() {
            return "LaunchImage{" +
                    "startdate='" + startdate + '\'' +
                    ", enddate='" + enddate + '\'' +
                    ", fullstartdate='" + fullstartdate + '\'' +
                    ", url='" + url + '\'' +
                    ", urlbase='" + urlbase + '\'' +
                    ", copyright='" + copyright + '\'' +
                    ", copyrightlink='" + copyrightlink + '\'' +
                    ", title='" + title + '\'' +
                    ", quiz='" + quiz + '\'' +
                    ", wp=" + wp +
                    ", drk=" + drk +
                    ", top=" + top +
                    ", bot=" + bot +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.startdate);
            dest.writeString(this.enddate);
            dest.writeString(this.fullstartdate);
            dest.writeString(this.url);
            dest.writeString(this.urlbase);
            dest.writeString(this.copyright);
            dest.writeString(this.copyrightlink);
            dest.writeString(this.title);
            dest.writeString(this.quiz);
            dest.writeByte(this.wp ? (byte) 1 : (byte) 0);
            dest.writeInt(this.drk);
            dest.writeInt(this.top);
            dest.writeInt(this.bot);
        }

        public LaunchImage() {
        }

        protected LaunchImage(Parcel in) {
            this.startdate = in.readString();
            this.enddate = in.readString();
            this.fullstartdate = in.readString();
            this.url = in.readString();
            this.urlbase = in.readString();
            this.copyright = in.readString();
            this.copyrightlink = in.readString();
            this.title = in.readString();
            this.quiz = in.readString();
            this.wp = in.readByte() != 0;
            this.drk = in.readInt();
            this.top = in.readInt();
            this.bot = in.readInt();
        }

        public static final Creator<LaunchImage> CREATOR = new Creator<LaunchImage>() {
            @Override
            public LaunchImage createFromParcel(Parcel source) {
                return new LaunchImage(source);
            }

            @Override
            public LaunchImage[] newArray(int size) {
                return new LaunchImage[size];
            }
        };
    }

    public static class ToolTips implements Parcelable {
        private String loading;
        private String previous;
        private String next;
        private String walle;
        private String walls;

        public String getLoading() {
            return loading;
        }

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getWalle() {
            return walle;
        }

        public void setWalle(String walle) {
            this.walle = walle;
        }

        public String getWalls() {
            return walls;
        }

        public void setWalls(String walls) {
            this.walls = walls;
        }

        @Override
        public String toString() {
            return "ToolTips{" +
                    "loading='" + loading + '\'' +
                    ", previous='" + previous + '\'' +
                    ", next='" + next + '\'' +
                    ", walle='" + walle + '\'' +
                    ", walls='" + walls + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.loading);
            dest.writeString(this.previous);
            dest.writeString(this.next);
            dest.writeString(this.walle);
            dest.writeString(this.walls);
        }

        public ToolTips() {
        }

        protected ToolTips(Parcel in) {
            this.loading = in.readString();
            this.previous = in.readString();
            this.next = in.readString();
            this.walle = in.readString();
            this.walls = in.readString();
        }

        public static final Creator<ToolTips> CREATOR = new Creator<ToolTips>() {
            @Override
            public ToolTips createFromParcel(Parcel source) {
                return new ToolTips(source);
            }

            @Override
            public ToolTips[] newArray(int size) {
                return new ToolTips[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.images);
        dest.writeParcelable(this.tooltips, flags);
    }

    public LaunchResponse() {
    }

    protected LaunchResponse(Parcel in) {
        this.images = in.createTypedArrayList(LaunchImage.CREATOR);
        this.tooltips = in.readParcelable(ToolTips.class.getClassLoader());
    }

    public static final Creator<LaunchResponse> CREATOR = new Creator<LaunchResponse>() {
        @Override
        public LaunchResponse createFromParcel(Parcel source) {
            return new LaunchResponse(source);
        }

        @Override
        public LaunchResponse[] newArray(int size) {
            return new LaunchResponse[size];
        }
    };
}
