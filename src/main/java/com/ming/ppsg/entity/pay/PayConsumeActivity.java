package com.ming.ppsg.entity.pay;

import java.util.Date;
import java.util.List;

/**
 * 充值消费活动
 * Created by Administrator on 2020/1/19 0019.
 */
public class PayConsumeActivity {

    private Long id;

    private Date startDate;//总开始时间
    private Date endDate;//总结束时间

    private String name;

    private Integer status;//结束、进行中

    private List<PayConsumeActivityDetail> detailList;
}
