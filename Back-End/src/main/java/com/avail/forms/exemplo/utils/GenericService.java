package com.avail.forms.exemplo.utils;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avail.availForms.CreateForms;
import com.avail.forms.exemplo.erros.ErrorServiceInterface;
import com.avail.forms.exemplo.erros.FieldsErrorDetalhe;
import com.avail.forms.exemplo.erros.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public abstract class GenericService<T extends BaseEntity<ID>, ID extends Serializable> implements ServiceMap {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	protected JpaRepository<T, ID> genericRepository;

	@Autowired
	public ErrorServiceInterface errorServiceInterface;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	FieldsErrorDetalhe fieldsErrorDetalhe = new FieldsErrorDetalhe();

	@SuppressWarnings("rawtypes")
	public Message message = new Message();

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter owSemImg = objectMapper.writerWithView(ViewJson.SemImg.class);
	ObjectWriter owComImg = objectMapper.writerWithView(ViewJson.ComImg.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> findAll() {
		this.LOGGER.info("Buscando todos os registros.");
		return montaResponse(this.genericRepository.findAll(), false);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody @Validated T entity, Errors errors) {
		this.LOGGER.info(String.format("Salvando Entidade [%s]", entity));

		if (errorServiceInterface.addErrors(fieldsErrorDetalhe, errors)) {
			this.LOGGER.error("Log erro hibernate validator" + fieldsErrorDetalhe);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(fieldsErrorDetalhe);
		}
		entity.setId(null);

		try {
			this.genericRepository.save(entity);
			message.AddField("mensage", "Salvo com sucesso");
			message.setData(entity);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (DataIntegrityViolationException e) {
			message.AddField("message",
					e.getRootCause().getMessage().replaceAll("Duplicate entry", "Registro duplicado"));
			message.AddField("detalhe", e.getMessage());
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		} catch (Exception e) {
			message.AddField("message", e.getLocalizedMessage());
			message.AddField("detalhe", e.getMessage());
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody @Validated T entity, Errors errors) {
		this.LOGGER.info(String.format("Atualizando Entidade [%s]", entity));

		if (errorServiceInterface.addErrors(fieldsErrorDetalhe, errors)) {
			this.LOGGER.error("Log erro hibernate validator" + fieldsErrorDetalhe);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(fieldsErrorDetalhe);
		}

		if (entity.getId() == null) {
			message.AddField("mensage", "Id não pode ser nulo");
			message.setData(entity);
		}

		try {
			this.genericRepository.save(entity);
			message.AddField("mensage", "Editado com sucesso");
			message.setData(entity);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (DataIntegrityViolationException e) {
			message.AddField("message",
					e.getRootCause().getMessage().replaceAll("Duplicate entry", "Registro duplicado"));
			message.AddField("detalhe", e.getMessage());
		} catch (Exception e) {
			message.AddField("message", e.getLocalizedMessage());
			message.AddField("detalhe", e.getMessage());

		}

		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestBody T entity) {
		this.LOGGER.info(String.format("Deletando a Entidade [%s]", entity));
		try {
			this.genericRepository.delete(entity);

			message.getAtributeMessage().clear();
			message.AddField("statusError", "success");
			message.AddField("message", " Deletado com sucesso");

			return ResponseEntity.status(HttpStatus.OK).body(message);

		} catch (DataIntegrityViolationException e) {
			message.AddField("message",
					" Não foi possivel deletar o registro pois este está relacionado com outros registros.");
			message.AddField("detalhe", e.getMessage());
		} catch (Exception e) {
			message.AddField("detalhe", e.getMessage());
			message.AddField("message", "  Registro não pode ser deletado");

		}

		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
	}

	public ResponseEntity<Object> montaResponse(List<?> obj, Boolean comImagem) {
		try {
			if (comImagem != null && comImagem == true) {
				return new ResponseEntity<Object>(this.owComImg.writeValueAsString(obj), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(this.owSemImg.writeValueAsString(obj), HttpStatus.OK);
			}

		} catch (JsonProcessingException e) {
			message.AddField("detalhe", e.getMessage());
			message.AddField("message", " Erro ao criar ResponseEntity");
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		}
	}
	
	public ResponseEntity<Object> montaResponse(Object obj, Boolean comImagem) {
		try {
			if (comImagem != null && comImagem == true) {
				return new ResponseEntity<Object>(this.owComImg.writeValueAsString(obj), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(this.owSemImg.writeValueAsString(obj), HttpStatus.OK);
			}

		} catch (Exception e) {
			message.AddField("detalhe", e.getMessage());
			message.AddField("message", " Erro ao criar ResponseEntity");
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/getTela")
	public ResponseEntity<Object> getDadosEntidade(T clazz){
		try {			
			return this.montaResponse(CreateForms.getDadosForm(clazz.getClass()), false);
		} catch (Exception e) {
			message.AddField("detalhe", e.getMessage());
			message.AddField("message", " Erro ao carregar dados da tela.");
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		}
	} 
	
	@RequestMapping(method = RequestMethod.GET, path = "/getTelaListagem")
	public ResponseEntity<Object> getDadosListagem(T clazz){
		try {			
			return this.montaResponse(CreateForms.getDadosListagem(clazz.getClass()), false);
		} catch (Exception e) {
			message.AddField("detalhe", e.getMessage());
			message.AddField("message", " Erro ao carregar dados da tela.");
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		}
	}
}
