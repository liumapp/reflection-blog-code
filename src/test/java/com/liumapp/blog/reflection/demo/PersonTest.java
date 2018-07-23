package com.liumapp.blog.reflection.demo;

import junit.framework.TestCase;

import java.lang.reflect.Method;

/**
 * @author liumapp
 * @file PersonTest.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/23/18
 */
public class PersonTest extends TestCase {

//    private Logger

    public void testReflection () {
        try {
            Class clazz = Class.forName(Person.class.getName());
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m: methods) {
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
