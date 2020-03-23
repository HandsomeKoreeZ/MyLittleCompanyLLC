package Examples;

import Entities.Employee;
import Entities.TimeKeeper;
import Utils.DataUtils;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveTransientDemo {
    private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private static TimeKeeper persists_Transient(Session session, Employee emp){
        TimeKeeper tk2 = new TimeKeeper();
        tk2.setEmployee(emp);
        tk2.setIO(TimeKeeper.IN);
        tk2.setDateTime(new Date());

        //now tk2 are state Transient
        System.out.println("tk2 is persistent? "+ session.contains(tk2));

        // save() very similar to persist()
        // save() returns ID,  persist() returns void
        // no action DB
        System.out.println("======CALL SAVE tk======");
        Serializable id = session.save(tk2);
        System.out.println("id = "+id);
        System.out.println("tk2 id = "+ tk2.getTimekeeperId());

        //not tk are state Persistent
        System.out.println("tk2 is persistent? "+ session.contains(tk2));

        //revew
        System.out.println("id = "+tk2.getTimekeeperId());
        System.out.println("IO = "+tk2.getIO());
        System.out.println("dateTime = "+df.format(tk2.getDateTime()));
        //DB action
        //session.flush();
        return tk2;
    }

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtils.getSessionFacroty();
        Session session = sf.getCurrentSession();
        Employee emp = null;
        try{
            session.getTransaction().begin();
            emp = DataUtils.findEmployee(session,"E7788");
            persists_Transient(session,emp);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
