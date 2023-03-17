package com.cslg.reserve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public enum ReserveEnums {
    RESERVE("r01", "s02", "发起申请"),
    AUDIT_TEACHER("s02", "s03", "发起申请"),
    AUDIT_MANAGER("s03", "", "发起申请");
    private String step;
    private String nexStep;
    private String stepName;
}
