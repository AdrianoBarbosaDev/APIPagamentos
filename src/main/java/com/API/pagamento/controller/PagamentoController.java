package com.API.pagamento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.API.pagamento.DAO.PagamentosRepository;
import com.API.pagamento.model.Pagamentos;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentosRepository pagamentosDAO;
	
	@GetMapping("/{id}")
	public Pagamentos buscarPagamentosPorId(@PathVariable Long id) {
		Optional<Pagamentos> pagamento = pagamentosDAO.findById(id);
		if(pagamento.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return pagamento.get();
	}

	@GetMapping
	public List<Pagamentos> listaPagamentos(
			@RequestParam(name = "codDebito", required = false) String codDebito,
            @RequestParam(name = "documento", required = false) String documento,
            @RequestParam(name = "status", required = false) String status) {
		 if (codDebito != null) {
	            return pagamentosDAO.findBycodDebito(codDebito);
	        } else if (documento != null) {
	            return pagamentosDAO.findByDocumento(documento);
	        } else if (status != null) {
	            return pagamentosDAO.findByStatus(status);
	        } else {
	        	return pagamentosDAO.findAll();
	        }
	}
	
	@PostMapping
	public Pagamentos salvaPagamentos(@RequestBody Pagamentos pagamentos) {
		pagamentos.setStatus("Pendente de Processamento");
		if(pagamentos.getMetodoPagamento().equalsIgnoreCase("cartao_credito") || pagamentos.getMetodoPagamento().equalsIgnoreCase("cartao_debito")) {
			if(pagamentos.getNumeroCartao()!=null) {
				return pagamentosDAO.save(pagamentos);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		} else {
			if(pagamentos.getNumeroCartao()!=null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
		return pagamentosDAO.save(pagamentos);
	}
	
	@PutMapping("/{id}")
	public Pagamentos atualizarPagamentos(@PathVariable Long id, @RequestBody Pagamentos pagamento) {
		return pagamentosDAO.update(id, pagamento);
	}
	
	@DeleteMapping("/{id}")
	public void deletaProduto(@PathVariable Long id) {
		pagamentosDAO.remove(id);
	}
	
}
