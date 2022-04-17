package br.com.rafacdev.pix.registra

import br.com.rafacdev.RegistraChavePixRequest
import br.com.rafacdev.TipoDeChave
import br.com.rafacdev.TipoDeConta

fun RegistraChavePixRequest.toModel() : NovaChavePix{
    return NovaChavePix(
        clienteId = clienteId,
        tipoDeChave = when(tipoDeChave) {
            TipoDeChave.UNKNOW_TIPO_CHAVE -> null
            else -> br.com.rafacdev.pix.TipoDeChave.valueOf(tipoDeChave.name)
        },
        chave = chave,
        tipoDeConta = when(tipoDeConta) {
            TipoDeConta.UNKNOW_TIPO_CONTA -> null
            else -> br.com.rafacdev.pix.TipoDeConta.valueOf(tipoDeConta.name)
        }
    )
}