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
        System.out.println("进入医生管理了");
        List<Fmeditem> list=null;
        List<Fmeditem> listz=null;

            System.out.println("进入了wod");
            IFmeditemDao ser=new FmeditemDao();
            try {
                list=ser.getAllFemditem();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        try {
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
        System.out.println("回信息完毕");


//
//        ObjectMapper ma=new ObjectMapper();
//        String json=ma.writeValueAsString(list);
//        PrintWriter pw=response.getWriter();
//        System.out.println(json);
//        pw.println(json);
//        pw.flush();
//        System.out.println("返回信息完毕");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
