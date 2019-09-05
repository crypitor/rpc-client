package org.crypitor.rpc;

import org.crypitor.domain.GsonSingleton;

public class RpcResponse {
    private int id;
    private Error error;
    private Object result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return GsonSingleton.getInstance().toJson(this);
    }
}
