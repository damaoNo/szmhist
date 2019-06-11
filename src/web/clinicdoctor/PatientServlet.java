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

        if (kind.equals("selectp")){
            try {
                String  regidS=request.getParameter("regid");
                System.out.println(regidS);
                int id=Integer.parseInt(regidS);
                IConsultService cs=new ConsultService();
                MedicalRecord mr=cs.findMedRecord(id);
                ObjectMapper mapper=new ObjectMapper();
                String json=mapper.writeValueAsString(mr);
                PrintWriter pw=response.getWriter();
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
                String  checkResult=request.getParameter("checkResult");
                String  diagnosis=request.getParameter("diagnosis");
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
                if (checkResult!=null && checkResult.length()!=0){
                    mr.setCheckResult(checkResult);
                }
                if (diagnosis!=null && diagnosis.length()!=0){
                    mr.setDiagnosis(diagnosis);
                }
                System.out.println(mr);
                IConsultService cs=new ConsultService();
                cs.consulting(regid,1,mr);
                ObjectMapper mapper=new ObjectMapper();
                String json=mapper.writeValueAsString(mr);
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
