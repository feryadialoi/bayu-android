package dev.feryadi.bayu.validations.rules.annotations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class Min(val value: Int)
