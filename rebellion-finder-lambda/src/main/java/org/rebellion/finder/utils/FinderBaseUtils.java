package org.rebellion.finder.utils;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.util.Precision;
import org.rebellion.finder.model.Position;
import org.rebellion.finder.utils.error.BadlyReceivedMessageException;
import org.rebellion.finder.utils.error.EmisorPositionNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class FinderBaseUtils {

    private double[][] localPositions;
    private double[] localDistances;

    public FinderBaseUtils() {
        setLocalPositions();
    }

    public Position findEmisorPosition(List<Double> distances) throws EmisorPositionNotFoundException {
        setDistances(distances);

        try {

            TrilaterationFunction function = new TrilaterationFunction(localPositions, localDistances);
            NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(function, new LevenbergMarquardtOptimizer());
            double[] response = solver.solve().getPoint().toArray();
            int PRECISION_SCALE = 2;
            return  new Position().withX(Precision.round(response[0], PRECISION_SCALE)).withY(Precision.round(response[1], PRECISION_SCALE));

        } catch (Exception ex) {
            throw new EmisorPositionNotFoundException();
        }
    }

    public String decodeMessage(List<List<String>> receivedMessages) throws BadlyReceivedMessageException {
        List<String> response = new ArrayList<>();

        for (List<String> message : receivedMessages) {

            if(response.size() == 0 ) {
                response = message;
                continue;
            }

            for (int i = 0; i < message.size(); i++) {

                if (response.get(i).isEmpty()) {
                    response.set(i, message.get(i));

                } else {
                    if (!message.get(i).isEmpty() && !response.get(i).equals(message.get(i))) {
                        throw new BadlyReceivedMessageException();
                    } //Si son iguales no necesito hacer nada
                }
            }
        }

        if (response.stream().anyMatch(String::isEmpty)) throw new BadlyReceivedMessageException();

        return String.join(" ", response);

    }

    private void setDistances(List<Double> distances) {
        this.localDistances = new double[distances.size()];
        for (int i = 0; i < distances.size(); i++) {
            this.localDistances[i] = distances.get(i);
        }
    }

    private void setLocalPositions() {
        this.localPositions = new double[3][2];

        this.localPositions[0][0] = -500.;
        this.localPositions[0][1] = -200.;

        this.localPositions[1][0] = 100.;
        this.localPositions[1][1] = -100.;

        this.localPositions[2][0] = 500;
        this.localPositions[2][1] = 100;

    }
}
