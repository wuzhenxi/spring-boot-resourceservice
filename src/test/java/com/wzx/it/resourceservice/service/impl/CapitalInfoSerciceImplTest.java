package com.wzx.it.resourceservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.wzx.it.resourceservice.domain.CapitalInfo;
import com.wzx.it.resourceservice.domain.CapitalInfoReposiyory;
import com.wzx.it.resourceservice.utils.ReadTestProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
/**
 * CapitalInfoSerciceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>04/19/2019</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CapitalInfoSerciceImplTest {

    @Autowired private CapitalInfoSerciceImpl capitalInfoSercice;

    @Autowired private CapitalInfoReposiyory capitalInfoReposiyory;

    @Autowired private ReadTestProperties readTestProperties;

    private CapitalInfo capitalInfo;

    @Before
    public void before() throws Exception {
        capitalInfo = capitalInfoReposiyory.save(get("capital_save_data01", CapitalInfo.class));
    }

    @After
    public void after() throws Exception {
        capitalInfoReposiyory.deleteAll();
    }

    /**
     * Method: getCapitalInfos()
     */
    @Test
    public void testGetCapitalInfos() throws Exception {
        assertEquals(1,capitalInfoSercice.getCapitalInfos().size());
    }

    /**
     * Method: getCapitalByEmpId(Integer empId)
     */
    @Test
    public void testGetCapitalByEmpId() {
        List<CapitalInfo> capitalInfos = capitalInfoSercice.getCapitalByEmpId(capitalInfo.getEmpId());
        assertEquals(get("capital_save_data01", CapitalInfo.class).getCreditRate(),capitalInfos.get(0).getCreditRate());
    }

    private <T> T get(String dataKey,Class<T> clazz) {
        String propertiesValue = readTestProperties.getPropertiesForKey(dataKey);
        return JSON.parseObject(propertiesValue, clazz);
    }
} 
