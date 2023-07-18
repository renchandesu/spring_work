package com.hillstone.util;

import com.hillstone.entity.po.Device;
import com.hillstone.entity.vo.DeviceVo;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class BeanUtilTest {

    @Test
    public void testCopyBean() {
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setIp("10.10.2.5");
        deviceVo.setSn("xxxxxxxxx");
        deviceVo.setHostname("hillstone");

        Device device = new Device();
        device.setIp("10.10.2.5");
        device.setSn("xxxxxxxxx");
        device.setHostname("hillstone");

        assertThat(BeanUtil.copyBean(deviceVo,Device.class),equalTo(device));


    }
}