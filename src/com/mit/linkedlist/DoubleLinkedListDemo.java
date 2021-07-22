package com.mit.linkedlist;

/**
 * 双向链表demo
 *
 * @author mit
 * @date 2021/7/22 上午11:52
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }
}

/**
 * 双向链表
 *
 * @author mit
 * @date 2021/7/22 上午11:53
 */
class DoubleLinkedList {

    /**
     * 初始化一个头节点，头节点不存放具体的数据
     */
    private final HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 显示链表(遍历)
     *
     * @author mit
     * @date 2021/7/22 下午4:54
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空～～");
            return;
        }
        // 遍历链表
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.printf("节点编号: %d, name: %s, nickName: %s \n", temp.no, temp.name, temp.nickName);
            temp = temp.next;
        }
    }

    /**
     * 添加节点到双向链表中-添加到链表尾部
     *
     * @param heroNode 添加的节点
     * @author mit
     * @date 2021/7/21 下午3:31
     */
    public void add(HeroNode2 heroNode) {
        // 查找到链表的最后一个节点，将最后节点的next指向新节点
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        heroNode.pre = temp;
        temp.next = heroNode;
    }

    /**
     * 添加节点到链表中-按排名插入到指定位置
     *
     * @param heroNode 添加的节点
     * @author mit
     * @date 2021/7/21 下午3:42
     */
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
            heroNode.pre = temp;
            heroNode.next = temp.next;
            temp.next = heroNode;
            temp.next.next.pre = heroNode;
        }
    }

    /**
     * 根据no修改节点信息(no不能修改)
     *
     * @param newHeroNode 需要修改的节点
     * @author mit
     * @date 2021/7/21 下午3:53
     */
    public void update(HeroNode2 newHeroNode) {
        // 链表可能存在为空的情况
        if (head.next == null) {
            System.out.println("链表为空～～");
            return;
        }
        // 找出no相同的节点
        HeroNode2 temp = head.next;
        boolean isFind = false;
        while (true) {
            if (temp== null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                isFind = true;
                break;
            }
            temp = temp.next;
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
        HeroNode2 temp = head.next;
        boolean isFind = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                isFind = true;
                break;
            }
            temp = temp.next;
        }
        if (isFind) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到编号为 %d 的节点！\n", no);
        }
    }
}


/**
 * 定义双向链表使用的 HeroNode, 每个 HeroNode 对象就是一个节点
 *
 * @author mit
 * @date 2021/7/21 下午3:21
 */
class HeroNode2 {

    public int no;
    public String name;
    public String nickName;
    public HeroNode2 pre;
    public HeroNode2 next;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}