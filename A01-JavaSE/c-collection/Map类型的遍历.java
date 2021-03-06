
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Map遍历的三种方式
 * 1.先获取Key存于Set，据key来get(key)=value
 * 2.将Map转换成一个是Map的Set，即Set<Map.Entry<K,V>>，再遍历Set
 * 3.使用Lambda的流遍历
 */
class TraversalMap {
    /**
     * 把所有的键都存放在一个Set中，据key去get获取Value
     */
    public <K,V> void traversalByKey(Map<K,V> map) {
        Set<K> keys = map.keySet();
        //由于Set是Collection的子孙，所以这里遍历Set就有4中方法，这里选取一种：增强for
        for (K key : keys) {
            System.out.println("K:"+key+", V:"+map.get(key));
        }
        System.out.println();
    }

    /**
     * 将Map转换成一个是Map的Set，即Set<Map.Entry<K,V>>，再遍历Set
     */
    public <K,V> void traversalByEntrySet(Map<K,V> map) {
        Set<Map.Entry<K,V>> entrySet = map.entrySet();
        //由于Set是Collection的子孙，又有四种遍历方式
        for(Map.Entry<K,V> ele:entrySet) {
            System.out.println("K:"+ele.getKey()+", V:"+ele.getValue());
        }
        System.out.println();
    }

    /**
     * 使用Lambda的流遍历
     */
    public <K,V> void traversalByStream(Map<K,V> map) {
        map.forEach(((k, v) -> System.out.println("K:"+k+", V:"+v)));
        System.out.println();
    }

}

public class MainTest {
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();
        map.put(19,"桃乃木香奈");
        map.put(33,"前田可奈子");
        map.put(22,"葵");
        map.put(24,"天然香");
        map.put(20,"枫力美");

        TraversalMap tt = new TraversalMap();
        tt.traversalByEntrySet(map);
        tt.traversalByKey(map);
        tt.traversalByStream(map);

        //输出：
        //K:33, V:前田可奈子
        //K:19, V:桃乃木香奈
        //K:20, V:枫力美
        //K:22, V:葵
        //K:24, V:天然香
        //
        //K:33, V:前田可奈子
        //K:19, V:桃乃木香奈
        //K:20, V:枫力美
        //K:22, V:葵
        //K:24, V:天然香
        //
        //K:33, V:前田可奈子
        //K:19, V:桃乃木香奈
        //K:20, V:枫力美
        //K:22, V:葵
        //K:24, V:天然香
        //
        //Process finished with exit code 0

    }
}