package org.crypitor.rpc;

import org.crypitor.domain.GsonSingleton;

import java.util.List;

public class RpcRequest {
    private String jsonrpc;
    private int id;
    private String method;
    private List<Object> params;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return GsonSingleton.getInstance().toJson(this);
    }
}
