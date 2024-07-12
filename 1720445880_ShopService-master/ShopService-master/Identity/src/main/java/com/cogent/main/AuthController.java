package com.cogent.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.main.entity.UserDAO;
import com.cogent.main.entity.UserEntity;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController
{
	@Autowired
	private AuthService service;

	@PostMapping("/register")
	public String addUser(@RequestBody UserEntity userEntity)
	{
		return service.saveUser(userEntity);
	}

	@PostMapping("/login")
	public String login(@RequestBody AuthRequest authRequest)
	{
		return service.login(authRequest);
	}

	@PostMapping("/id")
	public String getUserId(@RequestParam("token") String token)
	{
		return service.getId(token);
	}
	
	@PostMapping("/address")
	public String getAddress(@RequestParam("userId") int userId)
	{
		return service.getAddress(userId);
	}

	@PostMapping("/logout")
	public void logout(@RequestParam("token") String JwtToken)
	{
		service.logout(JwtToken);
	}

	@PostMapping("/validate")
	public String validate(@RequestParam("token") String jwtToken)
	{
		System.out.println(jwtToken);
		return service.validate(jwtToken)
				.toString();
	}

	@Autowired
	private AdminService adminService;

	@GetMapping("/admin/users")
	public List<UserEntity> getAll()
	{
		return adminService.getAllUsers();
	}

	@DeleteMapping("/admin/users/{id}")
	public void delete(@PathVariable int id)
	{
		adminService.delete(id);
	}

	@PutMapping("/admin/users/{id}")
	public UserEntity update(@RequestBody UserDAO userDAO, @PathVariable int id)
	{
		return adminService.update(userDAO, id);
	}
}
