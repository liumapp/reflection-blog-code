package com.liumapp.blog.reflection.demo;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author liumapp
 * @file PersonTest.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/23/18
 */
public class PersonTest extends TestCase {

    private Logger logger = LoggerFactory.getLogger(PersonTest.class);

    public void testReflection () {
        try {
            Class clazz = Class.forName(Person.class.getName());
            Method[] methods = clazz.getDeclaredMethods();

            for (Method m: methods) {
                logger.info("out put clazz methods : ");
                logger.info(m.toString());
                logger.info("\n");
            }

            Field[] fields = clazz.getDeclaredFields();
            for (Field f: fields) {
                logger.info("out put clazz fields : ");
                logger.info(f.toString());
                logger.info("\n");
            }

            Constructor[] constructors = clazz.getDeclaredConstructors();
            for (Constructor c: constructors) {
                logger.info("out put clazz constructors : ");
                logger.info(c.toString());
                logger.info("\n");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
