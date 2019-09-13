package com.husy.network.model;

import java.util.List;

/**
 * @author husy
 * @date 2019/9/5
 */
public class SearchResult {
    private List<ItemList> itemList;
    private String nextPageUrl;

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "itemList=" + itemList +
                ", nextPageUrl='" + nextPageUrl + '\'' +
                '}';
    }
}
