package org.rebellion.global.model.error;

public class ValidationException extends BaseException {

    public ValidationException() {
        super(ErrorCauses.VALIDATION_INPUT);
    }
}
