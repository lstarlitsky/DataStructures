package com.mit.queue;

import java.util.Scanner;

/**
 * @author mit
 * @Description 数组模拟队列 (一次性队列)
 * @date 2021/7/1 下午12:04
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        // 创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
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
 * 数组模拟队列类
 */
class ArrayQueue {
    private int maxSize;    // 数组的最大容量
    private int front;      // 队列头
    private int rear;       // 队列尾
    private int[] arr;      // 存放数据的数组，用于模拟队列

    /**
     * 构造函数
     *
     * @param arrMaxSize 队列最大长度
     */
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部，队列头的前一个位置
        rear = -1;  // 指向队列尾部，队列的最后一个数据
    }

    /**
     * 判断队列满
     *
     * @return boolean
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 获取队列长度
     *
     * @return 队列长度
     */
    public int length() {
        return rear - front;
    }

    /**
     * 增加数进队列
     *
     * @param number 新加入队列的数
     */
    public void addQueue(int number) {
        if (isFull()) {
            System.out.println("队列已满，不能增加新的数");
        } else {
            rear++;
            arr[rear] = number;
        }
    }

    /**
     * 从队列中取出一个数
     *
     * @return 取出的数
     * @throws RuntimeException 队列为空, 不能取数据
     */
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空, 不能取数据");
        }
        front++;
        return arr[front];
    }

    /**
     * 展示队列中所有数
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的，没有数");
        }
        for (int i = 0; i < length(); i++) {
            System.out.printf("%d\n", arr[i]);
        }
    }

    /**
     * 展示队列头部的数
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的，没有数");
        }
        int headIndex = front + 1;
        return arr[headIndex];
    }


}
