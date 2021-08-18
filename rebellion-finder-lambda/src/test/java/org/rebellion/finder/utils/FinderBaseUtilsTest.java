package org.rebellion.finder.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.rebellion.finder.model.Position;
import org.rebellion.finder.utils.error.BadlyReceivedMessageException;
import org.rebellion.finder.utils.error.EmisorPositionNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FinderBaseUtilsTest {

    private FinderBaseUtils utils;

    @Before
    public void setup(){
        utils = new FinderBaseUtils();
    }

    @Test
    public void findEmisorPosition_whenOk() throws EmisorPositionNotFoundException {
        Position response = utils.findEmisorPosition(getDistancesOk());
        assertEquals(new Double(-58.32), response.getX());
        assertEquals(new Double(-69.55), response.getY());
    }

//    @Test//(expected=EmisorPositionNotFoundException.class)
//    public void findEmisorPosition_whenWrongDistances() throws EmisorPositionNotFoundException {
//        Position response = utils.findEmisorPosition(getDistancesWrongly());
//
//        assertEquals(new Double(1.05), response.getX(), 1.0);
//        assertEquals(new Double(-51.42), response.getY(), 1.0);
//
//    }

    @Test
    public void decodeMessage_whenOk() throws BadlyReceivedMessageException {
        String message = utils.decodeMessage(getReceivedMessagesOk());

        assertEquals("Este es un mensaje secreto", message);

    }

    @Test(expected=BadlyReceivedMessageException.class)
    public void decodeMessage_whenIncomplete() throws BadlyReceivedMessageException {
        utils.decodeMessage(getReceivedMessagesIncomplete());
    }

    @Test(expected=BadlyReceivedMessageException.class)
    public void decodeMessage_whenMerged() throws BadlyReceivedMessageException {
        utils.decodeMessage(getReceivedMessagesMerged());
    }

    private List<Double> getDistancesOk(){
        List<Double> list = new ArrayList<Double>();
        list.add(100d);
        list.add(115.5);
        list.add(142.7);
        return list;
    }

    private List<Double> getDistancesWrongly(){
        List<Double> list = new ArrayList<Double>();
        list.add(10d);
        list.add(10d);
        list.add(10d);
        return list;
    }

    private List<List<String>> getReceivedMessagesOk(){
        List<List<String>> messagesList = new ArrayList<>();

        messagesList.add(Arrays.asList("Este", "", "", "mensaje", ""));
        messagesList.add(Arrays.asList("", "es", "", "", "secreto"));
        messagesList.add(Arrays.asList("Este", "", "un", "", ""));
        return messagesList;
    }

    private List<List<String>> getReceivedMessagesIncomplete(){
        List<List<String>> messagesList = new ArrayList<>();

        messagesList.add(Arrays.asList("Este", "", "", "mensaje", ""));
        messagesList.add(Arrays.asList("", "es", "", "", ""));
        messagesList.add(Arrays.asList("Este", "", "un", "", ""));
        return messagesList;
    }

    private List<List<String>> getReceivedMessagesMerged(){
        List<List<String>> messagesList = new ArrayList<>();

        messagesList.add(Arrays.asList("Este", "", "", "mensaje", ""));
        messagesList.add(Arrays.asList("", "es", "", "", "secreto"));
        messagesList.add(Arrays.asList("Este", "", "un", "", "mensaje"));
        return messagesList;
    }
}
