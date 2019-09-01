package com.husy.network.home;

/**
 * @author husy
 * @date 2019/9/1
 */
public class Label {
    private String text;
    private String card;
    private String detail;
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setCard(String card) {
        this.card = card;
    }
    public String getCard() {
        return card;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getDetail() {
        return detail;
    }
}
