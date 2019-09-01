package com.husy.network.home;

import java.util.List;

/**
 * @author husy
 * @date 2019/9/1
 */
public class Data {
    private String dataType;
    private int id;
    private String title;
    private String description;
    private String image;
    private String actionUrl;
    private String adTrack;
    private boolean shade;
    private Label label;
    private List<String> labelList;
    private Header header;
    private boolean autoPlay;
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public String getDataType() {
        return dataType;
    }

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

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
    public String getActionUrl() {
        return actionUrl;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }
    public String getAdTrack() {
        return adTrack;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }
    public boolean getShade() {
        return shade;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
    public Label getLabel() {
        return label;
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }
    public List<String> getLabelList() {
        return labelList;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
    public Header getHeader() {
        return header;
    }

    public void setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
    }
    public boolean getAutoPlay() {
        return autoPlay;
    }
}
