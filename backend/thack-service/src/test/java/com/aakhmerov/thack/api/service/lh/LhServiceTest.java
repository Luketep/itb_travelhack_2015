package com.aakhmerov.thack.api.service.lh;

import com.aakhmerov.thack.api.service.lh.tos.LHFlightsTO;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
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
public class LhServiceTest {

    private static final String TXL = "TXL";
    private static final String DEST = "MUC";
    @Autowired
    private LhService lhService;

    @Test
    public void testGetFlights() throws Exception {
        LHFlightsTO result = lhService.getFlights(TXL, DEST,new DateTime());

        assertThat(result,is(notNullValue()));
        assertThat(result.getJourneys(),is(notNullValue()));
        assertThat(result.getJourneys().size(),is(10));
        assertThat(result.getJourneys().get(0),is(notNullValue()));
        assertThat(result.getJourneys().get(0).get(0).getPrice(),is(notNullValue()));
        assertThat(result.getJourneys().get(0).get(0).getPrice().getTotal(),is(notNullValue()));
        assertThat(result.getJourneys().get(0).get(0).getPrice().getTotal().getSum(),is(538.30d));

    }

    @Test
    public void testParseResult() throws Exception {
        String input = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("data/lhResult.json"));
        LHFlightsTO result = lhService.parseResult(input);

        assertThat(result,is(notNullValue()));
        assertThat(result.getJourneys(),is(notNullValue()));
        assertThat(result.getJourneys().size(),is(33));
        assertThat(result.getJourneys().get(0),is(notNullValue()));
        assertThat(result.getJourneys().get(0).get(0).getPrice(),is(notNullValue()));
        assertThat(result.getJourneys().get(0).get(0).getPrice().getTotal(),is(notNullValue()));
        assertThat(result.getJourneys().get(0).get(0).getPrice().getTotal().getSum(),is(538.30d));
    }
}