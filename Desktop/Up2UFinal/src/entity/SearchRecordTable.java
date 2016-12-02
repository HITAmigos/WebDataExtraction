package entity;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    List<SearchRecord> SearchRecords = null;
    try {
      tran = session.beginTransaction();
      SearchRecords = session.createQuery("FROM SearchRecord").list();
    } catch (HibernateException e) {
      if (tran != null) {
        tran.rollback();
      }
      e.printStackTrace();
    } finally {
      HibernateUtil.closeSession();
    }
    return SearchRecords;
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
}
