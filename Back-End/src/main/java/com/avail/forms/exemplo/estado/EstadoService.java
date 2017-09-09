package com.avail.forms.exemplo.estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avail.forms.exemplo.utils.GenericService;
import com.avail.forms.exemplo.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.ESTADO_PRIVATE_PATH)
public class EstadoService extends GenericService<EstadoEntity, Long> {
	@Autowired
	EstadoRepository estadoRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/pesquisaPorId")
	public EstadoEntity pesquisaPorId(@RequestParam Long id){
		return estadoRepo.findOne(id);
	}
}
