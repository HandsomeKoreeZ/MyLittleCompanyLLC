package Examples;

import Entities.Employee;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class HQLDemo3Columns {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtils.getSessionFacroty();
        Session session = sf.getCurrentSession();

        try{
            session.getTransaction().begin();

            //query
            String hql = "Select e.empID, e.empNO, e.empName from " + Employee.class.getName() + " e ";
            Query<Object[]> query = session.createQuery(hql);

            //execution
            List<Object[]> datas = query.getResultList();

            //show result
            for (Object[] e:datas) {
                System.out.println("Emp ID:" +e[0]);
                System.out.println("Emp No:" +e[1]);
                System.out.println("Emp Name:" +e[2]);
            }

            //commit data
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();

            //onfail rollback
            session.getTransaction().rollback();
        }
    }
}
