package com.model.arithmetic3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/5/18 0018 下午 19:14
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/5/18 0018 下午 19:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private List<Float> readLine;

    private List<Float> blueLine;

    private Integer point;
}
