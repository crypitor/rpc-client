package org.crypitor.rpc.exception;

import org.crypitor.rpc.Error;

public class RpcHttpException extends Exception {
    private long status;
    private Error error;

    private static final long serialVersionUID = -3389735550453652555L;

    public RpcHttpException() {
        super();
    }

    public RpcHttpException(long status, Error error) {
        super(error.getMessage());
        this.status = status;
        this.error = error;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
