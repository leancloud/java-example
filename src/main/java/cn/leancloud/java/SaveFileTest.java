package cn.leancloud.java;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;

public class SaveFileTest extends BaseTest {

  private AVFile file;

  @After
  public void tearDown() throws AVException {
    file.delete();
  }

  @Test
  public void test() throws AVException {
    file = new AVFile("LeanCloud.png",
        "https://leancloud.cn/images/static/press/Logo%20-%20Blue%20Padding.png",
        new HashMap<String, Object>());
    file.save();
    assertNotNull(file.getObjectId());
  }

}
