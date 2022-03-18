package com.travel.dao.entity;

import java.util.Objects;

public class Order {
    private int id;
    private int userId;
    private int tourId;
    private String status;
    private int discount;

    public Order() {
    }

    public Order(int id, int userId, int tourId, String status, int discount) {
        this.id = id;
        this.userId = userId;
        this.tourId = tourId;
        this.status = status;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", tourId=" + tourId +
                ", status='" + status + '\'' +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
