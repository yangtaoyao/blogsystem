package com.yty.blogsystem.ControllerTest;

import com.yty.blogsystem.BaseMockTest;
import com.yty.blogsystem.logger.Log;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
* @author  yangtaoyao
* @date  Created in 2019/5/13 0013 18:42
* @description
 * RESTful API 单元测试
 * 对于服务类而言，编写单元测试是相对简单的，只需要像控制层自动引入接口类一样。
 * 但编写控制层即RESTful API 单元测试时，一般上就需要利用Mock技术进行测试了。
 * 当然也可以使用像Swagger或者PostMan这样的api测试工具进行测试(或者使用RestTemplate测试也是可行的)，它可进行自动化测试，
 * 关于Postman会在之后的章节进行更新，作者也没有过多研究过，也只是用到了它的最基本的发起http请求的功能，之后会整理相关资料的。
*/
public class UserTest extends BaseMockTest {

    @Test
    public void test() throws Exception {
        String msg = "hello";
        MvcResult result = this.mockMvc.perform(get("/test").param("msg", msg)).andDo(print()).andExpect(status().isOk())
                .andReturn();
        //断言 是否和预期相等
        Log.getInst(this).info("================================"+result.getResponse().getContentAsString());
        Assert.assertEquals(msg, result.getResponse().getContentAsString());
    }

    @Test
    public void userTest() throws Exception {
        String msg = "hello";
        MvcResult result = this.mockMvc.perform(get("/getUser").param("id", String.valueOf(1))).andDo(print()).andExpect(status().isOk())
                .andReturn();
        //断言 是否和预期相等
        Log.getInst(this).info("================================"+result.getResponse().getContentAsString());
        Assert.assertEquals(msg, result.getResponse().getContentAsString());
    }

}
