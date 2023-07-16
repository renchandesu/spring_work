package com.hillstone.service;

import com.hillstone.dao.DeviceDao;
import com.hillstone.dao.RedisClient;
import com.hillstone.entity.po.Device;
import com.hillstone.entity.vo.DeviceCustomerVo;
import com.hillstone.entity.vo.DeviceVo;
import com.hillstone.util.BeanUtil;
import com.hillstone.util.R;
import com.hillstone.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hillstone.constant.Constants.SUCCESS;

@Service
@Slf4j
public class DeviceService {

    @Autowired
    DeviceDao deviceDao;
    @Autowired
    RedisClient redisClient;

    @Transactional(rollbackFor = Exception.class)
    public R addDevice(DeviceVo deviceVo) {
        Device device = BeanUtil.copyBean(deviceVo, Device.class);
        deviceDao.addDevice(device);
        deviceDao.addDevicePerson(device.getSn(),deviceVo.getCustomerId());
        log.info("新增设备成功 [{}] 操作人员id:[{}]",device,deviceVo.getCustomerId());
        return ResultUtil.result(SUCCESS,null,null);
    }

    @Transactional(rollbackFor = Exception.class)
    public R deleteDevice(String sn) {
        deviceDao.deleteDevice(sn);
        deviceDao.deleteRelation(sn);
        log.info("删除设备成功 sn:[{}]",sn);
        return ResultUtil.result(SUCCESS,null,null);
    }

    @Transactional(rollbackFor = Exception.class)
    public R updateDeviceInfo(DeviceVo deviceVo) {
        Device device = BeanUtil.copyBean(deviceVo, Device.class);
        deviceDao.updateDevice(device);
        deviceDao.updateRelation(deviceVo.getSn(),deviceVo.getCustomerId());
        return ResultUtil.result(SUCCESS,null,null);
    }

    public R<List<DeviceCustomerVo>> queryPage(DeviceVo deviceVo) {
        List<DeviceCustomerVo> res = deviceDao.queryDevicePage(deviceVo);
        for (DeviceCustomerVo re : res) {
            re.setOnline(Integer.valueOf(redisClient.getStatus(re.getSn())));
        }
        return ResultUtil.result(SUCCESS,res,null);
    }
}
