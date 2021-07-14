package com.mit.queue;

import java.util.Scanner;

/**
 * @author mit
 * @date 2021/7/14 下午4:23
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        // 创建一个队列
        CircleArray queue = new CircleArray(3);
        char key; // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");

            System.out.print("输入指令执行相应操作: ");
            key = scanner.next().charAt(0);

            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    loop = false;
                    break;
                case 'a':
                    System.out.print("输入要增加的数: ");
                    int num = scanner.nextInt();
                    queue.addQueue(num);
                    break;
                case 'g':
                    try {
                        int i = queue.get();
                        System.out.println("取出的数是： " + i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int headNumber = queue.headQueue();
                        System.out.println("队列头部的数是： " + headNumber);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                default:
                    System.out.println("输入的指令不正确，重新输入...");
            }
        }
    }
}

/**
 * 环形数组模拟队列
 * 1、目前数组使用一次就不能用，没有达到复用的效果；
 * 2、将这个数组使用算法，改进成一个环形的队列，关键: 取模 %
 *
 * @author mit
 * @date 2021/7/1 下午8:26
 */
class CircleArray {
    /*
     * 思路如下：
     *  1、front变量的含义做一个调整: front就指向队列的第一个元素，也就是说 arr[front]就是对列的第一个元素，front的初始值=0
     *  2、rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间做为约定，rear的初始值=0
     *  3、队列满的条件是：
     *      (rear + 1) % maxSize = front
     *      // 这个要明确理解的就是，rear指向队列的最后一个元素的后一个位置，rear + 1 是因为有一个空位置是约定位；
     *      // % maxSize 用于处理rear在front前面的情况(环形循环使用了)
     *  4、队列为空的条件是:
     *      rear == front
     *  5、队列中有效数据的个数：
     *      (rear + maxSize - front) % maxSize
     *      // +maxSize 与 %maxSize 用于处理rear在front前面的情况(环形循环使用了)
     *  6、在上面思路的基础上实现环形队列
     */

    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    /**
     * 构造函数
     *
     * @param maxSize 队列最大长度
     */
    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        front = 0;
        rear = 0;
        arr = new int[maxSize];
    }

    /**
     * 判断队列满
     *
     * @return boolean
     * @author mit
     * @date 2021/7/14 上午11:43
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列空
     *
     * @return boolean
     * @author mit
     * @date 2021/7/14 上午11:42
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 获取队列长度
     *
     * @return int 队列长度
     * @author mit
     * @date 2021/7/14 上午11:48
     */
    public int length() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 增加数进队列
     *
     * @param number 新增的数
     * @author mit
     * @date 2021/7/14 上午11:50
     */
    public void addQueue(int number) {
        if (isFull()) {
            System.out.println("队列已满，不能增加新的数");
        } else {
            arr[rear] = number;
            rear = (rear + 1) % maxSize;
        }
    }

    /**
     * 取出一个数
     *
     * @return int 取出的数
     * @author mit
     * @date 2021/7/14 下午3:58
     */
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        int num = arr[front];
        front = (front + 1) % maxSize;
        return num;
    }

    /**
     * 展示队列中所有数
     *
     * @author mit
     * @date 2021/7/14 下午4:05
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
        } else {
            for (int i = 0; i < length(); i++) {
                System.out.printf("arr[%d]==%d\n", front + i, arr[(front + i) % maxSize]);
            }
        }
    }

    /**
     * 展示队列头部的数据
     *
     * @return int 队列头部的数据
     * @author mit
     * @date 2021/7/14 下午4:15
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}
