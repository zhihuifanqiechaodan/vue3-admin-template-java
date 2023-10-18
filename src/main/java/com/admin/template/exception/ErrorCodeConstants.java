package com.admin.template.exception;

public interface ErrorCodeConstants {

    ErrorCode SUCCESS = new ErrorCode(200, "成功");

    // ========== 客户端错误段 ==========
    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "账号未登录");
    ErrorCode FORBIDDEN = new ErrorCode(402, "没有该操作权限");
    ErrorCode NOT_FOUND = new ErrorCode(403, "请求未找到");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(404, "请求方法不正确");
    ErrorCode LOCKED = new ErrorCode(405, "请求失败，请稍后重试");
    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(406, "请求过于频繁，请稍后重试");
    ErrorCode PARTIAL_DATA_ERROR = new ErrorCode(407, "部分数据异常");

    // ========== 服务端错误段 ==========
    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "系统异常");
    ErrorCode REPEATED_REQUESTS = new ErrorCode(501, "重复请求，请稍后重试");
    ErrorCode UNKNOWN = new ErrorCode(502, "未知错误");
    ErrorCode SERVER_WARN = new ErrorCode(503, "后端数据警告提示");

    // ========== 用户模块 1002000000 ==========
    ErrorCode USER_NOT_EXIST = new ErrorCode(1002000000, "登录失败，用户不存在");
    ErrorCode PASSWORD_ERROR = new ErrorCode(1002000001, "登录失败，用户密码错误");
    ErrorCode NO_ROLE_MENU_ERROR = new ErrorCode(1002000002, "创建用户失败，所选角色下不存在菜单信息");

    // ========== 菜单模块 1003000000 ==========
    ErrorCode TITLE_EXIST_ERROR = new ErrorCode(1003000000, "title名称重复");
    ErrorCode PATH_EXIST_ERROR = new ErrorCode(1003000001, "path名称重复");
    ErrorCode BUTTON_TITLE_EXIST_ERROR = new ErrorCode(1003000002, "按钮名称重复");
    ErrorCode BUTTON_ID_EXIST_ERROR = new ErrorCode(1003000003, "按钮名称重复");
}
