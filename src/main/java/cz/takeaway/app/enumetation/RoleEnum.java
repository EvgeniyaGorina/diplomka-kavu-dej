package cz.takeaway.app.enumetation;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority  {
	ADMIN("ADMIN"), USER("USER"), USER_OWNER("USER_OWNER");
	
    private final String authority;

    RoleEnum(String authority) {
        this.authority = authority;
    }

	@Override
	public String getAuthority() {
		return authority;
	}
}
