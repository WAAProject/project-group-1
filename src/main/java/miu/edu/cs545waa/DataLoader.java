package miu.edu.cs545waa;

import miu.edu.cs545waa.domain.Admin;
import miu.edu.cs545waa.domain.Buyer;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Admin admin = new Admin("Admin", "Admin", "admin@admin.com", passwordEncoder.encode("admin"));
        Buyer buyer1 = new Buyer("Buyer1", "Buyer1", "buyer1@buyer1.com", passwordEncoder.encode("buyer1"));
        Buyer buyer2 = new Buyer("Buyer2", "Buyer2", "buyer2@buyer2.com", passwordEncoder.encode("buyer2"));
        Seller seller1 = new Seller("Seller1", "Seller1", "seller1@seller1.com", passwordEncoder.encode("seller1"));
        Seller seller2 = new Seller("Seller2", "Seller2", "seller2@seller2.com", passwordEncoder.encode("seller2"));
        userRepository.save(admin);
        userRepository.save(buyer1);
        userRepository.save(buyer2);
        userRepository.save(seller1);
        userRepository.save(seller2);
    }
}
