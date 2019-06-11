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

@WebServlet("/TakeMedicineServlet")
public class TakeMedicineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的类型
        String uri=request.getRequestURI();
        System.out.println(uri);
        String  idStr=request.getParameter("binglihao");
        String  date=request.getParameter("date");
        String ID=request.getParameter("ID");
        System.out.println(idStr);
        System.out.println(date);
        System.out.println(ID);
        //查询成药处方
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
        System.out.println(list);
        ObjectMapper mapper=new ObjectMapper();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        String json=mapper.writeValueAsString(list);
        PrintWriter pw=response.getWriter();
        System.out.println(json);
        pw.println(json);
        pw.flush();
        pw.close();

        //改变药方状态
        String[] id=ID.split(",");
        try {
            TD.changeState(4,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
