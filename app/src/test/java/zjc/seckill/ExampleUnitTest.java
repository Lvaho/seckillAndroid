package zjc.seckill;

import android.os.SystemClock;
import android.util.Log;

import org.junit.Test;

import zjc.seckill.util.MD5Util;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test() {
        MD5Util md5Util=new MD5Util();
        String  password="123456";
        String salt="1a2b3c4d";
        String str = "" + salt.charAt(0) + salt.charAt(2) + password + salt.charAt(5) + salt.charAt(4);
        String md5Password= md5Util.md5(str);
        System.out.println(md5Password);
    }
}