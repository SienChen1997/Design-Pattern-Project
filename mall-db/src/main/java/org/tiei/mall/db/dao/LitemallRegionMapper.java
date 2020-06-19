package org.tiei.mall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tiei.mall.db.domain.mallRegion;
import org.tiei.mall.db.domain.mallRegionExample;

public interface mallRegionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    long countByExample(mallRegionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    int deleteByExample(mallRegionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    int insert(mallRegion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    int insertSelective(mallRegion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    mallRegion selectOneByExample(mallRegionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    mallRegion selectOneByExampleSelective(@Param("example") mallRegionExample example, @Param("selective") mallRegion.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    List<mallRegion> selectByExampleSelective(@Param("example") mallRegionExample example, @Param("selective") mallRegion.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    List<mallRegion> selectByExample(mallRegionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    mallRegion selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") mallRegion.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    mallRegion selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") mallRegion record, @Param("example") mallRegionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") mallRegion record, @Param("example") mallRegionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(mallRegion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_region
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(mallRegion record);
}