package test.java.qunar;

import com.google.common.base.Strings;
import org.junit.Test;

/**
 * @author jianrui.chen
 */
public class StringsDemo {
    @Test
    public void nullAndEmpty(){
        String a = null;
        System.out.println(Strings.isNullOrEmpty(a));
        System.out.println(Strings.nullToEmpty(a));
    }

    @Test
    public void commonPrefixAndSuffix(){
        String a = "12abc";
        String b = "123abc";
        System.out.println(Strings.commonPrefix(a,b));
        System.out.println(Strings.commonSuffix(a,b));
    }

    @Test
    public void padding(){
        String a = "1";

        System.out.println(Strings.padStart(a,5,'*'));
        System.out.println(Strings.padEnd(a,5,'*'));
        System.out.println(Strings.repeat(a,10));
    }
}
