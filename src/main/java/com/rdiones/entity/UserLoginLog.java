package com.rdiones.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "user_login_log")
@XmlRootElement(name = "UserLoginLog")
public class UserLoginLog {
    @Id
    @Column(name = "login_id")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")//定于主键的生成策略
    @GeneratedValue(generator = "system-uuid")
    private String loginId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "login_time")
    private Date loginTime = new Date();


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
