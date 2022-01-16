package zjc.seckill;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import zjc.seckill.util.MD5Util;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MD5Util md5Util=new MD5Util();
        String  password="123456";
        String salt="1a2b3c4d";
        String str = "" + salt.charAt(0) + salt.charAt(2) + password + salt.charAt(5) + salt.charAt(4);
        String md5Password= md5Util.md5(str);
        Log.i("zjc",md5Password);
        assertEquals("zjc.seckill", appContext.getPackageName());
    }
}