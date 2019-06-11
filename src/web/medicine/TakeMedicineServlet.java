package web.medicine;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.medicalmanage.ITakeDrugsService;
import service.medicalmanage.TakeDrugsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name="TakeMedicineServlet" ,urlPatterns ="/html/TakeMedicine/*" )
public class TakeMedicineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取请求的类型*/
        String uri=request.getRequestURI();
        String kindM=uri.substring(uri.indexOf("TakeMedicine"));
        String kind=kindM.substring(kindM.indexOf("/")+1);
//        System.out.println(uri);
//        System.out.println(kind);

        String  idStr=request.getParameter("binglihao");
        String  date=request.getParameter("date");
        String ID=request.getParameter("ID");
        //System.out.println(idStr);
        //System.out.println(date);
        System.out.println(ID);
        //查询成药处方
        if ("1".equals(kind)){
            int CaseNumber=Integer.parseInt(idStr);
            Date PrescriptionTime=null;
            SimpleDateFormat spdf=new SimpleDateFormat("yyyy-MM-dd");
            List list=null;
            try {
                PrescriptionTime=spdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ITakeDrugsService TD=new TakeDrugsService();
            try {
                list=TD.takeDrugs(CaseNumber,PrescriptionTime,3);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //System.out.println(list);
            ObjectMapper mapper=new ObjectMapper();
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            mapper.setDateFormat(df);
            String json=mapper.writeValueAsString(list);
            PrintWriter pw=response.getWriter();
            System.out.println(json);
            pw.println(json);
            pw.flush();
            pw.close();
        }


        //改变药方状态
        if ("2".equals(kind)){
            String[] id=null;
            id= ID.split(",");
            /*for(int i=0;i<id.length;i++){
                System.out.println(id[i]);
            }*/
            ITakeDrugsService TD=new TakeDrugsService();
            try {
                TD.changeState(4,id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
