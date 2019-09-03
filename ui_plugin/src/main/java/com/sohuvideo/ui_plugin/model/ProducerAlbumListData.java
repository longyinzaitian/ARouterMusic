package com.sohuvideo.ui_plugin.model;

import java.util.List;

/**
 * Created by tinghaoma on 2015/12/22.
 */
public class ProducerAlbumListData {

    private int count;
    private List<ProducerAlbumData> albums;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProducerAlbumData> getAlbums() {
        return albums;
    }

    public void setAlbums(List<ProducerAlbumData> albums) {
        this.albums = albums;
    }
}
