package cn.leancloud.java;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

public class QueryWhereExistsTest extends BaseTest {

  private AVObject todo1, todo2;

  @Before
  public void setUp() throws AVException {
    // 存储一个带有图片的 Todo 到 LeanCloud 云端
    AVFile aTodoAttachmentImage =
        new AVFile("test.jpg", "http://www.zgjm.org/uploads/allimg/150812/1_150812103912_1.jpg",
            new HashMap<String, Object>());
    todo1 = new AVObject("Todo");
    todo1.put("images", aTodoAttachmentImage);
    todo1.put("content", "记得买过年回家的火车票！！！");
    todo1.save();

    todo2 = new AVObject("Todo");
    todo2.put("title", "工程师周会");
    todo2.put("content", "每周工程师会议，周一下午2点");
    todo2.save();
  }

  @Test
  public void test() {
    // 使用非空值查询获取有图片的 Todo
    AVQuery<AVObject> query = new AVQuery<>("Todo");
    query.whereExists("images");
    query.findInBackground(new FindCallback<AVObject>() {
      @Override
      public void done(List<AVObject> list, AVException e) {
        // list 返回的就是有图片的 Todo 集合
      }
    });

    // 使用空值查询获取没有图片的 Todo
    query.whereDoesNotExist("images");
  }

}
