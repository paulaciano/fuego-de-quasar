package org.rebellion.finder.processor;

import org.rebellion.finder.model.FinderRequest;
import org.rebellion.finder.model.FinderResponse;
import org.rebellion.finder.model.Satellite;
import org.rebellion.finder.utils.FinderBaseUtils;
import org.rebellion.finder.utils.error.BaseException;

import java.util.ArrayList;
import java.util.List;

public class FinderProcessor {

    private List<Double> distances;
    private List<List<String>> receivedMessages;
    private FinderBaseUtils utils;

//    private Position positionKenobi = new Position().withX(-500.).withY(-200.);
//    private Position positionSkywalker = new Position().withX(100.).withY(-100.);
//    private Position positionSato = new Position().withX(500.).withY(100.);

    public FinderResponse process(FinderRequest inputList) throws BaseException {
        processInput(inputList);
        utils = new FinderBaseUtils();

        FinderResponse response = new FinderResponse();

        response.setPosition(utils.findEmisorPosition(distances));
        response.setMessage(utils.decodeMessage(receivedMessages));

        return response;
    }

    public FinderProcessor(FinderBaseUtils utils) {
        this.utils = utils;
    }

    public FinderProcessor() {
        this(new FinderBaseUtils());
    }

    private void processInput(FinderRequest inputList) {
        this.distances = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
        for (Satellite input : inputList.getSatellites()) {
            distances.add( input.getDistance());
            receivedMessages.add(input.getMessage());
        }
    }
}
