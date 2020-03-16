package Examples;

import Entities.Employee;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class HQLDemo {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFacroty();

        Session session = factory.getCurrentSession();

        try{
            //ALL THE ACTION WITH DB IN Hibernate
            //must be located in one transaction
            session.getTransaction().begin();

            //select * from employee order by EMP_NAME, EMP_NO
            String sql = "Select e from "+ Employee.class.getName() + " e " + " order by e.empName, e.empNO";
            Query<Employee> query = session.createQuery(sql);

            //execution
            List<Employee> employees = query.getResultList();
            for (Employee e: employees) System.out.println("Emp: "+ e.getEmpNO() + " - "+ e.getEmpName());

            //commit
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();

            //onFail rollback transaction
            session.getTransaction().rollback();
        }
    }
}
