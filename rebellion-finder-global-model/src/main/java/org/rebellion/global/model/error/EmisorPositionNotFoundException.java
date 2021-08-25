package org.rebellion.global.model.error;

public class EmisorPositionNotFoundException extends BaseException {

    public EmisorPositionNotFoundException() {
        super(ErrorCauses.EMISOR_POSITION_CANNOT_BE_FOUND);
    }
}
