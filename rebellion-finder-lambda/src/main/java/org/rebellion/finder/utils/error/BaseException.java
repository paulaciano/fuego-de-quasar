package org.rebellion.finder.utils.error;

public class BaseException extends Exception {

    private String exceptionCause;

    public BaseException(ErrorCauses exceptionCause) {
        this.exceptionCause = exceptionCause.getText();
    }
}
