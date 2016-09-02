package cn.leancloud.java;

import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class PointerTest extends BaseTest {
  private AVObject todoFolder;
  private String objectId;

  @Before
  public void setUp() throws AVException {
    todoFolder = new AVObject("TodoFolder");
    todoFolder.put("name", "我的游戏");
    todoFolder.save();
    objectId = todoFolder.getObjectId();
  }

  @Test
  public void test() throws AVException {
    AVObject comment = new AVObject("Comment");// 构建 Comment 对象
    comment.put("like", 1);// 如果点了赞就是 1，而点了不喜欢则为 -1，没有做任何操作就是默认的 0
    comment.put("content", "这个太赞了！楼主，我也要这些游戏，咱们团购么？");// 留言的内容

    // 假设已知了被分享的该 TodoFolder 的 objectId 是 5590cdfde4b00f7adb5860c8
    comment.put("targetTodoFolder", AVObject.createWithoutData("TodoFolder", objectId));
    // 以上代码就是的执行结果就会在 comment 对象上有一个名为 targetTodoFolder 属性，它是一个 Pointer
    // 类型，指向 objectId 为 5590cdfde4b00f7adb5860c8 的 TodoFolder
    comment.save();
  }
}
