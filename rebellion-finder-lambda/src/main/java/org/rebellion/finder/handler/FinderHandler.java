package org.rebellion.finder.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.rebellion.finder.model.FinderRequest;
import org.rebellion.finder.model.FinderResponse;
import org.rebellion.finder.processor.FinderProcessor;
import org.rebellion.finder.utils.error.BaseException;

public class FinderHandler implements RequestHandler<FinderRequest, FinderResponse> {

    private FinderProcessor processor;

    public FinderHandler(FinderProcessor processor) {
        this.processor = processor;
    }

    public FinderHandler() {
        this.processor = new FinderProcessor();
    }

    public FinderResponse handleRequest(FinderRequest satellites, Context context) {
        FinderResponse response = null;

        try {
            response = processor.process(satellites);
        } catch (BaseException e) {
            throw new RuntimeException("status:404");
        }

        return response;
    }
}
