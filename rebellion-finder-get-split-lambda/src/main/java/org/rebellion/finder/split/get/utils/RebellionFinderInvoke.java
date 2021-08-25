package org.rebellion.finder.split.get.utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rebellion.finder.split.get.model.FinderRequest;
import org.rebellion.finder.split.get.model.SplitGetResponse;

import java.nio.charset.StandardCharsets;

public class RebellionFinderInvoke {
    private static final Logger logger = LogManager.getLogger(RebellionFinderInvoke.class);

   public SplitGetResponse invokeLambda(FinderRequest finderRequest) {
       logger.info("invokeLambda starts");
       logger.debug("Request: {}", finderRequest);
       AWSLambdaClientBuilder builder = AWSLambdaClientBuilder.standard()
               .withRegion(Regions.US_EAST_2);

       AWSLambda client = builder.build();
       InvokeRequest req = new InvokeRequest()
               .withFunctionName("rebellion-finder-lambda")
               .withPayload(finderRequest.toString());

       InvokeResult result = client.invoke(req);
       SplitGetResponse response = getResponseFromInvokeResult(result);

       logger.debug("Response: {}", response );
       logger.info("invokeLambda ends");
        return response;
    }

    private SplitGetResponse getResponseFromInvokeResult(InvokeResult result) {
        Gson g = new Gson();
        return g.fromJson(StandardCharsets.UTF_8.decode(result.getPayload()).toString(), SplitGetResponse.class);
    }
}