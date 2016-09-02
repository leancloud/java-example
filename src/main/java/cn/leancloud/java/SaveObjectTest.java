package cn.leancloud.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class SaveObjectTest extends BaseTest {

  private AVObject todo;

  @After
  public void tearDown() throws AVException {
    todo.delete();
  }

  @Test
  public void saveObject() {
    todo = new AVObject("Todo");
    todo.put("title", "工程师周会");
    todo.put("content", "每周工程师会议，周一下午2点");
    try {
      todo.save();
      // 保存成功
      assertNotNull(todo.getObjectId());
      assertNotNull(todo.getCreatedAt());
    } catch (AVException e) {
      // 失败的话，请检查网络环境以及 SDK 配置是否正确
      e.printStackTrace();
      fail();
    }
  }

}
