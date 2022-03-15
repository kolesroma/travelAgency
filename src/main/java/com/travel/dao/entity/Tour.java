package com.travel.dao.entity;

import java.util.Objects;

public class Tour {
    private int id;
    private int price;
    private boolean isHot;
    private int groupSize;
    private String type;
    private int hotelStars;

    public Tour() {
    }

    public Tour(int id, int price, boolean isHot, int groupSize, String type, int hotelStars) {
        this.id = id;
        this.price = price;
        this.isHot = isHot;
        this.groupSize = groupSize;
        this.type = type;
        this.hotelStars = hotelStars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHotelStars() {
        return hotelStars;
    }

    public void setHotelStars(int hotelStars) {
        this.hotelStars = hotelStars;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", price=" + price +
                ", isHot=" + isHot +
                ", groupSize=" + groupSize +
                ", type='" + type + '\'' +
                ", hotelStars=" + hotelStars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour)) return false;
        Tour tour = (Tour) o;
        return getId() == tour.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
