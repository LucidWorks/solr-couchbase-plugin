package com.lucidworks.couchbase;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.couchbase.capi.CAPIBehavior;

public class SolrCAPIBehaviour implements CAPIBehavior {

  public Map<String, Object> welcome() {
    Map<String,Object> responseMap = new HashMap<String, Object>();
    responseMap.put("welcome", "solr-couchbase-plugin");
    return responseMap;
  }
  
  public String databaseExists(String database) {
      if("default".equals(database)) {
          return null;
      }
      return "missing";
  }
  
  public Map<String, Object> getDatabaseDetails(String database) {
      String doesNotExistReason = databaseExists(database);
      if(doesNotExistReason == null) {
          Map<String, Object> responseMap = new HashMap<String, Object>();
          responseMap.put("db_name", database);
          return responseMap;
      }
      return null;
  }
  
  public boolean createDatabase(String database) {
      // FIXME add test
      return false;
  }
  
  public boolean deleteDatabase(String database) {
      // FIXME add test
      return false;
  }
  
  public boolean ensureFullCommit(String database) {
      if("default".equals(database)) {
          return true;
      }
      return false;
  }
  
  public Map<String, Object> revsDiff(String database,
          Map<String, Object> revsMap) {
      if("default".equals(database)) {
          Map<String, Object> responseMap = new HashMap<String, Object>();
          for (Entry<String, Object> entry : revsMap.entrySet()) {
              String id = entry.getKey();
              Object revs = entry.getValue();
              Map<String, Object> rev = new HashMap<String, Object>();
              rev.put("missing", revs);
              responseMap.put(id, rev);
          }
          return responseMap;
      }
      return null;
  }
  
  public List<Object> bulkDocs(String database, List<Map<String, Object>> docs) {
  
      if("default".equals(database)) {
  
          List<Object> result = new ArrayList<Object>();
  
          for (Map<String, Object> doc : docs) {
  
              String id = (String)doc.get("_id");
              String rev = (String)doc.get("_rev");
  
              Map<String, Object> itemResponse = new HashMap<String, Object>();
              itemResponse.put("id", id);
              itemResponse.put("rev", rev);
              result.add(itemResponse);
          }
  
          //TODO index documents to Solr here
          return result;
      }
      return null;
  }
  
  public Map<String, Object> getDocument(String database, String docId) {
      if("default".equals(database)) {
          if("docid".equals(docId)) {
              Map<String, Object> document = new HashMap<String, Object>();
              document.put("_id", "docid");
              document.put("_rev", "1-abc");
              document.put("value", "test");
              return document;
          }
      }
      return null;
  }
  
  public Map<String, Object> getLocalDocument(String database, String docId) {
      if("default".equals(database)) {
          if("_local/docid".equals(docId)) {
              Map<String, Object> document = new HashMap<String, Object>();
              document.put("_id", "_local/docid");
              document.put("_rev", "1-abc");
              document.put("value", "test");
              return document;
          } else if("_local/441-0921e80de6603d60b1d553bb7c253def/beer-sample/beer-sample".equals(docId)) {
              Map<String, Object> historyItem = new HashMap<String, Object>();
              historyItem.put("session_id", "121f9c416336108dd0b891a054f9b878");
              historyItem.put("start_time", "Thu, 30 Aug 2012 18:22:02 GMT");
              historyItem.put("end_time", "Thu, 30 Aug 2012 18:22:02 GMT");
              historyItem.put("start_last_seq", 0);
              historyItem.put("end_last_seq", 10);
              historyItem.put("recorded_seq", 10);
              historyItem.put("docs_checked", 10);
              historyItem.put("docs_written", 10);
  
              List<Object> history = new ArrayList<Object>();
              history.add(historyItem);
  
              Map<String, Object> document = new HashMap<String, Object>();
              document.put("session_id", "121f9c416336108dd0b891a054f9b878");
              document.put("source_last_seq", 10);
              document.put("start_time", "Thu, 30 Aug 2012 18:22:02 GMT");
              document.put("end_time", "Thu, 30 Aug 2012 18:22:02 GMT");
              document.put("docs_checked", 10);
              document.put("docs_written", 10);
              document.put("history", history);
              return document;
          }
      }
      return null;
  }
  
  public String storeDocument(String database, String docId,
          Map<String, Object> document) {
      // FIXME add test
      return null;
  }
  
  public String storeLocalDocument(String database, String docId,
          Map<String, Object> document) {
      // FIXME add test
      return null;
  }
  
  public InputStream getAttachment(String database, String docId,
          String attachmentName) {
      // FIXME add test
      return null;
  }
  
  public String storeAttachment(String database, String docId,
          String attachmentName, String contentType, InputStream input) {
      // FIXME add test
      return null;
  }
  
  public InputStream getLocalAttachment(String databsae, String docId,
          String attachmentName) {
      // FIXME add test
      return null;
  }
  
  public String storeLocalAttachment(String database, String docId,
          String attachmentName, String contentType, InputStream input) {
      // FIXME add test
      return null;
  }
  
  @Override
  public Map<String, Object> getStats() {
      return new HashMap<String, Object>();
  }
  
  public String getVBucketUUID(String pool, String bucket, int vbucket) {
      if("default".equals(bucket)) {
          return "00000000000000000000000000000000";
      }
      return null;
  }
  
  public String getBucketUUID(String pool, String bucket) {
      if("default".equals(bucket)) {
          return "00000000000000000000000000000000";
      }
      return null;
  }

}
