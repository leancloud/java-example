package cn.leancloud.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

public class DeleteObjectByCQLTest extends BaseTest {
  private AVObject todo;
  private String objectId;

  @Before
  public void setUp() throws AVException {
    todo = new AVObject("Todo");
    todo.put("name", "工程师周会");
    todo.save();
    objectId = todo.getObjectId();
  }

  @Test
  public void testGetByObjectId() throws Exception {
    // 执行 CQL 语句实现更新一个 Todo 对象
    AVCloudQueryResult result = AVQuery.doCloudQuery("delete from Todo where objectId=?", objectId);
    assertEquals(0, result.getCount());
    try {
      new AVQuery<>("Todo").get(objectId);
      fail();
    } catch (AVException e) {
      assertEquals("Object is not found.", e.getMessage());
    }
  }
}
