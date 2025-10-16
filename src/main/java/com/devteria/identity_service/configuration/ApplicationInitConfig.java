package com.devteria.identity_service.configuration;

import com.devteria.identity_service.constant.PredefinedRole;
import com.devteria.identity_service.entity.Permission;
import com.devteria.identity_service.entity.Role;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.repository.PermissionRepository;
import com.devteria.identity_service.repository.RoleRepository;
import com.devteria.identity_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
public class ApplicationInitConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            // 1. Tạo permission mặc định nếu chưa có
            Permission view = permissionRepository.findById("VIEW")
                    .orElseGet(() -> permissionRepository.save(
                            new Permission("VIEW", "Allow view")
                    ));

            Permission writeUser = permissionRepository.findById("WRITE_USER")
                    .orElseGet(() -> permissionRepository.save(
                            new Permission("WRITE_USER", "Allow creating or updating user information")
                    ));
            Permission deleteUser = permissionRepository.findById("DELETE_USER").orElseGet(()->permissionRepository.save(
                    new Permission("DELETE_USER","Allow deleting user information")));
            // 2. Tạo role ADMIN nếu chưa có
            if (roleRepository.findById(PredefinedRole.ADMIN_ROLE).isEmpty()) {
                Role adminRole = new Role(
                        PredefinedRole.ADMIN_ROLE,
                        "Administrator role"
                );
                adminRole.getPermissions().add(view);
                adminRole.getPermissions().add(writeUser);
                adminRole.getPermissions().add(deleteUser);
                roleRepository.save(adminRole);
            }
            // 3. Tạo role USER nếu chưa có
            if (roleRepository.findById(PredefinedRole.USER_ROLE).isEmpty()) {
                Role userRole = new Role(
                        PredefinedRole.USER_ROLE,
                        "User role"
                );
                userRole.getPermissions().add(view); // chỉ có quyền đọc
                roleRepository.save(userRole);
            }


            // 4. Tạo tài khoản admin mặc định
            if (userRepository.findByName("admin").isEmpty()) {
                var roles = new HashSet<Role>();
                roleRepository.findById(PredefinedRole.ADMIN_ROLE).ifPresent(roles::add);

                User user = User.builder()
                        .name("admin")
                        .passWord(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn(">>> Admin account created with default password: admin. Please change it!");
            }
        };
    }
}
