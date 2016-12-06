package entity;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.assistantEntity.HibernateUtil;
import entity.assistantEntity.User;

public class UserTable {
  public static boolean Regist(User user){
    boolean result = true;
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    try {
      tran = session.beginTransaction();
      session.save(user);
      session.getTransaction().commit();
    } catch (HibernateException e) {
      result = false;
      if (tran != null) {
        tran.rollback();
      }
      e.printStackTrace();
    } finally {
      HibernateUtil.closeSession();
    }
    return result;
  }
  
  public static List<User> getUserSet() {
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    List<User> Users = null;
    try {
      tran = session.beginTransaction();
      Users = session.createQuery("FROM User").list();
    } catch (HibernateException e) {
      if (tran != null) {
        tran.rollback();
      }
      e.printStackTrace();
    } finally {
      HibernateUtil.closeSession();
    }
    return Users;
  }
  
  public static User getUser(String username){
    List<User> UserSet = getUserSet();
    User user = null;
    for(int i = 0 ; i < UserSet.size() ;i++){
      if(UserSet.get(i).getUsername().equals(username)){
        user = UserSet.get(i);
        break;
      }
    }
    return user;
  }
}
