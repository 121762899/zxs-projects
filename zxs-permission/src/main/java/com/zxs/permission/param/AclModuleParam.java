package com.zxs.permission.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: zxs
 * @Date: 2019/3/14 19:26
 * @Version 1.0
 * @Describe
 */
@Getter
@Setter
@ToString
public class AclModuleParam {

    private Integer id;

    @NotBlank(message = "权限模块名称不能为空")
    @Length(min = 2,max = 20,message = "权限模块名称的长度需要在2到200字之间")
    private String name;

    private Integer parentId = 0;

    @NotNull(message = "权限模块展示数据不能为空")
    private Integer seq;

    @NotNull(message = "权限模块状态不能为空")
    @Min(value = 0,message = "权限模块状态不合法")
    @Max(value = 1,message = "权限模块状态不合法")
    private Integer status;

    @Length(max = 200,message = "权限模块的备注信息需要在200字以内")
    private String remark;
}
