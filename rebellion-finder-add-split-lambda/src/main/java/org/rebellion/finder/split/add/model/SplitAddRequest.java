package org.rebellion.finder.split.add.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class SplitAddRequest {

    private String name;
    private Double distance;
    private List<String> message;

    public SplitAddRequest(String name, Double distance, List<String> message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    public SplitAddRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public SplitAddRequest withName(String name) {
        this.name = name;
        return this;
    }

    public SplitAddRequest withDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public SplitAddRequest withMessage(List<String> message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
