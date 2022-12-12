package reflection;

import java.lang.reflect.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClazzDemo {

    enum Size{
        SMALL,MEDIUM,BIG
    };

    class Student{
        public static final int MAX_NAME_LEN = 255;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {


        //对象
        Class<Date> dateClass = Date.class;
        //接口
        Class<Comparable> comparableClass = Comparable.class;

        //基本类型没有getClass方法
        Class<Integer> integerClass = int.class;
        Class<Byte> byteClass = byte.class;
        Class<Character> characterClass = char.class;
        Class<Double> doubleClass = double.class;

        //void
        Class<Void> voidClass = void.class;

        //数组
        String[] strArr = new String[10];
        int[][] twoDimArr = new int[3][2];
        int[] oneDimArr = new int[10];
        Class<? extends String[]> strArrClass = strArr.getClass();
        Class<? extends int[][]> twoDimArrCls = twoDimArr.getClass();
        Class<? extends int[]> oneDimArrCls = oneDimArr.getClass();

        //枚举类型
        Class<Size> sizeClass = Size.class;

        //Class静态方法forName,可根据类名直接加载Class,获取Class对象
        try {
            Class<?> cls = Class.forName("java.util.HashMap");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /**
         * 名称信息：
         *   1.public String getName()  java内部使用的真正的名称
         *   2.public String getSimpleName()  public String getName()
         *   3.public String getCanonicalName()   返回名称更友好
         *   4.public Package getPackage() 返回包信息
         */
        System.out.println(String.class.getName());//java.lang.String
        System.out.println(String.class.getSimpleName());//String
        System.out.println(String.class.getCanonicalName());//java.lang.String
        System.out.println(String.class.getPackage());//package java.lang, Java Platform API Specification, version 1.8

        /**
         * 二、字段信息
         *   字段：类中定义的静态和实例变量，类Field表示，java.lang.reflect下
         *   4个获取字段信息的方法
         *     1.public Field[] getFields()  返回所有字段，包括父类
         *     2.public Field[] getDeclaredFields()  返回本类声明的所有字段，包括非public，不包括父类
         *     3.public Field getField(String name) 返回本类或父类指定名称的public字段，找不到会抛出NoSuchFieldException
         *     4.public Field getDeclaredField(String name) 返回本类中声明的指定名称的字段，找不到抛出同上异常
         */
        /**
         * Field中方法：
         *  public String getName();
         *  public boolean isAccessible
         *
         *  public void setAccessible(boolean flag)
         *  public Object get(Object obj)
         *  public void set(Object obj,Object value)
         */
         Class<String> stringClass = String.class;
         Field[] stringFields = stringClass.getFields();
         for (int i=0;i<stringFields.length;i++){
             System.out.println(stringFields[i].getName());
             System.out.println(stringFields[i].isAccessible());
         }
         Field[] stringFieldByGetDecl = stringClass.getDeclaredFields();
        System.out.println(stringFieldByGetDecl.length);
        for (int j=0;j<stringFieldByGetDecl.length;j++){
            stringFieldByGetDecl[j].setAccessible(true);
            //System.out.println(stringFields[j].getName());
            //System.out.println(stringFields[j].isAccessible());
            System.out.println(j);
        }
        /**
         * Field其他方法：
         *         //返回字段的修饰符
         *         public int getModifiers()
         *         //返回字段的类型
         *         public Class<? > getType()
         *         //以基本类型操作字段
         *         public void setBoolean(Object obj, boolean z)
         *         public boolean getBoolean(Object obj)
         *         public void setDouble(Object obj, double d)
         *         public double getDouble(Object obj)
         *         //查询字段的注解信息，下一章介绍注解
         *         public <T extends Annotation> T getAnnotation(Class<T> annotationClass)
         *         public Annotation[] getDeclaredAnnotations()
         */
        /**
         *   以下片段输出：
         *         public static final
         *         isPublic: true
         *         isStatic: true
         *         isFinal: true
         *         isVolatile: false
         */
        Field f = Student.class.getField("MAX_NAME_LEN");
        int mod = f.getModifiers();
        System.out.println(Modifier.toString(mod));
        System.out.println("isPublic: " + Modifier.isPublic(mod));
        System.out.println("isStatic: " + Modifier.isStatic(mod));
        System.out.println("isFinal: " + Modifier.isFinal(mod));
        System.out.println("isVolatile: " + Modifier.isVolatile(mod));

        /**
         * 三、方法信息
         *   方法：类中静态和实例方法，类Method表示，class中相关方法如下：
         *         //返回所有的public方法，包括其父类的，如果没有方法，返回空数组
         *         public Method[] getMethods()
         *         //返回本类声明的所有方法，包括非public的，但不包括父类的
         *         public Method[] getDeclaredMethods()
         *         //返回本类或父类中指定名称和参数类型的public方法，
         *         //找不到抛出异常NoSuchMethodException
         *         public Method getMethod(String name, Class<? >... parameterTypes)
         *         //返回本类中声明的指定名称和参数类型的方法，找不到抛出异常NoSuchMethodException
         *         public Method getDeclaredMethod(String name, Class<? >... parameterTypes)
         */
        /**
         *         //获取方法的名称
         *         public String getName()
         *         //flag设为true表示忽略Java的访问检查机制，以允许调用非public的方法
         *         public void setAccessible(boolean flag)
         *         //在指定对象obj上调用Method代表的方法，传递的参数列表为args
         *         public Object invoke(Object obj, Object... args) throws
         *           IllegalAccessException, Illegal-ArgumentException, InvocationTargetException
         *
         *           //
         */

        Class<? > cls = Integer.class;
        try {
            Method method = cls.getMethod("parseInt", new Class[]{String.class});
            System.out.println(method.invoke(null, "123"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        /**
         * 四、创建对象和构造方法
         *     1.Class中有一创建对象方法，默认调用无参构造方法创建对象
         *        public T newInstance() throws InstantiationException, IllegalAccessException
         *     2.
         */
        Map<String, Integer> map = HashMap.class.newInstance();
        map.put("hello", 123);

        /**
         * Class中获取所有构造方法：
         *         //获取所有的public构造方法，返回值可能为长度为0的空数组
         *         1.public Constructor<? >[] getConstructors()
         *         //获取所有的构造方法，包括非public的
         *         2.public Constructor<? >[] getDeclaredConstructors()
         *         //获取指定参数类型的public构造方法，没找到抛出异常NoSuchMethodException
         *         3.public Constructor<T> getConstructor(Class<? >... parameterTypes)
         *         //获取指定参数类型的构造方法，包括非public的，没找到抛出异常NoSuchMethodException
         *         4.public Constructor<T> getDeclaredConstructor(Class<? >... parameterTypes)
         */

        /**
         * 类Constructor表示构造方法，也可以构造对象；
         *        public T newInstance(Object ... initargs) throws InstantiationException,
         *         IllegalAccessException, IllegalArgumentException, InvocationTargetException
         */
        Constructor<StringBuilder> contructor= StringBuilder.class
                .getConstructor(new Class[]{int.class});
        StringBuilder sb = contructor.newInstance(100);

        /**
         * 五、类型检查和转换
         *    Class中动态类型检查public native boolean isInstance(Object obj)
         *
         *            if(list instanceof ArrayList){
         *             System.out.println("array list");
         *         }
         *
         *                 Class cls = Class.forName("java.util.ArrayList");
         *         if(cls.isInstance(list)){
         *             System.out.println("array list");
         *         }
         *
         *         以上两段代码输出相同，但下面的更有灵活性
         *
         *    Class中动态强制类型转换：public T cast(Object obj)
         *
         *          List list = ..;
         *         if(list instanceof ArrayList){
         *             ArrayList arrList = (ArrayList)list;
         *         }
         *
         *        public static <T> T toType(Object obj, Class<T> cls){
         *             return cls.cast(obj);
         *         }
         *
         *   判断Class之间的关系：public native boolean isAssignableFrom(Class<? > cls);//检查参数类型cls能否赋给当前Class类型的变量
         *         Object.class.isAssignableFrom(String.class)
         *         String.class.isAssignableFrom(String.class)
         *         List.class.isAssignableFrom(ArrayList.class)
         *       以上输出皆是true
         *
         */

        /**
         *  六、Class代表的类型：
         *         public native boolean isArray()  //是否是数组
         *         public native boolean isPrimitive()  //是否是基本类型
         *         public native boolean isInterface()  //是否是接口
         *         public boolean isEnum()  //是否是枚举
         *         public boolean isAnnotation()  //是否是注解
         *         public boolean isAnonymousClass()  //是否是匿名内部类
         *         public boolean isMemberClass()  //是否是成员类，成员类定义在方法外，不是匿名类
         *         public boolean isLocalClass()  //是否是本地类，本地类定义在方法内，不是匿名类
         */

        /**
         *  七、类的声明信息
         *    如修饰符、父类、接口、注解等
         *         //获取修饰符，返回值可通过Modifier类进行解读
         *         public native int getModifiers()
         *         //获取父类，如果为Object，父类为null
         *         public native Class<? super T> getSuperclass()
         *         //对于类，为自己声明实现的所有接口，对于接口，为直接扩展的接口，不包括通过父类继承的
         *         public native Class<? >[] getInterfaces();
         *         //自己声明的注解
         *         public Annotation[] getDeclaredAnnotations()
         *         //所有的注解，包括继承得到的
         *         public Annotation[] getAnnotations()
         *         //获取或检查指定类型的注解，包括继承得到的
         *         public <A extends Annotation> A getAnnotation(Class<A> annotationClass)
         *         public boolean isAnnotationPresent(
         *             Class<? extends Annotation> annotationClass)
         *
         */

        /**
         * 八、类的加载，Class的两个静态方法，根据类名加载类：
         *         public static Class<? > forName(String className)  //相当于Class.forName(className, true, currentLoader)
         *         public static Class<? > forName(String name, boolean initialize,
         *             ClassLoader loader)
         *
         *      ！！基本类型不支持forName方法
         */


        /**
         *         //通过封装使得根据类名构造Class对象
         *         public static Class<? > forName(String className)
         *                 throws ClassNotFoundException{
         *             if("int".equals(className)){
         *                 return int.class;
         *             }
         *             //其他基本类型略
         *             return Class.forName(className);
         *         }
         */

        /**
         * 九、反射与数组
         *     获取数组元素类型：public native Class<? > getComponentType()
         */
        String[] arr = new String[]{};
        System.out.println(arr.getClass().getComponentType());

        /**
         *  java.lang.reflect中的Array类，提供对于数组的一些反射支持
         *         //创建指定元素类型、指定长度的数组
         *         public static Object newInstance(Class<? > componentType, int length)
         *         //创建多维数组
         *         public static Object newInstance(Class<? > componentType, int... dimensions)
         *         //获取数组array指定的索引位置index处的值
         *         public static native Object get(Object array, int index)
         *         //修改数组array指定的索引位置index处的值为value
         *         public static native void set(Object array, int index, Object value)
         *         //返回数组的长度
         *         public static native int getLength(Object array)
         */
        int[] intArr = (int[])Array.newInstance(int.class, 10);
        String[] strArr1 = (String[])Array.newInstance(String.class, 10);

        /**
         * Array也支持以各种基本类型操作数组元素：
         *         public static native double getDouble(Object array, int index)
         *         public static native void setDouble(Object array, int index, double d)
         *         public static native void setLong(Object array, int index, long l)
         *         public static native long getLong(Object array, int index)
         */

        /**
         * 十、反射与枚举
         *     public T[] getEnumConstants()//获取所有枚举常量
         */

    }
}
