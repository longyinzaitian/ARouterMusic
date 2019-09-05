package com.husy.network.model;

import java.util.List;

/**
 * @author husy
 * @date 2019/9/5
 */
public class Daily {
    public List<IssueList> issueList;
    public String nextPageUrl;

    public static class IssueList{
        public long releaseTime;
        public String type;
        public long date;
        public long publishTime;
        private int count;
        public List<ItemList> itemList;
    }
}
