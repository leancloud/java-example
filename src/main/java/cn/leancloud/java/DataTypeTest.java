package cn.leancloud.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

public class DataTypeTest extends BaseTest {

  private AVObject object;

  @After
  public void tearDown() throws AVException {
    object.delete();
  }

  @Test
  public void testDateType() throws AVException {
    boolean bool = true;
    int number = 2015;
    String string = number + " 年度音乐排行";
    Date date = new Date();

    byte[] data = "短篇小说".getBytes();
    ArrayList<Object> arrayList = new ArrayList<>();
    arrayList.add(number);
    arrayList.add(string);
    HashMap<Object, Object> hashMap = new HashMap<>();
    hashMap.put("数字", number);
    hashMap.put("字符串", string);

    object = new AVObject("DataTypes");
    object.put("testBoolean", bool);
    object.put("testInteger", number);
    object.put("testDate", date);
    object.put("testData", data);
    object.put("testArrayList", arrayList);
    object.put("testHashMap", hashMap);
    object.save();
  }

}
