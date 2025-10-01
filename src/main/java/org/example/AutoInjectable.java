package org.example;

import java.lang.annotation.*;

/**
 * Аннотация для пометки полей, которые должны быть автоматически инициализированы
 * контейнером {@link Injector}.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInjectable {
}
