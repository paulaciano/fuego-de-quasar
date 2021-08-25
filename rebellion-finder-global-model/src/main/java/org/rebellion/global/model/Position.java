package org.rebellion.global.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Position {
    private Double x;
    private Double y;

    public Double getX() {
        return x;
    }

    public Position withX(Double x) {
        this.x = x;
        return this;
    }

    public Double getY() {
        return y;
    }

    public Position withY(Double y) {
        this.y = y;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return (getX().equals(position.getX()) &&
                getY().equals(position.getY()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
