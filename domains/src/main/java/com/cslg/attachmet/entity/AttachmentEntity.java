package com.cslg.attachmet.entity;

import com.cslg.system.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Select;

/**
 * 附件实体类
 */
@Getter
@Setter
@ToString
public class AttachmentEntity extends BaseEntity {
    /**
     * 原文件名
     */
    private String fileName;
    /**
     * 文件类型(不止通过文件名判断)
     */
    private String fileHexType;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件摘要
     */
    private String fileDigest;
    /**
     * 文件相对路径
     * 访问指定接口可以查看,下载
     */
    private String fileSubUrl;
}
