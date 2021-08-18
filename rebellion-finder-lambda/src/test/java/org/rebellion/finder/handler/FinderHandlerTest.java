package org.rebellion.finder.handler;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class FinderHandlerTest {

    private FinderHandler handler;

    @Mock
    private Context context;

    @Before
    public void setup(){
        handler = new FinderHandler();
    }

    @Test
    public void handleRequest_whenOk() {

        FinderResponse response = handler.handleRequest(getSatellites("OK"), context);

        assertEquals(new Double(-58.32), response.getPosition().getX());
        assertEquals(new Double(-69.55), response.getPosition().getY());
        assertEquals("Este es un mensaje secreto", response.getMessage());

    }

    @Test(expected = RuntimeException.class)
    public void handleRequest_whenWrongMessage() throws BaseException{
        handler.handleRequest(getSatellites("Not OK"), context);
    }

    private FinderRequest getSatellites(String status) {
        List<Satellite> list = new ArrayList<>();
        list.add(new Satellite().withName("Kenobi")
                .withDistance(100.)
                .withReceivedMessage(Arrays.asList("Este", "", "", "mensaje", "")));

        if ("OK".equals(status)) {
            list.add(new Satellite().withName("Skywalker")
                    .withDistance(115.5)
                    .withReceivedMessage(Arrays.asList("", "es", "", "", "secreto")));
        } else {
            list.add(new Satellite().withName("Skywalker")
                    .withDistance(115.5)
                    .withReceivedMessage(Arrays.asList("", "es", "", "", "")));
        }
        list.add(new Satellite().withName("Sato")
                .withDistance(142.7)
                .withReceivedMessage(Arrays.asList("Este", "", "un", "", "")));

        FinderRequest response = new FinderRequest();
        response.setSatellites(list);
        return response;
    }

}
