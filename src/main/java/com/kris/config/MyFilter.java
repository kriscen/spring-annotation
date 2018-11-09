package com.kris.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyFilter implements TypeFilter {
    /**
     *
     * @param metadataReader
     *          读取到当前正在扫描的类的信息
     * @param metadataReaderFactory
     *          工厂类，获取到其他类的信息
     * @return 匹配返回true
     * @throws IOException
     */
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类注解信息
        AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
        //获取当前类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类资源信息，比如类路径
        Resource resource = metadataReader.getResource();
        //当前类名
        String className = classMetadata.getClassName();
        return false;
    }
}
