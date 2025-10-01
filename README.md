# Класс Injector

[Injector](https://github.com/Nikita-bite/fifthLabJava/blob/main/src/main/java/org/example/Injector.java) инициализирует поля, помеченные аннотацией [AutoInjectable](https://github.com/Nikita-bite/fifthLabJava/blob/main/src/main/java/org/example/AutoInjectable.java), на основе конфигурации из properties-файла [config.properties](https://github.com/Nikita-bite/fifthLabJava/blob/main/src/main/resources/config.properties).

## Пример использования

### Пример файла конфигурации ([config.properties](https://github.com/Nikita-bite/fifthLabJava/blob/main/src/main/resources/config.properties)):
  ```
org.example.SomeInterface=org.example.SomeImpl
org.example.SomeOtherInterface=org.example.SODoer
```
  
### Пример класса 
```
class SomeBean {
    @AutoInjectable
    private SomeInterface field1;
    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo(){
        field1.doSomething();
        field2.doSomething();
    }
}
```


### Создание объекта
`SomeBean sb = (new Injector<SomeBean>()).inject(new SomeBean());`



## Результат работы

Для конфигурации:

![](https://github.com/Nikita-bite/fifthLabJava/blob/main/images/prop1.png)

Результат:

![](https://github.com/Nikita-bite/fifthLabJava/blob/main/images/res1.png)

Для конфигурации:

![](https://github.com/Nikita-bite/fifthLabJava/blob/main/images/prop2.png)

Результат:

![](https://github.com/Nikita-bite/fifthLabJava/blob/main/images/res2.png)
