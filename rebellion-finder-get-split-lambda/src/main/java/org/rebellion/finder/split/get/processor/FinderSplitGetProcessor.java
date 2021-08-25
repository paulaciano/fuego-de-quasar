package org.rebellion.finder.split.get.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rebellion.finder.split.get.model.FinderRequest;
import org.rebellion.finder.split.get.model.SplitGetResponse;
import org.rebellion.finder.split.get.utils.RebellionFinderInvoke;
import org.rebellion.finder.split.get.utils.SatellitesInfoManager;

public class FinderSplitGetProcessor {
    private static final Logger logger = LogManager.getLogger(FinderSplitGetProcessor.class);

    private final SatellitesInfoManager dbManager;
    private final RebellionFinderInvoke invoker;

    public FinderSplitGetProcessor(SatellitesInfoManager dbManager, RebellionFinderInvoke invoker) {
        this.dbManager = dbManager;
        this.invoker = invoker;
    }

    public FinderSplitGetProcessor(){
        this.dbManager = new SatellitesInfoManager();
        this.invoker = new RebellionFinderInvoke();
    }

    public SplitGetResponse process() {
        logger.info("Starting process.");

        FinderRequest finderRequest = dbManager.getSatelliteData();

        logger.debug("Invoker Request: {}", finderRequest);
        SplitGetResponse getResponse = invoker.invokeLambda(finderRequest);

        logger.info("Ending process. Response: {}", getResponse);
        return getResponse;
    }
}
