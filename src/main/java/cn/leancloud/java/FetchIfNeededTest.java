package cn.leancloud.java;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class FetchIfNeededTest extends BaseTest {
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
    // 假如已知了 objectId 可以用如下的方式构建一个 AVObject
    AVObject object = AVObject.createWithoutData("Todo", objectId);
    // 然后调用刷新的方法，将数据从服务端拉到本地
    object.fetchIfNeeded();
    String title = object.getString("title");// 读取 title
    String content = object.getString("content");// 读取 content
    assertEquals(todo.get("title"), title);
    assertEquals(todo.get("content"), content);
  }

}
