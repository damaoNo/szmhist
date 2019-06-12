package web.clinicdoctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.consult.ConsultService;
import service.consult.IConsultService;
import vo.NonDrugsPay;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "AddXiangMuServlet",urlPatterns ="/html/AddXiangMu/*")
public class AddXiangMuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取请求的类型*/
        String uri=request.getRequestURI();
        String kindM=uri.substring(uri.indexOf("AddXiangMu"));
        String kind=kindM.substring(kindM.indexOf("/")+1);
        String  Str=request.getParameter("zhujima");
        System.out.println(uri);
        System.out.println(kind);
        System.out.println(Str);
        //新增医技
        if ("1".equals(kind)){
            List<NonDrugsPay> list=null;
            IConsultService IC=new ConsultService();
            NonDrugsPay nd=new NonDrugsPay();
             nd.setMnemonicCode(Str);
            try {
                 list= IC.findNDrugByType(nd);
                System.out.println(list.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ObjectMapper mapper=new ObjectMapper();
            String json=mapper.writeValueAsString(list);
            PrintWriter pw=response.getWriter();
            System.out.println(json);
            pw.println(json);
            pw.flush();
            pw.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
