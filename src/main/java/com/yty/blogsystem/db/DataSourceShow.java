package com.yty.blogsystem.db;

import com.yty.blogsystem.logger.Log;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
/**
* @author  yangtaoyao
* @date  Created in 2019/5/17 0017 23:53
* @description  监测数据库连接池类型
*/
@Component
public class DataSourceShow implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Log.getInst(this).info("打印加载数据源：" + applicationContext.getBean(DataSource.class).getClass().getName());
    }
}
