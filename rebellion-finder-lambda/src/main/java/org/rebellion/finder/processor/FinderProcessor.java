package org.rebellion.finder.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rebellion.finder.model.FinderRequest;
import org.rebellion.finder.model.FinderResponse;
import org.rebellion.finder.utils.FinderBaseUtils;
import org.rebellion.global.model.Satellite;
import org.rebellion.global.model.error.BaseException;

import java.util.ArrayList;
import java.util.List;

public class FinderProcessor {
    private static final Logger logger = LogManager.getLogger(FinderProcessor.class);

    private List<Double> distances;
    private List<List<String>> receivedMessages;
    private FinderBaseUtils utils;

    public FinderResponse process(FinderRequest inputList) throws BaseException {
        logger.info("Starting process. Request: {}", inputList );

        processInput(inputList);
        utils = new FinderBaseUtils();

        FinderResponse response = new FinderResponse();

        response.setPosition(utils.findEmisorPosition(distances));
        response.setMessage(utils.decodeMessage(receivedMessages));

        logger.info("Ending process. Response: {}", response);
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
