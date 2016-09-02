package cn.leancloud.java;

import static org.junit.Assert.assertNotSame;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

public class BatchSaveTest extends BaseTest {

  private AVObject todo1;
  private AVObject todo2;

  @Before
  public void setUp() throws AVException {
    todo1 = new AVObject("Todo");
    todo1.put("title", "工程师周会");
    todo1.put("content", "每周工程师会议，周一下午2点");
    todo1.save();
    todo2 = new AVObject("Todo");
    todo2.put("title", "聚餐");
    todo2.put("content", "每月聚餐，本周五下下班后");
    todo2.save();
  }

  @After
  public void tearDown() throws AVException {
    todo1.delete();
    todo2.delete();
  }

  @Test
  public void testGetByObjectId() throws Exception {
    AVQuery<AVObject> query = new AVQuery<>("Todo");
    List<AVObject> todos = query.find();
    Date updatedAt = todos.get(0).getUpdatedAt();
    for (AVObject todo : todos) {
      todo.put("status", 1);
    }
    AVObject.saveAll(todos);
    assertNotSame(updatedAt, todos.get(0).getUpdatedAt());
  }
}
