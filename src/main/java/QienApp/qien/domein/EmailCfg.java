package QienApp.qien.domein;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailCfg {
    @Value("${spring.mail.host:default}")
    private String host;

    @Value("${spring.mail.port:default}")
    private int port;

    @Value("${spring.mail.username:default}")
    private String username;

    @Value("${spring.mail.password:default}")
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}