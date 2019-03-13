package org.rcisoft.business.system.other.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.other.service.OtherService;
import org.rcisoft.dao.SysImageDao;
import org.rcisoft.entity.SysImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by JiChao on 2019/3/13.
 * 登录页背景图
 */
@Service
public class OtherServiceImpl implements OtherService {

    /** 根路径 */
    @Value("${location.path}")
    String path;
    /** 背景图文件夹 */
    @Value("${location.image}")
    String image;
    /** 访问路径 */
    @Value("${location.url}")
    String url;

    @Autowired
    SysImageDao sysImageDao;

    @Override
    public List<SysImage> queryImageUrlList() {
        List<SysImage> list = sysImageDao.selectAll();
        // 将访问路径拼到url中
        list.forEach(sysImage -> {
            sysImage.setUrl(url + image + "/" + sysImage.getUrl());
        });
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public ServiceResult uploadImage(MultipartFile file) {
        // 返回值
        Integer result = 0;
        // 后缀
        String suffix = "";
        String[] fileNameArray = StringUtils.split(file.getOriginalFilename(), ".");
        if (fileNameArray.length > 1)
            suffix = "." + fileNameArray[fileNameArray.length - 1];
        // 新的文件名
        String fileName = UuidUtil.create32() + suffix;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path + image + File.separator + fileName));
            result = sysImageDao.insert(new SysImage(null, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ServiceResult(result, url + image + "/" + fileName);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public Integer deleteImage(Integer id) {
        SysImage sysImage = sysImageDao.selectByPrimaryKey(id);
        File file = new File(path + image + File.separator + sysImage.getUrl());
        if (file.exists())
            file.delete();
        return sysImageDao.deleteByPrimaryKey(id);
    }
}
