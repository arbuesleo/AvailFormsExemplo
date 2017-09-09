package com.avail.forms.exemplo.security;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avail.forms.exemplo.utils.ServiceMap;
import com.avail.forms.exemplo.utils.ServicePath;

@RestController
@RequestMapping(ServicePath.LOGIN_PATH)
public class SecurityService implements ServiceMap{
	
	@RequestMapping(method = { RequestMethod.GET })
	public Principal user(Principal user) {
		return user;
	}
	
}
