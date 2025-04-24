# Classpy


[![Maven Central](https://img.shields.io/maven-central/v/tech.medivh/shengsheng-classpy.svg?label=Maven%20Central)](https://search.maven.org/artifact/tech.medivh/shengsheng-classpy)


This Project is forked from [classpy](https://github.com/zxh0/classpy)

The GUI part has been removed, and it is now a standalone library for reading `.class` files.

You can add it directly to your dependencies from the Maven repository.


## Use

add dependency to your project

### Maven

```xml

<dependency>
    <groupId>tech.medivh</groupId>
    <artifactId>shengsheng-classpy</artifactId>
    <version>1.0.0</version>
</dependency>

```

### Gradle

```kotlin
implementation("tech.medivh:shengsheng-classpy:1.0.0")
```

### API

```java
ClassFile classFile = new ClassFileParser().parse(new File("path/to/your/classfile.class"));
```



## License

This project retains the original license and author attribution from the upstream repository.
