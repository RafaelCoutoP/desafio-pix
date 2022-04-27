package br.com.rafacdev.pix.registra

import br.com.rafacdev.KeymanagerRegistraGrpcServiceGrpc
import br.com.rafacdev.RegistraChavePixRequest
import br.com.rafacdev.RegistraChaveResponse
import br.com.rafacdev.shared.grpc.ErrorHandler
import io.grpc.stub.StreamObserver
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
@ErrorHandler
class RegistraChaveEndPoint(@Inject private val service: NovaChavePixService,)
    : KeymanagerRegistraGrpcServiceGrpc.KeymanagerRegistraGrpcServiceImplBase() {

    override fun registra(
        request: RegistraChavePixRequest,
        responseObserver: StreamObserver<RegistraChaveResponse>
    ) {
        val novaChave = request.toModel()
        val chaveCriada = service.registra(novaChave)

        responseObserver.onNext(RegistraChaveResponse.newBuilder()
            .setClienteId(chaveCriada.clienteId.toString())
            .setPixId(chaveCriada.id.toString())
            .build())
        responseObserver.onCompleted()
    }
}