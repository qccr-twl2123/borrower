package com.trj.jk.web.annotation;

import java.lang.annotation.*;

/**
 * token校验
 * Created by xierongli on 17/8/2.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenCheck {

}
