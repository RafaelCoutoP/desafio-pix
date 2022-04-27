package br.com.rafacdev.shared.grpc

import br.com.rafacdev.shared.grpc.handlers.DefaultExceptionHandler
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class ExceptionHandlerResolver(@Inject private val handlers: List<ExceptionHandler<*>>) {

    private var defaultHandler: ExceptionHandler<Exception> = DefaultExceptionHandler()

    constructor(handlers: List<ExceptionHandler<Exception>>, defaultHandler: ExceptionHandler<Exception>) : this(handlers){
        this.defaultHandler = defaultHandler
    }

    fun resolve(e: Exception) : ExceptionHandler<*>{
        val foundHandlers = handlers.filter { it.supports(e) }

            if (foundHandlers.size > 1)
                throw IllegalStateException("Too many handlers supporting the same exception '${e.javaClass.name}': $foundHandlers")

            return foundHandlers.firstOrNull() ?: defaultHandler
        }
}