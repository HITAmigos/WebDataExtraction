package entity.assistantEntity;

import org.hibernate.*;
import org.hibernate.cfg.*; 

public class HibernateUtil {

  public static final SessionFactory sessionFactory;
  public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

  static {
    try {
      sessionFactory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static Session currentSession() throws HibernateException {
    Session s = (Session) session.get();
    if (s == null) {
      s = sessionFactory.openSession();
      session.set(s);
    }
    return s;
  }

  public static void closeSession() throws HibernateException {
    Session s = (Session) session.get();
    if (s != null) {
      s.close();
    }
    session.set(null);
  }
}
