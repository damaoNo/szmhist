package web.doctordeal;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FmeditemDao;
import dao.IFmeditemDao;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import vo.Fmeditem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "yjglServlet",urlPatterns = "/html/yjgl")
public class yjglServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("wep");
        if(request.getParameter("cmd")!=null){
           IFmeditemDao ser=new FmeditemDao();
           Fmeditem us=null;
           try {
               us=ser.getFemdByItemc(request.getParameter("val"));
           } catch (SQLException e) {
               e.printStackTrace();
           }
           ObjectMapper om=new ObjectMapper();
           String json=om.writeValueAsString(us);
           PrintWriter pw=response.getWriter();
           pw.println(json);
           pw.flush();
           pw.close();
       }else{
           List<Fmeditem> list=null;
           List<Fmeditem> listz=null;
           IFmeditemDao ser=new FmeditemDao();
           try {
               list=ser.getAllFemditem();
               listz=ser.getPageFemditem(Integer.parseInt( request.getParameter("page")),Integer.parseInt(request.getParameter("limit")) );
           } catch (SQLException e) {
               e.printStackTrace();
           }
           //需要注意返回参数的格式，参数包括count、code、msg、data
           //并且需要以json字符串返回
           Map<String,Object> rsMap = new HashMap<String,Object>();
           rsMap.put("count", list.size());
           rsMap.put("code", 0);
           rsMap.put("msg", "detail");
           rsMap.put("data", listz);

           ObjectMapper ma=new ObjectMapper();
           String json=ma.writeValueAsString(rsMap);
           PrintWriter pw=response.getWriter();
           pw.println(json);
           pw.flush();
           pw.close();
           System.out.println("回信息完毕");

       }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
