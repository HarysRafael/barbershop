package com.br.barbershop.models;

import java.io.Serializable;

public class JWTRequest implements Serializable {

private static final long serialVersionUID = 5926468583005150707L;

private String username;
private String senha;

public JWTRequest()
{
}

public JWTRequest(String username, String senha) {
	this.setUsername(username);
	this.setSenha(senha);
}

public String getUsername() {
return this.username;
}

public void setUsername(String username) {
this.username = username;
}

public String getSenha() {
return this.senha;
}

public void setSenha(String senha) {
this.senha = senha;
}

}
