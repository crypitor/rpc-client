package org.crypitor.rpc;

import org.apache.commons.lang.math.RandomUtils;

import java.util.List;

public class RpcRequestBuilder {
    private String jsonrpc = "2.0";
    private String method = null;
    private List<Object> params = null;

    public static RpcRequestBuilder getBuilder() {
        return new RpcRequestBuilder();
    }

    private static int randomId() {
        return RandomUtils.nextInt();
    }

    public RpcRequest build() {
        RpcRequest request = new RpcRequest();
        request.setId(randomId());
        request.setJsonrpc(jsonrpc);
        request.setMethod(method);
        request.setParams(params);
        return request;
    }

    public RpcRequestBuilder setUrl(String url) {
        RpcConnection.setUrl(url);
        return this;
    }

    public RpcRequestBuilder setUser(String user) {
        RpcConnection.setUser(user);
        return this;
    }

    public RpcRequestBuilder setPassword(String password) {
        RpcConnection.setPassword(password);
        return this;
    }

    public RpcRequestBuilder setMethod(String method) {
        this.method = method;
        return this;
    }

    public RpcRequestBuilder setParams(List<Object> params) {
        this.params = params;
        return this;
    }
}
