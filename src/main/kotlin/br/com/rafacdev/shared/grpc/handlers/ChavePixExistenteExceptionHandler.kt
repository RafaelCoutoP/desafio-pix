package br.com.rafacdev.shared.grpc.handlers

import br.com.rafacdev.pix.ChavePixExistenteException
import br.com.rafacdev.shared.grpc.ExceptionHandler
import br.com.rafacdev.shared.grpc.ExceptionHandler.StatusWithDetails
import io.grpc.Status
import jakarta.inject.Singleton

@Singleton
class ChavePixExistenteExceptionHandler : ExceptionHandler<ChavePixExistenteException> {

    override fun handle(e: ChavePixExistenteException): StatusWithDetails {
        return StatusWithDetails(Status.ALREADY_EXISTS
                                       .withDescription(e.message)
                                       .withCause(e))
    }

    override fun supports(e: Exception): Boolean {
        return e is ChavePixExistenteException
    }
}