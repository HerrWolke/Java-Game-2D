package de.marcus.javagame.framework.logging;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging {
    String displayName();
}
