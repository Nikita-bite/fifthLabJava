package org.example;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;

/**
 * Контейнер инициализирует поля,
 * помеченные аннотацией {@link AutoInjectable}, на основе конфигурации из properties-файла.
 *
 *
 * <p>Этот класс использует механизм рефлексии для поиска полей с аннотацией {@code @AutoInjectable}
 * и создает экземпляры классов, указанных в файле конфигурации {@code config.properties}.
 *
 *
 * <p><b>Пример файла конфигурации (config.properties):</b>
 * <pre>
 * org.example.SomeInterface=org.example.SomeImpl
 * org.example.SomeOtherInterface=org.example.SODoer
 * </pre>
 *
 * @param <T> тип объекта, который создаётся
 * @author Filippov Nikita
 * @version 1.0
 */
public class Injector<T> {
    public T inject(T obj) {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Class<?> objClass = obj.getClass();
        Field[] arrayOfFields = objClass.getDeclaredFields();
        for (Field f : arrayOfFields) {
            Annotation[] fieldAnnotations = f.getAnnotations();
            for (Annotation a : fieldAnnotations) {
                if (a.annotationType().equals(AutoInjectable.class)) {
                    String implementationClassName = properties.getProperty(f.getType().getName());
                    try {
                        Class<?> implementationClass = Class.forName(implementationClassName);
                        Object implementationInstance = implementationClass.getDeclaredConstructor().newInstance();
                        f.setAccessible(true);
                        f.set(obj, implementationInstance);
                    } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException | InstantiationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return obj;
    }
}