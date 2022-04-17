package br.com.rafacdev.pix

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid

@Entity
@Table(uniqueConstraints = [UniqueConstraint(
    name = "uk_chave_pix",
    columnNames = ["chave"]
)])
class ChavePix(
    @Column(nullable = false)
    val clienteId: UUID,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoDeChave: TipoDeChave,

    @Column(unique = true, nullable = false)
    val chave: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoDeConta: TipoDeConta,

    @field:Valid
    @Embedded
    val conta: ContaAssociada
) {
    @Id
    @GeneratedValue
    val id: UUID? = null
    @Column(nullable = false)
    val criadaEm: LocalDateTime = LocalDateTime.now()


    override fun toString(): String {
        return "ChavePix(clienteId='$clienteId', tipoDeChave=$tipoDeChave, chave='$chave', tipoDeConta=$tipoDeConta, conta=$conta, id=$id, criadaEm=$criadaEm)"
    }
}