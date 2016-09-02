package cn.leancloud.java;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class SaveObjectGetObjectIdTest extends BaseTest {
  private AVObject todo;

  @After
  public void tearDown() throws AVException {
    todo.delete();
  }

  @Test
  public void saveObject() throws AVException {
    todo = new AVObject("Todo");
    todo.put("title", "工程师周会");
    todo.put("content", "每周工程师会议，周一下午2点");
    todo.save();
    // 保存成功之后，objectId 会自动从服务端加载到本地
    String objectId = todo.getObjectId();
    assertNotNull(objectId);
    assertNotNull(todo.getCreatedAt());
  }
}
