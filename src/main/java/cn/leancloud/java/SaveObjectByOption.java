package cn.leancloud.java;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVSaveOption;
import com.avos.avoscloud.LogUtil.log;

public class SaveObjectByOption extends BaseTest {

  private AVObject wiki;
  private Date oriUpdatedAt;

  @Before
  public void setUp() throws AVException {
    wiki = new AVObject("Wiki");
    wiki.put("content", "Hello World!");
    wiki.put("version", 3);
    wiki.save();
    oriUpdatedAt = wiki.getUpdatedAt();
  }

  @After
  public void tearDown() throws AVException {
    wiki.delete();
  }

  @Test
  public void saveObject() {
    // 获取 version 值
    int version = wiki.getInt("version");
    AVQuery<AVObject> query = new AVQuery<>("Wiki");
    query.whereEqualTo("version", version);
    try {
      wiki.put("content", "Hello Java!");
      wiki.increment("version");
      wiki.save(new AVSaveOption().query(query));
      assertNotSame(oriUpdatedAt, wiki.getUpdatedAt());
    } catch (AVException e) {
      if (e.getCode() == 305) {
        log.d("无法保存修改，wiki 已被他人更新。");
      } else {
        e.printStackTrace();
        fail();
      }
    }
  }

  @Test
  public void saveObject_versionChanged() {
    // 获取 version 值
    int version = wiki.getInt("version");
    AVQuery<AVObject> query = new AVQuery<>("Wiki");
    query.whereEqualTo("version", version - 1); // 模拟版本号过期
    try {
      wiki.put("content", "Hello Exception!");
      wiki.increment("version");
      wiki.save(new AVSaveOption().query(query));
      fail();
    } catch (AVException e) {
      assertEquals(305, e.getCode());
      if (e.getCode() == 305) {
        log.d("无法保存修改，wiki 已被他人更新。");
      } else {
        e.printStackTrace();
        fail();
      }
    }
  }
}
