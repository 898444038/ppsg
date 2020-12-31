package com.ming.ppsg2.utils.zt;

//时间片轮转算法
public class ListQueue {
    // 链队列
    class Link {
        private String name;
        private int sertime;
        private int priority;
        public Link next;
        public Link() {
        }
        public Link(String name, int sertime, int priority) {
            this.name = name;
            this.sertime = sertime;
            this.priority = priority;
        }
        public void displayLink() {
            System.out.println("进程名 " + name + "服务时间 " + sertime + "优先级"+priority);
        }
    }
    public static Link head;
    public static Link rear;
    public boolean isEmptyQueue() {
        return head == null;
    }
    public ListQueue() {
        head = rear = null;
    }
    // 从尾部插入
    public void insertToQueue(String name, int sertime, int priority) {
        Link newLink = new Link(name, sertime, priority);
        // 第一次的链接点为head端
        if (isEmptyQueue()) {
            head = newLink;
        } else {
            rear.next = newLink;
        }
        rear = newLink;
    }
    // 从头部删除
    public void removeFromQueue() {
        if (isEmptyQueue()) {
            System.out.println("Queue is empty");
        } else {
            if (null == head.next) {
                rear = null;
            }
            head = head.next;
        }
    }
    public void displayQueue() {
        Link currentLink = head;
        while (null != currentLink) {
            currentLink.displayLink();
            currentLink = currentLink.next;
        }
    }

    public static void main(String[] args) {

        for(int i=0;i<60;i++){
            System.out.println(i);
        }



        int i = 0;
        ListQueue lq = new ListQueue();
        // 插入数据
        lq.insertToQueue("A", 0, 0);
        lq.insertToQueue("B", 0, 0);
        lq.insertToQueue("C", 0, 0);
        lq.insertToQueue("D", 0, 0);
        System.out.println("时间片轮转算法:");
        for (Link visitLink = head; visitLink != null; visitLink = visitLink.next) {
            System.out.print("t=" + i + " " + visitLink.name + "执行 ");
            if (visitLink.sertime <= 0) {
                System.out.println(visitLink.sertime+" 完成任务");
                lq.removeFromQueue();
            } else {
                System.out.println((visitLink.sertime)+" 未完成任务");
                lq.insertToQueue(visitLink.name, visitLink.sertime - 1,visitLink.priority);
                lq.removeFromQueue();
            }
            i++;
        }
    }
}

