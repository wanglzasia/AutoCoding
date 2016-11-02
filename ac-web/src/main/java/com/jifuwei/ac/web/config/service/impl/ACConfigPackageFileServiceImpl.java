package com.jifuwei.ac.web.config.service.impl;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.web.config.dao.ACConfigPackageFileDao;
import com.jifuwei.ac.web.config.data.po.ACConfigPackageFilePO;
import com.jifuwei.ac.web.config.data.vo.ACConfigPackageFileVO;
import com.jifuwei.ac.web.config.service.ACConfigPackageFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 模板文件信息实现类
 * Created by JFW on 2016/11/2.
 */
@Service("ACConfigPackageFileServiceImpl")
public class ACConfigPackageFileServiceImpl implements ACConfigPackageFileService {

    @Resource(name = "ACConfigPackageFileDaoImpl")
    private ACConfigPackageFileDao dataDao = null;

    @Override
    public void save(ACConfigPackageFileVO vo) {
        ACConfigPackageFilePO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po != null) {
            throw new ACServiceException(ACErrorMsg.ERROR_DUMPLICATE_PRIMARY_KEY);
        }
        po = vo.toPO();
        po.setCreate_by("sys");
        po.setCreate_time(new Timestamp(new Date().getTime()));
        dataDao.save(po);
    }
}
