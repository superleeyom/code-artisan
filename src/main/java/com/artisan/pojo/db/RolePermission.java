package com.artisan.pojo.db;

import javax.persistence.*;

@Table(name = "role_permission")
public class RolePermission {
    /**
     * 角色主键
     */
    @Id
    @Column(name = "p_id")
    private Integer pId;

    /**
     * 权限主键
     */
    @Id
    @Column(name = "r_id")
    private Integer rId;

    /**
     * 获取角色主键
     *
     * @return p_id - 角色主键
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * 设置角色主键
     *
     * @param pId 角色主键
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    /**
     * 获取权限主键
     *
     * @return r_id - 权限主键
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * 设置权限主键
     *
     * @param rId 权限主键
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
        sb.append(", pId=").append(pId);
        sb.append(", rId=").append(rId);
        sb.append("]");
        return sb.toString();
    }
}