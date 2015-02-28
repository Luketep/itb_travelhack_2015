package com.aakhmerov.thack.api.service.sabre;

import com.aakhmerov.thack.api.service.sabre.tos.SabreDestinationTO;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/serviceContext-test.xml"})
public class SabreServiceTest {

    private static final String TXL = "TXL";
    @Autowired
    private SabreService sabreService;

    @Test
    public void testGetDestinations() throws Exception {
        List<SabreDestinationTO> wrapper = sabreService.getDestinations(TXL, new DateTime(), new DateTime().plusDays(1));
        assertThat(wrapper,is(notNullValue()));
        assertThat(wrapper.size(),is(11));
        assertThat(wrapper.get(0).getDestinationLocation(),is("ZRH"));
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

    @Test
    public void testFormat() throws Exception {
        assertThat(sabreService.format(new DateTime()),is("2015-02-28"));
    }

    @Test
    public void testParseResult() throws Exception {

        String result = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("data/sabreResult.json"));
        List<SabreDestinationTO> wrapper = sabreService.parseResult(result);
        assertThat(wrapper,is(notNullValue()));
        assertThat(wrapper.size(),is(11));
        assertThat(wrapper.get(0).getDestinationLocation(),is("ZRH"));
    }
}