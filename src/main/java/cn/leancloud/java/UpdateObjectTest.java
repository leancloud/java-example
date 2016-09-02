package cn.leancloud.java;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class UpdateObjectTest extends BaseTest {
  private AVObject todo;
  private String objectId;
  private Date updatedAt;

  @Before
  public void setUp() throws AVException {
    todo = new AVObject("Todo");
    todo.put("title", "工程师周会");
    todo.put("content", "每周工程师会议，周一下午2点");
    todo.save();
    objectId = todo.getObjectId();
    updatedAt = todo.getUpdatedAt();
  }

  @After
  public void tearDown() throws AVException {
    todo.delete();
  }

  @Test
  public void testGetByObjectId() throws AVException {
    // 第一参数是 className,第二个参数是 objectId
    AVObject todo = AVObject.createWithoutData("Todo", objectId);
    // 修改 content
    todo.put("content", "每周工程师会议，本周改为周三下午3点半。");
    // 保存到云端
    todo.save();
    assertNotSame(updatedAt, todo.getUpdatedAt());
  }
}
