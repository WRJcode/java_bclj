package reflection;

public class SimpleMapperDemo {

    public static void main(String[] args) {
        Student zhangsan = new Student("张三",18,89d);
        String str = SimpleMapper.toString(zhangsan);
        System.out.println(str);
        Student zhangsan2 = (Student) SimpleMapper.fromString(str);
        System.out.println(zhangsan2);
        System.out.println(zhangsan==zhangsan2);
        System.out.println(zhangsan.equals(zhangsan2));
    }
}
