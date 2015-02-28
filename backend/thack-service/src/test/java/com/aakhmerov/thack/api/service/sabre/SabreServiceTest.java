package com.aakhmerov.thack.api.service.sabre;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/serviceContext-test.xml"})
public class SabreServiceTest {

    private static final String TXL = "TXL";
    @Autowired
    private SabreService sabreService;

    @Test
    public void testGetDestinations() throws Exception {
        assertThat(sabreService.getDestinations(TXL,new DateTime(),new DateTime().plusDays(1)),is(notNullValue()));
    }

    @Test
    public void testGetAuthentication() throws Exception {
        assertThat(sabreService.getAuthorizationToken(),is(notNullValue()));
        assertThat(sabreService.getAuthorizationToken(),containsString("access_token"));
    }

    @Test
    public void testObtainToken() throws Exception {
        assertThat(sabreService.obtainAuth(),is(notNullValue()));

    }
}