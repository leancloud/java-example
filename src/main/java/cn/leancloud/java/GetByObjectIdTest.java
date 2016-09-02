package cn.leancloud.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

public class GetByObjectIdTest extends BaseTest {

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
  public void testGetByObjectId() throws AVException {
    AVQuery<AVObject> avQuery = new AVQuery<>("Todo");
    AVObject avObject = avQuery.get(objectId);
    assertEquals(todo.get("title"), avObject.get("title"));
  }

}
