package com.API.pagamento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.API.pagamento.Repository.PagamentosRepository;
import com.API.pagamento.model.Pagamentos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentosRepository pagamentosRepository;
	
	@Operation(summary = "Busca Pagamento pelo ID")
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Pagamentos buscarPagamentosPorId(@PathVariable Long id) {
		Optional<Pagamentos> pagamento = pagamentosRepository.findById(id);
		if(pagamento.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return pagamento.get();
	}

	@Operation(summary = "Busca Todos os Pagamentos",description = "Busca a partir do Parâmetro fornecido, caso todos sejam nulos, retorna a lista de todos os pagamentos")
	@CrossOrigin(origins = "*")
	@GetMapping
	public List<Pagamentos> listaPagamentos(
			@RequestParam(name = "codDebito", required = false) String codDebito,
            @RequestParam(name = "documento", required = false) String documento,
            @RequestParam(name = "status", required = false) String status) {
		 if (codDebito != null) {
	            return pagamentosRepository.findBycodDebito(codDebito);
	        } else if (documento != null) {
	            return pagamentosRepository.findByDocumento(documento);
	        } else if (status != null) {
	            return pagamentosRepository.findByStatus(status);
	        } else {
	        	return pagamentosRepository.findAll();
	        }
	}
	
	@Operation(summary = "Salva Pagamentos")
	@CrossOrigin(origins = "*")
	@PostMapping
	public Pagamentos salvaPagamentos(@RequestBody Pagamentos pagamentos) {
		pagamentos.setStatus("Pendente de Processamento");
		if(pagamentos.getMetodoPagamento().equalsIgnoreCase("cartao_credito") || pagamentos.getMetodoPagamento().equalsIgnoreCase("cartao_debito")) {
			if(pagamentos.getNumeroCartao()!=null) {
				return pagamentosRepository.save(pagamentos);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		} else {
			if(pagamentos.getNumeroCartao()!=null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
		return pagamentosRepository.save(pagamentos);
	}
	
	@Operation(summary = "Atualiza Pagamentos")
	@PutMapping("/{id}")
	public Pagamentos atualizarPagamentos(@PathVariable Long id, @RequestBody Pagamentos pagamento) {
		return pagamentosRepository.update(id, pagamento);
	}
	
	@Operation(summary = "Deleta Pagamentos")
	@DeleteMapping("/{id}")
	public void deletaProduto(@PathVariable Long id) {
		pagamentosRepository.remove(id);
	}
	
}
