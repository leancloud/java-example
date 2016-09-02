package cn.leancloud.java;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

public class AccessPropertiesTest extends BaseTest {

  private AVObject todo;
  private String objectId;

  @Before
  public void setUp() throws AVException {
    todo = new AVObject("Todo");
    todo.put("title", "testTitle");
    todo.put("content", "testContent");
    todo.put("location", "会议室");
    todo.put("priority", 1);
    todo.save();
    objectId = todo.getObjectId();
  }

  @After
  public void tearDown() throws AVException {
    todo.delete();
  }

  @Test
  public void testAccessProperties() throws AVException {
    AVQuery<AVObject> avQuery = new AVQuery<>("Todo");
    AVObject avObject = avQuery.get(objectId);

    int priority = avObject.getInt("priority");
    String location = avObject.getString("location");
    String title = avObject.getString("title");
    String content = avObject.getString("content");

    // 获取三个特殊属性
    String objectId = avObject.getObjectId();
    Date updatedAt = avObject.getUpdatedAt();
    Date createdAt = avObject.getCreatedAt();

    assertEquals(todo.get("priority"), priority);
    assertEquals(todo.get("location"), location);
    assertEquals(todo.get("title"), title);
    assertEquals(todo.get("content"), content);
    assertEquals(todo.getObjectId(), objectId);
    assertNotNull(updatedAt);
    assertNotNull(createdAt);
  }

}
