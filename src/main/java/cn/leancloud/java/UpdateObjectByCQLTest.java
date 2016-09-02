package cn.leancloud.java;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

public class UpdateObjectByCQLTest extends BaseTest {
  private AVObject todoFolder;
  private String objectId;

  @Before
  public void setUp() throws AVException {
    todoFolder = new AVObject("TodoFolder");
    todoFolder.put("name", "工作");
    todoFolder.save();
    objectId = todoFolder.getObjectId();
  }

  @After
  public void tearDown() throws AVException {
    todoFolder.delete();
  }

  @Test
  public void testGetByObjectId() throws Exception {
    // 执行 CQL 语句实现更新一个 TodoFolder 对象
    AVQuery.doCloudQuery("update TodoFolder set name='家庭' where objectId=?", objectId);

    AVObject avObject = new AVQuery<>("TodoFolder").get(objectId);
    assertEquals("家庭", avObject.getString("name"));
  }
}
