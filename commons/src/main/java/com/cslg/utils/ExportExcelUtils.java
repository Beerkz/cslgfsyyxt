package com.cslg.utils;

import com.alibaba.excel.EasyExcel;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExportExcelUtils<T> {
    private List<T> exportList;
    public static <T> ResponseEntity<byte[]> exportExcel( List<T> data,Class<T> c, String fileName){
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        EasyExcel.write(byteArrayOutputStream,c).sheet().doWrite(data);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());
        return ResponseEntity.ok().headers(headers).body(byteArrayOutputStream.toByteArray());
    }
}
