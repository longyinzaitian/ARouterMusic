package com.husy.network.home;

import java.util.List;

/**
 * @author husy
 * @date 2019/9/1
 */
public class JsonRootBean {
    private List<ItemList> itemList;
    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;
    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }
    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }
    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }
    public boolean getAdExist() {
        return adExist;
    }
}
