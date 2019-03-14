package com.example.application_jxf.pojo;

public class RestourantItem {
    private long _id;
    private double x;
    private double y;
    private String title;
    private String description;
    private String url;

    public RestourantItem() {}

    public RestourantItem(long _id, double x, double y, String title, String description, String url) {
        this._id = _id;
        this.x = x;
        this.y = y;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RestourantItem{" +
                "_id=" + _id +
                ", x=" + x +
                ", y=" + y +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
