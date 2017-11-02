package com.artisan.pojo.db;

import javax.persistence.*;

public class File {
    /**
     * 主键
     */
    @Id
    @Column(name = "f_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fId;

    /**
     * 文件名
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件路径
     */
    @Column(name = "file_url")
    private String fileUrl;

    /**
     * 获取主键
     *
     * @return f_id - 主键
     */
    public Integer getfId() {
        return fId;
    }

    /**
     * 设置主键
     *
     * @param fId 主键
     */
    public void setfId(Integer fId) {
        this.fId = fId;
    }

    /**
     * 获取文件名
     *
     * @return file_name - 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件路径
     *
     * @return file_url - 文件路径
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 设置文件路径
     *
     * @param fileUrl 文件路径
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fId=").append(fId);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileUrl=").append(fileUrl);
        sb.append("]");
        return sb.toString();
    }
}