package com.cslg.attachmet.impl;

import cn.hutool.core.date.DateUtil;
import com.cslg.attachmet.AttachmentService;
import com.cslg.attachmet.entity.AttachmentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {
    @Value("${attachment.base-dir}")
    private String fileBaseDir;

    private static final String FILE_DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public AttachmentEntity uploadAttachments(InputStream inputStream, String fileOriginalName) {
        log.info("上传文件保存的目录:{}", fileBaseDir);
        log.info("上传文件保存的文件名:{}", fileOriginalName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FILE_DATE_PATTERN);
        String now = DateUtil.now();
        final Path tempDir = FileSystems.getDefault().getPath(fileBaseDir, fileBaseDir, now);
        return null;
    }
}
