package org.rebellion.finder.utils.error;

public class BadlyReceivedMessageException extends BaseException {

    public BadlyReceivedMessageException() {
        super(ErrorCauses.RECEIVED_MESSAGES_DO_NOT_MATCH);
    }
}
