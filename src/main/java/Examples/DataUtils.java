package Examples;

import Entities.Department;
import Entities.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DataUtils {
    public static Department findDepartment(Session session, String deptNo){
        String hql = "Select d from "+Department.class.getName()+" d where d.deptNo=:deptNo";   // write hql query
        Query query = session.createQuery(hql);                                                 // create query
        query.setParameter("deptNo",deptNo);
        return (Department) query.getSingleResult();                                            // execute and return
    }

    public static Long getMaxEmpId(Session session){
        String hql = "Select max(e.empID) from " + Employee.class.getName() +" e";
        Query<Number> query = session.createQuery(hql);                                         // create query
        Number value = query.getSingleResult();
        if(value == null) return 0L;                                                            // null exception
        return value.longValue();
    }

    public static Employee findEmployee(Session session, String empNo){
        String hql = "Select e from "+Employee.class.getName()+" e where e.empNO=:empNo";       // write hql
        Query<Employee> query = session.createQuery(hql);                                       // create query
        query.setParameter("empNo", empNo);
        return query.getSingleResult();
    }
}
