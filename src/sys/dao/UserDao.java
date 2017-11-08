package sys.dao;

import java.util.List;
import sys.model.User;

public interface UserDao {		
	public List<User> listUsers();
	public Integer addUser(User e);
	public void modifyUser(User e);
	public void deleteUser(Integer id);	
	public User findByUsername(String username);
	public User findByID(Integer id);
}
