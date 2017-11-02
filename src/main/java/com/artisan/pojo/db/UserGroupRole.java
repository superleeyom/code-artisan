package com.artisan.pojo.db;

import javax.persistence.*;

@Table(name = "user_group_role")
public class UserGroupRole {
    /**
     * 用户组
     */
    @Id
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 角色
     */
    @Id
    @Column(name = "r_id")
    private Integer rId;

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
     * 获取角色
     *
     * @return r_id - 角色
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * 设置角色
     *
     * @param rId 角色
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
        sb.append(", groupId=").append(groupId);
        sb.append(", rId=").append(rId);
        sb.append("]");
        return sb.toString();
    }
}