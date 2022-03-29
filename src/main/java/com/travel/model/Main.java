package com.travel.model;

import org.apache.log4j.Logger;

public class Main {
    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        Main obj = new Main();

        try{
            obj.divide();
        }catch(ArithmeticException ex){
            logger.error("Sorry, something wrong!", ex);
        }


    }

    private void divide(){

        int i = 10 /0;

    }
}
