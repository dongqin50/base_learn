package com.zy.java_base;


import com.zy.java_base.utils.PrintUtils;

import java.util.Scanner;

public class FloatDemo {



    public static void main(String[] args) {
//        System.out.print("fdfdfdfdfd \n");
//        double f = 6.7222225;
//        BigDecimal b = new BigDecimal(f);
//        double f1 = b.setScale(1, BigDecimal.ROUND_FLOOR).doubleValue();
//        System.out.print("rs : " + f1);
        try {
            testRegisterUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception 1、使用6-16位大小写英文字母、数字或符号的组合；
     *                   2、符号不包括特殊字符，必须包含大小写英文字母，数字和符号需二选一；
     *                   3、提示如下：
     * 密码长度不合要求，提示同目前，“密码不少于6位” “密码不能超过16位”；（优先判断该条规则）
     * 密码为数字和符号的组合，纯小写或纯大写英文字母，提示“密码必须包含大小写英文字母”；
     * 密码为大写和小写英文字母，提示“密码必须包含数字或符号”；
     * 密码包含如中文或特殊字符、空格等不合要求的字符，提示“使用6-16位大小写英文字母、数字或符号的组合”。
     */
    public static void testRegisterUser() throws Exception {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String pwd = scanner.nextLine();
            printReg(pwd);
        }
    }


    private static void printReg(String pwd){
        String chars = "`~'!@#￥$^*\\-_=:.,";
//        String chars = "`~!@#$%^&*()_+-=\\[\\]{}|;:'\",./<>?";
        String charsOrNumOrLetter =  "(?=.*[^"+chars+"a-zA-Z0-9])";
        String charsOrNum = "(?=.*["+ chars+"0-9])";
        String capital = "(?=.*[A-Z])";
        String lowerCast = "(?=.*[a-z])";

        // Qq1,.:=_-*^$#

//        String length = "";  //6-16的长度
        String length = ".*";  //6-16的长度

        if (pwd.matches(".{17,}")) {
            PrintUtils.println("密码不能超过16位");
            return;
        }

        if (pwd.matches(".{1,5}")) {
            PrintUtils.println("密码不少于6位");
            return;
        }

        if(pwd.matches(buildReg(charsOrNumOrLetter,length))){
            PrintUtils.println("使用6-16位大小写英文字母、数字或符号的组合");
            return;
        }

        if(!pwd.matches(buildReg(capital,length)) ||
                !pwd.matches(buildReg(lowerCast,length))){
            PrintUtils.println("密码必须包含大小写英文字母");
            return;
        }

        if(!pwd.matches(buildReg(charsOrNum,length))){
            PrintUtils.println("密码必须包含数字或符号");
            return;
        }

        PrintUtils.println("注册成功");
    }


    public static String buildReg(String... regs) {
        if (regs != null && regs.length > 0) {
            StringBuffer stringBuffer = new StringBuffer();

            for (String s : regs) {
                stringBuffer.append(s);
            }
            return stringBuffer.toString();
        }
        return "";
    }
}

