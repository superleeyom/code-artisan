package com.artisan.pojo.vo;


import java.util.Date;

/**
 * User视图层实体类对象
 * @author leeyom
 * @date 2017年10月30日 21:15
 */
public class UserVO {

    /**
     * 主键
     */
    private Integer uId;

    /**
     * 用户名
     */
    private String uName;

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
     * token
     */
    private String token;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", age=" + age +
                ", token='" + token + '\'' +
                '}';
    }
}
