package home.catechumen.bootMVC.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name" , unique = true)
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "highedu")
    private Boolean highEdu;
    @Column(name = "dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Transient
    private Long lifeTimeDays;
// for front
    @Transient
    private List<String> rolesIds;

    public List<String> getRolesIds() {
        return rolesIds;
    }

    public void setRolesIds(List<String > rolesIds) {
        this.rolesIds = rolesIds;
    }
//for front
    public User() {
    }

    public User(String name, String password, Boolean highEdu, LocalDate dateOfBirth, Set<Role> roles) {
        this.name = name;
        this.password = password;
        this.highEdu = highEdu;
        this.dateOfBirth = dateOfBirth;
        this.roles = roles;
    }

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;


    public Long getId() {
        return id;
    }

    public Collection<Role> getRoles() {

        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHighEdu() {
        return highEdu;
    }

    public void setHighEdu(Boolean highEdu) {
        this.highEdu = highEdu;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getLifeTimeDays() {
        this.lifeTimeDays = ChronoUnit.DAYS.between(dateOfBirth, LocalDate.now());
        return lifeTimeDays;
    }

    public void setLifeTimeDays(Long lifeTimeDays) {
        this.lifeTimeDays = lifeTimeDays;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", highEdu=").append(highEdu);
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", lifeTimeDays=").append(lifeTimeDays);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && name.equals(user.name) && Objects.equals(password, user.password) && highEdu.equals(user.highEdu) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(lifeTimeDays, user.lifeTimeDays) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
