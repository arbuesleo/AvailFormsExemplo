package com.avail.forms.exemplo.usuario;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avail.forms.exemplo.erros.Message;
import com.avail.forms.exemplo.fileBase64.FileBase64Repository;
import com.avail.forms.exemplo.permissao.PermissaoEntity;
import com.avail.forms.exemplo.security.CurrentUser;
import com.avail.forms.exemplo.utils.GenericService;
import com.avail.forms.exemplo.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.USER_PATH)
public class UsuarioService extends GenericService<UsuarioEntity, Long> {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	private CurrentUser currentUser;

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	FileBase64Repository imgRepo;

	@Autowired
	UsuarioRepository usuarioRepo;

	@SuppressWarnings("rawtypes")
	Message msg = new Message();

	@Override
	public ResponseEntity<?> insert(@RequestBody @Validated UsuarioEntity user, Errors errors) {

		this.LOGGER.info("Criptografando senha do usuario.");

		user.setSenha(this.passwordEncoder.encode(user.getSenha()));

		Boolean currentUserIsAdmin = false;
		Boolean cadastrandoAdmin = false;
		for (PermissaoEntity permissao : user.getPermissoes()) {
			if (permissao.getRole().equals("ADMIN")) {
				cadastrandoAdmin = true;
				for (String permisaoUserLogado : currentUser.getActiveUser().getRoles()) {
					if (permisaoUserLogado.equals("ADMIN")) {
						currentUserIsAdmin = true;
					}
				}
			}
		}

		if (!currentUserIsAdmin && cadastrandoAdmin) {
			message.AddField("message", "Você não pode adicionar um usuário administrador.");
			message.AddField("detalhe",
					"Apenas usuários administradores que podem adicionar um usuário administrador.");
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		}

		return super.insert(user, errors);
	}

	@Override
	public ResponseEntity<?> update(@RequestBody @Validated UsuarioEntity user, Errors errors) {

		this.LOGGER.info("Criptografando senha do usuario.");

		if (!user.getSenha().equals(usuarioRepo.findOne(user.getId()).getSenha())) {
			user.setSenha(this.passwordEncoder.encode(user.getSenha()));
		}

		Boolean currentUserIsAdmin = false;
		Boolean cadastrandoAdmin = false;
		Boolean oldIsAdmin = false;

		for (String permisaoUserLogado : currentUser.getActiveUser().getRoles()) {
			if (permisaoUserLogado.equals("ADMIN")) {
				currentUserIsAdmin = true;
			}
		}

		for (PermissaoEntity permissao : user.getPermissoes()) {
			if (permissao.getRole().equals("ADMIN")) {
				cadastrandoAdmin = true;
			}
		}

		UsuarioEntity usuarioOld = usuarioRepository.findOne(user.getId());

		for (PermissaoEntity permisaoOld : usuarioOld.getPermissoes()) {
			if (permisaoOld.getRole().equals("ADMIN")) {
				oldIsAdmin = true;
			}
		}

		if (!currentUserIsAdmin && cadastrandoAdmin | (!currentUserIsAdmin && oldIsAdmin)) {
			message.AddField("message", "Você não pode editar um usuário administrador.");
			message.AddField("detalhe", "Apenas usuários administradores que podem editar um usuário administrador.");
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		}

		if (!currentUserIsAdmin && (!user.getEmail().equals(currentUser.getActiveUser().getEmail()))) {
			message.AddField("message", "Você não pode editar um usuário diferente do seu.");
			message.AddField("detalhe", "Apenas usuários administradores que podem editar um usuário.");
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		}
		if (user.getImagemPerfil() != null && usuarioOld.getImagemPerfil() != null) {
			if (user.getImagemPerfil().getId() == null) {
				this.deleteImagemPerfil(usuarioOld.getImagemPerfil().getId());
				user.setImagemPerfil(null);
			}
		}
		return super.update(user, errors);
	}

	@Override
	public ResponseEntity<Object> findAll() {
		this.LOGGER.info("Buscando todos os registros de usuários.");
		List<UsuarioEntity> usuarios;
		usuarios = this.usuarioRepository.findAll();		
		return super.montaResponse(usuarios, false);
	}

	@RequestMapping(path = "/pesquisaPorId", method = RequestMethod.GET)
	public ResponseEntity<Object> pesquisaPorId(@RequestParam(value = "id") Long id,
			@RequestParam(required = false) Boolean comImagem) {
		LOGGER.info("Pesquisando usuario com id = " + id);
		return super.montaResponse(usuarioRepository.findOne(id), comImagem);
		
	}



	@RequestMapping(method = RequestMethod.GET, path = "/pesquisaImagemUsuario")
	public ResponseEntity<Object> pesquisaImagemUsuario(@RequestParam Long idUsuario) {
		UsuarioEntity usuarioEntity = usuarioRepo.findOne(idUsuario);
		if (usuarioEntity != null) {
			return super.montaResponse(usuarioEntity.getImagemPerfil(), true);
		} else {
			message.AddField("message", "Usuario não encontrado");
			message.AddField("detalhe", "Usuário id " + idUsuario + " não foi encontrado");
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
		}
	}

	public void deleteImagemPerfil(Integer idImagem) {
		imgRepo.delete(idImagem);
	}
}
