package org.tiei.mall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.tiei.mall.admin.annotation.RequiresPermissionsDesc;
import org.tiei.mall.core.util.ResponseUtil;
import org.tiei.mall.core.validator.Order;
import org.tiei.mall.core.validator.Sort;
import org.tiei.mall.db.domain.mallSearchHistory;
import org.tiei.mall.db.service.mallSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/history")
public class AdminHistoryController {
    private final Log logger = LogFactory.getLog(AdminHistoryController.class);

    @Autowired
    private mallSearchHistoryService searchHistoryService;

    @RequiresPermissions("admin:history:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "搜索历史"}, button = "查询")
    @GetMapping("/list")
    public Object list(String userId, String keyword,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<mallSearchHistory> historyList = searchHistoryService.querySelective(userId, keyword, page, limit,
                sort, order);
        return ResponseUtil.okList(historyList);
    }
}
