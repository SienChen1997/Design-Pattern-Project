package org.tiei.mall.db.service;

import com.github.pagehelper.PageHelper;
import org.tiei.mall.db.dao.mallOrderMapper;
import org.tiei.mall.db.dao.OrderMapper;
import org.tiei.mall.db.domain.mallOrder;
import org.tiei.mall.db.domain.mallOrderExample;
import org.tiei.mall.db.util.OrderUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class mallOrderService {
    @Resource
    private mallOrderMapper mallOrderMapper;
    @Resource
    private OrderMapper orderMapper;

    public int add(mallOrder order) {
        order.setAddTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        return mallOrderMapper.insertSelective(order);
    }

    public int count(Integer userId) {
        mallOrderExample example = new mallOrderExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return (int) mallOrderMapper.countByExample(example);
    }

    public mallOrder findById(Integer orderId) {
        return mallOrderMapper.selectByPrimaryKey(orderId);
    }

    public mallOrder findById(Integer userId, Integer orderId) {
        mallOrderExample example = new mallOrderExample();
        example.or().andIdEqualTo(orderId).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return mallOrderMapper.selectOneByExample(example);
    }

    private String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public int countByOrderSn(Integer userId, String orderSn) {
        mallOrderExample example = new mallOrderExample();
        example.or().andUserIdEqualTo(userId).andOrderSnEqualTo(orderSn).andDeletedEqualTo(false);
        return (int) mallOrderMapper.countByExample(example);
    }

    // TODO 这里应该产生一个唯一的订单，但是实际上这里仍然存在两个订单相同的可能性
    public String generateOrderSn(Integer userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String orderSn = now + getRandomNum(6);
        while (countByOrderSn(userId, orderSn) != 0) {
            orderSn = now + getRandomNum(6);
        }
        return orderSn;
    }

    public List<mallOrder> queryByOrderStatus(Integer userId, List<Short> orderStatus, Integer page, Integer limit, String sort, String order) {
        mallOrderExample example = new mallOrderExample();
        example.setOrderByClause(mallOrder.Column.addTime.desc());
        mallOrderExample.Criteria criteria = example.or();
        criteria.andUserIdEqualTo(userId);
        if (orderStatus != null) {
            criteria.andOrderStatusIn(orderStatus);
        }
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallOrderMapper.selectByExample(example);
    }

    public List<mallOrder> querySelective(Integer userId, String orderSn, LocalDateTime start, LocalDateTime end, List<Short> orderStatusArray, Integer page, Integer limit, String sort, String order) {
        mallOrderExample example = new mallOrderExample();
        mallOrderExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(orderSn)) {
            criteria.andOrderSnEqualTo(orderSn);
        }
        if(start != null){
            criteria.andAddTimeGreaterThanOrEqualTo(start);
        }
        if(end != null){
            criteria.andAddTimeLessThanOrEqualTo(end);
        }
        if (orderStatusArray != null && orderStatusArray.size() != 0) {
            criteria.andOrderStatusIn(orderStatusArray);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallOrderMapper.selectByExample(example);
    }

    public int updateWithOptimisticLocker(mallOrder order) {
        LocalDateTime preUpdateTime = order.getUpdateTime();
        order.setUpdateTime(LocalDateTime.now());
        return orderMapper.updateWithOptimisticLocker(preUpdateTime, order);
    }

    public void deleteById(Integer id) {
        mallOrderMapper.logicalDeleteByPrimaryKey(id);
    }

    public int count() {
        mallOrderExample example = new mallOrderExample();
        example.or().andDeletedEqualTo(false);
        return (int) mallOrderMapper.countByExample(example);
    }

    public List<mallOrder> queryUnpaid(int minutes) {
        mallOrderExample example = new mallOrderExample();
        example.or().andOrderStatusEqualTo(OrderUtil.STATUS_CREATE).andDeletedEqualTo(false);
        return mallOrderMapper.selectByExample(example);
    }

    public List<mallOrder> queryUnconfirm(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.minusDays(days);
        mallOrderExample example = new mallOrderExample();
        example.or().andOrderStatusEqualTo(OrderUtil.STATUS_SHIP).andShipTimeLessThan(expired).andDeletedEqualTo(false);
        return mallOrderMapper.selectByExample(example);
    }

    public mallOrder findBySn(String orderSn) {
        mallOrderExample example = new mallOrderExample();
        example.or().andOrderSnEqualTo(orderSn).andDeletedEqualTo(false);
        return mallOrderMapper.selectOneByExample(example);
    }

    public Map<Object, Object> orderInfo(Integer userId) {
        mallOrderExample example = new mallOrderExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        List<mallOrder> orders = mallOrderMapper.selectByExampleSelective(example, mallOrder.Column.orderStatus, mallOrder.Column.comments);

        int unpaid = 0;
        int unship = 0;
        int unrecv = 0;
        int uncomment = 0;
        for (mallOrder order : orders) {
            if (OrderUtil.isCreateStatus(order)) {
                unpaid++;
            } else if (OrderUtil.isPayStatus(order)) {
                unship++;
            } else if (OrderUtil.isShipStatus(order)) {
                unrecv++;
            } else if (OrderUtil.isConfirmStatus(order) || OrderUtil.isAutoConfirmStatus(order)) {
                uncomment += order.getComments();
            } else {
                // do nothing
            }
        }

        Map<Object, Object> orderInfo = new HashMap<Object, Object>();
        orderInfo.put("unpaid", unpaid);
        orderInfo.put("unship", unship);
        orderInfo.put("unrecv", unrecv);
        orderInfo.put("uncomment", uncomment);
        return orderInfo;

    }

    public List<mallOrder> queryComment(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.minusDays(days);
        mallOrderExample example = new mallOrderExample();
        example.or().andCommentsGreaterThan((short) 0).andConfirmTimeLessThan(expired).andDeletedEqualTo(false);
        return mallOrderMapper.selectByExample(example);
    }

    public void updateAftersaleStatus(Integer orderId, Short statusReject) {
        mallOrder order = new mallOrder();
        order.setId(orderId);
        order.setAftersaleStatus(statusReject);
        order.setUpdateTime(LocalDateTime.now());
        mallOrderMapper.updateByPrimaryKeySelective(order);
    }
}
