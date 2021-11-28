//package disney.service;
//
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.util.StringUtils;
//
//import disney.model.Compte;
//
//public class CustomUserDetails implements UserDetails {
//
//	private static final long serialVersionUID = 1L;
//	private Compte compte;
//
//	public CustomUserDetails(Compte compte) {
//		super();
//		this.compte = compte;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return AuthorityUtils
//				.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString(compte.getStringRoles(compte)));
//	}
//
//	@Override
//	public String getPassword() {
//		return new BCryptPasswordEncoder().encode(compte.getPassword());
//	}
//
//	@Override
//	public String getUsername() {
//		return compte.getLogin();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return compte.isEnable();
//	}
//
//}
