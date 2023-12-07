// Admin Annotation

package dev.khetexpert.inc.objects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD, ElementType.PACKAGE})
public @interface Admin {

    // FOR MARKING PURPOSE

}
