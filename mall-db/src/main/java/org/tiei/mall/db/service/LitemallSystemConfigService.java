package org.tiei.mall.db.service;

import org.tiei.mall.db.dao.mallSystemMapper;
import org.tiei.mall.db.domain.mallSystem;
import org.tiei.mall.db.domain.mallSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class mallSystemConfigService {
    @Resource
    private mallSystemMapper systemMapper;

    public Map<String, String> queryAll() {
        mallSystemExample example = new mallSystemExample();
        example.or().andDeletedEqualTo(false);

        List<mallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> systemConfigs = new HashMap<>();
        for (mallSystem item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }

        return systemConfigs;
    }

    public Map<String, String> listMail() {
        mallSystemExample example = new mallSystemExample();
        example.or().andKeyNameLike("mall_mall_%").andDeletedEqualTo(false);
        List<mallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(mallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listWx() {
        mallSystemExample example = new mallSystemExample();
        example.or().andKeyNameLike("mall_wx_%").andDeletedEqualTo(false);
        List<mallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(mallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listOrder() {
        mallSystemExample example = new mallSystemExample();
        example.or().andKeyNameLike("mall_order_%").andDeletedEqualTo(false);
        List<mallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(mallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listExpress() {
        mallSystemExample example = new mallSystemExample();
        example.or().andKeyNameLike("mall_express_%").andDeletedEqualTo(false);
        List<mallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(mallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            mallSystemExample example = new mallSystemExample();
            example.or().andKeyNameEqualTo(entry.getKey()).andDeletedEqualTo(false);

            mallSystem system = new mallSystem();
            system.setKeyName(entry.getKey());
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());
            systemMapper.updateByExampleSelective(system, example);
        }

    }

    public void addConfig(String key, String value) {
        mallSystem system = new mallSystem();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        systemMapper.insertSelective(system);
    }
}
