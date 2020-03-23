package Examples;

import Entities.Department;
import Entities.Employee;
import Utils.DataUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class PersistDemo {

    public static void main(String[] args) {
        SessionFactory sf = Utils.HibernateUtils.getSessionFacroty();
        Session session = sf.getCurrentSession();

        Department department = null;
        Employee emp = null;

        try{
            session.getTransaction().begin();                           // start transaction

            Long maxEmpID = DataUtils.getMaxEmpId(session);
            Long empID = maxEmpID+1;                                    //set ID new employee

            department = DataUtils.findDepartment(session,"D30");   // get persistent object

            emp = new Employee();                                       // Create transient object
            emp.setEmpID(empID);
            emp.setEmpNO("E"+empID);
            emp.setEmpName("Johnny Mnemonic");
            emp.setJob("Chicago bull");
            emp.setSalary(1000f);
            emp.setManager(null);
            emp.setHireDate(new Date());
            emp.setDepartment(department);

            session.persist(emp);                                       // set to emp "Pessistent" status

            session.getTransaction().commit();                          // execute and insert data
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();                        //onafail rollback
        }

        System.out.println("emp No:"+emp.getEmpNO());
    }
}
