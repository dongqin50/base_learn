package com.zy.android_base;

import com.zy.java_base.utils.PrintUtils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegularTest {


    public static final String CAPITALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCAST = CAPITALS.toLowerCase();
    public static final String NUMS = "0123456789";
    public static final String CHARS = "`~!@#$%^&*()_+-=[]\\{}|;:'\",./<>?";
    public static final String HANZI = "汉子中国热大家看见对方健康的减肥看得见打开就是看丹江口市多少时代的可爱的萨克斯打";
    public static final String UNCHARS = "（）【】」「 ";


    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testRegister() throws Exception{
        String string = CAPITALS +  LOWERCAST + NUMS + CHARS + HANZI ;
        //正确的
        String numOrReg = "(?=.*[\\W|_|\\d])"; //数字或字符(不包括汉子，字母，数字)
        String lowercastReg = "(?=.*[a-z])"; //纯英文
        String capitalReg = "(?=.*[A-Z])"; //纯英文
        String length = ".{6,16}";  //6-16的长度
//        String chinese = "(?=.*[^\\u4E00-\\u9FFF].*)";

        for (long i = 0; i < 1000000; i++){

            StringBuffer pwd = new StringBuffer();
            int maxlength = (int) (Math.random() * 17);
            for(int j = 0; j < maxlength; j++){
                long index = (long) (Math.random()*10000*1000);
                pwd.append(string.charAt((int) (index%string.length())));
            }
            String password = pwd.toString();
            System.out.println(password);
            boolean isRegisterSuccess = isLengthRight(password)&&isNumberOrChars(password) && isCapitals(password)&& isLowerCast(password)&&(!isHanZi(password))
                    ;
//                    && (!isUnChars(password));
            boolean isRegSuccess = password.matches(buildReg(numOrReg, lowercastReg, capitalReg, length));

            assertEquals(isRegisterSuccess,isRegSuccess);
            if(!isRegisterSuccess){
                System.err.println("失败");
//                assertEquals(isNotMinLength(password),password.matches(".{1,5}"));
//                assertEquals(isNotMaxLength(password),password.matches(".{17,}"));
//                assertEquals(isNumberOrChars(password)&&isLengthRight(password),password.matches(buildReg(numOrReg, length)));
//                assertEquals((isLowerCast(password)&&isCapitals(password)|| isLowerCast(password) || isCapitals(password))&&isLengthRight(password),
//                        password.matches(buildReg(lowercastReg, length)) ||
//                                password.matches(buildReg(capitalReg, length)) ||
//                                password.matches(buildReg(lowercastReg, capitalReg, length)));
            }else {
                System.out.println("成功");
            }





        }
    }


    /**
     * @throws Exception 1、使用6-16位大小写英文字母、数字或符号的组合；
     *                   2、符号不包括特殊字符，必须包含大小写英文字母，数字和符号需二选一；
     *                   3、提示如下：
     *                   密码长度不合要求，提示同目前，“密码不少于6位” “密码不能超过16位”；
     *                   密码为数字和符号的组合，提示“密码必须包含大小写英文字母”；
     *                   密码为纯英文字母，提示“密码必须包含数字或符号”；
     *                   密码包含如中文或特殊字符等不合要求的字符，提示“使用6-16位大小写英文字母、数字或符号的组合”。
     */
    public  void testRegisterUser(String pwd)  {

//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String pwd = scanner.nextLine();
//            String rePassword = scanner.nextLine();

//        String pwd = "ereredFF";
//        String rePassword = "erere5fdfdFF";
            //正确的
            String numOrReg = "(?=.*[\\W|_|\\d])";  //数字或字符(不包括汉子，字母，数字)
            String lowercastReg = "(?=.*[a-z])"; //纯英文
            String capitalReg = "(?=.*[A-Z])"; //纯英文
            String length = ".{6,16}";  //6-16的长度
//        if(pwd == null ||  "".equals(pwd) ){
//            PrintUtils.println("密码不少于6位");
//        }else if(rePassword == null || "".equals(rePassword)){
//            PrintUtils.println("重置密码不能为空");
//        }else if(!pwd.equals(rePassword)){
//            PrintUtils.println("密码不一致");
//        }else
            if (pwd.matches(buildReg(".{16,}"))) {
                PrintUtils.println("密码不能超过16位");
            } else if (pwd.matches(buildReg(".{1,5}"))) {
                PrintUtils.println("密码不少于6位");
            } else if (pwd.matches(buildReg(numOrReg, lowercastReg, capitalReg, length))) {
                PrintUtils.println("注册成功");
            } else if (pwd.matches(buildReg(numOrReg, length))) {
                PrintUtils.println("密码必须包含大小写英文字母");
            } else if (pwd.matches(buildReg(lowercastReg, length)) ||
                    pwd.matches(buildReg(capitalReg, length)) ||
                    pwd.matches(buildReg(lowercastReg, capitalReg, length))) {
                PrintUtils.println("密码必须包含数字或符号");
            } else {
                PrintUtils.println("其他错误");
            }
//        }
    }




    public  String buildReg(String... regs) {
        if (regs != null && regs.length > 0) {
            StringBuffer stringBuffer = new StringBuffer();

            for (String s : regs) {
                stringBuffer.append(s);
            }
            return stringBuffer.toString();
        }
        return "";
    }
    
    private boolean isCapitals(String pwd){
        if(isNUll(pwd)){
            return false;
        }

        for(char c : pwd.toCharArray()){
            if(CAPITALS.indexOf(c) != -1){
                return true;
            }
        }
        return false;
    }
    
    private boolean isLowerCast(String pwd){
        if(isNUll(pwd)){
            return false;
        }

        for(char c : pwd.toCharArray()){
            if(LOWERCAST.indexOf(c) != -1){
                return true;
            }
        }
        return false;
    }
    
    
    private boolean isNUll(String pwd){
        if(pwd != null && pwd.length() <= 0){
            return true;
        }
        
        return false;
    }
    

    private boolean isNumberOrChars(String pwd){
        
        if(isNUll(pwd)){
            return false;
        }
        
        for(char c : pwd.toCharArray()){
            if(NUMS.indexOf(c) != -1 || CHARS.indexOf(c) != -1){
                return true;
            }
        }
        
        return false;
    }

    private boolean isNotMinLength(String pwd){
        if(isNUll(pwd)){
            return false;
        }

        if(pwd.length() < 6){
//            System.err.println("密码不少于6位");
            return true;
        }

        return false;
    }

    private boolean isNotMaxLength(String pwd){
        if(isNUll(pwd)){
            return false;
        }

        if( pwd.length() > 16){
//            System.err.println("密码不超过16位");
            return true;
        }

        return false;
    }

    private boolean isLengthRight(String pwd){
        if(isNUll(pwd)){
            return false;
        }

        if(pwd.length() >= 6 && pwd.length() <= 16){
            return true;
        }

        return false;
    }

    private boolean isHanZi(String pwd){
        if(isNUll(pwd)){
            return false;
        }

        for(char c : pwd.toCharArray()){
            if(HANZI.indexOf(c) != -1){
                return true;
            }
        }
        return false;
    }

    //包含不显示的特殊字符
    private boolean isUnChars(String pwd){

        return !(isNUll(pwd)|| isHanZi(pwd) || isLowerCast(pwd) || isCapitals(pwd) || isNumberOrChars(pwd));

    }

}
