package com.space.rbq.store.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Order
 * @author zhuzhe
 * @date 2018/6/7 9:49
 * @email 1529949535@qq.com
 */
@Data
public class Order implements Serializable{

    private Long id;

    private Double price;

    private String remark;
}
