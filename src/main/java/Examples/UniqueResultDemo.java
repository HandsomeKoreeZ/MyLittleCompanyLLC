package Examples;

import Entities.Department;
import Entities.Employee;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Set;

public class UniqueResultDemo {

    public static Department getDepartment(Session session, String deptNo){
        String hql = "Select d from " + Department.class.getName() + " d where d.deptNo=:deptNo";
        Query<Department> query = session.createQuery(hql);
        query.setParameter("deptNo",deptNo);
        return (Department) query.getSingleResult();
    }

    public static Employee getEmployee(Session session, Long empID){
        String hql = "Select e from "+Employee.class.getName()+" e where e.empID=:empID";   // make sql string
        Query<Employee> query = session.createQuery(hql);                                   // make query
        query.setParameter("empID",empID);                                               // set parameters
        return (Employee) query.getSingleResult();
    }

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtils.getSessionFacroty();
        Session session = sf.getCurrentSession();

        try{
            session.getTransaction().begin();                                               // begin transaction
            Department dept = getDepartment(session,"D30");                         // recieve department
            Set<Employee> emps = dept.getEmployees();
            System.out.println(dept.getLocation());

            Employee emp = getEmployee(session,7935L);
            System.out.println("EMP:" + emp.getEmpNO() +" - "+emp.getEmpName());


            session.getTransaction().commit();                                              // commit transaction
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();                                            // onfail rollback
        }
    }
}
