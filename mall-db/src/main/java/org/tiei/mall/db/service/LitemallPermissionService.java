package org.tiei.mall.db.service;

import org.tiei.mall.db.dao.mallPermissionMapper;
import org.tiei.mall.db.dao.mallRoleMapper;
import org.tiei.mall.db.domain.mallPermission;
import org.tiei.mall.db.domain.mallPermissionExample;
import org.tiei.mall.db.domain.mallRole;
import org.tiei.mall.db.domain.mallRoleExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class mallPermissionService {
    @Resource
    private mallPermissionMapper permissionMapper;

    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }

        mallPermissionExample example = new mallPermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<mallPermission> permissionList = permissionMapper.selectByExample(example);

        for(mallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }


    public Set<String> queryByRoleId(Integer roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }

        mallPermissionExample example = new mallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<mallPermission> permissionList = permissionMapper.selectByExample(example);

        for(mallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId) {
        if(roleId == null){
            return false;
        }

        mallPermissionExample example = new mallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Integer roleId) {
        mallPermissionExample example = new mallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        permissionMapper.logicalDeleteByExample(example);
    }

    public void add(mallPermission mallPermission) {
        mallPermission.setAddTime(LocalDateTime.now());
        mallPermission.setUpdateTime(LocalDateTime.now());
        permissionMapper.insertSelective(mallPermission);
    }
}
