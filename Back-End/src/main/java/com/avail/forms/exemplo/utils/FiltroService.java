package com.avail.forms.exemplo.utils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avail.forms.exemplo.erros.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
@RequestMapping(path = ServicePath.FILTRO_PRIVATE_PATH)
public class FiltroService {

	@PersistenceContext
	EntityManager manager;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter owSemImg = objectMapper.writerWithView(ViewJson.SemImg.class);

	@SuppressWarnings("rawtypes")
	public Message message = new Message();
	//http://localhost:9999/api/private/filtro?campo=razaoSocial&criterio=Compre&condicao=contem&longClazzName=com.avail.farmaFacil.farmacia.FarmaciaEntity
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> pesquisa(@RequestParam String campo, @RequestParam String criterio, String condicao, String longClazzName) {
		try {
			
			Class<?> clazz = Class.forName(longClazzName);
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT o FROM ")
				.append(clazz.getSimpleName())
				.append(" o WHERE o.").append(campo);
			
			if ("contem".equals(condicao)) {
				sb.append(" like '%").append(criterio).append("%'");
			}else if ("diferente".equals(condicao)){
				sb.append(" <> '").append(criterio).append("'");
			}else if("igual".equals(condicao)){
				sb.append(" = '").append(criterio).append("'");
			}
			
			return new ResponseEntity<Object>(owSemImg.writeValueAsString(manager.createQuery(sb.toString(), clazz).getResultList()), HttpStatus.OK);
			
		} catch (NoResultException e) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			message.AddField("detalhe", e.getMessage());
			message.AddField("message", " Erro ao criar ResponseEntity");
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		}
	}
}
