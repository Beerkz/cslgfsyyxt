package com.cslg.attachmet.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import com.cslg.attachmet.AttachmentService;
import com.cslg.attachmet.entity.AttachmentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {
    @Value("${attachment.base-dir}")
    private String fileBaseDir;
    @Value("${attachment.mapping}")
    private String mapping;
    private static final String PATH_SPLIT = "/";

    private static final String FILE_DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public AttachmentEntity uploadAttachments(InputStream inputStream, String fileOriginalName) {
        log.info("上传文件保存的目录:{}", fileBaseDir);
        log.info("上传文件保存的文件名:{}", fileOriginalName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FILE_DATE_PATTERN);
        String day = simpleDateFormat.format(DateUtil.date());//DateUtil.today();
        final Path baseDir = FileSystems.getDefault().getPath(fileBaseDir, day);
        //NIO流创建文件夹，返回值是创建文件夹的路径
        try {
            //文件保存路径 父目录
            Path directories = Files.createDirectories(baseDir);
            //文件摘要加密
            String fileDigest = DigestUtils.md5DigestAsHex(inputStream);
            String now = DateUtil.now();
            //拼接路径
            Path resolve = directories.resolve(now + fileDigest + fileOriginalName);
            log.info("创建文件夹的路径:{}", resolve.toString());
            if (Files.exists(resolve)) {
                log.error("文件已经存在:{}", resolve);
                throw new RuntimeException("文件已经存在！");
            }
            Files.createFile(resolve);
            //文件大小
            final long size = Files.size(resolve);
            //hutool 工具获取获取文件类型
            final String type = FileTypeUtil.getType(inputStream);
            return new AttachmentEntity(fileOriginalName, type, size, fileDigest, mapping + PATH_SPLIT + resolve.getFileName().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
