package com.zxs.permission.dto;

import com.zxs.permission.model.SysAcl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

/**
 * @Author: zxs
 * @Date: 2019/3/15 16:11
 * @Version 1.0
 * @Describe
 */
@Getter
@Setter
@ToString
public class AclDto extends SysAcl {

    //是否要默认选中
    private boolean checked = false;

    //是否有权限操作
    private boolean hasAcl = false;

    public static AclDto adapt(SysAcl acl){
        AclDto dto = new AclDto();
        BeanUtils.copyProperties(acl,dto);
        return dto;
    }
}
