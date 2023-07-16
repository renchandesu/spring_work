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


    @NotNull(groups = {InsertGroup.class, DeleteGroup.class, UpdateGroup.class, QueryGroup.class})
    @Length(groups = {InsertGroup.class, DeleteGroup.class, UpdateGroup.class,QueryGroup.class},max = 16,min = 16)
    private String sn;

    //ipv4十进制正则
    @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$",groups = {QueryGroup.class,InsertGroup.class,UpdateGroup.class})
    @NotNull(groups = {InsertGroup.class})
    private String ip;
    @Length(groups = {InsertGroup.class,UpdateGroup.class,QueryGroup.class},max = 32,min = 1)
    @NotNull(groups = {InsertGroup.class})
    private String hostname;

    @Length(groups = {InsertGroup.class,UpdateGroup.class,QueryGroup.class},max = 9,min = 9)
    @NotNull(groups = {InsertGroup.class})
    @Pattern(regexp = "\\d+",groups = {InsertGroup.class,UpdateGroup.class,QueryGroup.class})
    private String customerId;

    @NotNull(groups = QueryGroup.class)
    @Range(groups = QueryGroup.class,min = 1,max = 100)
    private Integer page;
    @NotNull(groups = QueryGroup.class)
    @Range(groups = QueryGroup.class,min = 20,max = 50)
    private Integer limit;
    @Min(value = 0,groups = QueryGroup.class)
    private Long startTime;
    @Min(value = 0,groups = QueryGroup.class)
    private Long endTime;
}
