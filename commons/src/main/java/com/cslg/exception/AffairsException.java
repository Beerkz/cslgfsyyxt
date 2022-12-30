package com.cslg.exception;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AffairsException extends RuntimeException {

    private Integer code;
    private String msg;
}
