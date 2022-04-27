package br.com.rafacdev.shared.grpc

import io.micronaut.aop.Around
import io.micronaut.context.annotation.Type
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(CLASS, FILE, PROPERTY_GETTER, PROPERTY_SETTER)
@Around
@Type(ExceptionHandlerInterceptor::class)
annotation class ErrorHandler()
