package com.br.barbershop.models.enums;

public enum Role {
    ADMIN("ADMIN"), CLIENTE("CLIENTE");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
    
}
