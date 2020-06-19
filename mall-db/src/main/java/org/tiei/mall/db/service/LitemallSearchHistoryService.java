package org.tiei.mall.db.service;

import com.github.pagehelper.PageHelper;
import org.tiei.mall.db.dao.mallSearchHistoryMapper;
import org.tiei.mall.db.domain.mallSearchHistory;
import org.tiei.mall.db.domain.mallSearchHistoryExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class mallSearchHistoryService {
    @Resource
    private mallSearchHistoryMapper searchHistoryMapper;

    public void save(mallSearchHistory searchHistory) {
        searchHistory.setAddTime(LocalDateTime.now());
        searchHistory.setUpdateTime(LocalDateTime.now());
        searchHistoryMapper.insertSelective(searchHistory);
    }

    public List<mallSearchHistory> queryByUid(int uid) {
        mallSearchHistoryExample example = new mallSearchHistoryExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        example.setDistinct(true);
        return searchHistoryMapper.selectByExampleSelective(example, mallSearchHistory.Column.keyword);
    }

    public void deleteByUid(int uid) {
        mallSearchHistoryExample example = new mallSearchHistoryExample();
        example.or().andUserIdEqualTo(uid);
        searchHistoryMapper.logicalDeleteByExample(example);
    }

    public List<mallSearchHistory> querySelective(String userId, String keyword, Integer page, Integer size, String sort, String order) {
        mallSearchHistoryExample example = new mallSearchHistoryExample();
        mallSearchHistoryExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return searchHistoryMapper.selectByExample(example);
    }
}
