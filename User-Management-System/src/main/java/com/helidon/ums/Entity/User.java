
package com.helidon.ums.Entity;

import com.helidon.ums.Dto.UserRoleDto;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "findByUserId", query = "select u from User u where u.userId = :userId"),
        @NamedQuery(name = "existsByEmail", query = "select (count(u) > 0) from User u where u.email = :email"),
        @NamedQuery(name = "findByEmail", query = "select u from User u where u.email = :email"),
        @NamedQuery(name = "findAll", query = "select u from User u")
})
@NamedStoredProcedureQueries(
        {@NamedStoredProcedureQuery(name = "findUserByEmail",
                procedureName = "findUserByEmail",
                parameters = {
                        @StoredProcedureParameter(name = "email_param", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "user_id", type = Integer.class, mode = ParameterMode.OUT),
                        @StoredProcedureParameter(name = "firstname", type = String.class, mode = ParameterMode.OUT),
                        @StoredProcedureParameter(name = "lastname", type = String.class, mode = ParameterMode.OUT),
                        @StoredProcedureParameter(name = "email", type = String.class, mode = ParameterMode.OUT),
                        @StoredProcedureParameter(name = "password", type = String.class, mode = ParameterMode.OUT),
                        @StoredProcedureParameter(name = "user_role", type = String.class, mode = ParameterMode.OUT)
                }),
                @NamedStoredProcedureQuery(
                        name = "getByFirstName",
                        procedureName = "getByFirstName",
                        parameters = {
                                @StoredProcedureParameter(name = "firstname_param", type = String.class, mode = ParameterMode.IN),
                                @StoredProcedureParameter(name = "user_id", type = Integer.class, mode = ParameterMode.OUT),
                                @StoredProcedureParameter(name = "firstname", type = String.class, mode = ParameterMode.OUT),
                                @StoredProcedureParameter(name = "email", type = String.class, mode = ParameterMode.OUT)
                        }
                ),
                @NamedStoredProcedureQuery(
                        name = "getLastName",
                        procedureName = "getLastName",
                        parameters = {
                                @StoredProcedureParameter(name = "lastname_p", type = String.class, mode = ParameterMode.IN),
                                @StoredProcedureParameter(name = "user_id", type = Integer.class, mode = ParameterMode.OUT),
                                @StoredProcedureParameter(name = "firstname", type = String.class, mode = ParameterMode.OUT),
                                @StoredProcedureParameter(name = "lastname", type = String.class, mode = ParameterMode.OUT),
                                @StoredProcedureParameter(name = "email", type = String.class, mode = ParameterMode.OUT)
                        }
                ),
                @NamedStoredProcedureQuery(
                        name = "getUserById",
                        procedureName = "getUserById",
                        resultClasses = User.class,
                        parameters = {
                                @StoredProcedureParameter(name = "user_id_p", type = Integer.class, mode = ParameterMode.IN),
                                @StoredProcedureParameter(name = "user_id", type = Integer.class, mode = ParameterMode.OUT),
                                @StoredProcedureParameter(name = "firstname", type = String.class, mode = ParameterMode.OUT),
                                @StoredProcedureParameter(name = "lastname", type = String.class, mode = ParameterMode.OUT),
                                @StoredProcedureParameter(name = "email", type = String.class, mode = ParameterMode.OUT),
                                @StoredProcedureParameter(name = "password", type = String.class, mode = ParameterMode.OUT),
                                @StoredProcedureParameter(name = "user_role", type = String.class, mode = ParameterMode.OUT)
                        }
                ),
                @NamedStoredProcedureQuery(
                        name = "getUserList",
                        procedureName = "getUserList",
                        resultSetMappings = {"getUserList"}

                ),
                @NamedStoredProcedureQuery(
                        name = "getFirstNameLike",
                        procedureName = "getFirstNameLike",
                        resultSetMappings = {"getFirstNameLike"},
                        parameters = {
                                @StoredProcedureParameter(name = "firstname_p", type = String.class, mode = ParameterMode.IN)
                        }
                ),
                @NamedStoredProcedureQuery(
                        name = "deleteById",
                        procedureName = "deleteById",
                       // resultSetMappings = {"getFirstNameLike"},
                        parameters = {
                                @StoredProcedureParameter(name = "user_id_p", type = Integer.class, mode = ParameterMode.IN)
                        }
                )
        })

@SqlResultSetMapping(
        name = "getUserList",
        classes = {
                @ConstructorResult(

                        targetClass = UserRoleDto.class, columns = {
                        @ColumnResult(name = "user_id"),
                        @ColumnResult(name = "firstname"),
                        @ColumnResult(name = "lastname"),
                        @ColumnResult(name = "email"),
                        @ColumnResult(name = "password"),
                        @ColumnResult(name = "role_id"),
                        @ColumnResult(name = "role")
                        }
                )
        }
)

@SqlResultSetMapping(
        name = "getFirstNameLike",
        classes = {
                @ConstructorResult(
                        targetClass = UserRoleDto.class, columns = {
                        @ColumnResult(name = "user_id"),
                        @ColumnResult(name = "firstname"),
                        @ColumnResult(name = "lastname"),
                        @ColumnResult(name = "email"),
                        @ColumnResult(name = "role")
                }
                )
        }
)
@Access(AccessType.PROPERTY)
@JsonbPropertyOrder({"userId", "firstname", "lastname", "email", "password", "role"})
public class User {

    private Long userId;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private Role role;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @SequenceGenerator(name = "user_id", sequenceName = "user_id", allocationSize=1)
    @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Role getRole() {
        return role;
    }

    @Transient
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public User(Long userId, String firstname, String lastname, String email, String password, Role role) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

