package com.artisan.pojo.db;

import javax.persistence.*;

@Table(name = "user_role")
public class UserRole {
    /**
     * 用户id
     */
    @Id
    @Column(name = "u_id")
    private Integer uId;

    /**
     * 角色id
     */
    @Id
    @Column(name = "r_id")
    private Integer rId;

    /**
     * 获取用户id
     *
     * @return u_id - 用户id
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * 设置用户id
     *
     * @param uId 用户id
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * 获取角色id
     *
     * @return r_id - 角色id
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * 设置角色id
     *
     * @param rId 角色id
     */
    public void setrId(Integer rId) {
        this.rId = rId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uId=").append(uId);
        sb.append(", rId=").append(rId);
        sb.append("]");
        return sb.toString();
    }
}