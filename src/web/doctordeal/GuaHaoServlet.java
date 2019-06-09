package web.doctordeal;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.regist.IRegistService;
import service.regist.RegistService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GuaHaoServlet",urlPatterns = "/html/regist")
public class GuaHaoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的类型
        String uri=request.getRequestURI();
        String kind=uri.substring(uri.indexOf("regist"));
        System.out.println(kind);

        String  idStr=request.getParameter("userid");
        int id=Integer.parseInt(idStr);
        IRegistService rs=new RegistService();
        try {
            List list=rs.invoiceCaseNumPay(id);
            ObjectMapper mapper=new ObjectMapper();
            String json=mapper.writeValueAsString(list);
            PrintWriter pw=response.getWriter();
            pw.println(json);
            pw.flush();
            pw.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
