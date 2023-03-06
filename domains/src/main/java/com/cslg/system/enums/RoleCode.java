package com.cslg.system.enums;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public enum RoleCode {
    INSTRUCTOR("instructor", "TEACHER"),
    MANAGER("manager", "MANAGER");
    private String groupId;
    private String roleCode;

}
