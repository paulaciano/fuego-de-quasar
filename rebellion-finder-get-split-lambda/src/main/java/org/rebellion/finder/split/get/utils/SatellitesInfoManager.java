package org.rebellion.finder.split.get.utils;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rebellion.finder.split.get.model.FinderRequest;
import org.rebellion.global.model.Satellite;
import org.rebellion.global.utils.StringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SatellitesInfoManager {
    private static final Logger logger = LogManager.getLogger(SatellitesInfoManager.class);

    private String SATELLITE_TABLE = "satelliteInfo";

    public FinderRequest getSatelliteData() {
        logger.info("getSatelliteData starts");

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        ScanRequest scanRequest = new ScanRequest()
                .withTableName(SATELLITE_TABLE);
        logger.debug("getSatelliteData DB request: {}", scanRequest);

        ScanResult result = client.scan(scanRequest);

        FinderRequest response = new FinderRequest();
        response.setSatellites(getSatelliteList(result));

        logger.info("getSatelliteData ends");
        logger.debug("Response: {}", response);
        return response;
    }

    private List<Satellite> getSatelliteList(ScanResult result) {
        List<Satellite> satelliteList = new ArrayList<>();

        Satellite kenobi = null;
        Satellite skywalker = null;
        Satellite sato = null;

        for (Map<String, AttributeValue> item : result.getItems()){
            if (item.get("name").getS().equalsIgnoreCase("kenobi")) {
                kenobi = new Satellite();
                kenobi.setName(item.get("name").getS());
                kenobi.setDistance(Double.parseDouble(item.get("distance").getN()));
                kenobi.setMessage(StringConverter.getBackTheStringList(item.get("message").getS()));
            } else if (item.get("name").getS().equalsIgnoreCase("skywalker")) {
                skywalker = new Satellite();
                skywalker.setName(item.get("name").getS());
                skywalker.setDistance(Double.parseDouble(item.get("distance").getN()));
                skywalker.setMessage(StringConverter.getBackTheStringList(item.get("message").getS()));
            } else if (item.get("name").getS().equalsIgnoreCase("sato")) {
                sato = new Satellite();
                sato.setName(item.get("name").getS());
                sato.setDistance(Double.parseDouble(item.get("distance").getN()));
                sato.setMessage(StringConverter.getBackTheStringList(item.get("message").getS()));
            }
        }

        if (kenobi == null || skywalker == null || sato == null)
            return null;

        satelliteList.add(kenobi);
        satelliteList.add(skywalker);
        satelliteList.add(sato);
        return satelliteList;
    }
}
