package org.rcisoft.business.system.other.service;

import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.entity.SysImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by JiChao on 2019/3/13.
 * 登录页背景图
 */
public interface OtherService {

    /**
     * 查询所有背景图片的路径
     * @return
     */
    List<SysImage> queryImageUrlList();

    /**
     * 上传图片
     * @param file 文件对象
     * @return
     */
    ServiceResult uploadImage(MultipartFile file);

    /**
     * 根据id删除文件及数据库记录
     * @param id
     * @return
     */
    Integer deleteImage(Integer id);

}
