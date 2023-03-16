package com.cslg.attachmet;

import com.cslg.attachmet.entity.AttachmentEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface AttachmentService {
    AttachmentEntity uploadAttachments(InputStream inputStream, String fileOriginalName, MultipartFile file);
}
