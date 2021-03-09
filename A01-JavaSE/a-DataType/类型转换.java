
/**
 * 类型转换
 */
class DataTypeConversion {

    /**
     * 基本类型转串：obj.toString();
     */
    public String toString01(Object obj) {
        if(obj == null) { //处理变量未空的情况，放置抛空指针异常
            return null;
        }
        return obj.toString();
    }

    /**
     * 基本类型转串：String.valueOf(obj);
     */
    public String toString02(Object obj) {
        return String.valueOf(obj);
    }

    /**
     * 基本类型转串：字符串拼接
     */
    public String toString03(Object obj) {
        return ""+obj;
    }

}


public class MainTest {
    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }

    //字节(byte)相关转换
    private static void test03() {
        // byte数组赋值
        byte[] b = new byte[10]; //一个字节(byte) = 一个ASCII码（但不是一个Char）
        for(int i=0;i<b.length;i++) {
            b[i]=(byte)(65+i); //字节数组的赋值，值的范围[-127,+127]
        }
        //byte数组的输出
        for(int i=0;i<b.length;i++) {
            System.out.println((char)b[i]); //将ASCII码转换为字符输出；
            System.out.println(b[i]); //直接输出，则输出该byte数组元素的ASCII码；
        }

        //byte数组、string、char数组的转换（一个char，2个Bytes）
        char[] ch = new char[3];
        ch[0]='a';
        ch[1]='b';
        ch[2]='c';
        String str = new String(ch); //char[]转String；

        //char[]转byte[]：char-->String-->byte;
        byte[] bb = str.getBytes();  //String转byte[];

        char[] chStr = new char[3];
        for(int i=0;i<chStr.length;i++) {
            chStr[i]=(char)bb[i]; //byte[]转char[];
        }

        String ss = new String(chStr); //char[]转String
        System.out.println(new String(ss));

        byte[] bbb = new byte[3];
        for(int i=0;i<bbb.length;i++) {
            bbb[i]=(byte)ch[i]; //char[]转byte[]：强制类型转换；
        }
    }

    private static void test02() {
        String s = "3.1415926";
        System.out.println(Double.parseDouble(s)); //输出：3.1415926
        System.out.println(Float.parseFloat(s)); //输出：3.1415925（可以发现只有数字的前6位是精准的）

        System.out.println(Boolean.parseBoolean(s)); //输出：false
        System.out.println(Long.parseLong(s)); //抛出异常：java.lang.NumberFormatException
        System.out.println(Short.parseShort(s)); //抛出异常：java.lang.NumberFormatException
        System.out.println(Byte.parseByte(s)); //抛出异常：java.lang.NumberFormatException
        System.out.println(Integer.parseInt(s)); //抛出异常：java.lang.NumberFormatException
    }

    public static void test01() {
        //基本数据类型转串
        byte b = 1;
        short s = 2;
        int i = 4;
        char c = 'a';

        float f = 3.14F;
        double d = 3.141592654D;

        DataTypeConversion dtc = new DataTypeConversion();

        System.out.println(dtc.toString01(null)); //输出：null
        System.out.println(dtc.toString01(b)); //输出：1
        System.out.println(dtc.toString01(d)); //输出：3.141592654

        System.out.println(dtc.toString02(null));  //输出：null
        System.out.println(dtc.toString02(c));  //输出：a
        System.out.println(dtc.toString02(f));  //输出：3.14

        System.out.println(dtc.toString03(null));  //输出：null
        System.out.println(dtc.toString03(i)); //输出：4
        System.out.println(dtc.toString03(s)); //输出：2
    }
}
