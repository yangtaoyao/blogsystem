package com.yty.blogsystem.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    public static Logger getInst(Object object){
        Logger logger= LoggerFactory.getLogger(object.getClass());
        return logger;
    }
    public static Logger getInst(Class c){
        Logger logger= LoggerFactory.getLogger(c);
        return logger;
    }
}
