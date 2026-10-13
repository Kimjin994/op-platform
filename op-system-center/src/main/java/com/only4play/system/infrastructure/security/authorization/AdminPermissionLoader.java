package com.only4play.system.infrastructure.security.authorization;

import com.only4play.system.domain.admin.AdminUserRole;
import com.only4play.system.domain.admin.repository.AdminUserRoleRepository;
import com.only4play.system.domain.permission.resource.Resource;
import com.only4play.system.domain.permission.resource.repository.ResourceRepository;
import com.only4play.system.domain.permission.role.Role;
import com.only4play.system.domain.permission.role.RoleResourceRel;
import com.only4play.system.domain.permission.role.repository.RoleRepository;
import com.only4play.system.domain.permission.role.repository.RoleResourceRelRepository;
import com.only4play.system.infrastructure.constants.AuthConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminPermissionLoader {

  private final PermissionCache permissionCache;

  private final AdminUserRoleRepository adminUserRoleRepository;

  private final RoleRepository roleRepository;

  private final RoleResourceRelRepository roleResourceRelRepository;

  private final ResourceRepository resourceRepository;

  public Collection<GrantedAuthority> load(Long userId) {
    return permissionCache.get(userId).orElseGet(() -> {
      Collection<GrantedAuthority> loaded = query(userId);
      permissionCache.put(userId, loaded);
      return loaded;
    });
  }

  public void evict(Long userId) {
    permissionCache.evict(userId);
  }

  public void evictByRole(Long roleId) {
    for (AdminUserRole rel : adminUserRoleRepository.findAllByRoleId(roleId)) {
      permissionCache.evict(rel.getUserId());
    }
  }

  private Collection<GrantedAuthority> query(Long userId) {
    Set<String> codes = new LinkedHashSet<>();
    List<AdminUserRole> userRoles = adminUserRoleRepository.findAllByUserId(userId);
    for (AdminUserRole userRole : userRoles) {
      Role role = roleRepository.findById(userRole.getRoleId()).orElse(null);
      if (role == null) {
        continue;
      }
      if (AuthConstants.SUPER_ADMIN_ROLE.equals(role.getRole())) {
        codes.add("ROLE_ADMIN");
      }
      List<RoleResourceRel> rels = roleResourceRelRepository.findAllByRoleId(role.getId());
      for (RoleResourceRel rel : rels) {
        Resource resource = resourceRepository.findById(rel.getResourceId()).orElse(null);
        if (resource != null && StringUtils.isNotBlank(resource.getCode())) {
          codes.add(resource.getCode());
        }
      }
    }
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String code : codes) {
      authorities.add(new SimpleGrantedAuthority(code));
    }
    return authorities;
  }
}
