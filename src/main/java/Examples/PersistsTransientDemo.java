package Examples;

import Entities.Department;
import Entities.Employee;
import Entities.TimeKeeper;
import Utils.DataUtils;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersistsTransientDemo {
    //atributes
    private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    //methods
    private static TimeKeeper persist_Transient(Session session, Employee emp){

        //configuring of timekeeeperID
        TimeKeeper tk1 = new TimeKeeper();

        tk1.setEmployee(emp);
        tk1.setIO(TimeKeeper.IN);
        tk1.setDateTime(new Date());

        //now tk1 is transient object
        System.out.println("tk1 persistent? " +session.contains(tk1));
        System.out.println("===========CALL PERSIST(tk)===========");

        //Hibernate assign value of to id of 'tk1'
        //No action to DB
        session.persist(tk1);
        System.out.println(tk1.getTimekeeperId());
        System.out.println("tk1 persistent? " +session.contains(tk1));

        //call flush
        session.flush();

        String timekeeperID = tk1.getTimekeeperId();
        System.out.println("timekeeperID = "+timekeeperID);
        System.out.println("IO = "+tk1.getIO());
        System.out.println("dateTime = "+df.format(tk1.getDateTime()));
        return tk1;
    }

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtils.getSessionFacroty();
        Session session = sf.getCurrentSession();
        Employee emp = null;

        try{
            session.getTransaction().begin();
            emp = DataUtils.findEmployee(session,"E7499");
            persist_Transient(session,emp);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
