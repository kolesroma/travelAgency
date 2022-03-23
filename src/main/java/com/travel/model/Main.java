package com.travel.model;

import com.travel.dao.*;
import com.travel.dao.entity.User;

public class Main {

    public static void main(String[] args) {
        String uri = "shopping=on&priceFrom=&priceTo=&groupFrom=&groupTo=&page=";
        System.out.println(uri.substring(0, uri.lastIndexOf("page=")));
    }
}
