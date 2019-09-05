package com.husy.network.model;

import java.util.List;

/**
 * @author husy
 * @date 2019/9/5
 */
public class AuthorDetailData {
    public List<ItemList> itemList;
    public PgcInfo pgcInfo;
    public String nextPageUrl;

    public static class PgcInfo{
        public int id;
        public String icon;
        public String name;
        public String brief;
        public String description;
    }
}
