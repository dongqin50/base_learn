package com.zy.java_base.annotation;


// 指定要解析的注解

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("myAnnotation.TestAnnotation")
// 指定JDK版本
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class MyAnnotationProcesser extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement te : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(te)) {
                TestAnnotation testAnnotation = element.getAnnotation(TestAnnotation.class);
                // do something
            }
        }
        return true;
    }
}

