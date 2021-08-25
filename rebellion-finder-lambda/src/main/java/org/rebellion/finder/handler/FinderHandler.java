package org.rebellion.finder.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rebellion.finder.model.FinderRequest;
import org.rebellion.finder.model.FinderResponse;
import org.rebellion.finder.processor.FinderProcessor;
import org.rebellion.global.model.error.BaseException;

public class FinderHandler implements RequestHandler<FinderRequest, FinderResponse> {

    private FinderProcessor processor;
    private static final Logger logger = LogManager.getLogger(FinderHandler.class);

    public FinderHandler(FinderProcessor processor) {
        this.processor = processor;
    }

    public FinderHandler() {
        this.processor = new FinderProcessor();
    }

    public FinderResponse handleRequest(FinderRequest satellites, Context context) {
        logger.info("Starting handleRequest");
        FinderResponse response;
        try {
            response = processor.process(satellites);
        } catch (BaseException e) {
            throw new RuntimeException("status:404");
        }
        logger.info("Ending handleRequest");
        return response;
    }
}
