package com.labtech.events.auth.users.records;

import com.labtech.events.constants.Enums.Roles_user;

import java.util.List;

public record RolesDTO(String email, List<Roles_user> roles) {

}
