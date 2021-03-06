
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Stream;


/**
 * Collection的遍历方式：
 *  1.Object[] toArray() //返回一个Object的数组，把集合中的元素全部存储到一个Object的数组中返回；
 *  2.Iterator<E> iterator() //迭代器遍历集合中的元素
 *  3.增强for循环
 *  4.forEach：始于JDK1.8（Lambda表达式）
 */
class TraversalCollection {
    /**
     * 将Collection转换为Object数组
     */
    public <E> void traversalByObjectArray(Collection<E> coll) {
        Object[] arr = coll.toArray();
        for(int i=0; i< arr.length; i++) {
            System.out.print(arr[i]+ "  ");
        }
        System.out.println();
    }

    /**
     * 将Collection使用迭代器遍历
     */
    public <E> void traversalByIterator(Collection<E> coll) {
        Iterator<E> it = coll.iterator();
        while(it.hasNext()) {
            System.out.print(it.next()+"  ");
        }
        //迭代器的增强For循环遍历
        //for (E e : coll) {
        //    System.out.print(e + "  ");
        //}
        System.out.println();
    }

    /**
     * 将Coll使用增强for遍历
     */
    public <E> void traversalByEnhanceFor(Collection<E> coll) {
        for (E e : coll) {
            System.out.print(e + "  ");
        }
        System.out.println();
    }

    /**
     * 使用Lambda中流的forEach遍历
     */
    public <E> void traversalByForEach(Collection<E> coll) {
        Stream<E> stream = coll.stream(); //获取到流
        stream.forEach(e -> System.out.print(e+"  "));
        System.out.println();
    }
}

/**
 * List集合除了拥有以上四种遍历方式，还拥有一种特殊的遍历方式：ListIterator
 * 性质：
 *  1.仅能遍历List
 *  2.可双向遍历（底层是双向循环链表，数组下标的索引倒叙）
 *  3.可一边遍历一边修改
 */
class ListTraversal {

    /**
     * ListIterator的遍历方式
     * 性质：只能是List类型的集合才能使用这种方式进行遍历
     */
    public <E> void traversal(List<E> list) {
        System.out.println("正向遍历：");
        ListIterator<E> listIterator = list.listIterator();
        while(listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();

        System.out.println("逆向遍历：");
        while(listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println();

        System.out.println("List容器特有的遍历方式，随机访问机制：");
        for(int i=0, len=list.size(); i<len; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public void traversalAndModify(List<Integer> list) {
        System.out.println("在遍历时添加：");
        ListIterator<Integer> it = list.listIterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
            it.add(666); //在遍历时添加
            //list.add(666);  这里很容器犯这样的错
        }
        System.out.println();

        System.out.println("在遍历时添加的数据只能在遍历结束后才能查看：");
        System.out.println(list);
    }

}




public class MainTest {

    public static void main(String[] args) {

        //测试List
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);

        TraversalCollection tc = new TraversalCollection();
        tc.traversalByObjectArray(list);
        tc.traversalByIterator(list);
        tc.traversalByEnhanceFor(list);
        tc.traversalByForEach(list);

        Set<Integer> set = new HashSet<>();
        set.add(11);
        set.add(22);
        set.add(11);
        set.add(22);
        set.add(33);
        tc.traversalByObjectArray(set);
        tc.traversalByIterator(set);
        tc.traversalByEnhanceFor(set);
        tc.traversalByForEach(set);
        //输出：
        //    11  12  13  14  15
        //    11  12  13  14  15
        //    11  12  13  14  15
        //    11  12  13  14  15
        //    33  22  11
        //    33  22  11
        //    33  22  11
        //    33  22  11

        //测试ListIterator
        ListTraversal tt = new ListTraversal();
        tt.traversal(list);
        tt.traversalAndModify(list);
        //输出：
        //正向遍历：
        //11 12 13 14 15
        //逆向遍历：
        //15 14 13 12 11
        //List容器特有的遍历方式，随机访问机制：
        //11 12 13 14 15
        //在遍历时添加：
        //11 12 13 14 15
        //在遍历时添加的数据只能在遍历结束后才能查看：
        //[11, 666, 12, 666, 13, 666, 14, 666, 15, 666]
    }
}