package entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.assistantEntity.BeijingTime;
import entity.assistantEntity.HibernateUtil;
import entity.assistantEntity.SearchRecord;

public class SearchRecordTable {
  public static boolean insert(SearchRecord searchRecord) {
    boolean result = true;
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    try {
      tran = session.beginTransaction();
      session.save(searchRecord);
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

  public static List<SearchRecord> getSearchRecordSet() {
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    List<SearchRecord> searchRecords = null;
    try {
      tran = session.beginTransaction();
      searchRecords = session.createQuery("FROM SearchRecord").list();
    } catch (HibernateException e) {
      if (tran != null) {
        tran.rollback();
      }
      e.printStackTrace();
    } finally {
      HibernateUtil.closeSession();
    }
    return searchRecords;
  }

  public static List<SearchRecord> getUserSearchRecord(String username) {
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    List<SearchRecord> searchRecords = null;
    List<SearchRecord> userSearchRecords = new ArrayList<SearchRecord>();
    try {
      tran = session.beginTransaction();
      searchRecords = session.createQuery("FROM SearchRecord").list();
      for (SearchRecord searchRecord : searchRecords) {
        if (searchRecord.getUsername().equals(username)) {
          userSearchRecords.add(searchRecord);
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
    return userSearchRecords;
  }

  public static boolean UrlIsExist(String url) {
    boolean result = false;
    List<SearchRecord> SearchRecordSet = getSearchRecordSet();
    for (int i = 0; i < SearchRecordSet.size(); i++) {
      if (SearchRecordSet.get(i).getLink().equals(url)) {
        result = true;
        break;
      }
    }
    return result;
  }

  public static SearchRecord getSearchRecord(String tablename) {
    List<SearchRecord> SearchRecordSet = getSearchRecordSet();
    SearchRecord searchRecord = null;
    for (int i = 0; i < SearchRecordSet.size(); i++) {
      if (SearchRecordSet.get(i).getTablename().equals(tablename)) {
        searchRecord = SearchRecordSet.get(i);
        break;
      }
    }
    return searchRecord;
  }

  public static int getLastId() {
    int id = 0;
    List<SearchRecord> SearchRecordSet = getSearchRecordSet();
    if (!SearchRecordSet.isEmpty()) {
      id = SearchRecordSet.get(SearchRecordSet.size() - 1).getId();
    }
    return id;
  }

  public static List<SearchRecord> getLastestRecord(int dateNum) {
    List<SearchRecord> SearchRecordSet = getSearchRecordSet();
    List<SearchRecord> Targets = new ArrayList<SearchRecord>();
    Date currentDate = BeijingTime.getWebsiteDatetime();
    Date recordDate = null;
    int days = 0; // 记录与当前相差天数
    for (int i = 0; i < SearchRecordSet.size(); i++) {
      recordDate = SearchRecordSet.get(i).getDate();
      days = (int) (currentDate.getTime() / (1000 * 60 * 60 * 24)
          - recordDate.getTime() / (1000 * 60 * 60 * 24));
      if (days <= dateNum) {
        Targets.add(SearchRecordSet.get(i));
      }
    }
    for (int i = 0; i < Targets.size(); i++) {
      System.out.println(Targets.get(i).getTablename());
    }
    return Targets;
  }

  public static boolean DeleteSearchRecord(SearchRecord searchRecord) {
    boolean result = true;
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    try {
      tran = session.beginTransaction();
      session.delete(searchRecord);
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

  public static boolean ClearAllRecords(String username) {
    boolean result = true;
    List<SearchRecord> userSearchRecords = getUserSearchRecord(username);
    for (int i = 0; i < userSearchRecords.size(); i++) {
      if (!DeleteSearchRecord(userSearchRecords.get(i))) {
        result = false;
        break;
      }
    }
    return result;
  }

  public static void main(String args[]) {
    SearchRecordTable.getLastestRecord(3);
  }

}
