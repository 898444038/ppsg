package com.ming.ppsg2.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Administrator on 2020/8/20 0020.
 */
public class StackUtil {
    public static Stack<Integer> stack = new Stack<Integer>();
    public static List<List<Integer>> lists = new ArrayList<>();
    public static void main(String[] args) {
        int shu[] = {1,2,3};

        f(shu,2,0,0); // 从这个数组4个数中选择三个
    }

    /**
     *
     * @param shu  元素
     * @param targ  要选多少个元素
     * @param has   当前有多少个元素
     * @param cur   当前选到的下标
     *
     * 1    2   3     //开始下标到2
     * 1    2   4     //然后从3开始
     */
    public static void f(int[] shu, int targ, int has, int cur) {
        if(has == targ) {
            System.out.println(stack);
            List<Integer> list = new ArrayList<>();
            for (Integer x : stack) {
                list.add(x);
            }
            lists.add(list);
            return;
        }

        for(int i=cur;i<shu.length;i++) {
            if(!stack.contains(shu[i])) {
                stack.add(shu[i]);
                f(shu, targ, has+1, i);
                stack.pop();
            }
        }

    }
}
