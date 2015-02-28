package com.aakhmerov.thack.api.service.gyg;

import com.aakhmerov.thack.api.service.gyg.tos.GygResponseTO;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/serviceContext-test.xml"})
public class GygServiceTest {

    @Autowired
    private GygService gygService;

    @Test
    public void testGetTours() throws Exception {
        GygResponseTO parsed = gygService.getTours("ZRH");
        assertThat(parsed,is(notNullValue()));
        assertThat(parsed.getData(),is(notNullValue()));
        assertThat(parsed.getData().getTours(),is(notNullValue()));
        assertThat(parsed.getData().getTours().size(),is(10));
        assertThat(parsed.getData().getTours().get(0),is(notNullValue()));
        assertThat(parsed.getData().getTours().get(0).getPrice().getValues().getAmount(),is(notNullValue()));
        assertThat(parsed.getData().getTours().get(0).getTitle(),is("Lucerne and Engelberg Full-Day Tour from Zurich"));
    }

    @Test
    public void testParseTours() throws Exception {
        String response = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("data/gygResult.json"));
        GygResponseTO parsed = gygService.parseResponse(response);
        assertThat(parsed,is(notNullValue()));
        assertThat(parsed.getData(),is(notNullValue()));
        assertThat(parsed.getData().getTours(),is(notNullValue()));
        assertThat(parsed.getData().getTours().size(),is(10));
        assertThat(parsed.getData().getTours().get(0),is(notNullValue()));
        assertThat(parsed.getData().getTours().get(0).getPrice().getValues().getAmount(),is(notNullValue()));
        assertThat(parsed.getData().getTours().get(0).getTitle(),is("Lucerne and Engelberg Full-Day Tour from Zurich"));
    }
}