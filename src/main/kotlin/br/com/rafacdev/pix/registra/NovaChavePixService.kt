package br.com.rafacdev.pix.registra

import br.com.rafacdev.pix.ChavePix
import br.com.rafacdev.pix.ChavePixExistenteException
import br.com.rafacdev.pix.ChavePixRepository
import br.com.rafacdev.pix.itau.ContasDeClientesItauClient
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import jakarta.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class NovaChavePixService(
    @Inject val repository: ChavePixRepository,
    @Inject val itauClient: ContasDeClientesItauClient
) {

    @Transactional
    fun registra(@Valid novaChavePix: NovaChavePix) : ChavePix{
        //1=> verificar se a chave já existe no sistema
        if (repository.existsByChave(novaChavePix.chave)){
            throw ChavePixExistenteException("Chave Pix '${novaChavePix.chave}' existente")
        }
        // buscar dados do microservice no ITAU
        val response = itauClient.buscaContaPorTipo(novaChavePix.clienteId!!, novaChavePix.tipoDeConta!!.name)
        val conta = response.body()?.toModel() ?: throw IllegalArgumentException("Cliente não encontrado no Itau")

        // Salvar no banco de dados
        val chave = novaChavePix.toModel(conta)
        repository.save(chave)
        return chave
    }
}