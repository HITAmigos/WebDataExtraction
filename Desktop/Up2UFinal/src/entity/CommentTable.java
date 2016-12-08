package entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.assistantEntity.Comment;
import entity.assistantEntity.HibernateUtil;
import entity.assistantEntity.SearchRecord;

public class CommentTable {
  public static boolean insert(Comment comment) {
    boolean result = true;
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    try {
      tran = session.beginTransaction();
      session.save(comment);
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

  public static List<Comment> getCommentSet() {
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    List<Comment> comments = null;
    try {
      tran = session.beginTransaction();
      comments = session.createQuery("FROM Comment").list();
    } catch (HibernateException e) {
      if (tran != null) {
        tran.rollback();
      }
      e.printStackTrace();
    } finally {
      HibernateUtil.closeSession();
    }
    return comments;
  }

  public static List<Comment> getUserComment(String username) {
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    List<Comment> comments = null;
    List<Comment> userComments = new ArrayList<Comment>();
    try {
      tran = session.beginTransaction();
      comments = session.createQuery("FROM Comment").list();
      for (Comment comment : comments) {
        if (comment.getUsername().equals(username)) {
          userComments.add(comment);
        }
      }
    } catch (HibernateException e) {
      if (tran != null) {
        tran.rollback();
      }
      e.printStackTrace();
    } finally {
      HibernateUtil.closeSession();
    }
    return userComments;
  }
}
