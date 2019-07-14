package com.yty.blogsystem;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class BaseMockTest {
    public MockMvc mockMvc;

    @Autowired
    WebApplicationContext wc;

    @Before
    public void beforeSetUp() {
        System.out.println("开始测试-----------------");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }
    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}
