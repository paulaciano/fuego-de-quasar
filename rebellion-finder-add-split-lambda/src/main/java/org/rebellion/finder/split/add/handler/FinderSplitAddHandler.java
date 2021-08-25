package org.rebellion.finder.split.add.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rebellion.finder.split.add.model.SplitAddRequest;
import org.rebellion.finder.split.add.processor.SplitAddProcessor;
import org.rebellion.global.model.error.ValidationException;

public class FinderSplitAddHandler implements RequestHandler<SplitAddRequest, Void>{
    private SplitAddProcessor processor;
    private static final Logger logger = LogManager.getLogger(FinderSplitAddHandler.class);

    public FinderSplitAddHandler() {
        this.processor = new SplitAddProcessor();
    }

    public FinderSplitAddHandler(SplitAddProcessor processor) {
        this.processor = processor;
    }

    public Void handleRequest(SplitAddRequest input, Context context) {
        logger.info("Starting handleRequest");
        try {
            validateInput(input);
            processor.process(input);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("status:404");
        }
        logger.info("Ending handleRequest");
        return null;
    }

    private void validateInput(SplitAddRequest input) throws ValidationException {
        if (input.getName() == null || input.getName().isEmpty() ||
                input.getDistance() == null || input.getDistance() <= 0d || input.getDistance().isNaN() ||
                input.getMessage() == null || input.getMessage().isEmpty())
            throw new ValidationException();
    }
}
