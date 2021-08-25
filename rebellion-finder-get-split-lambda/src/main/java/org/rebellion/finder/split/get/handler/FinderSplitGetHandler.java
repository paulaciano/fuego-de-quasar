package org.rebellion.finder.split.get.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rebellion.finder.split.get.model.SplitGetResponse;
import org.rebellion.finder.split.get.processor.FinderSplitGetProcessor;

public class FinderSplitGetHandler implements RequestHandler<Void, SplitGetResponse> {
    private static final Logger logger = LogManager.getLogger(FinderSplitGetHandler.class);
    private final FinderSplitGetProcessor processor;

    public FinderSplitGetHandler(FinderSplitGetProcessor processor) {
        this.processor = processor;
    }

    public FinderSplitGetHandler(){
        this.processor = new FinderSplitGetProcessor();
    }

    @Override
    public SplitGetResponse handleRequest(Void unused, Context context) {
        logger.info("Starting handleRequest");
        SplitGetResponse response;
        try {
            response = processor.process();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("status:404");
        }

        if (response == null || response.getMessage() == null || response.getMessage().isEmpty())
            throw new RuntimeException("status:418");

        logger.info("Ending handleRequest. {}", response);
        return response;
    }
}
