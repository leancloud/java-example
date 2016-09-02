package cn.leancloud.java;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class IncrementTest extends BaseTest {

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
  public void testIncrement() throws AVException {
    final AVObject theTodo = AVObject.createWithoutData("Todo", objectId);
    theTodo.put("views", 0);// 初始值为 0
    theTodo.save();
    // 原子增加查看的次数
    theTodo.increment("views");
    theTodo.setFetchWhenSave(true);
    theTodo.save();
    assertEquals(1, theTodo.getInt("views"));
    // 也可以使用 incrementKey:byAmount: 来给 Number 类型字段累加一个特定数值。
    theTodo.increment("views", 5);
    theTodo.save();
    // saveInBackground 调用之后，如果成功的话，对象的计数器字段是当前系统最新值。
    assertEquals(6, theTodo.getInt("views"));
  }

}
