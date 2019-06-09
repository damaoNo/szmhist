package web.registrationfee;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import vo.LoginMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns ="/login")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("接收到请求");
        System.out.println(request.getHeaderNames());
       if(request.getParameter("account").equals("root")&&request.getParameter("password").equals("root")){
           System.out.println("进入登录");
           LoginMessage ms=new LoginMessage();
           ms.setTime(2);
           ms.setStr("this is www ding min");
//           JSONObject json=JSONObject.fromObject(ms);
//           String str=json.toString();
//           ObjectMapper mapper=new ObjectMapper();
//
//           String usersjson=mapper.writeValueAsString(ms);
           PrintWriter pw=response.getWriter();

           String url="localhost:8888/his/html/main.html";
           pw.println(url);
           pw.flush();
           pw.close();
//           pw.println(usersjson);
//           pw.flush();
//           System.out.println(usersjson);
//           pw.close();
           /*response.sendRedirect(request.getContextPath()+"/html/main.html");
           System.out.println(request.getContextPath()+"/html/main.html");*/
       }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}



