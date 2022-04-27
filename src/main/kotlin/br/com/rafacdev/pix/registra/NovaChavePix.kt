package br.com.rafacdev.pix.registra

import br.com.rafacdev.pix.ChavePix
import br.com.rafacdev.pix.ContaAssociada
import br.com.rafacdev.pix.TipoDeChave
import br.com.rafacdev.pix.TipoDeConta
import br.com.rafacdev.shared.validation.ValidUUID
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
@ValidPixKey
data class NovaChavePix(
    @ValidUUID
    @field:NotBlank
    val clienteId: String?,
    @field:NotNull
    val tipoDeChave: TipoDeChave?,
    @field:Size(max = 77)
    val chave: String?,
    @field:NotNull
    val tipoDeConta: TipoDeConta?
) {
    fun toModel(conta: ContaAssociada): ChavePix {
        return ChavePix(
            clienteId = UUID.fromString(this.clienteId),
            tipoDeChave = TipoDeChave.valueOf(this.tipoDeChave!!.name),
            chave = if (this.tipoDeChave == TipoDeChave.ALEATORIA) UUID.randomUUID().toString() else this.chave!!,
            tipoDeConta = TipoDeConta.valueOf(this.tipoDeConta!!.name),
            conta = conta
        )
    }
}
