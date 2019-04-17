package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusDevicePicture;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by JiChao on 2019/4/17.
 */
@Repository
public interface BusDevicePictureDao extends Mapper<BusDevicePicture> {

    /**
     * 根据项目Id查询设备图片
     */
    @Select("select * from bus_device_picture where project_id = #{proId};")
    List<BusDevicePicture> queryDevicePicByProId(@Param("proId") String proId);
}
