package org.rebellion.finder.utils.error;

public enum ErrorCauses {
    RECEIVED_MESSAGES_DO_NOT_MATCH("Received messages do not match"),
    EMISOR_POSITION_CANNOT_BE_FOUND("Data is not enough to determine the emisor position"),
    OTHER("Other");

    private String text;

    ErrorCauses(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
