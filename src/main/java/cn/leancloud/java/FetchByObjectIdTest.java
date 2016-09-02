package cn.leancloud.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class FetchByObjectIdTest extends BaseTest {

  private AVObject todo;
  private String objectId;

  @Before
  public void setUp() throws AVException {
    todo = new AVObject("Todo");
    todo.put("title", "testTitle");
    todo.put("content", "testContent");
    todo.save();
    objectId = todo.getObjectId();
  }

  @After
  public void tearDown() throws AVException {
    todo.delete();
  }

  @Test
  public void testFetchByObjectId() throws AVException {
    // 第一参数是 className,第二个参数是 objectId
    AVObject object = AVObject.createWithoutData("Todo", objectId);
    object.fetch();
    String title = object.getString("title");// 读取 title
    String content = object.getString("content");// 读取 content
    assertEquals(todo.get("title"), title);
    assertEquals(todo.get("content"), content);
  }

}
