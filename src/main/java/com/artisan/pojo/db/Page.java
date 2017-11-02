package com.artisan.pojo.db;

import javax.persistence.*;

public class Page {
    /**
     * 主键
     */
    @Id
    @Column(name = "page_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pageId;

    /**
     * 页面元素编码
     */
    @Column(name = "page_code")
    private String pageCode;

    /**
     * 获取主键
     *
     * @return page_id - 主键
     */
    public Integer getPageId() {
        return pageId;
    }

    /**
     * 设置主键
     *
     * @param pageId 主键
     */
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取页面元素编码
     *
     * @return page_code - 页面元素编码
     */
    public String getPageCode() {
        return pageCode;
    }

    /**
     * 设置页面元素编码
     *
     * @param pageCode 页面元素编码
     */
    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pageId=").append(pageId);
        sb.append(", pageCode=").append(pageCode);
        sb.append("]");
        return sb.toString();
    }
}