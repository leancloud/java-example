package cn.leancloud.java;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.internal.impl.JavaRequestSignImplementation;

public class BaseTest {

  @BeforeClass
  public static void setUpBeforeClass() {
    AVOSCloud.setDebugLogEnabled(true);
    AVOSCloud.initialize("uSCnF87cubg6K5OSfF2XY3mk-gzGzoHsz", "jEnIbqW7CYDNpMwkCwi09all",
        "DCnIETVC0vKAKQD7tlVQkPIo");
    JavaRequestSignImplementation.instance().setUseMasterKey(true);
  }

  protected void assertNotContainedIn(AVObject object, List<AVObject> objects) {
    for (AVObject obj : objects) {
      if (obj.equals(object)) {
        fail();
      }
    }
    assertTrue(true);
  }

  protected void assertContainedIn(AVObject object, List<AVObject> objects) {
    for (AVObject obj : objects) {
      if (obj.equals(object)) {
        assertTrue(true);
        return;
      }
    }
    fail();
  }

  protected Date getDateWithDateString(String dateString) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = dateFormat.parse(dateString);
    return date;
  }

}
