package com.artisan.pojo.db;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

public class User {
    /**
     * 主键
     */
    @Id
    @Column(name = "u_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    @NotEmpty
    private String userName;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 获取主键
     *
     * @return u_id - 主键
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * 设置主键
     *
     * @param uId 主键
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uId=").append(uId);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", birthday=").append(birthday);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append("]");
        return sb.toString();
    }
}