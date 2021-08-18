package org.rebellion.finder.processor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.rebellion.finder.model.FinderRequest;
import org.rebellion.finder.model.FinderResponse;
import org.rebellion.finder.model.Satellite;
import org.rebellion.finder.utils.error.BaseException;

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
        assertEquals("Este es un mensaje secreto", response.getMessage());
    }



    private FinderRequest getSatellites() {
        List<Satellite> list = new ArrayList<Satellite>();
        list.add(new Satellite().withName("Kenobi")
                .withDistance(new Double(100))
                .withReceivedMessage(Arrays.asList("Este", "", "", "mensaje", "")));

        list.add(new Satellite().withName("Skywalker")
                .withDistance(new Double(115.5))
                .withReceivedMessage(Arrays.asList("", "es", "", "", "secreto")));

        list.add(new Satellite().withName("Sato")
                .withDistance(new Double(142.7))
                .withReceivedMessage(Arrays.asList("Este", "", "un", "", "")));

        FinderRequest response = new FinderRequest();
        response.setSatellites(list);
        return response;
    }

}
