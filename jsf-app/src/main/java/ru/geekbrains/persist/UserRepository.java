package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class UserRepository implements Serializable {

    private final Map<Long, User> userMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    void init(){
        this.saveOrUpdate(new User(null, "Petr", "petr@mail.ru"));
        this.saveOrUpdate(new User(null, "Pavel", "pavel@mail.ru"));
        this.saveOrUpdate(new User(null, "Ivan", "ivan@mail.ru"));
    }

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public User findById(Long id) {
        return userMap.get(id);
    }

    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            Long id = identity.incrementAndGet();
            user.setId(id);
        }
        userMap.put(user.getId(), user);
    }

    public void deleteById(Long id) {
        userMap.remove(id);
    }
}
