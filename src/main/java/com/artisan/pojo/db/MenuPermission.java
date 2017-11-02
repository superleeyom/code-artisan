package com.artisan.pojo.db;

import javax.persistence.*;

@Table(name = "menu_permission")
public class MenuPermission {
    /**
     * 菜单主键
     */
    @Column(name = "m_id")
    private Integer mId;

    /**
     * 权限主键
     */
    @Column(name = "p_id")
    private Integer pId;

    /**
     * 获取菜单主键
     *
     * @return m_id - 菜单主键
     */
    public Integer getmId() {
        return mId;
    }

    /**
     * 设置菜单主键
     *
     * @param mId 菜单主键
     */
    public void setmId(Integer mId) {
        this.mId = mId;
    }

    /**
     * 获取权限主键
     *
     * @return p_id - 权限主键
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * 设置权限主键
     *
     * @param pId 权限主键
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
        sb.append(", mId=").append(mId);
        sb.append(", pId=").append(pId);
        sb.append("]");
        return sb.toString();
    }
}