package com.avail.forms.exemplo.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avail.forms.exemplo.utils.Situacao;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	
	public UsuarioEntity findByEmailAndSituacao (String email, Situacao situacao);
	
	@Query(value = "select * from usuario as u where exists(select 1 from horario as h inner join medicamento_horario as mh on mh.id_horario = h.id_horario where h.id_usuario = u.id_usuario and mh.id_medicamento = ?1 and (h.quantidade_disponivel / (h.dosagem_vez * ( SELECT COUNT(*) FROM horario_alerta as qtd_vezes_dia where id_horario = h.id_horario))) < 4)", nativeQuery = true)
	public List<UsuarioEntity> pesquisaUsuariosRelacionadosOferta(Long idMedicamento);
	
}
