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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "GuaHaoServlet",urlPatterns = "/html/regist/*")
public class GuaHaoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求的类型
        String uri=request.getRequestURI();
        System.out.println(uri);
        String kindM=uri.substring(uri.indexOf("regist"));
        String kind=kindM.substring(kindM.indexOf("/")+1);
        System.out.println(kind);
        //进入挂号页面加载病历号-付费方式-发票号
        if ("1".equals(kind)){
            try {
                String  idStr=request.getParameter("userid");
                System.out.println(idStr);
                int id=Integer.parseInt(idStr);
                IRegistService rs=new RegistService();
                List list=rs.invoiceCaseNumPay(id);
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



        if ("2".equals(kind)){
            try {
                String  noon=request.getParameter("noon");
                System.out.println(noon);
                String date=request.getParameter("date");
                System.out.println("date"+date);
                DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                IRegistService rs=new RegistService();
                List list=rs.getDeptNames(df.parse(date),noon);
                ObjectMapper mapper=new ObjectMapper();
                String json=mapper.writeValueAsString(list);
                PrintWriter pw=response.getWriter();
                System.out.println(json);
                pw.println(json);
                pw.flush();
                pw.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if ("3".equals(kind)){
            try {
                String  noon=request.getParameter("noon");
                System.out.println(noon);
                String date=request.getParameter("date");
                System.out.println("date"+date);
                String deptIDStr=request.getParameter("deptID");
                System.out.println(deptIDStr);
                String regLeIDStr=request.getParameter("regLeID");
                System.out.println(regLeIDStr);
                int deptID=Integer.parseInt(deptIDStr);
                int regLeID=Integer.parseInt(regLeIDStr);
                IRegistService rs=new RegistService();
                List list=rs.findDoctorInfo(date,noon,deptID,regLeID);
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

        if ("4".equals(kind)){
            try {
                String date=request.getParameter("date");
                System.out.println("date"+date);
                String regLeIDStr=request.getParameter("regLeID");
                System.out.println(regLeIDStr);
                String docIDStr=request.getParameter("docID");
                System.out.println(docIDStr);
                int docID=Integer.parseInt(docIDStr);
                int regLeID=Integer.parseInt(regLeIDStr);
                IRegistService rs=new RegistService();
                List list=rs.findMoneyTicket(docID,regLeID,date);
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

        if ("sub".equals(kind)){
            /**
             * 挂号-开发票-插入挂号记录
             * @param reg   CaseNumber-病历号,RealName-患者名,Gender-性别,IDnumber,BirthDate,Age,AgeType-int,
             *      HomeAddress,VisitDate-1,Noon,DeptID,UserID,RegistLeID,SettleID,
             *      IsBook-是否要病历本,RegistTime-系统当前时间（自动设置）,RegisterID,VisitState-1-已挂号（自动设置）
             * @param iv
             * InvoiceNum-发票号,Money-金额,State-1,CreationTime-系统当前时间,UserID-操作人id,RegistID-挂号id,FeeType-收费方式,Back-空,DailyState-空日结
             *
             * @throws SQLException
             */
            /*try {*/
                String casuNum=request.getParameter("caseNum");
            System.out.println(casuNum);
                String realName=request.getParameter("name");
            System.out.println(realName);
                String gender=request.getParameter("gender");
            System.out.println(gender);
                String idNum=request.getParameter("ID");
            System.out.println(idNum);
                String birthDate=request.getParameter("birth");
            System.out.println(birthDate);
                String age=request.getParameter("age");
            System.out.println(age);
                String agetypeStr=request.getParameter("agetype");
            System.out.println(agetypeStr);
                String homeAdd=request.getParameter("homeAddr");
            System.out.println(homeAdd);
                String visitDateStr=request.getParameter("date");
            System.out.println(visitDateStr);
                String noon=request.getParameter("noon");
            System.out.println(noon);
                String deptID=request.getParameter("deptID");
            System.out.println(deptID);
                String userID=request.getParameter("doc");
            System.out.println(userID);
                String registLeID=request.getParameter("registLe");
            System.out.println(registLeID);
                String settleID=request.getParameter("settleCat");
            System.out.println(settleID);
                String isBook=request.getParameter("bingli");
            System.out.println(isBook);



                /*int docID=Integer.parseInt(docIDStr);
                int regLeID=Integer.parseInt(regLeIDStr);
                IRegistService rs=new RegistService();
                List list=rs.findMoneyTicket(docID,regLeID,date);
                ObjectMapper mapper=new ObjectMapper();
                String json=mapper.writeValueAsString(list);
                PrintWriter pw=response.getWriter();
                System.out.println(json);
                pw.println(json);
                pw.flush();
                pw.close();*/
            /*} catch (SQLException e) {
                e.printStackTrace();
            }*/
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
