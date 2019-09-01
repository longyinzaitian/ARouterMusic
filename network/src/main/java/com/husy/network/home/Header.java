package com.husy.network.home;

/**
 * @author husy
 * @date 2019/9/1
 */
public class Header {
    private int id;
    private String title;
    private String font;
    private String subTitle;
    private String subTitleFont;
    private String textAlign;
    private String cover;
    private String label;
    private String actionUrl;
    private String labelList;
    private String rightText;
    private String icon;
    private String description;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setFont(String font) {
        this.font = font;
    }
    public String getFont() {
        return font;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitleFont(String subTitleFont) {
        this.subTitleFont = subTitleFont;
    }
    public String getSubTitleFont() {
        return subTitleFont;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }
    public String getTextAlign() {
        return textAlign;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getCover() {
        return cover;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
    public String getActionUrl() {
        return actionUrl;
    }

    public void setLabelList(String labelList) {
        this.labelList = labelList;
    }
    public String getLabelList() {
        return labelList;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }
    public String getRightText() {
        return rightText;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
