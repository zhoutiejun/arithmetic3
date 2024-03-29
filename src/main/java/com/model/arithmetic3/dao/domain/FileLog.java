package com.model.arithmetic3.dao.domain;

import java.util.Date;

public class FileLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_log.id
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_log.account
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    private String account;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_log.file_name
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    private String fileName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_log.create_time
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_log.update_time
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_log.type
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_log.id
     *
     * @return the value of file_log.id
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_log.id
     *
     * @param id the value for file_log.id
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_log.account
     *
     * @return the value of file_log.account
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_log.account
     *
     * @param account the value for file_log.account
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_log.file_name
     *
     * @return the value of file_log.file_name
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_log.file_name
     *
     * @param fileName the value for file_log.file_name
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_log.create_time
     *
     * @return the value of file_log.create_time
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_log.create_time
     *
     * @param createTime the value for file_log.create_time
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_log.update_time
     *
     * @return the value of file_log.update_time
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_log.update_time
     *
     * @param updateTime the value for file_log.update_time
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_log.type
     *
     * @return the value of file_log.type
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_log.type
     *
     * @param type the value for file_log.type
     *
     * @mbg.generated Tue May 19 14:42:16 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }
}