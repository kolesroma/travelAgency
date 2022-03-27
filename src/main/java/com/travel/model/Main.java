package com.travel.model;

import com.travel.controller.BanUser;
import com.travel.dao.*;
import com.travel.dao.entity.User;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Reflections reflections = new Reflections("com.travel.controller");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(AdminAccess.class);
        System.out.println(classes);


        for(Class cls: classes) {
            AdminAccess target = (AdminAccess) cls.getAnnotation(AdminAccess.class);
            System.out.println(target);
        }

    }
}
