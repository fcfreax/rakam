package org.rakam.http;

import com.google.common.net.HostAndPort;
import io.airlift.configuration.Config;
import io.airlift.configuration.ConfigDescription;
import org.rakam.util.CryptUtil;

public class HttpServerConfig {
    private static final int RAKAM_DEFAULT_PORT = 9999;
    private static final String RAKAM_DEFAULT_HOST = "0.0.0.0";

    private HostAndPort address = HostAndPort.fromParts(RAKAM_DEFAULT_HOST, RAKAM_DEFAULT_PORT);
    private boolean disabled;
    private boolean proxyProtocol;
    private boolean debug;
    private String secretKey = CryptUtil.generateRandomKey(50);

    @Config("http.server.address")
    public HttpServerConfig setAddress(String address) {
        if(address == null)
            this.address = HostAndPort.fromParts(RAKAM_DEFAULT_HOST, RAKAM_DEFAULT_PORT);
        else
            this.address = HostAndPort.fromString(address).withDefaultPort(RAKAM_DEFAULT_PORT);
        return this;
    }

    public HostAndPort getAddress() {
        return address;
    }

    @Config("http.server.disabled")
    public HttpServerConfig setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public boolean getDisabled() {
        return disabled;
    }

    @Config("http.server.proxy-protocol")
    public HttpServerConfig setProxyProtocol(boolean proxyProtocol) {
        this.proxyProtocol = proxyProtocol;
        return this;
    }

    public boolean getProxyProtocol() {
        return proxyProtocol;
    }

    @Config("http.server.debug")
    public HttpServerConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public boolean getDebug() {
        return debug;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Config("secret-key")
    @ConfigDescription("The secret key that will be used when encrypting sessions and passwords. " +
            "Do not expose this key because if it's known, the sessions may be hijacked.")
    public HttpServerConfig setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }
}