package web.clinicdoctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.consult.ConsultService;
import service.consult.IConsultService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PatientServlet",urlPatterns = "/html/pinfo/*")
public class PatientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri=request.getRequestURI();
        System.out.println(uri);
        String kindM=uri.substring(uri.indexOf("pinfo"));
        String kind=kindM.substring(kindM.indexOf("/")+1);
        System.out.println(kind);
        if (kind.equals("doc")){
            try {
                String  idStr=request.getParameter("userid");
                System.out.println(idStr);
                int id=Integer.parseInt(idStr);
                IConsultService cs=new ConsultService();
                List list1=cs.findDocUVP(id);
                List list2=cs.findDocVP(id);
                List list=new ArrayList();
                list.add(list1);
                list.add(list2);
                ObjectMapper mapper=new ObjectMapper();
                String json=mapper.writeValueAsString(list);
                PrintWriter pw=response.getWriter();
                System.out.println(json);
                pw.println(json);
                pw.flush();
                pw.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
