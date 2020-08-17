import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class NullTest {
    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        Float f1 = 0.36f;
        Float f2 = 0.36f;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(f1 == f2);
        System.out.println("----------------------------");
        int a = 10;
        long b = 10L;
        float c = 10.0f;
        System.out.println(a == b);
        System.out.println(a == c);
        //
        Map map = new HashMap<>();
        map.put(null, "d");
        map.put(null, null);
        // System.out.println(map.get(null));

        // Hashtable hashtable = new Hashtable();
        // hashtable.put(null, "oo");

        System.out.println("--------------------------------");
        System.out.println(BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(0.9)));

        System.out.println(0.1);
        System.out.println(1 * 0.1);
        System.out.println(2 * 0.1);
        System.out.println(3 * 0.1); // 0.30000000000000004
        System.out.println(4 * 0.1);
        System.out.println(5 * 0.1);
        System.out.println(6 * 0.1);// 0.6000000000000001
        System.out.println(7 * 0.1);// 0.7000000000000001
        System.out.println(8 * 0.1);
        System.out.println(9 * 0.1);
        System.out.println(10 * 0.1);
        System.out.println(3 * 0.1 == 0.3);// false
        System.out.println(0.3 * 1 == 0.3);// true
        System.out.println(0.1 + 0.2);
        System.out.println(0.1 + 0.2 == 0.3);// false
        short s1 = 1;
        s1 += 1; // += 会自动转型，这里如果是 s1 = s1 +1;则直接编译不通过，因为s1 为 short 类型，1为 int 类型，两者类型不匹配，不能够直接做 + 运算
    }
}
