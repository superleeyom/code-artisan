package com.artisan.pojo.qo;

/**
 * user查询实体
 * @author Leeyom Wang
 * @date 2017年10月26日 15:22
 */
public class UserQO {

    /**
     * 姓名，查询字段
     */
    private String uName;

    /**
     * 性别，查询字段
     */
    private Integer sex;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserQO{" +
                "uName='" + uName + '\'' +
                ", sex=" + sex +
                '}';
    }
}
