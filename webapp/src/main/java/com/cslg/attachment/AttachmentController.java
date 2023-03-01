package com.cslg.attachment;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.cslg.attachmet.AttachmentService;
import com.cslg.attachmet.entity.AttachmentEntity;
import com.cslg.vo.RestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("secured/attachment/")
@Api(tags = "附件上传")
@RestController
@AllArgsConstructor
//@SaCheckLogin
public class AttachmentController {

    private AttachmentService attachmentService;

    @PostMapping("upload")
    @ApiOperation("附件上传")
    public RestBody<List<AttachmentEntity>> uploads(@RequestParam MultipartFile[] files) {
        List<AttachmentEntity> collect = Arrays.stream(files).map(file -> {
            final String fileOriginalName = file.getOriginalFilename();
            try (final InputStream inputStream = file.getInputStream()) {
                return attachmentService.uploadAttachments(inputStream, fileOriginalName);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }).collect(Collectors.toList());
        return RestBody.okData(collect);
    }
}
