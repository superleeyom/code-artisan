package com.artisan.pojo.db;

import javax.persistence.*;

@Table(name = "user_group")
public class UserGroup {
    /**
     * 主键
     */
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    /**
     * 用户组名称
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 父级用户组名称
     */
    @Column(name = "parent_group_name")
    private String parentGroupName;

    /**
     * 获取主键
     *
     * @return group_id - 主键
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置主键
     *
     * @param groupId 主键
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取用户组名称
     *
     * @return group_name - 用户组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置用户组名称
     *
     * @param groupName 用户组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取父级用户组名称
     *
     * @return parent_group_name - 父级用户组名称
     */
    public String getParentGroupName() {
        return parentGroupName;
    }

    /**
     * 设置父级用户组名称
     *
     * @param parentGroupName 父级用户组名称
     */
    public void setParentGroupName(String parentGroupName) {
        this.parentGroupName = parentGroupName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupId=").append(groupId);
        sb.append(", groupName=").append(groupName);
        sb.append(", parentGroupName=").append(parentGroupName);
        sb.append("]");
        return sb.toString();
    }
}