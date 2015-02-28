package com.aakhmerov.thack.api.service;

import com.aakhmerov.thack.api.service.tos.SearchResultTO;
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
        SearchResultTO result = searchService.find("test", new DateTime());
        assertThat(result,is(notNullValue()));
        assertThat(result.getDestinations(),is(notNullValue()));
        assertThat(result.getDestinations().size(),is(1));
        assertThat(result.getDestinations().get(0),is(notNullValue()));
        assertThat(result.getDestinations().get(0).getCity(),is("Hamburg"));
        assertThat(result.getDestinations().get(0).getEvents(),is(notNullValue()));
        assertThat(result.getDestinations().get(0).getEvents().size(),is(2));
    }
}