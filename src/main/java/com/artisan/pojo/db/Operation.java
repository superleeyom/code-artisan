package com.artisan.pojo.db;

import javax.persistence.*;

public class Operation {
    /**
     * 主键
     */
    @Id
    @Column(name = "o_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oId;

    /**
     * 操作名称
     */
    @Column(name = "o_name")
    private String oName;

    /**
     * 操作编码
     */
    @Column(name = "o_code")
    private String oCode;

    /**
     * 拦截的url
     */
    private String url;

    /**
     * 父级操作id
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 获取主键
     *
     * @return o_id - 主键
     */
    public Integer getoId() {
        return oId;
    }

    /**
     * 设置主键
     *
     * @param oId 主键
     */
    public void setoId(Integer oId) {
        this.oId = oId;
    }

    /**
     * 获取操作名称
     *
     * @return o_name - 操作名称
     */
    public String getoName() {
        return oName;
    }

    /**
     * 设置操作名称
     *
     * @param oName 操作名称
     */
    public void setoName(String oName) {
        this.oName = oName;
    }

    /**
     * 获取操作编码
     *
     * @return o_code - 操作编码
     */
    public String getoCode() {
        return oCode;
    }

    /**
     * 设置操作编码
     *
     * @param oCode 操作编码
     */
    public void setoCode(String oCode) {
        this.oCode = oCode;
    }

    /**
     * 获取拦截的url
     *
     * @return url - 拦截的url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置拦截的url
     *
     * @param url 拦截的url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取父级操作id
     *
     * @return parent_id - 父级操作id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父级操作id
     *
     * @param parentId 父级操作id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oId=").append(oId);
        sb.append(", oName=").append(oName);
        sb.append(", oCode=").append(oCode);
        sb.append(", url=").append(url);
        sb.append(", parentId=").append(parentId);
        sb.append("]");
        return sb.toString();
    }
}