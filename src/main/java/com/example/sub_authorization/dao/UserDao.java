package com.example.sub_authorization.dao;

import com.example.sub_authorization.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public User insert(User newUser){
        Session session = sessionFactory.getCurrentSession();
        session.save(newUser);
        return newUser;
    }

    public User getUserById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    public User getUserByUsername(String username){
        Session session = sessionFactory.getCurrentSession();
        String hql = "select u from User u where u.username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        List<User> users = query.list();
        if(users.size() == 0){
            return null;
        }else{
            return users.get(0);
        }
    }
}
