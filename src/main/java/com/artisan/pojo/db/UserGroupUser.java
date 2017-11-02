package com.artisan.pojo.db;

import javax.persistence.*;

@Table(name = "user_group_user")
public class UserGroupUser {
    /**
     * 用户组
     */
    @Id
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 用户

     */
    @Id
    @Column(name = "u_id")
    private Integer uId;

    /**
     * 获取用户组
     *
     * @return group_id - 用户组
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置用户组
     *
     * @param groupId 用户组
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取用户

     *
     * @return u_id - 用户

     */
    public Integer getuId() {
        return uId;
    }

    /**
     * 设置用户

     *
     * @param uId 用户

     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupId=").append(groupId);
        sb.append(", uId=").append(uId);
        sb.append("]");
        return sb.toString();
    }
}