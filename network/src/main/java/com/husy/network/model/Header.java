package com.husy.network.model;

/**
 * @author husy
 * @date 2019/9/5
 */
public class Header {
    public String type;
    public Data data;

    public static class Data{
        public String text;
        public String font;
        public String addTrack;
    }
}
