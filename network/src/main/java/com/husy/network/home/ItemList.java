package com.husy.network.home;

/**
 * @author husy
 * @date 2019/9/1
 */
public class ItemList {
    private String type;
    private Data data;
    private String tag;
    private int id;
    private int adIndex;
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setAdIndex(int adIndex) {
        this.adIndex = adIndex;
    }
    public int getAdIndex() {
        return adIndex;
    }
}
