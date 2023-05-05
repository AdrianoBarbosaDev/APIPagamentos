package com.API.pagamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.pagamento.DAO.PagamentosDAO;
import com.API.pagamento.model.Pagamentos;

@Service
public class PagamentosService {
	
    @Autowired
    private PagamentosDAO pagamentosDAO;

    public List<Pagamentos> findBycodDebito(String CodDebito) {
        return pagamentosDAO.findBycodDebito(CodDebito);
    }
    
    public List<Pagamentos> findByDocumento(String Documento) {
        return pagamentosDAO.findByDocumento(Documento);
    }
    
    public List<Pagamentos> findByStatus(String status) {
        return pagamentosDAO.findByStatus(status);
    }
    
//    public void remove(Long id) {
//    	pagamentosDAO.deleteById(id);
//    }
    
}
