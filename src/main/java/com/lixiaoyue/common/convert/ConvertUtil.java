package com.lixiaoyue.common.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JDK 8 + Spring Boot 2.7.x + MP 3.5.5 兼容版
 * 实体-DTO 互转工具类（无 List.of()，避免 JDK 版本报错）
 */
@Component
public class ConvertUtil {

    // ==================== 单对象互转 ====================
    /**
     * 源对象 → 目标对象（字段名一致自动映射）
     */
    public static <S, T> T convert(S source, Class<T> targetClass) {
        if (source == null) return null;
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("对象转换失败：" + e.getMessage(), e);
        }
    }

    /**
     * 源对象 → 目标对象（忽略 null 值）
     */
    public static <S, T> T convertIgnoreNull(S source, Class<T> targetClass) {
        if (source == null) return null;
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
            return target;
        } catch (Exception e) {
            throw new RuntimeException("对象转换失败（忽略 null）：" + e.getMessage(), e);
        }
    }

    /**
     * 源对象 → 目标对象（排除指定字段）
     */
    public static <S, T> T convertExclude(S source, Class<T> targetClass, String... excludeFields) {
        if (source == null) return null;
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target, excludeFields);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("对象转换失败（排除字段）：" + e.getMessage(), e);
        }
    }

    // ==================== 列表互转（JDK 8 兼容修改） ====================
    /**
     * 源列表 → 目标列表（字段名一致自动映射）
     * 替换 List.of() 为 Collections.emptyList()（JDK 8 支持）
     */
    public static <S, T> List<T> convertList(List<S> sourceList, Class<T> targetClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList(); // JDK 8 兼容：返回空列表（不可变）
            // 若需可变列表，用 new ArrayList<>()：return new ArrayList<>();
        }
        return sourceList.stream()
                .map(source -> convert(source, targetClass))
                .collect(Collectors.toList());
    }

    /**
     * 源列表 → 目标列表（忽略 null 值）
     */
    public static <S, T> List<T> convertListIgnoreNull(List<S> sourceList, Class<T> targetClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        return sourceList.stream()
                .map(source -> convertIgnoreNull(source, targetClass))
                .collect(Collectors.toList());
    }

    /**
     * 源列表 → 目标列表（排除指定字段）
     */
    public static <S, T> List<T> convertListExclude(List<S> sourceList, Class<T> targetClass, String... excludeFields) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        return sourceList.stream()
                .map(source -> convertExclude(source, targetClass, excludeFields))
                .collect(Collectors.toList());
    }

    // ==================== 辅助方法（JDK 8 兼容） ====================
    /**
     * 获取源对象中值为 null 的字段名（用于忽略 null 拷贝）
     */
    private static <S> String[] getNullPropertyNames(S source) {
        java.beans.BeanInfo beanInfo;
        try {
            beanInfo = java.beans.Introspector.getBeanInfo(source.getClass());
            return java.util.Arrays.stream(beanInfo.getPropertyDescriptors())
                    .filter(descriptor -> {
                        try {
                            return descriptor.getReadMethod().invoke(source) == null;
                        } catch (Exception e) {
                            return false;
                        }
                    })
                    .map(java.beans.PropertyDescriptor::getName)
                    .toArray(String[]::new);
        } catch (Exception e) {
            throw new RuntimeException("获取 null 字段失败：" + e.getMessage(), e);
        }
    }
}