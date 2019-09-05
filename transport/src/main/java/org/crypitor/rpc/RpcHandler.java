package org.crypitor.rpc;

import org.crypitor.rpc.exception.RpcCommunicateException;
import org.crypitor.rpc.exception.RpcHttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.crypitor.domain.GsonSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

public class RpcHandler {

    private static final Logger logger = LoggerFactory.getLogger(RpcHandler.class);

    private static HttpClient client = HttpClientBuilder.create().build();
    private static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(10000)
            .setConnectionRequestTimeout(10000)
            .setSocketTimeout(10000)
            .build();

    public static RpcResponse performRequest(RpcRequest request) throws RpcCommunicateException, RpcHttpException {
        String authorize = encode(RpcConnection.getUser(), RpcConnection.getPassword());
        RpcResponse response = invokeAndReadResponse(RpcConnection.getUrl(), request, authorize);
        return response;
    }

    private static String encode(String username, String password) {
        String authorize = String.format("%s:%s", username, password);
        return "Basic " + Base64.getEncoder().encodeToString(authorize.getBytes());
    }

    private static RpcResponse invokeAndReadResponse(String url, RpcRequest request, String authorize) throws RpcHttpException, RpcCommunicateException {
        try {
            HttpPost post = new HttpPost(url);

            StringEntity input = new StringEntity(request.toString(), "UTF-8");
            input.setContentType("application/json;charset=utf-8");
            post.setEntity(input);
            post.setHeader("Connection", "keep-alive");
            post.addHeader("Authorization", authorize);
            post.setConfig(requestConfig);
            HttpResponse response = client.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder stringBuffer = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            String result = stringBuffer.toString();
            bufferedReader.close();
            RpcResponse rpcResponse = GsonSingleton.getInstance().fromJson(result, RpcResponse.class);

            if (statusCode >= 200 && statusCode < 300 && !result.equals("")) {
                if (rpcResponse.getId() != (request.getId())) {
                    logger.error("request id and response id does not match");
                    throw new RpcCommunicateException("Error occur, request id and response id does not match");
                }
                return rpcResponse;
            } else {
                throw new RpcHttpException(statusCode, rpcResponse.getError());
            }
        } catch (IOException e) {
            logger.error("error rpc communicate with message: " + e.getMessage());
            throw new RpcCommunicateException("A problem occured while processing the request.", e);
        }
    }
}
