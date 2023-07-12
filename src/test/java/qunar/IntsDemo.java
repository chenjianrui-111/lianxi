package test.java.qunar;

import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author jianrui.chen
 */
public class IntsDemo {
    @Test
    public void compare(){
        int a = 2000000000;
        int b = a *  -1;

        boolean greater = (a - b > 0);
        System.out.println(greater);

        System.out.println(Ints.compare(a,b));
    }

    @Test
    public void contain(){
        System.out.println(Ints.contains(new int[]{1,2,3},1));
    }

    @Test
    public void maxAndMin(){
        System.out.println(Ints.max(1,2,3));
        System.out.println(Ints.min(1,2,3));
    }

    @Test
    public void asList(){
        int[] array =new int[]{1,2,3};
        System.out.println(Arrays.asList(array));
        System.out.println(Ints.asList(array));
        System.out.println(Arrays.toString(Arrays.asList(array).get(0)));

        //默认继承AbstractList 没有重写add()扩容会抛出异常
        List<Integer> list = Ints.asList(array);
        list.add(4);
    }
}
