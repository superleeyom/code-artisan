package com.artisan.pojo.db;

import javax.persistence.*;

public class Menu {
    /**
     * 主键
     */
    @Id
    @Column(name = "m_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mId;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单URL
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 父级菜单
     */
    @Column(name = "parent_m_id")
    private Integer parentMId;

    /**
     * 获取主键
     *
     * @return m_id - 主键
     */
    public Integer getmId() {
        return mId;
    }

    /**
     * 设置主键
     *
     * @param mId 主键
     */
    public void setmId(Integer mId) {
        this.mId = mId;
    }

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取菜单URL
     *
     * @return menu_url - 菜单URL
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单URL
     *
     * @param menuUrl 菜单URL
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * 获取父级菜单
     *
     * @return parent_m_id - 父级菜单
     */
    public Integer getParentMId() {
        return parentMId;
    }

    /**
     * 设置父级菜单
     *
     * @param parentMId 父级菜单
     */
    public void setParentMId(Integer parentMId) {
        this.parentMId = parentMId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mId=").append(mId);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuUrl=").append(menuUrl);
        sb.append(", parentMId=").append(parentMId);
        sb.append("]");
        return sb.toString();
    }
}