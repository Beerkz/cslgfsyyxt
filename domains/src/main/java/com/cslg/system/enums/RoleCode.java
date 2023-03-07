package com.cslg.system.enums;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public enum RoleCode {
    INSTRUCTOR("1", "instructor", "TEACHER"),
    MANAGER("2", "manager", "MANAGER");
    private String id;
    private String groupId;
    private String roleCode;

}
