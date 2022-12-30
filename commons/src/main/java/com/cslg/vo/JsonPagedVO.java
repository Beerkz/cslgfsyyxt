package com.cslg.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class JsonPagedVO<T> {
    private List<T> data;
    private Long total;

}
