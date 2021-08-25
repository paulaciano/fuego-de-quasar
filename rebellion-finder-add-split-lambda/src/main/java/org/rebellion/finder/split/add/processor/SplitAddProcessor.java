package org.rebellion.finder.split.add.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rebellion.finder.split.add.model.SplitAddRequest;
import org.rebellion.finder.split.add.utils.SatellitesInfoManager;

public class SplitAddProcessor {
    private static final Logger logger = LogManager.getLogger(SplitAddProcessor.class);

    private final SatellitesInfoManager dbManager;

    public Void process(SplitAddRequest input) {
        logger.info("Starting process. Request: {}", input);

        dbManager.persistSatelliteData(input);

        logger.info("Ending process.");
        return null;
    }

    public SplitAddProcessor() {
        dbManager = new SatellitesInfoManager();
    }

    public SplitAddProcessor(SatellitesInfoManager manager) {
        this.dbManager = manager;
    }


}
