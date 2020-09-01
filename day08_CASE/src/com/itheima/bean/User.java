package com.itheima.bean;

import java.util.Arrays;
import java.util.Objects;

/**
 * 姓名:<input type="text" name="username"  /><br/>
 * 密码:<input type="password" name="password"  /><br/>
 * 昵称:<input type="text" name="nickname"  /><br/>
 * 地址:<input type="text" name="address"  /><br/>
 * 邮箱:<input type="text" name="email"  /><br/>
 * 性别:<input type="radio" name="sex" value="女" />女
 * <input type="radio" name="sex"  value="男" />男<br/>
 * 爱好: <input type="checkbox" name="hobby" value="抽烟"/> 抽烟
 * <input type="checkbox" name="hobby" value="喝酒"/> 喝酒
 * <input type="checkbox" name="hobby" value="烫头"/> 烫头<br/>
 *
 * <input type="submit" value="注册"/>
 */
public class User {
    private String userid;
    private String username;
    private String password;
    private String nickname;
    private String address;
    private String email;
    private String sex;

    public User() {
    }

    public User(String userid, String username, String password, String nickname, String address, String email, String sex) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.email = email;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
