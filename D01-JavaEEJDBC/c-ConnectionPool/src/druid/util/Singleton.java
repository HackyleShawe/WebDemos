package druid.util;

/**
 * 单例模式
 */
public class Singleton {
    /**
     * 测试
     */
    public static void main(String[] args) {
        SingletonInstant singletonInstant01 = SingletonInstant.getInstance();
        SingletonInstant singletonInstant02 = SingletonInstant.getInstance();

        System.out.println(singletonInstant01 == singletonInstant02);

        SingletonLazy singletonLazy01 = SingletonLazy.getInstance();
        SingletonLazy singletonLazy02 = SingletonLazy.getInstance();
        System.out.println(singletonLazy01==singletonLazy02);
    }
}


/**
 * 饿汉单例，单线程
 */
class SingletonInstant {
    private static final SingletonInstant singletonInstant = new SingletonInstant();
    //其他类属性

    private SingletonInstant() {
        if(singletonInstant != null) { //防止通过反射进行实例化而破坏单例
            throw new RuntimeException("Object has been instanced.");
        }

        //初始化其他的单例类属性
    }

    public static SingletonInstant getInstance() {
        return singletonInstant;
    }

    //操作其他属性的方法
}

/**
 * 懒汉单例，单线程
 */
class SingletonLazy {
    private static SingletonLazy singletonLazy = null;
    //其他类属性

    private SingletonLazy() {
        if(singletonLazy != null) {
            throw new RuntimeException("Object has been instanced!");
        }

        //对其他类属性初始化
    }

    public static SingletonLazy getInstance() {
        if(singletonLazy == null) {
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }

    //其他方法
}


/**
 * 懒汉单例，并发环境
 */
class SingletonLayConcurrency {
    private static SingletonLayConcurrency singletonLayConcurrency = null;
    //其他属性

    private SingletonLayConcurrency() {
        //防止反射
        //初始化其他属性
    }

    //解决并发环境方案一：对方法加锁
    //方案分析：
    // 1.由于"singletonLayConcurrency = new SingletonLayConcurrency();"只会在最初获取时才会执行，执行次数极少
    // 2.对整个方法加锁就显得臃肿了
    //public synchronized static SingletonLayConcurrency getInstance() {
    //    if(singletonLayConcurrency == null) {
    //        singletonLayConcurrency = new SingletonLayConcurrency();
    //    }
    //    return singletonLayConcurrency;
    //}

    //解决并发环境方案一：对代码块加锁
    public static SingletonLayConcurrency getInstance() {
        if(singletonLayConcurrency == null) {
            synchronized (SingletonLayConcurrency.class) { //将所的范围减小，提升速度
                singletonLayConcurrency = new SingletonLayConcurrency();
            }
        }
        return singletonLayConcurrency;
    }

    //其他方法
}