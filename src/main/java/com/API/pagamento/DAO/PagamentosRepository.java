package com.API.pagamento.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.API.pagamento.model.Pagamentos;

@Repository
public interface PagamentosRepository extends JpaRepository<Pagamentos, Long> {

	public default Pagamentos update(Long id, Pagamentos pagamento) {
		Optional<Pagamentos> optPagamento = findById(id);
		Pagamentos newPagamento = optPagamento.get();
		
		if(newPagamento.getStatus().equalsIgnoreCase("Processado com Sucesso")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else if(newPagamento.getStatus().equalsIgnoreCase("Processado com Falha")) {
			if(!pagamento.getStatus().equalsIgnoreCase("Pendente de Processamento")) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
		
		
		newPagamento.setStatus(pagamento.getStatus());
		return save(newPagamento);
	}

	List<Pagamentos> findBycodDebito(String codDebito);

	List<Pagamentos> findByDocumento(String documento);

	List<Pagamentos> findByStatus(String status);

	public default void remove(Long id) {
		Optional<Pagamentos> optPagamento = findById(id);
		Pagamentos newPagamento = optPagamento.get();
		if(newPagamento.getStatus().equals("Pendente de Processamento")) {
			this.deleteById(id);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	


}
