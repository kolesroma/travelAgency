package com.travel.model;

import org.apache.log4j.Logger;

public class Main {
    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {


        logger.debug("Some info");
        logger.error("Sorry, something wrong!");


    }
}
