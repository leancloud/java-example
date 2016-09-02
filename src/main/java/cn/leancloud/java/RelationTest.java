package cn.leancloud.java;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.After;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

public class RelationTest extends BaseTest {

  private AVObject todoFolder;
  private AVObject todo1;
  private AVObject todo2;
  private AVObject todo3;

  @After
  public void tearDown() throws AVException {
    todoFolder.delete();
    todo1.delete();
    todo2.delete();
    todo3.delete();
  }

  @Test
  public void test() throws AVException {
    todoFolder = new AVObject("TodoFolder");// 构建对象
    todoFolder.put("name", "工作");
    todoFolder.put("priority", 1);

    todo1 = new AVObject("Todo");
    todo1.put("title", "工程师周会");
    todo1.put("content", "每周工程师会议，周一下午2点");
    todo1.put("location", "会议室");

    todo2 = new AVObject("Todo");
    todo2.put("title", "维护文档");
    todo2.put("content", "每天 16：00 到 18：00 定期维护文档");
    todo2.put("location", "当前工位");

    todo3 = new AVObject("Todo");
    todo3.put("title", "发布 SDK");
    todo3.put("content", "每周一下午 15：00");
    todo3.put("location", "SA 工位");

    AVObject.saveAll(Arrays.asList(todo1, todo2, todo3));
    AVRelation<AVObject> relation = todoFolder.getRelation("containedTodos");// 新建一个
                                                                             // AVRelation
    relation.add(todo1);
    relation.add(todo2);
    relation.add(todo3);
    // 上述 3 行代码表示 relation 关联了 3 个 Todo 对象
    todoFolder.save();

    assertNotNull(todoFolder.getObjectId());
  }

}
