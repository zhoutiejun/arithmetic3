package com.model.arithmetic3.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/5/19 0019 下午 14:39
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/5/19 0019 下午 14:39
 */
@Data
public class FileLogVO {

    private String fileName;

    private Integer type;

    private Integer size;

    private String createTime;
}
