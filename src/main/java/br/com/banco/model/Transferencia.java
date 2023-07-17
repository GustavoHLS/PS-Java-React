package br.com.banco.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transferencia")
public class Transferencia {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "data_transferencia", nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssx")
  private OffsetDateTime data;
  
  @Column(name = "valor", nullable = false)
  private double valor;

  @Column(name = "tipo", nullable = false)
  private String tipo;

  @Column(name = "nome_operador_transacao")
  private String nomeOperador;

  @Column(name = "conta_id", nullable = false)
  private long contaId;

  public void SetValor(double valor) {
        this.valor = duasCasasDecimais(valor);
    }

  public double duasCasasDecimais(double valor) {
    return Math.round(valor * 100) / 100.00;
  }
}