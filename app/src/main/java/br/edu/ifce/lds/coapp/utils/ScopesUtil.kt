package br.edu.ifce.lds.coapp.utils

import java.lang.annotation.Documented
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by lds on 7/13/17.
 */
@Documented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ContactScope

@Documented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PlanScope

@Documented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginScope