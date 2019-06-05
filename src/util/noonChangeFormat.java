package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date begin=null;
        Date end=null;
        try {
             begin=sdf.parse("2019-06-10");
             end=sdf.parse("2019-06-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i=0;i<7;i++){
            SimpleDateFormat dateFm=new SimpleDateFormat("EEEE");//日期转换成星期
            String  currSun=dateFm.format(begin);
        }
      System.out.println("list"+list.toString());
    }
}
