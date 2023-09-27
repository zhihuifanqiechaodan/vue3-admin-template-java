package com.admin.template.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    //菜单类型 0:目录，1:菜单，2:按钮
    CATALOGUE(0, "目录"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    //类型
    private final Integer type;
    //状态名
    private String statusName;

    public static String getTypeName(Integer type) {
        MenuTypeEnum roletypeEnum = Arrays.stream(MenuTypeEnum.values()).filter(x -> x.getType().equals(type))
                .findAny().orElse(null);
        if (roletypeEnum == null) {
            return null;
        }
        return roletypeEnum.getStatusName();
    }

    public static Integer getType(String statusName) {
        MenuTypeEnum roletypeEnum = Arrays.stream(MenuTypeEnum.values()).filter(x -> x.getStatusName().equals(statusName))
                .findAny().orElse(null);
        if (roletypeEnum == null) {
            return null;
        }
        return roletypeEnum.getType();
    }

    public static MenuTypeEnum getEnum(Integer type) {
        MenuTypeEnum roletypeEnum = Arrays.stream(MenuTypeEnum.values()).filter(x -> x.getType().equals(type))
                .findAny().orElse(null);
        if (roletypeEnum == null) {
            return null;
        }
        return roletypeEnum;
    }
}
