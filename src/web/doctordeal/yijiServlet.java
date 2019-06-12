package web.doctordeal;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.medicaltreatment.IPatientExaminationService;
import service.medicaltreatment.PatientExaminationService;
import vo.PatientExamination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "yijiServlet",urlPatterns ="/html/yiji")
public class yijiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入医技检测了");
        String casn=request.getParameter("casn");
        PatientExamination user=null;
  //      System.out.println(casn);
        IPatientExaminationService ser=new PatientExaminationService();
        try {
            user=ser.getPatientExamByCaseNumber(casn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObjectMapper js=new ObjectMapper();
        String json=js.writeValueAsString(user);
        System.out.println(json);
        PrintWriter pw=response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
