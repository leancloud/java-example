package cn.leancloud.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

public class SaveObjectByCQL extends BaseTest {

  private String objectId;

  @After
  public void tearDown() throws AVException {
    AVObject.createWithoutData("TodoFolder", objectId).delete();

  }

  @Test
  public void saveObjectBySQL() {
    // 执行 CQL 语句实现新增一个 TodoFolder 对象
    try {
      AVCloudQueryResult result =
          AVQuery.doCloudQuery("insert into TodoFolder(name, priority) values('工作', 1)");
      // 保存成功
      objectId = result.getResults().get(0).getObjectId();
      assertNotNull(objectId);
    } catch (Exception e) {
      // 失败的话，请检查网络环境以及 SDK 配置是否正确
      e.printStackTrace();
      fail();
    }
  }
}
