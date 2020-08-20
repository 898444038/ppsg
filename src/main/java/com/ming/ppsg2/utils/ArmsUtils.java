package com.ming.ppsg2.utils;

import com.ming.ppsg2.entity.Device;
import com.ming.ppsg2.entity.ThreeDimensional;
import com.ming.ppsg2.enums.GeneralsEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/20 0020.
 */
public class ArmsUtils {

    public static void main(String[] args) {
        GeneralsEnum.WarDevice warDevice = GeneralsEnum.WarDevice.gun;
        double rate = 1d;
        List<Device> list = new ArrayList<>();
        list.add(new Device(133,131,133,warDevice,rate));
        list.add(new Device(131,133,133,warDevice,rate));
        list.add(new Device(133,133,132,warDevice,rate));
        compareArmsList(warDevice,list);
    }

    public static void compareArmsList(GeneralsEnum.WarDevice warDevice,List<Device> list){
        int [] com = new int[list.size()];
        for (int i=0;i<list.size();i++){
            com[i] = i;
        }
        int k = 2;
        StackUtil.f(com,k,0,0);
        List<List<Integer>> lists = StackUtil.lists;
        System.out.println(lists);
        for (List<Integer> list1 : lists){
            compareArms(warDevice,list.get(list1.get(0)),list.get(list1.get(1)));
        }
    }

    public static void compareArms(GeneralsEnum.WarDevice warDevice,Device device1,Device device2){
        ThreeDimensional base1 = device1.getDeviceBaseThreeDimensional();
        ThreeDimensional base2 = device2.getDeviceBaseThreeDimensional();
        System.out.println("战器("+base1.getForce()+"/"+base1.getIntellect()+"/"+base1.getTroops()+")"+" PK 战器("+base2.getForce()+"/"+base2.getIntellect()+"/"+base2.getTroops()+")");
        System.out.println("基础三维比较");
        Integer baseForce = base1.getForce()-base2.getForce();
        Integer baseIntellect = base1.getIntellect()-base2.getIntellect();
        Integer baseTroops = base1.getTroops()-base2.getTroops();
        System.out.println("武力："+base1.getForce()+" "+base2.getForce()+" "+baseForce);
        System.out.println("智力："+base1.getIntellect()+" "+base2.getIntellect()+" "+baseIntellect);
        System.out.println("兵力："+base1.getTroops()+" "+base2.getTroops()+" "+baseTroops);

        System.out.println("强化三维比较");
        ThreeDimensional strengthen1 = device1.getDeviceStrengthenThreeDimensional();
        ThreeDimensional strengthen2 = device2.getDeviceStrengthenThreeDimensional();
        Integer strengthenForce = strengthen1.getForce()-strengthen2.getForce();
        Integer strengthenIntellect = strengthen1.getIntellect()-strengthen2.getIntellect();
        Integer strengthenTroops = strengthen1.getTroops()-strengthen2.getTroops();
        System.out.println("武力："+strengthen1.getForce()+" "+strengthen2.getForce()+" "+strengthenForce);
        System.out.println("智力："+strengthen1.getIntellect()+" "+strengthen2.getIntellect()+" "+strengthenIntellect);
        System.out.println("兵力："+strengthen1.getTroops()+" "+strengthen2.getTroops()+" "+strengthenTroops);

        System.out.println("淬炼三维比较");
        ThreeDimensional quenching1 = device1.getDeviceQuenchingThreeDimensional();
        ThreeDimensional quenching2 = device2.getDeviceQuenchingThreeDimensional();
        Integer quenchingForce = quenching1.getForce()-quenching2.getForce();
        Integer quenchingIntellect = quenching1.getIntellect()-quenching2.getIntellect();
        Integer quenchingTroops = quenching1.getTroops()-quenching2.getTroops();
        System.out.println("武力："+quenching1.getForce()+" "+quenching2.getForce()+" "+quenchingForce);
        System.out.println("智力："+quenching1.getIntellect()+" "+quenching2.getIntellect()+" "+quenchingIntellect);
        System.out.println("兵力："+quenching1.getTroops()+" "+quenching2.getTroops()+" "+quenchingTroops);

        System.out.println("战器战力比较");
        Integer total1 = base1.getTotalZl()+strengthen1.getTotalZl()+quenching1.getTotalZl();
        Integer total2 = base2.getTotalZl()+strengthen2.getTotalZl()+quenching2.getTotalZl();
        System.out.println("战力："+total1+" "+total2+" "+(total1-total2));

    }

}
