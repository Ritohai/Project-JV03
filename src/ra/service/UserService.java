package ra.service;

import ra.model.User;

import java.util.List;

public class UserService implements IGenericService<User,Integer> {
    private List<User> users;

    public UserService() {
        this.users = users; // Dữ liệu đọc từ file
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public User findById(Integer id) {
        return null;
    }
}
