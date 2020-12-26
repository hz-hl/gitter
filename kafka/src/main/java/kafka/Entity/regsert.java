package kafka.Entity;

import java.io.Serializable;

/**
 * @ClassNameregsert
 * @Author ${张有鹏}
 * @Description
 * @Date 2020/12/9 20:06
 * @Param
 * @return
 */
public class regsert implements Serializable {
    private String username;
    private String password;

    public regsert() {
    }

    public regsert(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "regsert{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
