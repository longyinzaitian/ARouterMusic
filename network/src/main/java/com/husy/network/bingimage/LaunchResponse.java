package com.husy.network.bingimage;

import java.util.Arrays;
import java.util.List;

/**
 * @author husy
 * @date 2019/8/31
 */
public class LaunchResponse {
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

    public class LaunchImage {
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
            return url;
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
    }

    public class ToolTips {
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
    }
}
