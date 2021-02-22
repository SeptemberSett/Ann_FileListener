package com.mao.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author Mr mao
 * @Date 11:02
 * @Version 1.0
 * @Description
 */
@TableName("PPProDocPathDTO")
public class PPProDocPathDTO {

    @TableId(value = "ID", type = IdType.ID_WORKER_STR)
    private String id;
    @TableField("FILE_NAME")
    private  String file_Name;
    @TableField("FILE_PATH")
    private  String file_Path;
    @TableField("FILE_TYPE")
    private  String file_Type;
    @TableField("STATUS")
    private  String status;
    @TableField("REMARKS")
    private  String remarks;
    @TableField("REVISION")
    private  String revision;
    @TableField("CREATE_BY")
    private  String create_By;
    @TableField("CREATE_TIME")
    private Date create_Time;
    @TableField("UPDATE_BY")
    private  String update_By;
    @TableField("UPDATE_TIME")
    private Date update_Time;
    @TableField("DELETE_BY")
    private  String delete_By;
    @TableField("DELETE_TIME")
    private Date delete_Time;
    @TableField("DOWNLOAD_DOC_ADDRESS")
    private String downLoad_Doc_Address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile_Name() {
        return file_Name;
    }

    public void setFile_Name(String file_Name) {
        this.file_Name = file_Name;
    }

    public String getFile_Path() {
        return file_Path;
    }

    public void setFile_Path(String file_Path) {
        this.file_Path = file_Path;
    }

    public String getFile_Type() {
        return file_Type;
    }

    public void setFile_Type(String file_Type) {
        this.file_Type = file_Type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getCreate_By() {
        return create_By;
    }

    public void setCreate_By(String create_By) {
        this.create_By = create_By;
    }

    public Date getCreate_Time() {
        return create_Time;
    }

    public void setCreate_Time(Date create_Time) {
        this.create_Time = create_Time;
    }

    public String getUpdate_By() {
        return update_By;
    }

    public void setUpdate_By(String update_By) {
        this.update_By = update_By;
    }

    public Date getUpdate_Time() {
        return update_Time;
    }

    public void setUpdate_Time(Date update_Time) {
        this.update_Time = update_Time;
    }

    public String getDelete_By() {
        return delete_By;
    }

    public void setDelete_By(String delete_By) {
        this.delete_By = delete_By;
    }

    public Date getDelete_Time() {
        return delete_Time;
    }

    public void setDelete_Time(Date delete_Time) {
        this.delete_Time = delete_Time;
    }

    public String getDownLoad_Doc_Address() {
        return downLoad_Doc_Address;
    }

    public void setDownLoad_Doc_Address(String downLoad_Doc_Address) {
        this.downLoad_Doc_Address = downLoad_Doc_Address;
    }
}
