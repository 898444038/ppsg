package com.ming.ppsg2.utils;

/**
 * Created by Administrator on 2020/4/28 0028.
 */
public class MapUtils {
/*

        */
/**

          * 按key排序(sort by key).

          * 

          * @param oriMap 要排序的map集合

          * @param isAsc（true:升序，false：降序）

          * @return

          *//*


                private Map<String, Long> sortMapByKey(Map<String, Long> oriMap, final boolean isAsc) {  

        Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();  

        if (oriMap != null && !oriMap.isEmpty()) {  

            List<Map.Entry<String, Long>> entryList = new ArrayList<Map.Entry<String, Long>>(oriMap.entrySet());  

            Collections.sort(entryList,  

                                        new Comparator<Map.Entry<String, Long>>() {  

                        public int compare(Entry<String, Long> entry1,  

                                                        Entry<String, Long> entry2) {  

  

                            String key1 = entry1.getKey();

                            String key2 = entry2.getKey();

                             

                            // 判定

                            int rst = 0;

                            if (isAsc) {

                                rst = key1.compareTo(key2);

                            } else {

                                rst = key2.compareTo(key1);

                            }

                            return rst; //1大于；0等于；-1小于

                        }  

                    });  

            Iterator<Map.Entry<String, Long>> iter = entryList.iterator();  

            Map.Entry<String, Long> tmpEntry = null;

            while (iter.hasNext()) {  

                tmpEntry = iter.next();  

                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  

            }  

        }  

        return sortedMap; 

    }



    */
/**

      * 按值排序(sort by value).

      * 

      * @param oriMap 要排序的map集合

      * @param isAsc（true:升序，false：降序）

      * @return

      *//*


            private Map<String, Long> sortMapByValueLong(Map<String, Long> oriMap, final boolean isAsc) {  

        Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();  

        if (oriMap != null && !oriMap.isEmpty()) {  

            List<Map.Entry<String, Long>> entryList = new ArrayList<Map.Entry<String, Long>>(oriMap.entrySet());  

            Collections.sort(entryList,  

                                        new Comparator<Map.Entry<String, Long>>() {  

                        public int compare(Entry<String, Long> entry1,  

                                                        Entry<String, Long> entry2) {  

                            long value1 = 0, value2 = 0;  

                            try {  

                                value1 = entry1.getValue();

                                value2 = entry2.getValue();

                            } catch (NumberFormatException e) {  

                                value1 = 0;  

                                value2 = 0;  

                            } 

                            // 判定

                            long rst = 0;

                            if (isAsc) {

                                rst = value1 - value2;

                            } else {

                                rst = value2 - value1;

                            }

                            return (int)rst;

                        }  

                    });  

            Iterator<Map.Entry<String, Long>> iter = entryList.iterator();  

            Map.Entry<String, Long> tmpEntry = null;

            while (iter.hasNext()) {  

                tmpEntry = iter.next();  

                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  

            }  

        }  

        return sortedMap; 

    }
*/

}
