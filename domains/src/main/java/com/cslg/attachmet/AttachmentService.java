package com.cslg.attachmet;

import com.cslg.attachmet.entity.AttachmentEntity;

import java.io.InputStream;

public interface AttachmentService {
    AttachmentEntity uploadAttachments(InputStream inputStream, String fileOriginalName);
}
