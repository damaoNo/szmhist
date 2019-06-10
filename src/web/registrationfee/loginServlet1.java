package web.registrationfee;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.IUserDao;
import dao.UserDao;
import net.sf.json.JSONObject;
import vo.LoginMessage;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns ="/login")
public class loginServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String pass=request.getParameter("pass");
        System.out.println(username+":"+pass);
        IUserDao ud=new UserDao();
        User user=null;
        try {
            user=ud.findUserByNamePass(username,pass);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter writer=response.getWriter();
        ObjectMapper mapper=new ObjectMapper();

        String uri="/his/login.html";
        List info=new ArrayList();
        info.add(user);
        if (user.getRealName()!=null){

            if (user.getUseTpye()==1){
                uri="/his/html/main.html";
            }
            if (user.getUseTpye()==0){
                uri="/his/html/main.html";
            }
            if (user.getUseTpye()==3){
                uri="/his/html/main.html";
            }
            if (user.getUseTpye()==2){
                uri="/his/html/main.html";
            }

        }
        info.add(uri);
        String json=mapper.writeValueAsString(info);
        writer.println(json);

        writer.flush();
        writer.close();
        System.out.println(json);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}



