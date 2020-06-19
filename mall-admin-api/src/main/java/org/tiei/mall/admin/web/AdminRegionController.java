package org.tiei.mall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tiei.mall.admin.vo.RegionVo;
import org.tiei.mall.core.util.ResponseUtil;
import org.tiei.mall.db.domain.mallRegion;
import org.tiei.mall.db.service.mallRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/region")
@Validated
public class AdminRegionController {
    private final Log logger = LogFactory.getLog(AdminRegionController.class);

    @Autowired
    private mallRegionService regionService;

    @GetMapping("/clist")
    public Object clist(@NotNull Integer id) {
        List<mallRegion> regionList = regionService.queryByPid(id);
        return ResponseUtil.okList(regionList);
    }

    @GetMapping("/list")
    public Object list() {
        List<RegionVo> regionVoList = new ArrayList<>();

        List<mallRegion> mallRegions = regionService.getAll();
        Map<Byte, List<mallRegion>> collect = mallRegions.stream().collect(Collectors.groupingBy(mallRegion::getType));
        byte provinceType = 1;
        List<mallRegion> provinceList = collect.get(provinceType);
        byte cityType = 2;
        List<mallRegion> city = collect.get(cityType);
        Map<Integer, List<mallRegion>> cityListMap = city.stream().collect(Collectors.groupingBy(mallRegion::getPid));
        byte areaType = 3;
        List<mallRegion> areas = collect.get(areaType);
        Map<Integer, List<mallRegion>> areaListMap = areas.stream().collect(Collectors.groupingBy(mallRegion::getPid));

        for (mallRegion province : provinceList) {
            RegionVo provinceVO = new RegionVo();
            provinceVO.setId(province.getId());
            provinceVO.setName(province.getName());
            provinceVO.setCode(province.getCode());
            provinceVO.setType(province.getType());

            List<mallRegion> cityList = cityListMap.get(province.getId());
            List<RegionVo> cityVOList = new ArrayList<>();
            for (mallRegion cityVo : cityList) {
                RegionVo cityVO = new RegionVo();
                cityVO.setId(cityVo.getId());
                cityVO.setName(cityVo.getName());
                cityVO.setCode(cityVo.getCode());
                cityVO.setType(cityVo.getType());

                List<mallRegion> areaList = areaListMap.get(cityVo.getId());
                List<RegionVo> areaVOList = new ArrayList<>();
                for (mallRegion area : areaList) {
                    RegionVo areaVO = new RegionVo();
                    areaVO.setId(area.getId());
                    areaVO.setName(area.getName());
                    areaVO.setCode(area.getCode());
                    areaVO.setType(area.getType());
                    areaVOList.add(areaVO);
                }

                cityVO.setChildren(areaVOList);
                cityVOList.add(cityVO);
            }
            provinceVO.setChildren(cityVOList);
            regionVoList.add(provinceVO);
        }

        return ResponseUtil.okList(regionVoList);
    }
}
