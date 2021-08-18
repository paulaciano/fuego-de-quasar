package org.rebellion.finder.model;

import java.util.List;

public class FinderRequest {

    private List<Satellite> satellites;

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }

    @Override
    public String toString() {
        return "FinderRequest{" +
                "satellites=" + satellites +
                '}';
    }
}
