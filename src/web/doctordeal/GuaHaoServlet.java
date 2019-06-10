package web.doctordeal;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.regist.IRegistService;
import service.regist.RegistService;
import vo.Invoice;
import vo.Register;

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
            try {
                DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                Register reg=new Register();
                String casuNum=request.getParameter("caseNum");
                reg.setCaseNumber(casuNum);

                String realName=request.getParameter("name");
                reg.setRealName(realName);
                String genderStr=request.getParameter("gender");
                int gender=Integer.parseInt(genderStr);
                reg.setGender(gender);
                String idNumStr=request.getParameter("ID");
                reg.setIdNumber(idNumStr);
                String birthDate=request.getParameter("birth");
                reg.setBirthDate(df.parse(birthDate));
                String ageStr=request.getParameter("age");
                int age=Integer.parseInt(ageStr);
                reg.setAge(age);
                String agetypeStr=request.getParameter("agetype");
                reg.setAgeTpye(agetypeStr);
                String homeAdd=request.getParameter("homeAddr");
                if (homeAdd!=null && homeAdd.length()!=0){
                    reg.setHomeAddress(homeAdd);
                }
                String visitDateStr=request.getParameter("date");
                reg.setVisitDate(df.parse(visitDateStr));
                String noon=request.getParameter("noon");
                reg.setNoon(noon);
                String deptID=request.getParameter("deptId");
                int deptId=Integer.parseInt(deptID);
                reg.setDeptID(deptId);
                String userID=request.getParameter("doc");
                int userId=Integer.parseInt(userID);
                reg.setUserID(userId);
                String registLeID=request.getParameter("registLe");
                int regLeID=Integer.parseInt(registLeID);
                reg.setRegistLeID(regLeID);
                String settleID=request.getParameter("settleCat");
                int settleId=Integer.parseInt(settleID);
                reg.setDeptID(settleId);
                String isBook=request.getParameter("bingli");
                if (isBook!=null && isBook.length()!=0){
                    reg.setisBook(isBook);
                }
                /*挂号员id 需更改*/
                reg.setRegisterID(1);
                Invoice iv=new Invoice();
                 /*InvoiceNum-发票号,Money-金额,State-1,CreationTime-系统当前时间,UserID-操作人id,RegistID-挂号id,FeeType-收费方式,Back-空,DailyState-空日结*/
                String invoiceNumStr=request.getParameter("invoiceNum");
                System.out.println(invoiceNumStr);
                iv.setInvoiceNum(invoiceNumStr);
                String moneyStr=request.getParameter("money");
                System.out.println(moneyStr);
                Double money=Double.valueOf(moneyStr);
                iv.setMoney(money);
                /*挂号元id 需更改*/
                iv.setUserID(1);
                String feeType=request.getParameter("feeType");
                System.out.println(feeType);
                if (feeType!=null && feeType.length()!=0){
                    int feeT=Integer.parseInt(feeType);
                    iv.setFeeType(feeT);
                }else {
                    iv.setFeeType(1);
                }
                iv.setState(1);
                IRegistService rs=new RegistService();
                rs.regist(reg,iv);

            } catch (Exception e) {
                ObjectMapper mapper=new ObjectMapper();
                String info="挂号失败";
                String json=mapper.writeValueAsString(info);
                PrintWriter pw=response.getWriter();
                System.out.println(json);
                pw.println(json);
                pw.flush();
                pw.close();
                e.printStackTrace();
            }
            ObjectMapper mapper=new ObjectMapper();
            String info="挂号成功";
            String json=mapper.writeValueAsString(info);
            PrintWriter pw=response.getWriter();
            System.out.println(json);
            pw.println(json);
            pw.flush();
            pw.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
