package com.aakhmerov.thack.api.service;

import com.aakhmerov.thack.api.service.tos.aggregated.SearchResultTO;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/serviceContext-test.xml"})
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;
    @Test
    public void testFind() throws Exception {
        long start = System.currentTimeMillis();
        SearchResultTO result = searchService.find("TXL", new DateTime());
        long end = System.currentTimeMillis();
        System.out.println("TOOK [" + (end-start) + "] ms");
        assertThat(result,is(notNullValue()));
        assertThat(result.getDestinations(),is(notNullValue()));
        assertThat(result.getDestinations().size(),is(11));
        assertThat(result.getDestinations().get(0),is(notNullValue()));
        assertThat(result.getDestinations().get(0).getSabreInfo().getDestinationLocation(),is("ZRH"));
        assertThat(result.getDestinations().get(0).getTours(),is(notNullValue()));
        assertThat(result.getDestinations().get(0).getTours().size(),is(10));
    }
}