package Examples;

import Entities.Employee;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class HQLDemo2 {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFacroty();
        Session session = factory.getCurrentSession();

        try{
            //always wrap in transaction
            session.getTransaction().begin();

            //form query
            String sql = "Select e from "+ Employee.class.getName() + " e "+" where e.department.deptNo=:deptNo";
            Query<Employee> query = session.createQuery(sql);
            query.setParameter("deptNo","D10");

            //execution
            List<Employee> emps = query.getResultList();

            //show results
            for(Employee e: emps) System.out.println("Emp: " + e.getEmpNO()+" - "+e.getEmpName());

            //commit traisaction
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            //onfail transaction rollback
            session.getTransaction().rollback();
        }
    }
}
