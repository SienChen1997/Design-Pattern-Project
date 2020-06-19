package org.tiei.mall.db.dao;

import org.apache.ibatis.annotations.Param;
import org.tiei.mall.db.domain.mallOrder;

import java.time.LocalDateTime;

public interface OrderMapper {
    int updateWithOptimisticLocker(@Param("lastUpdateTime") LocalDateTime lastUpdateTime, @Param("order") mallOrder order);
}