package com.softeem.test;

import java.util.Random;

public class OrderService {
    public void createOrder() {
        String name = Thread.currentThread().getName();
        System.out.println("OrderService 当前线程[" + name + "]中保存的数据是：" + ThreadLocalTest.threadLocal.get());
        new OrderDao().saveOrder();
    }
}

class OrderDao {

    public void saveOrder() {
        String name = Thread.currentThread().getName();
        System.out.println("OrderDao 当前线程[" + name + "]中保存的数据是：" + ThreadLocalTest.threadLocal.get());
    }

}

class ThreadLocalTest {

    //	public static Map<String,Object> data = new Hashtable<String,Object>();
    public static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

    private static Random random = new Random();


    public static class Task implements Runnable {
        @Override
        public void run() {
            // 在Run 方法中，随机生成一个变量（线程要关联的数据），然后以当前线程名为key 保存到map 中
            Integer i = random.nextInt(1000);//[0-999]
            // 获取当前线程名
            String name = Thread.currentThread().getName();
            System.out.println("线程[" + name + "]生成的随机数是：" + i);
            //		data.put(name,i);
            threadLocal.set(i);//随机数保存到本地线程中
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new OrderService().createOrder();
            // 在Run 方法结束之前，以当前线程名获取出数据并打印。查看是否可以取出操作
            //	Object o = data.get(name);
            Object o = threadLocal.get();
            System.out.println("在线程[" + name + "]快结束时取出关联的数据是：" + o);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }
    }
}
