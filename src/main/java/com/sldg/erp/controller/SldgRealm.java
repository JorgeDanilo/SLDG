package com.sldg.erp.controller;

import static org.apache.shiro.SecurityUtils.getSubject;

import java.util.Set;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.sldg.erp.model.Usuario;
import com.sldg.erp.repository.Usuarios;

public class SldgRealm extends AuthorizingRealm {

	private static final Object ROLES_SESSION_STRING = "@ROLES_STRING";

	private static final Object USER_SESSION = "@user";
	
	@Inject
	private Usuarios usuarios;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		
		SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
		
		authorization.setRoles((Set<String>) getSubject().getSession().getAttribute(ROLES_SESSION_STRING));
		
		return authorization;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {

		final UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
		
		Usuario usuario = null;
		
		usuario = usuarios.autenticar(passwordToken.getUsername(), new String(passwordToken.getPassword()));
		
		getSubject().getSession().setAttribute(USER_SESSION, usuario );
		
		return null;
	}

}
