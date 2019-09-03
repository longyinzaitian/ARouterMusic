package com.sohuvideo.ui_plugin.model;

import java.util.List;

/**
 * Created by tinghaoma on 2015/12/22.
 */
public class ProducerVideoListData {
    private int count;
    private List<ProducerVideoData> videos;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProducerVideoData> getVideos() {
        return videos;
    }

    public void setVideos(List<ProducerVideoData> videos) {
        this.videos = videos;
    }
}
