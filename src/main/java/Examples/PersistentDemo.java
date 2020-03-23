package Examples;

import Entities.Department;
import Utils.DataUtils;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersistentDemo {

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtils.getSessionFacroty();
        Session session = sf.getCurrentSession();
        Department dept = null;

        try{
            session.getTransaction().begin();

            System.out.println("- Finding Department deptNo = D10");

            //persist object
            dept = DataUtils.findDepartment(session,"D10");

            //changing element on persist object
            dept.setLocation("NEW YORK");
            System.out.println(" - Location = "+dept.getLocation());

            //flush - force the changes on DB
            // but it is unnecessary
            System.out.println(" - calling flush");
            session.flush();

            // commit transaction
            session.getTransaction().commit();

        } catch (Exception e){
            e.printStackTrace();
            //onfail rollback
            session.getTransaction().rollback();
        }
    }
}
