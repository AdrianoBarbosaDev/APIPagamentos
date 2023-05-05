package com.API.pagamento.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Pagamentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codDebito;
	@Column(name = "Documento")
	private String documento;
	@Column
	private String metodoPagamento;
	@Column
	private String numeroCartao;
	@Column
	private BigDecimal valorPagamento;
	@Column(name = "Status")
	private String status;

	public Long getCodDebito() {
		return codDebito;
	}

	public void setCodDebito(Long codDebito) {
		this.codDebito = codDebito;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
}
