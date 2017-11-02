package com.artisan.pojo.db;

import javax.persistence.*;

public class Permission {
    /**
     * 主键
     */
    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pId;

    /**
     * 权限类型
     */
    @Column(name = "p_type_name")
    private String pTypeName;

    /**
     * 获取主键
     *
     * @return p_id - 主键
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * 设置主键
     *
     * @param pId 主键
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    /**
     * 获取权限类型
     *
     * @return p_type_name - 权限类型
     */
    public String getpTypeName() {
        return pTypeName;
    }

    /**
     * 设置权限类型
     *
     * @param pTypeName 权限类型
     */
    public void setpTypeName(String pTypeName) {
        this.pTypeName = pTypeName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pId=").append(pId);
        sb.append(", pTypeName=").append(pTypeName);
        sb.append("]");
        return sb.toString();
    }
}