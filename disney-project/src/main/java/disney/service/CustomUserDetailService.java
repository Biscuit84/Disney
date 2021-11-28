//package disney.service;
//
//
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import disney.model.Admin;
//import disney.model.Compte;
//import disney.repository.IAdminRepo;
//import disney.repository.ICompteRepo;
//import disney.repository.IJoueurRepo;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//	@Autowired
//	private ICompteRepo compteRepo;
//	
//	@Autowired
//	private IJoueurRepo joueurRepo;
//	
//	@Autowired
//	private IAdminRepo adminRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<Compte> opt = compteRepo.findByLogin(username);
//
//		if (opt.isPresent()) {
//			if (opt.get() instanceof Admin) {
//				Compte c = opt.get();
//				c=new Admin();
//			}
//			return new CustomUserDetails(opt.get());
//		} else {
//			throw new UsernameNotFoundException(username + " Inconnu");
//		}
//	}
//
//}
