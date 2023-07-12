package test.java.qunar;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author jianrui.chen
 */
public class NumberDemo {

    private static Logger logger = LoggerFactory.getLogger(NumberDemo.class);
    @Test
    public void intFlow(){
        long milLsOfYears = 5000 * 365 * 24 * 24;
        //数值溢出问题
        logger.warn("milLsOfYears = {}",milLsOfYears);
    }

    @Test
    public void integerNpeTest(){
        Integer a = null;
        //java.lang.NullPointerException
        int b =a.intValue();
    }

    @Test
    public void lostAccurate(){
        double a =0.58;
        //精度丢失问题
        System.out.println(new BigDecimal(0.58));
        long b = (long) (a * 100);
        logger.warn("b = { }" , b);
    }

    @Test
    public void bigDecimalOperationTest(){
        BigDecimal a = new BigDecimal(0.58);
        long b = a.multiply(new BigDecimal(100L)).longValue();
        logger.warn(" b = { }" + b);
    }

    @Test
    public void bigDecimalConstruct(){
        BigDecimal a = BigDecimal.valueOf(0.580);
        BigDecimal b = new BigDecimal("0.580");
        System.out.println(a);
        System.out.println(b);
        logger.warn("a = b { }",a.equals(b));
        System.out.println(a.compareTo(b));
    }

    @Test
    public void divideTest(){
        BigDecimal a = BigDecimal.valueOf(1L);
        BigDecimal b = BigDecimal.valueOf(3L);

        logger.warn("{ }",a.divide(b, MathContext.DECIMAL64));
    }
}
