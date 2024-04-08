package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    public static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static  void printLogger() {
        logger.info("hello world");
    }
}
