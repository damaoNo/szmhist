package web.clinicdoctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.consult.ConsultService;
import service.consult.IConsultService;
import vo.MedicalRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session=request.getSession();
        ObjectMapper mapper=new ObjectMapper();
        PrintWriter pw=response.getWriter();
        if (kind.equals("doc")){
            try {

                int docid=(Integer) session.getAttribute("docid");
                System.out.println(docid);
                String  idStr=request.getParameter("userid");
                System.out.println(idStr);
                int id=Integer.parseInt(idStr);
                IConsultService cs=new ConsultService();
                List list1=cs.findDocUVP(docid);
                List list2=cs.findDocVP(docid);
                List list=new ArrayList();
                list.add(list1);
                list.add(list2);
                mapper=new ObjectMapper();
                String json=mapper.writeValueAsString(list);
                pw=response.getWriter();
                System.out.println(json);
                pw.println(json);
                pw.flush();
                pw.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (kind.equals("selectp")){
            try {
                String  regidS=request.getParameter("regid");
                System.out.println(regidS);
                int id=Integer.parseInt(regidS);
                IConsultService cs=new ConsultService();
                MedicalRecord mr=cs.findMedRecord(id);
                mapper=new ObjectMapper();
                String json=mapper.writeValueAsString(mr);
                 pw=response.getWriter();
                System.out.println(json);
                pw.println(json);
                pw.flush();
                pw.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (kind.equals("sub")){
            try {


                String  readme=request.getParameter("readme");
                System.out.println(readme);
                String  presentTreat=request.getParameter("presentTreat");
                String  allergy=request.getParameter("allergy");
                String  physique=request.getParameter("physique");
                String  proposal=request.getParameter("proposal");
                String  careful=request.getParameter("careful");
                String  history=request.getParameter("history");
                String  present=request.getParameter("present");
                String regidS=request.getParameter("regid");
                int regid=Integer.parseInt(regidS);
                MedicalRecord mr=new MedicalRecord();
                if (readme!=null && readme.length()!=0){
                    mr.setReadme(readme);
                }
                if (presentTreat!=null && presentTreat.length()!=0){
                    mr.setPresentTreat(presentTreat);
                }
                if (allergy!=null && allergy.length()!=0){
                    mr.setAllergy(allergy);
                }
                if (physique!=null && physique.length()!=0){
                    mr.setPhysique(physique);
                }
                if (proposal!=null && proposal.length()!=0){
                    mr.setProposal(proposal);
                }
                if (careful!=null && careful.length()!=0){
                    mr.setCareful(careful);
                }
                if (history!=null && history.length()!=0){
                    mr.setHistory(history);
                }
                if (present!=null && present.length()!=0){
                    mr.setPresent(present);
                }
                System.out.println(mr);
                IConsultService cs=new ConsultService();
                cs.consulting(regid,2,mr);
                mapper=new ObjectMapper();
                String json=mapper.writeValueAsString(mr);
                System.out.println(json);
                pw.println(json);
                pw.flush();
                pw.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (kind.equals("over")){
            try {
                String regidS=request.getParameter("regid");
                int regid=Integer.parseInt(regidS);
                IConsultService cs=new ConsultService();
                cs.consulted(regid,3);
                 mapper=new ObjectMapper();
                String json=mapper.writeValueAsString("已提交");
                 pw=response.getWriter();
                System.out.println(json);
                pw.println(json);
                pw.flush();
                pw.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }





        if (kind.equals("quezen")){
            try {



                String  cr=request.getParameter("cr");

                String  diagnosis=request.getParameter("diag");
                String  handling=request.getParameter("handling");
                String regidS=request.getParameter("regid");
                int regid=Integer.parseInt(regidS);
                System.out.println(regid);
                MedicalRecord mr=new MedicalRecord();
                if (cr!=null && cr.length()!=0){
                    mr.setCheckResult(cr);
                }
                if (diagnosis!=null && diagnosis.length()!=0){
                    mr.setDiagnosis(diagnosis);
                }
                if (handling!=null && handling.length()!=0){
                    mr.setHandling(handling);
                }
                mr.setRegisterID(regid);
                System.out.println(mr);
                IConsultService cs=new ConsultService();
                cs.diagnosis(mr);
                 mapper=new ObjectMapper();
                String json=mapper.writeValueAsString(mr);
                 pw=response.getWriter();
                System.out.println(json);
                pw.println(json);
                pw.flush();
                pw.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (kind.equals("chufang")){
            try {
                String regidS=request.getParameter("regid");
                int regid=Integer.parseInt(regidS);
                int userid=(Integer) session.getAttribute("docid");
                System.out.println(userid+" "+regid);
                IConsultService cs=new ConsultService();
                List l=cs.findPreByUserID(userid,regid);

                String json=mapper.writeValueAsString(l);
                 pw=response.getWriter();
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
