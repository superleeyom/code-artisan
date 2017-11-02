package com.artisan.pojo.db;

import javax.persistence.*;

@Table(name = "page_permission")
public class PagePermission {
    /**
     * 页面主键
     */
    @Column(name = "page_id")
    private Integer pageId;

    /**
     * 权限主键
     */
    @Column(name = "p_id")
    private Integer pId;

    /**
     * 获取页面主键
     *
     * @return page_id - 页面主键
     */
    public Integer getPageId() {
        return pageId;
    }

    /**
     * 设置页面主键
     *
     * @param pageId 页面主键
     */
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
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
        sb.append(", pageId=").append(pageId);
        sb.append(", pId=").append(pId);
        sb.append("]");
        return sb.toString();
    }
}