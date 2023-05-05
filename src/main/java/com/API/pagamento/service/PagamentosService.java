package com.API.pagamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.pagamento.Repository.PagamentosRepository;
import com.API.pagamento.model.Pagamentos;

@Service
public class PagamentosService {
	
    @Autowired
    private PagamentosRepository pagamentosRepository;

    public List<Pagamentos> findBycodDebito(String CodDebito) {
        return pagamentosRepository.findBycodDebito(CodDebito);
    }
    
    public List<Pagamentos> findByDocumento(String Documento) {
        return pagamentosRepository.findByDocumento(Documento);
    }
    
    public List<Pagamentos> findByStatus(String status) {
        return pagamentosRepository.findByStatus(status);
    }
    
    
}
