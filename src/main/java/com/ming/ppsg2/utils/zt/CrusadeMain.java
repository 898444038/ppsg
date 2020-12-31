package com.ming.ppsg2.utils.zt;

import java.util.List;

public class CrusadeMain {

    public static void main(String[] args) throws Exception {
        start(60);
    }

    public static void start(int second) throws Exception {
        //TimeUnit.MILLISECONDS.sleep(seconds);
        List<Crusade> seconds = CrusadeService.getSeconds(second);
        List<String> morales = CrusadeService.getMorales(seconds);
        for (int i = 0; i < seconds.size(); i++) {
            //System.out.println(seconds.get(i));
        }
    }
}
