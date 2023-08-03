package com.helidon.ums.Service;

import com.helidon.ums.Dto.UserDetails;
import com.helidon.ums.Dto.UserDto;
import com.helidon.ums.Entity.Role;
import com.helidon.ums.Entity.User;
import com.helidon.ums.Exception.CustomGlobalException;
import com.helidon.ums.Exception.EmailAlreadyFoundException;
import com.helidon.ums.Exception.UserNotFoundException;
import com.helidon.ums.Repository.UserRepository;
import com.helidon.ums.Response.ApiResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @PersistenceContext(unitName = "u1")
    private EntityManager entityManager;

    private final RoleService roleService;

    private final UserRepository userRepository;


    @Inject
    public UserService(RoleService roleService, UserRepository userRepository) {
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Transactional
    public User saveUser(UserDto userDto) throws EmailAlreadyFoundException {
        //check if email is already
        Query nativeQuery = entityManager.createNativeQuery("select count(*) from user where email = :email", Integer.class);
        nativeQuery.setParameter("email", userDto.getEmail());
        logger.info("Query native" + nativeQuery.getParameters());

        Integer count = (Integer) nativeQuery.getSingleResult();
        if (!count.equals(0)) {
            logger.error("email exists");
            throw new EmailAlreadyFoundException("Already exist email for user, Please try another " + userDto.getEmail());
        }
        logger.info("Users: " + userDto);
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        if (userDto.getRoleId() == null) {
            throw new CustomGlobalException("Role must be specified in the user data");
        } else {
            Role role = roleService.getRole(userDto.getRoleId());
            logger.info("Role: " + role.getRole());
            user.setRole(role);
        }

        entityManager.persist(user);
        logger.info("User created and save");
        return user;
    }

    //name query
    public List<User> getUserList() {
        return entityManager.createNamedQuery("findAll", User.class).getResultList();
    }

    //stored procedure
    public Object getAllUserList() {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getUserList");
        query.execute();
        return query.getResultList();
    }

    //stored procedure
    public Optional<Object> getFirstNameLike(String firstName) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getFirstNameLike");
        query.setParameter("firstname_p", firstName);
        query.execute();

        if (!query.getResultList().isEmpty()) {
            return Optional.of(new ApiResponse(
                    true, "User found first name like ", query.getResultList(), (long) Response.Status.OK.getStatusCode()));
        } else {
            return Optional.of(new ApiResponse(
                    false, "User not found ", List.of(), (long) Response.Status.NOT_FOUND.getStatusCode()));
        }


        /*Optional<Object> objectOptional = Optional.ofNullable(query.getResultList());
        if (objectOptional.isEmpty()) {
            throw new UserNotFoundException("No first name found :" + firstName);
        }
        return Optional.ofNullable(query.getResultList());*/
    }

    public Object getLastNameLike(String lastName) {
        StoredProcedureQuery byLikeLastName = userRepository.findByLikeLastName(lastName);
       // return byLikeLastName.getResultList();
        logger.info("getLastNameLike");
        if (!byLikeLastName.getResultList().isEmpty()) {
            logger.info("byLikeLastName.getResultList");
            return new ApiResponse(
                    true, "User name found ", byLikeLastName.getResultList(), (long) Response.Status.OK.getStatusCode());
        } else {
            return new ApiResponse(
                    false, "User not found ", List.of(), (long) Response.Status.NOT_FOUND.getStatusCode());
        }
    }


    public User getUserById(Long id) {
        logger.info("find User By Id");
        User user = entityManager.find(User.class, id);
        logger.info("User :" + user);
        if (user == null) {
            throw new UserNotFoundException("Unable to find user with ID " + id);
        }
        return user;
    }

    public ApiResponse deleteUserById(Long userId) {
        logger.info("Deleting user with ID " + userId);
        // call getUserById() method to get the user with the specified
        User user = getUserById(userId);
        if (user != null) {
            logger.info("If user is present then remove " + user);
            user.setRole(null);
            // logger.info("Role " + role);
            entityManager.remove(user);
        }
        return new ApiResponse(
                true,
                "User successfully deleted", (long) Response.Status.OK.getStatusCode()
        );
    }

    public User updateUser(Long userId, UserDto userDto) {

        logger.info("call getUserById method to check user is present or not");
        User user = getUserById(userId);
        if (user != null) {
            logger.info("if user is present");
            user.setFirstname(userDto.getFirstname());
            user.setLastname(userDto.getLastname());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
        }
        entityManager.persist(user);
        return user;
    }

    public UserDetails getUserByEmail(String email) {
        logger.info("email: " + email);
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("findUserByEmail");
        query.setParameter("email_param", email);
        query.execute();

        UserDetails user = new UserDetails();

        user.setUserid((Integer) query.getOutputParameterValue("user_id"));
        user.setFirstName((String) query.getOutputParameterValue("firstname"));
        user.setLastName((String) query.getOutputParameterValue("lastname"));
        user.setEmail((String) query.getOutputParameterValue("email"));
        user.setPassword((String) query.getOutputParameterValue("password"));
        user.setUser_role((String) query.getOutputParameterValue("user_role"));
        logger.info(user.toString());
        if (user.getEmail() == null) {
            logger.info("no name found");
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    public UserDetails getUserName(String userName) {
        logger.info("User first name: " + userName);
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getByFirstName");
        query.setParameter("firstname_param", userName);
        query.execute();
        UserDetails user = new UserDetails();
        user.setUserid((Integer) query.getOutputParameterValue("user_id"));
        user.setFirstName((String) query.getOutputParameterValue("firstname"));
        user.setEmail((String) query.getOutputParameterValue("email"));

        if (user.getFirstName() == null) {
            logger.info("no name found");
            throw new UserNotFoundException("User not found");
        }
        logger.info(user.toString());
        return user;
    }


    public UserDetails getUserLastName(String userLastName) {
        logger.info("User last name: " + userLastName);

        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getLastName");
        query.setParameter("lastname_p", userLastName);
        query.execute();

        UserDetails user = new UserDetails();

        user.setUserid((Integer) query.getOutputParameterValue("user_id"));
        user.setFirstName((String) query.getOutputParameterValue("firstname"));
        user.setLastName((String) query.getOutputParameterValue("lastname"));
        user.setEmail((String) query.getOutputParameterValue("email"));
        logger.info(user.toString());
        if (user.getLastName() == null) {
            logger.info("no name found");
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    public UserDetails findUserById(Long userId) {

        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getUserById");
        query.setParameter("user_id_p", userId);
        query.execute();

        return Optional.of(query)
                .map(q -> {
                    UserDetails user = new UserDetails();

                    user.setUserid((Integer) q.getOutputParameterValue("user_id"));
                    user.setFirstName((String) q.getOutputParameterValue("firstname"));
                    user.setLastName((String) q.getOutputParameterValue("lastname"));
                    user.setEmail((String) q.getOutputParameterValue("email"));
                    user.setPassword((String) q.getOutputParameterValue("password"));
                    user.setUser_role((String) q.getOutputParameterValue("user_role"));
                    logger.info(user.toString());
                    return user;
                }).orElseThrow(() -> {
                    logger.info("no name found");
                    throw new UserNotFoundException("User not found with userId :  " + userId);
                });
    }

    //stored procedure
    public Object deleteUserByIdSP(Long userId) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("deleteById");
        query.setParameter("user_id_p", userId);
        query.execute();
        return query.getSingleResult();
    }


    // email validator methods
   /* public static boolean isEmailValid(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }*/

   /* private boolean userEmailExists(String email) {
        List<User> users = userList();
        if (users.stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email))) return true;
        else return false;
    }*/


}
