package com.mit.linkedlist;

import javax.swing.plaf.PanelUI;

/**
 * @author mit
 * @date 2021/7/21 下午3:18
 */
public class SingleLinkedListDemo {
}

/**
 * 单链表
 *
 * @author mit
 * @date 2021/7/21 下午3:20
 */
class SingleLinkedList {
    /**
     * 初始化一个头节点，头节点不存放具体的数据
     */
    private final HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到链表中-添加到链表尾部
     *
     * @param heroNode 添加的节点
     * @author mit
     * @date 2021/7/21 下午3:31
     */
    public void add(HeroNode heroNode) {
        // 查找到链表的最后一个节点，将最后节点的next指向新节点
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 添加节点到链表中-按排名插入到指定位置
     *
     * @param heroNode 添加的节点
     * @author mit
     * @date 2021/7/21 下午3:42
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean hasSameNo = false;
        while (true) {
            if (temp.next == null) {
                break; // 链表遍历完成
            }
            if (temp.next.no < heroNode.no) {
                temp = temp.next;
            } else if (temp.next.no == heroNode.no) {
                hasSameNo = true;
                break;
            } else {
                break;
            }
        }
        if (hasSameNo) {
            System.out.printf("hero no %d already exists, can't add it \n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 根据no修改节点信息(no不能修改)
     * @author mit
     * @date 2021/7/21 下午3:53
     * @param newHeroNode 需要修改的节点
     */
    public void update(HeroNode newHeroNode) {
        // 链表可能存在为空的情况
        if (head.next == null) {
            System.out.println("链表为空～～");
            return;
        }
        // 找出no相同的节点
        HeroNode temp = head.next;
        boolean isFind = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.no < newHeroNode.no) {
                temp = temp.next;
            } else if (temp.no == newHeroNode.no) {
                isFind = true;
                break;
            }
        }
        if (isFind) {

        } else {
            System.out.printf("没有找到编号为 %d 的节点，不能修改", newHeroNode.no);
        }


    }
}

/**
 * 定义 HeroNode, 每个 HeroNode 对象就是一个节点
 *
 * @author mit
 * @date 2021/7/21 下午3:21
 */
class HeroNode {

    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}