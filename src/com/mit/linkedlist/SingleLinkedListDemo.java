package com.mit.linkedlist;

import javax.swing.plaf.PanelUI;

/**
 * @author mit
 * @date 2021/7/21 下午3:18
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // singleLinkedList.add(hero1);
        // singleLinkedList.add(hero4);
        // singleLinkedList.add(hero2);
        // singleLinkedList.add(hero3);

        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        //显示一把
        singleLinkedList.list();

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况~~");
        singleLinkedList.list();

        //删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("删除后的链表情况~~");
        singleLinkedList.list();
    }

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
     *
     * @param newHeroNode 需要修改的节点
     * @author mit
     * @date 2021/7/21 下午3:53
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
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号为 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     *
     * @param no 需要删除节点的编号
     * @author mit
     * @date 2021/7/22 上午10:50
     */
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空～～");
            return;
        }
        // 找出对应编号的节点
        HeroNode temp = head;
        boolean isFind = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                isFind = true;
                break;
            }
            temp = temp.next;
        }
        if (isFind) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号为 %d 的节点！\n", no);
        }
    }

    /**
     * 显示链表(遍历)
     *
     * @author mit
     * @date 2021/7/22 上午11:12
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空～～");
        }
        // 遍历链表
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.printf("节点编号: %d, name: %s, nickName: %s \n", temp.no, temp.name, temp.nickName);
            temp = temp.next;
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