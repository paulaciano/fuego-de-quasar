package org.rebellion.global.model.error;

public class BadlyReceivedMessageException extends BaseException {

    public BadlyReceivedMessageException() {
        super(ErrorCauses.RECEIVED_MESSAGES_DO_NOT_MATCH);
    }
}
