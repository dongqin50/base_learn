package com.zy.java_base.designPattern.jdk89.lambda;

import java.util.Objects;
import java.util.function.Function;

public interface YFunction<T,R>{

    R apply(T t);

    default <V> YFunction<T, V> andThen(YFunction<? super R, ? extends V> var1) {
        Objects.requireNonNull(var1);
        return (T t) -> var1.apply(apply(t));
    }

    default <V> Function<V, R> compose(YFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

//    default <V> Function<V, R> compose(YFunction<? super V, ? extends T> before) {
//        Objects.requireNonNull(before);
//        return (V v) -> apply(before.apply(v));
//    }

}
