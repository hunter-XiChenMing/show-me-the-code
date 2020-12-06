package com.starlib.util.invoke;

import com.starlib.model.response.RestResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MethodReflectTest {

    @Test
    public void copyBeanAttributes() {
        RestResponse response = new RestResponse();
        Map<String,Object> param = new HashMap<>();
        param.put("status",true);
        param.put("resultCode","200");
        ResourceCommonConst.REST_RESPONSE.copyBeanAttributes(response,param);
        Assert.assertEquals("200",response.getResultCode());
        Assert.assertNull(response.getExtendData());
        assertTrue(response.isStatus());
    }
}