package util;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析14位数字到具体星期上下午
 */
public class noonChangeFormat {


    public static void main(String[] args) {
        String week="11110001110001";
        List list=new ArrayList();
        for (int i=0;i<week.length();i++){
            String s=String.valueOf(week.charAt(i));
            if (s.equals("1")){
                switch (i){
                    case 0:
                        list.add("星期一上午");
                        break;
                    case 1:
                        list.add("星期一下午");
                        break;
                    case 2:
                        list.add("星期二上午");
                        break;
                    case 3:
                        list.add("星期二下午");
                        break;
                    case 4:
                        list.add("星期三上午");
                        break;
                    case 5:
                        list.add("星期三下午");
                        break;
                    case 6:
                        list.add("星期四上午");
                        break;
                    case 7:
                        list.add("星期四下午");
                        break;
                    case 8:
                        list.add("星期五上午");
                        break;
                    case 9:
                        list.add("星期五下午");
                        break;
                    case 10:
                        list.add("星期六上午");
                        break;
                    case 11:
                        list.add("星期六下午");
                        break;
                    case 12:
                        list.add("星期天上午");
                        break;
                    case 13:
                        list.add("星期天下午");
                        break;

                }
            }
        }
        System.out.println("list"+list.toString());
    }
}
