package com.artisan.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 * @author Leeyom Wang
 * @date 2017年10月19日 11:53
 */
public class VerificationUtil {
    /**
     * 邮箱格式验证
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_.\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /***
     * 密码验证
     *
     */
    public static boolean isPassword(String password) {
        String str = "^([a-zA-Z0-9]{0,50})";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    /***
     * 用户名验证证
     */
    public static boolean isAdminname(String adminname) {
        String str = "^([a-zA-Z]{1}[a-zA-Z0-9_]{5,15})";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(adminname);
        return m.matches();
    }

    public static boolean isImage(String ContentType) {
        String str = "^(image/gif|image/jpg|image/png|image/jpeg)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(ContentType);
        return m.matches();
    }

    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
