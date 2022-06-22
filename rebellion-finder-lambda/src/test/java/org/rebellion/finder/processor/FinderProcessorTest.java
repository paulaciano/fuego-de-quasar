package org.rebellion.finder.processor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.rebellion.finder.model.FinderRequest;
import org.rebellion.finder.model.FinderResponse;
import org.rebellion.global.model.Satellite;
import org.rebellion.global.model.error.BaseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FinderProcessorTest {

    private FinderProcessor processor;

    @Before
    public void setup(){
        processor = new FinderProcessor();
    }

    @Test
    public void processInputTest_whenOk() throws BaseException {

        FinderResponse response = processor.process(getSatellites());

        assertEquals(new Double(-58.32), response.getPosition().getX());
        assertEquals(new Double(-69.55), response.getPosition().getY());
        assertEquals("This is a secret message", response.getMessage());
    }



    private FinderRequest getSatellites() {
        List<Satellite> list = new ArrayList<>();
        list.add(new Satellite().withName("Kenobi")
                .withDistance(100.0)
                .withReceivedMessage(Arrays.asList("This", "", "", "secret", "")));

        list.add(new Satellite().withName("Skywalker")
                .withDistance(115.5)
                .withReceivedMessage(Arrays.asList("", "is", "", "", "message")));

        list.add(new Satellite().withName("Sato")
                .withDistance(142.7)
                .withReceivedMessage(Arrays.asList("This", "", "a", "", "")));

        FinderRequest response = new FinderRequest();
        response.setSatellites(list);
        return response;
    }

}
