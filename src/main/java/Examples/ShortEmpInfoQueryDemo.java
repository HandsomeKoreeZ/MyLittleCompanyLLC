package Examples;

import Entities.Employee;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ShortEmpInfoQueryDemo {

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtils.getSessionFacroty();
        Session session = sf.getCurrentSession();

        try{
            session.getTransaction().begin();

            //using constructon of ShortEmpInfo
            String hql = "Select new "+ShortEmpInfo.class.getName()+"(e.empID, e.empNO, e.empName)"+" from "+ Employee.class.getName()+" e";
            Query<ShortEmpInfo> query = session.createQuery(hql);

            //execution
            List<ShortEmpInfo> emps = query.getResultList();

            // show results
            for (ShortEmpInfo si: emps) System.out.println("Emp:" + si.getEmpNO()+" - "+si.getEmpName());

            //commit session
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            //onfail rollback
            session.getTransaction().rollback();
        }
    }
}
