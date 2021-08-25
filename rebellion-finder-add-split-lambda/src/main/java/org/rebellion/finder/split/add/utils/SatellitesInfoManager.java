package org.rebellion.finder.split.add.utils;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rebellion.finder.split.add.model.SplitAddRequest;
import org.rebellion.global.utils.StringConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SatellitesInfoManager {
    private static final Logger logger = LogManager.getLogger(SatellitesInfoManager.class);

    DynamoDB dynamoDb;

    public SatellitesInfoManager() {
        initDynamoDbClient();
    }

    private void initDynamoDbClient() {
        dynamoDb = new DynamoDB(AmazonDynamoDBClientBuilder.standard().build());
    }

    public PutItemOutcome persistSatelliteData(SplitAddRequest addRequest)
            throws ConditionalCheckFailedException {
        logger.info("persistSatelliteData starts");
        logger.debug("Adding item: {}", addRequest);
        String SATELLITE_TABLE = "satelliteInfo";
        return this.dynamoDb.getTable(SATELLITE_TABLE)
                .putItem(
                        new PutItemSpec().withItem(new Item()
                                .withString("name", addRequest.getName())
                                .withString("created_date", getFormattedDateForNow())
                                .withDouble("distance", addRequest.getDistance())
                                .withString("message", StringConverter.getStringListAsString(addRequest.getMessage()))));
    }

    private String getFormattedDateForNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }
}
