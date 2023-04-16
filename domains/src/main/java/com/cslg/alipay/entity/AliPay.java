package com.cslg.alipay.entity;

import lombok.Data;

@Data
public class AliPay {
    private String traceNo;
    private double totalAmount;
    private String subject;
    private String alipayTraceNo;
    private Long needId;
    private Long providerId;
}
