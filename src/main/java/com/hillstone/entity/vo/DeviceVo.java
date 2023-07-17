package com.hillstone.entity.vo;

import com.hillstone.validate.DeleteGroup;
import com.hillstone.validate.InsertGroup;
import com.hillstone.validate.QueryGroup;
import com.hillstone.validate.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class DeviceVo {


    @NotNull(groups = {InsertGroup.class, DeleteGroup.class, UpdateGroup.class},message = "validate.device.sn")
    @Length(groups = {InsertGroup.class, DeleteGroup.class, UpdateGroup.class,QueryGroup.class},max = 16,min = 16,message = "validate.device.sn.length")
    private String sn;

    //ipv4十进制正则
    @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$",groups = {QueryGroup.class,InsertGroup.class,UpdateGroup.class},message = "validate.device.ip")
    @NotNull(groups = {InsertGroup.class},message = "validate.device.ip.nu")
    private String ip;
    @Length(groups = {InsertGroup.class,UpdateGroup.class,QueryGroup.class},max = 32,min = 1,message = "validate.device.hostname")
    @NotNull(groups = {InsertGroup.class},message = "validate.device.hostname.nu")
    private String hostname;

    @Length(groups = {InsertGroup.class,UpdateGroup.class},max = 9,min = 9,message = "validate.device.customerId")
    @NotNull(groups = {InsertGroup.class},message = "validate.device.customerId.length")
    @Pattern(regexp = "\\d+",groups = {InsertGroup.class,UpdateGroup.class},message = "validate.device.customerId.nu")
    private String customerId;

    @Length(min = 1,max = 32,groups = QueryGroup.class,message = "validate.device.customerName")
    private String customerName;

    @NotNull(groups = QueryGroup.class,message = "validate.device.page")
    @Range(groups = QueryGroup.class,min = 1,max = 100,message = "validate.device.page")
    private Integer page;
    @NotNull(groups = QueryGroup.class,message = "validate.device.limit")
    @Range(groups = QueryGroup.class,min = 20,max = 50,message = "validate.device.limit")
    private Integer limit;
    @Min(value = 0,groups = QueryGroup.class,message = "validate.device.startTime")
    private Long startTime;
    @Min(value = 0,groups = QueryGroup.class,message = "validate.device.endTime")
    private Long endTime;
}
