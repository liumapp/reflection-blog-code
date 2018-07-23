## 1. 基础概念

### 1.1 定义

反射机制是Java动态性之一，而说到动态性首先得了解动态语言

#### 1.1.1 动态语言

动态语言，是指程序在运行时可以改变其结构：新的函数可以引进，已有的函数可以被删除等结构上的变化

比如常见的JavaScript就是动态语言，除此之外Ruby,Python等也属于动态语言，而C、C++则不属于动态语言

从动态语言能在运行时改变程序结构结构或则变量类型上看，Java和C、C++一样都不属于动态语言
 
但是JAVA却又一个非常突出的与动态相关的机制：反射机制

Java通过反射机制，可以在程序运行时加载，探知和使用编译期间完全未知的类，并且可以生成相关类对象实例，从而可以调用其方法或则改变某个属性值

所以JAVA也可以算得上是一个半动态的语言

#### 1.1.2 Java反射概念

在Java中的反射机制是指在运行状态中，对于任意一个类都能够知道这个类所有的属性和方法

并且对于任意一个对象，都能够调用它的任意一个方法

这种动态获取信息以及动态调用对象方法的功能成为Java语言的反射机制

#### 1.1.3 Java反射应用场景

在Java程序中许多对象在运行时都会出现两种类型：编译时类型和运行时类型

编译时的类型由声明对象时使用的类型来决定，运行时的类型由实际赋值给对象的类型决定
 
如：

Person p = new Student();

其中编译时类型为Person，运行时类型为Student

除此之外，程序在运行时还可能接收到外部传入的对象，该对象的编译时类型为Object,但是程序有需要调用该对象的运行时类型的方法

为了解决这些问题，程序需要在运行时发现对象和类的真实信息

然而，编译时根本无法预知该对象和类属于哪些类，程序只能依靠运行时信息来发现该对象和类的真实信息，此时就必须使用到反射了

#### 1.1.4 反射API

反射API用来生成JVM中的类、接口或则对象的信息
 
* Class类：反射的核心类，可以获取类的属性，方法等信息
 
* Field类：Java.lang.reflec包中的类，表示类的成员变量，可以用来获取和设置类之中的属性值
 
* Method类： Java.lang.reflec包中的类，表示类的方法，它可以用来获取类中的方法信息或者执行方法
 
* Constructor类： Java.lang.reflec包中的类，表示类的构造方法

### 1.2 基本操作

#### 1.2.1 步骤

* 获取想要操作类的Class对象

* 调用Class类中的方法

* 使用反射API来操作这些信息

#### 1.2.2 获取Class对象的方法

调用某个对象的getClass()方法
    
    Person p = new Person();
    Class clazz = p.getClass();

调用某个类的class属性来获取该类对应的Class对象

    Class clazz = Person.class;

使用Class类中的forName()静态方法; (最安全/性能最好)

    Class clazz = Class.forName("类的全路径"); (最常用)

#### 1.2.3 获取方法和属性信息

当我们获得了想要操作的类的Class对象后，可以通过Class类中的方法获取并查看该类中的方法和属性
示例代码：

    public class Person {
    
        private String name;
    
        private Integer age;
    
        private String sex;
    
        public Person() {
        }
    
        public Person(String name, Integer age, String sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public Integer getAge() {
            return age;
        }
    
        public void setAge(Integer age) {
            this.age = age;
        }
    
        public String getSex() {
            return sex;
        }
    
        public void setSex(String sex) {
            this.sex = sex;
        }
    
        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }

使用案例：

    public class PersonTest extends TestCase {
    
        private Logger logger = LoggerFactory.getLogger(PersonTest.class);
    
        public void testReflection () {
            try {
                Class clazz = Class.forName(Person.class.getName());
                Method[] methods = clazz.getDeclaredMethods();
    
                for (Method m: methods) {
                    logger.info("out put clazz methods : ");
                    logger.info(m.toString());
                    logger.info("\n");
                }
    
                Field[] fields = clazz.getDeclaredFields();
                for (Field f: fields) {
                    logger.info("out put clazz fields : ");
                    logger.info(f.toString());
                    logger.info("\n");
                }
    
                Constructor[] constructors = clazz.getDeclaredConstructors();
                for (Constructor c: constructors) {
                    logger.info("out put clazz constructors : ");
                    logger.info(c.toString());
                    logger.info("\n");
                }
    
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    
    }

输出结果：
    
    15:03:11.773 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz methods : 
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - public java.lang.String com.liumapp.blog.reflection.demo.Person.toString()
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz methods : 
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - public java.lang.String com.liumapp.blog.reflection.demo.Person.getName()
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz methods : 
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - public void com.liumapp.blog.reflection.demo.Person.setName(java.lang.String)
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz methods : 
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - public java.lang.Integer com.liumapp.blog.reflection.demo.Person.getAge()
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz methods : 
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - public void com.liumapp.blog.reflection.demo.Person.setAge(java.lang.Integer)
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz methods : 
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - public java.lang.String com.liumapp.blog.reflection.demo.Person.getSex()
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz methods : 
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - public void com.liumapp.blog.reflection.demo.Person.setSex(java.lang.String)
    15:03:11.778 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.779 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz fields : 
    15:03:11.779 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - private java.lang.String com.liumapp.blog.reflection.demo.Person.name
    15:03:11.780 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.780 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz fields : 
    15:03:11.780 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - private java.lang.Integer com.liumapp.blog.reflection.demo.Person.age
    15:03:11.780 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.780 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz fields : 
    15:03:11.780 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - private java.lang.String com.liumapp.blog.reflection.demo.Person.sex
    15:03:11.780 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.780 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz constructors : 
    15:03:11.783 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - public com.liumapp.blog.reflection.demo.Person()
    15:03:11.784 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 
    
    15:03:11.784 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - out put clazz constructors : 
    15:03:11.784 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - public com.liumapp.blog.reflection.demo.Person(java.lang.String,java.lang.Integer,java.lang.String)
    15:03:11.784 [main] INFO com.liumapp.blog.reflection.demo.PersonTest - 

#### 1.2.4 创建对象

当我们获取到所需类的Class对象后，可以用它来创建对象，创建对象的方法有两种：

使用Class对象的newInstance()方法来创建该Class对象对应类的实例

但是这种方法要求该Class对象对应的类有默认的空构造器

另一种方法是先使用Class对象获取指定的Constructor对象

再调用Constructor对象的newInstance()方法来创建Class对象对应类的实例,通过这种方法可以选定构造方法创建实例

示例代码：

package reflection;

import java.lang.reflect.Constructor;
public class Demo01 {

    public static void main(String[] args) {
        try {
            //获取Person类的Class对象
            Class clazz=Class.forName("reflection.Person"); 
            /**
             * 第一种方法创建对象
             */
            //创建对象
            Person p=(Person) clazz.newInstance();
            //设置属性
            p.setName("张三");
            p.setAge(16);
            p.setGender("男");
            System.out.println(p.toString());

            /**
             * 第二种方法创建
             */
            //获取构造方法
            Constructor c=clazz.getDeclaredConstructor(String.class,String.class,int.class);
            //创建对象并设置属性
            Person p1=(Person) c.newInstance("李四","男",20);
            System.out.println(p1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}