package devsuperior.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import devsuperior.demo.dto.SaleDto;
import devsuperior.demo.dto.SaleSuccessDto;
import devsuperior.demo.dto.SaleSumDto;
import devsuperior.demo.entities.Sale;
import devsuperior.demo.repositories.SaleRepository;
import devsuperior.demo.repositories.SellerRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Transactional(readOnly = true)
	public Page<SaleDto> findAll(Pageable pageable) {
		sellerRepository.findAll();
		Page<Sale> result = repository.findAll(pageable);
		return result.map(x -> new SaleDto(x));
	}
	
	@Transactional(readOnly = true)
	public List<SaleSumDto> amountGroupedBySeller() {
		return repository.amountGroupedBySeller();
	}
	
	@Transactional(readOnly = true)
	public List<SaleSuccessDto> successGroupedBySeller() {
		return repository.successGroupedBySeller();
	}
}