package org.juni.Test;

import junit.framework.Assert;
import org.example.Util.DBUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;


public class DBUtilTest {

    @Test
    public void testGetConnection(){

        Connection connection= DBUtil.getConnection();
        System.out.println(connection);
        Assert.assertNotNull(connection);
    }

}
