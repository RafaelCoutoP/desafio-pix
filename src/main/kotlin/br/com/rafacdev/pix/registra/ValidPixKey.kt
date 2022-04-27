package br.com.rafacdev.pix.registra

import jakarta.inject.Singleton
import javax.validation.Constraint
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@MustBeDocumented
@Target(CLASS, TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = [ValidPixKeyValidator::class])
annotation class ValidPixKey(
    val message: String = "chave Pix inválida (\${validatedValue.tipoDeChave})",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
)

@Singleton
class ValidPixKeyValidator: javax.validation.ConstraintValidator<ValidPixKey, NovaChavePix> {
    override fun isValid(value: NovaChavePix?, context: ConstraintValidatorContext): Boolean {

        if (value?.tipoDeChave == null) {
            return false
        }

        val valid = value.tipoDeChave.valida(value.chave)
        if (!valid) {
            context.disableDefaultConstraintViolation()
            context
                .buildConstraintViolationWithTemplate(context.defaultConstraintMessageTemplate)
                .addPropertyNode("chave").addConstraintViolation()
        }
        return valid
    }
}