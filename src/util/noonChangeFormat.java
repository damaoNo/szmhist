package util;

public class noonChangeFormat {


    public static void main(String[] args) {
        String week="111111000000";
        for (int i=0;i<week.length();i++){
            String s=String.valueOf(week.charAt(i));
            if (s.equals("1")){
                switch (i){
                    case 0:

                }
            }
        }



        String mona=String.valueOf(week.charAt(0));
        if(mona=="1"){
            String Noon="上午";
        }
        String monp=String.valueOf(week.charAt(1));
        if(monp=="1"){
            String Noon="下午";
        }
        String tuea=String.valueOf(week.charAt(2));
        if(tuea=="1"){
            String Noon="午上";
        }
        String tuep=String.valueOf(week.charAt(3));
        if(tuep=="1"){
            String Noon="上午";
        }
        String thra=String.valueOf(week.charAt(4));
        if(thra=="1"){
            String Noon="上午";
        }
        String thrp=String.valueOf(week.charAt(5));
        if(thrp=="1"){
            String Noon="上午";
        }
        String fora=String.valueOf(week.charAt(6));
        if(fora=="1"){
            String Noon="上午";
        }
        String forp=String.valueOf(week.charAt(7));
        if(forp=="1"){
            String Noon="上午";
        }
        String fiva=String.valueOf(week.charAt(8));
        if(fiva=="1"){
            String Noon="上午";
        }
        String fivp=String.valueOf(week.charAt(9));
        if(fivp=="1"){
            String Noon="上午";
        }
        String sixa=String.valueOf(week.charAt(10));
        if(sixa=="1"){
            String Noon="上午";
        }
        String sixp=String.valueOf(week.charAt(11));
        if(sixp=="1"){
            String Noon="上午";
        }
        String seva=String.valueOf(week.charAt(12));
        if(seva=="1"){
            String Noon="上午";
        }
        String sevp=String.valueOf(week.charAt(13));
        if(sevp=="1"){
            String Noon="上午";
        }
       System.out.println();
    }
}
