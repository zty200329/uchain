package com.uchain.accessctro;

import com.uchain.enums.RoleEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName RoleContro
 * @Author zty
 * @Date 19-7-12 下午7:45
 * @Description
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleContro {
    RoleEnum role();
}
