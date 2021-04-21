package org.example.Util;


import junit.framework.Assert;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class JSONUtilTest {
    @Test//注解
    public void testSerialize(){

        //测试序列化操作：使用map模拟
        Map map = new HashMap<>();
        map.put("name","java40");
        map.put("students",new int[]{1,2});
        String json = JSONUtil.serialize(map);
        System.out.println(json);
        Assert.assertNull(json);

    }


    @Test
    public void testDeserialize(){
        //类加载器加载某个资源，返回输入流
        InputStream is =JSONUtil.class.getClassLoader().
                getResourceAsStream("login.json");
        Map map =JSONUtil.deserialize(is,Map.class);
        Assert.assertNotNull(map);
    }

}
