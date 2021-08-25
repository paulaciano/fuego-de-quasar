package org.rebellion.global.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class Satellite {

    private String name;
    private Double distance;
    private List<String> message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public Satellite withName(String name) {
        this.name = name;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public Satellite withDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public List<String> getMessage() {
        return message;
    }

    public Satellite withReceivedMessage(List<String> receivedMessage) {
        this.message = receivedMessage;
        return this;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
