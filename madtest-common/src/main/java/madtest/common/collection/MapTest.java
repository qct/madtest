package madtest.common.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Created by alex on 2016/10/24.
 */
public class MapTest {

    public static void main(String[] args) {
        MapTest.noOrder();
        MapTest.hasOrder();
        MapTest.likedHashMap();
    }

    public static void noOrder() {
        System.out.println("------无序（随机输出------");
        Map<String, String> map = new HashMap<>();
        map.put("1", "Level 1");
        map.put("2", "Level 2");
        map.put("3", "Level 3");
        map.put("4", "Level 4");
        map.put("F", "Level F");
        map.put("Q", "Level Q");
        for (Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println("Key: " + stringStringEntry.getKey() + ";   Value: " + stringStringEntry.getValue());
        }
    }

    // 有序(默认排序，不能指定)
    public static void hasOrder() {
        System.out.println("------有序（但是按默认顺充，不能指定）------");
        Map<String, String> map = new TreeMap<>();
        map.put("F", "Level F");
        map.put("7", "Level 1");
        map.put("8", "Level 2");
        map.put("4", "Level 3");
        map.put("4", "Level 4");
        map.put("Q", "Level Q");
        map.put("E", "Level E");
        for (Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println("Key: " + stringStringEntry.getKey() + ";   Value: " + stringStringEntry.getValue());
        }
    }

    public static void likedHashMap() {
        System.out.println("------有序（根据输入的顺序输出）------");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("F", "Level F");
        map.put("7", "Level 1");
        map.put("8", "Level 2");
        map.put("4", "Level 3");
        map.put("4", "Level 4");
        map.put("Q", "Level Q");
        map.put("E", "Level E");
        for (Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println("Key: " + stringStringEntry.getKey() + ";   Value: " + stringStringEntry.getValue());
        }
    }
}
