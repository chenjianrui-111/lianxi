package test.java.qunar;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.junit.Test;

/**
 * @author jianrui.chen
 * StringBuffer 与 StringBuilder区别:
 * 1.共同继承 AbstractStringBuilder
 * 2.StringBuffer 有 synchronized 锁多线程情况下线程安全
 *  StringBuilder 无 synchronized锁 线程速度快
 */
public class StringDemo {

    private static Logger logger = LoggerFactory.getLogger(StringDemo.class);

    @Test
    public void stringBuilderTest(){
        StringBuilder sb = new StringBuilder(6);
        sb.append("abcdef");
        //进行扩容
        sb.append("1234");
        System.out.println(sb.toString());
    }

    @Test
    public void replaceTest(){
        String str = "abc";
        String replace = str.replace('a','d');
        logger.warn("str = { } replace ={ }",str,replace);
    }

    @Test
    public void stringEqualTest(){
        String a ="abc";
        String b = null;
    }
}
