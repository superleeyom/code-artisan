package com.artisan.pojo.db;

import javax.persistence.*;

@Table(name = "permission_operation")
public class PermissionOperation {
    @Id
    @Column(name = "p_id")
    private Integer pId;

    @Id
    @Column(name = "o_id")
    private Integer oId;

    /**
     * @return p_id
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * @param pId
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    /**
     * @return o_id
     */
    public Integer getoId() {
        return oId;
    }

    /**
     * @param oId
     */
    public void setoId(Integer oId) {
        this.oId = oId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pId=").append(pId);
        sb.append(", oId=").append(oId);
        sb.append("]");
        return sb.toString();
    }
}