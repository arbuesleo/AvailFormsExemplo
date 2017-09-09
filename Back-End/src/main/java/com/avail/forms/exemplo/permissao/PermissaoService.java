package com.avail.forms.exemplo.permissao;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avail.forms.exemplo.utils.GenericService;
import com.avail.forms.exemplo.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.PERMISSAO_PRIVATE_PATH)
public class PermissaoService extends GenericService<PermissaoEntity, Long> {

}
