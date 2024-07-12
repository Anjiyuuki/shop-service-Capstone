package com.cogent.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cogent.main.entity.Address;
import com.cogent.main.entity.UserDAO;
import com.cogent.main.entity.UserEntity;
import com.cogent.main.repository.UserRepository;

@Service
public class AdminService
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CartClient cartClient;
	@Autowired
	private WishlistClient wishlistClient;

	public List<UserEntity> getAllUsers()
	{
		List<UserEntity> sanitized = userRepository.findAll()
				.stream()
				.peek(d -> d.setPass("hidden"))
				.toList();
		return sanitized;
	}

	public void delete(int id)
	{
		userRepository.deleteById(id);
		cartClient.deleteCart(id);
		wishlistClient.deleteList(id);
	}

	public UserEntity update(UserDAO userDAO, int id)
	{
		if (userRepository.findById(id)
				.isPresent())
		{
			UserEntity entity = userRepository.findById(id)
					.get();
			entity.setId(id);
			entity.setAddress(userDAO.getAddress());
			entity.setEmail(userDAO.getEmail());
			entity.setName(userDAO.getName());
			entity.setPhoneNumber(userDAO.getPhoneNumber());
			entity.setRole(userDAO.getRole());
			return userRepository.save(entity);
		} else
		{
			throw new RuntimeException("User not found");
		}

	}

}
