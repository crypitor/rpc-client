package org.crypitor.rpc.exception;

public class RpcCommunicateException extends Exception {
    private static final long serialVersionUID = -3389735550453652555L;

    public RpcCommunicateException() {
        super();
    }

    public RpcCommunicateException(String message) {
        super(message);
    }

    public RpcCommunicateException(Throwable cause) {
        super("Sorry, an error occurred while processing your request.", cause);
    }

    public RpcCommunicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
