package com.space.rbq.store.service.impl;

import com.space.rbq.store.bean.Order;
import com.space.rbq.store.service.StoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhuzhe
 * @date 2018/6/7 10:14
 * @email 1529949535@qq.com
 */
@Transactional(readOnly = true)
@Service
public class StoreServiceImpl implements StoreService{

    @Transactional
    @Override
    public int update(Order order) {
        // 这里不做实现
        return 0;
    }
}
