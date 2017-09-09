package com.avail.forms.exemplo.cidade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<CidadeEntity, Long> {
	
	public List<CidadeEntity> findByEstadoId(Long idEstado); 
	
	public List<CidadeEntity> findByEstadoSigla(String sigla);
	
	public CidadeEntity findByNomeCidadeAndEstadoSigla(String nomeCidade,String sigla);

}
