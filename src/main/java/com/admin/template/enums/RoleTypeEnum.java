package com.admin.template.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RoleTypeEnum {

    //角色类型 0普通角色，1超级管理员
    NORMAL(0, "普通角色"),
    supers(1, "超级管理员");

    //类型
    private final Integer type;
    //状态名
    private String statusName;

    public static String getTypeName(Integer type) {
        RoleTypeEnum roletypeEnum = Arrays.stream(RoleTypeEnum.values()).filter(x -> x.getType().equals(type))
                .findAny().orElse(null);
        if (roletypeEnum == null) {
            return null;
        }
        return roletypeEnum.getStatusName();
    }

    public static Integer getType(String statusName) {
        RoleTypeEnum roletypeEnum = Arrays.stream(RoleTypeEnum.values()).filter(x -> x.getStatusName().equals(statusName))
                .findAny().orElse(null);
        if (roletypeEnum == null) {
            return null;
        }
        return roletypeEnum.getType();
    }

    public static RoleTypeEnum getEnum(Integer type) {
        RoleTypeEnum roletypeEnum = Arrays.stream(RoleTypeEnum.values()).filter(x -> x.getType().equals(type))
                .findAny().orElse(null);
        if (roletypeEnum == null) {
            return null;
        }
        return roletypeEnum;
    }
}
