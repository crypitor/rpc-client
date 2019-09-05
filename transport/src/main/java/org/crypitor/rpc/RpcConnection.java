package org.crypitor.rpc;

class RpcConnection {
    private static String url;
    private static String user;
    private static String password;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        RpcConnection.url = url;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        RpcConnection.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        RpcConnection.password = password;
    }
}
