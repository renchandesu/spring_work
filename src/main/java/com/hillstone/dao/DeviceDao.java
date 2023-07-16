package com.hillstone.dao;

import com.hillstone.entity.po.Device;
import com.hillstone.entity.vo.DeviceCustomerVo;
import com.hillstone.entity.vo.DeviceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DeviceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String INSERT_SQL = "INSERT INTO t_e_device(sn,ip,hostname,create_time,update_time) VALUES(?,?,?,?,?)";

    public static final String INSERT_RELATION_SQL = "INSERT INTO t_r_device_customer(sn,customer_id) VALUES(?,?)";

    public static final String DELETE_SQL = "DELETE FROM t_e_device WHERE sn = ?";

    public static final String DELETE_RELATION_SQL = "DELETE FROM t_r_device_customer WHERE sn = ?";

    public static final String UPDATE_RELATION_SQL = "UPDATE t_r_device_customer SET customer_id = ? WHERE sn = ?";

    public static final String QUERY_SQL = "SELECT d.sn,d.ip,d.hostname,c.customer_name,d.create_time"+
            " FROM t_e_device AS d LEFT JOIN t_r_device_customer as tr on d.sn = tr.sn LEFT JOIN t_e_customer as c on tr.customer_id = c.customer_id"+
            " WHERE 1=1 ";

    public void addDevice(Device device) {
        Long timestamp = new Date().getTime();
        jdbcTemplate.update(INSERT_SQL,device.getSn(),device.getIp(),device.getHostname(),timestamp,timestamp);
    }

    public void addDevicePerson(String sn, String customerId) {
        jdbcTemplate.update(INSERT_RELATION_SQL,sn,customerId);
    }

    public void deleteDevice(String sn) {
        jdbcTemplate.update(DELETE_SQL,sn);
    }

    public void deleteRelation(String sn) {
        jdbcTemplate.update(DELETE_RELATION_SQL,sn);
    }

    public void updateDevice(Device device) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE t_e_device SET ");
        List<Object> params = new ArrayList<>();
        if (device.getHostname() != null){
            sqlBuilder.append("hostname = ? , ");
            params.add(device.getHostname());
        }
        if (device.getIp() != null){
            sqlBuilder.append("ip = ? , ");
            params.add(device.getIp());
        }
        // 更新时间
        sqlBuilder.append("update_time = ? WHERE sn = ? ");
        params.add(new Date().getTime());
        params.add(device.getSn());
        jdbcTemplate.update(sqlBuilder.toString(),params.toArray());
    }

    public void updateRelation(String sn, String customerId) {
        jdbcTemplate.update(UPDATE_RELATION_SQL,customerId,sn);
    }

    public List<DeviceCustomerVo> queryDevicePage(DeviceVo deviceVo) {

        StringBuilder sb = new StringBuilder(QUERY_SQL);
        List<Object> params = new ArrayList<>();
        if (deviceVo.getSn() != null){
            sb.append("AND d.sn = ? ");
            params.add(deviceVo.getSn());
        }
        if (deviceVo.getHostname() != null){
            sb.append("AND d.hostname = ? ");
            params.add(deviceVo.getHostname());
        }
        if (deviceVo.getIp() != null){
            sb.append("AND d.ip = ? ");
            params.add(deviceVo.getIp());
        }
        if (deviceVo.getStartTime() != null){
            sb.append("AND d.create_time >= ? ");
            params.add(deviceVo.getStartTime());
        }
        if (deviceVo.getEndTime() != null){
            sb.append("AND d.create_time <= ? ");
            params.add(deviceVo.getEndTime());
        }
        if (deviceVo.getCustomerId() != null){
            sb.append("AND c.customer_id = ? ");
            params.add(deviceVo.getCustomerId());
        }
        sb.append("LIMIT ?,?");
        params.add((deviceVo.getPage() - 1) * deviceVo.getLimit());
        params.add(deviceVo.getLimit());
        return jdbcTemplate.queryForList(sb.toString(), params.toArray()).stream().map((stringObjectMap -> {
            String sn1 = (String) stringObjectMap.get("sn");
            String hostname = (String) stringObjectMap.get("hostname");
            String ip = (String) stringObjectMap.get("ip");
            Long createTime = (Long) stringObjectMap.get("create_time");
            String customerName = (String) stringObjectMap.get("customer_name");
            DeviceCustomerVo deviceCustomerVo = new DeviceCustomerVo();
            deviceCustomerVo.setCustomerName(customerName);
            deviceCustomerVo.setSn(sn1);
            deviceCustomerVo.setIp(ip);
            deviceCustomerVo.setHostname(hostname);
            deviceCustomerVo.setCreateTime(createTime);
            return deviceCustomerVo;
        })).collect(Collectors.toList());
    }
}
