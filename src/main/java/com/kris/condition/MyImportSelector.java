package com.kris.condition;

import com.kris.bean.Red;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//自定义逻辑返回需要导入的组件
public class MyImportSelector implements ImportSelector {

    //返回值就是要导入到容器中的组件全类名
    //importingClassMetadata：当前标注@import 注解类的所有注解信息，例如MainConfig2的注解
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.kris.bean.Red","com.kris.bean.Bule"};
    }
}
