package cn.leancloud.java;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

public class QueryArrayEqualToTest extends BaseTest {
  private AVObject todo1;
  private AVObject todo2;

  @Before
  public void setUp() throws AVException, ParseException {
    todo1 = new AVObject("Todo");
    todo1.put("title", "一定要起床");
    todo1.put("content", "定了三个闹钟，再起不来就是天意了。");
    Date reminder1 = getDateWithDateString("2015-11-11 08:10:00");
    Date reminder2 = getDateWithDateString("2015-11-11 08:20:00");
    Date reminder3 = getDateWithDateString("2015-11-11 08:30:00");
    todo1.addAllUnique("reminders", Arrays.asList(reminder1, reminder2, reminder3));
    todo1.save();

    todo2 = new AVObject("Todo");
    todo2.put("title", "做早餐");
    todo2.put("content", "如果还有时间的话。");
    Date reminder4 = getDateWithDateString("2015-11-11 08:40:00");
    Date reminder5 = getDateWithDateString("2015-11-11 08:50:00");
    todo2.addAllUnique("reminders", Arrays.asList(reminder4, reminder5));
    todo2.save();
  }

  @After
  public void tearDown() throws AVException {
    todo1.delete();
    todo2.delete();
  }

  @Test
  public void test() throws ParseException, AVException {
    Date reminder = getDateWithDateString("2015-11-11 08:30:00");
    AVQuery<AVObject> query = new AVQuery<>("Todo");
    // equalTo: 可以找出数组中包含单个值的对象
    query.whereEqualTo("reminders", reminder);
    List<AVObject> todos = query.find();
    assertContainedIn(todo1, todos);
    assertNotContainedIn(todo2, todos);
  }
}
