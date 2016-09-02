package cn.leancloud.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class FetchWithKeysTest extends BaseTest {
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
  public void testFetchByObjectId() throws AVException {
    // 假如已知了 objectId 可以用如下的方式构建一个 AVObject
    AVObject avObject = AVObject.createWithoutData("Todo", objectId);
    String keys = "priority,location";// 指定刷新的 key 字符串
    // 然后调用刷新的方法，将数据从服务端拉到本地
    avObject.fetch(keys);
    // avObject 的 location 和 content 属性的值就是与服务端一致的
    int priority = avObject.getInt("priority");
    String location = avObject.getString("location");
    assertEquals(todo.get("priority"), priority);
    assertEquals(todo.get("location"), location);
    assertNull(avObject.get("title"));
  }
}
