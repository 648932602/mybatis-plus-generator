# MyBatis-Plus 代码生成脚手架项目
## 1.使用说明
使用方法：
1. 在 instance 目录下新建一个类，具体内容参照DemoGenerator.java文件。
2. 直接执行 main 方法。

注意：
* 目前仅支持基本数据类型字段的生成。
* 此工具只会生成 DO 类、Mapper 类和 Mapper 文件，其他的不会生成。
* 此工具会完全覆盖之前已经生成过的代码，所以尽量不要在 Mapper 中做改动。若需要写自定义 SQL 建议创建新的 Mapper 类及对应 xml 文件。
* 若需要使用原生 Service 及 ServiceImpl，可以通用手动继承，代码如下：
```java
// Service
import com.baomidou.mybatisplus.extension.service.IService;

public interface DemoService extends IService<DemoDO> {

}

// ServiceImpl
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class DemoServiceImpl extends ServiceImpl<DemoMapper, DemoDO> {

}
```
* 若需要支持自动填充，则需要实现 MetaObjectHandler 接口，并使用Spring注入到容器中。代码如下：
```java
import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * 注意：此处的 fieldName 是生成的 DO 类的字段名，
 * 非数据库中的字段名。
 */
public class BaseMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        this.strictInsertFill(metaObject, "createdAt", Date.class, new Date());
        // 创建人
        this.strictInsertFill(metaObject, "createdUser", Long.class, 1L);
        // 更新时间
        this.strictInsertFill(metaObject, "updatedAt", Date.class, new Date());
        // 更新人
        this.strictInsertFill(metaObject, "updatedUser", Long.class, 1L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        this.strictInsertFill(metaObject, "updatedAt", Date.class, new Date());
        // 更新人
        this.strictInsertFill(metaObject, "updatedUser", Long.class, 1L);
    }

}

```
## 3.Todo List
- [x] 支持自定义类型字段的映射。
- [ ] 支持生成目录自由配置。
- [ ] 支持 Service 类及 ServiceImpl 类生成的相关配置属性。
- [x] 支持配置自动填充字段。