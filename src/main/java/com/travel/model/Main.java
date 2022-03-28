package com.travel.model;

import com.travel.controller.BanUser;
import com.travel.dao.*;
import com.travel.dao.entity.User;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        String password = "qwerty";
        String encryptedPassword = new Main().cryptPassword(password);

        System.out.println("Plain-text password: " + password);
        System.out.println("Encrypted password using MD5: " + encryptedPassword);
    }

    private String cryptPassword(String password) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (byte aByte : bytes) {
                s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
