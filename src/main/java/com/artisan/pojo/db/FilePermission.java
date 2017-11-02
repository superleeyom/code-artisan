package com.artisan.pojo.db;

import javax.persistence.*;

@Table(name = "file_permission")
public class FilePermission {
    /**
     * 文件主键id
     */
    @Column(name = "f_id")
    private Integer fId;

    /**
     * 权限主键id
     */
    @Column(name = "p_id")
    private Integer pId;

    /**
     * 获取文件主键id
     *
     * @return f_id - 文件主键id
     */
    public Integer getfId() {
        return fId;
    }

    /**
     * 设置文件主键id
     *
     * @param fId 文件主键id
     */
    public void setfId(Integer fId) {
        this.fId = fId;
    }

    /**
     * 获取权限主键id
     *
     * @return p_id - 权限主键id
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * 设置权限主键id
     *
     * @param pId 权限主键id
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fId=").append(fId);
        sb.append(", pId=").append(pId);
        sb.append("]");
        return sb.toString();
    }
}