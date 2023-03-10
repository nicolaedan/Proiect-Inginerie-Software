package com.parking.example.ejb;

import com.parking.example.common.ProductDto;
import com.parking.example.common.UserDto;
import com.parking.example.entities.Product;
import com.parking.example.entities.User;
import com.parking.example.entities.UserGroup;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class UserBean {
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    @Inject
    PasswordBean passwordBean;

    public List<UserDto> findAllUsers() {
        LOG.info("findAllUsers");
        try {
            TypedQuery<User> typedQuery =
                    entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> userDto;
        userDto = users
                .stream()
                .map(x -> new UserDto(x.getEmail(), x.getPassword(), x.getUsername(), x.getId(), x.getMoney_deposited())).collect(Collectors.toList());
        return userDto;
    }


    public void createUser(String username, String email, String password, Long money_deposited, Collection<String> groups) {
        LOG.info("createUser");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));
        newUser.setMoney_deposited(money_deposited);
        entityManager.persist(newUser);
        assignGroupsToUser(username, groups);
    }

    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");
        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }

    public Collection<String> findUsernamesByUserIds(Collection<Long> userIds) {
        List<String> usernames = entityManager.createQuery("SELECT u.username from User u where u.id in :userIds", String.class)
                .setParameter("userIds", userIds).getResultList();
        return usernames;
    }

    public Long getUserIdNyName(String name) {
        LOG.info("getUserIdNyName");
        try {
            TypedQuery<Long> typedQuery =
                    entityManager.createQuery("SELECT u.id from User u where u.username=:name ", Long.class);
            typedQuery.setParameter("name", name);
            Long id_user = typedQuery.getSingleResult();
            return id_user;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

//    public UserDto findById(Long userID) {
//
//        User user = entityManager.find(User.class, userID);
//        User user1 = new User(user.getEmail(), user.getPassword(),user.getUsername(), user.getId(),user.getMoney_deposited());
//
//        return user1;
//
//    }


}

