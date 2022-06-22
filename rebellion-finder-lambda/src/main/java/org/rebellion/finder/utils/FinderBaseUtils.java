package org.rebellion.finder.utils;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.util.Precision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rebellion.global.model.Position;
import org.rebellion.global.model.error.BadlyReceivedMessageException;
import org.rebellion.global.model.error.EmisorPositionNotFoundException;
import org.rebellion.global.model.error.ErrorCauses;

import java.util.ArrayList;
import java.util.List;

public class FinderBaseUtils {
    private static final Logger logger = LogManager.getLogger(FinderBaseUtils.class);

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
            logger.error(ex.getStackTrace());
            throw new EmisorPositionNotFoundException();
        }
    }

    public String decodeMessage(List<List<String>> receivedMessages) throws BadlyReceivedMessageException {
        List<String> response = new ArrayList<>();

        for (List<String> message : receivedMessages) {

            if(response.size() == 0) {
                response = message;
                continue;
            }

            if (message.size() != response.size()) {
                logger.error(ErrorCauses.RECEIVED_MESSAGES_DO_NOT_MATCH);
                throw new BadlyReceivedMessageException();
            }

            for (int i = 0; i < message.size(); i++) {

                if (response.get(i).trim().isEmpty()) {
                    response.set(i, message.get(i).trim());
                } else if (!message.get(i).trim().isEmpty() && !response.get(i).equals(message.get(i).trim())) {
                    logger.error(ErrorCauses.RECEIVED_MESSAGES_DO_NOT_MATCH);
                    throw new BadlyReceivedMessageException();
                }// If I receive the same word is already stored, I don't do anything.
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
        logger.debug("Setting local positions");
        this.localPositions = new double[3][2];

        this.localPositions[0][0] = -500.;
        this.localPositions[0][1] = -200.;

        this.localPositions[1][0] = 100.;
        this.localPositions[1][1] = -100.;

        this.localPositions[2][0] = 500;
        this.localPositions[2][1] = 100;

    }
}
