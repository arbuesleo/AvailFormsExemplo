package com.avail.forms.exemplo.cidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avail.forms.exemplo.utils.GenericService;
import com.avail.forms.exemplo.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.CIDADE_PRIVATE_PATH)
public class CidadeService extends GenericService<CidadeEntity, Long> {
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@RequestMapping(method = RequestMethod.GET, path = "/pesquisaPorIdEstado")
	public List<CidadeEntity> pesquisaPorIdEstado(@RequestParam Long id){
		return cidadeRepository.findByEstadoId(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/pesquisaPorSiglaEstado")
	public List<CidadeEntity> pesquisaPorSigla(@RequestParam String sigla){
		return cidadeRepository.findByEstadoSigla(sigla);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/pesquisaPorId")
	public CidadeEntity pesquisaPorId(@RequestParam Long id){
		return cidadeRepository.findOne(id);
	}
}
