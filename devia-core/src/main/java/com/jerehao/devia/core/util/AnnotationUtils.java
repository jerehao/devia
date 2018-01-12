/*
 * Copyright (c) 2018, jerehao.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jerehao.devia.core.util;

import org.apache.commons.lang3.ArrayUtils;
import sun.reflect.annotation.AnnotationType;

import javax.inject.Named;
import java.lang.annotation.*;
import java.util.*;

/**
 * @author <a href="http://jerehao.com">jerehao</a>
 * @version 0.0.1 2018-01-12 14:12 jerehao
 */
public class AnnotationUtils {

    //to store already parsed
    private static final Map<Class<?>, Set<Class<? extends Annotation>>> classMetaAnnotationMap;

    static {
        classMetaAnnotationMap = new HashMap<>();
    }

    public static Set<Class<? extends Annotation>> getMetaAnnotations(Class<?> clazz) {
        final Set<Class<? extends Annotation>> alreadyParses = new HashSet<>();
        final List<Class<? extends Annotation>> needParses = new LinkedList<>();
        final Set<Class<? extends Annotation>> metaAnnotations = new HashSet<>();

        if(classMetaAnnotationMap.containsKey(clazz))
            return classMetaAnnotationMap.get(clazz);

        //exclude some meta annotation that we don't want to get
        alreadyParses.add(Target.class);
        alreadyParses.add(Documented.class);
        alreadyParses.add(Inherited.class);
        alreadyParses.add(Retention.class);

        //如果是注解
        //判断此注解定义时使用的注解
        if(clazz.isAnnotation()) {
            Annotation[] annotations = ((Class<? extends Annotation>) clazz).getAnnotations();
            for (Annotation annotation : annotations)
                needParses.add(annotation.annotationType());
        }
        else {
            //如果是普通类
            //获取普通类上的所有注解，获取所有注解的定义注解
            for(Annotation annotation : clazz.getAnnotations()) {
                for (Annotation annotation1 : annotation.annotationType().getAnnotations())
                    needParses.add(annotation1.annotationType());
            }
        }

        Class<? extends Annotation> annotationType;

        while (!needParses.isEmpty()) {
            Class<? extends Annotation> current = needParses.remove(0);
            if(!alreadyParses.contains(current) && isMetaAnnotation(current)) {
                alreadyParses.add(current);
                metaAnnotations.add(current);
                for(Annotation annotation : current.getDeclaredAnnotations()) {
                    if(!alreadyParses.contains(annotation.annotationType()))
                        needParses.add(annotation.annotationType());
                }
            }
        }

        classMetaAnnotationMap.put(clazz,metaAnnotations);
        return metaAnnotations;
    }

    //which @target value is only ElemType.ANNOTATION_TYPE is recognize a meta annotation
    private static boolean isMetaAnnotation(Class<? extends Annotation> clazz) {

        if(!clazz.isAnnotationPresent(Target.class))
            return false;

        Target targetAnnotation = clazz.getAnnotation(Target.class);
        ElementType[] elementTypes = targetAnnotation.value();
        return elementTypes.length == 1 && elementTypes[0] == ElementType.ANNOTATION_TYPE;
    }

    private AnnotationUtils(){}
}