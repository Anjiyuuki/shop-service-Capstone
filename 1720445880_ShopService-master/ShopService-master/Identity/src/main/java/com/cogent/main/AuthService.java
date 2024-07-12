package com.cogent.main;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cogent.main.entity.TokenEntity;
import com.cogent.main.entity.UserEntity;
import com.cogent.main.repository.TokenRepository;
import com.cogent.main.repository.UserRepository;

@Service
public class AuthService
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenRepository tokenRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private CartClient cartClient;
	@Autowired
	private WishlistClient wishlistClient;

	public String saveUser(UserEntity userEntity)
	{
		System.out.println(userEntity);
		System.out.println(userEntity.getPass());
		userEntity.setPass(passwordEncoder.encode(userEntity.getPassword()));
		userRepository.save(userEntity);
		cartClient.initCart(userEntity.getId());
		wishlistClient.initList(userEntity.getId());

		return userEntity.toString();
	}

	public String generateToken(String userName)
	{
		return jwtService.generateToken(userName);
	}

	public String login(AuthRequest authRequest)
	{
		String email = authRequest.getEmail();
		String password = authRequest.getPassword();
		Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		if (auth.isAuthenticated())
		{
			// Find UserEntity
			UserEntity ue = userRepository.findByEmail(email)
					.get();
			// Invalidate all old tokens
			List<TokenEntity> data = tokenRepository.findAllValidTokenByUserEntity(ue.getId());
			if (!data.isEmpty())
			{
				tokenRepository.saveAll(data.stream()
						.peek(d -> d.setValid(false))
						.collect(Collectors.toList()));
			}
			// create and return new valid token
			String token = generateToken(authRequest.getEmail());
			tokenRepository.save(TokenEntity.builder()
					.token(token)
					.userEntity(ue)
					.valid(true)
					.role(ue.getRole())
					.build());
			return token;
		} else
		{
			throw new RuntimeException("Invalid Credentials");
		}
	}

	public void logout(String jwtToken)
	{
		TokenEntity token = tokenRepository.findByToken(jwtToken)
				.get();
		token.setValid(false);
		tokenRepository.save(token);
		System.out.println("Logged Out");
	}

	public String validate(String jwtToken)
	{
		// Throws exception if token is invalid
		return jwtService.validate(jwtToken);

	}

	public String getId(String token)
	{
		return String.valueOf(tokenRepository.findByToken(token)
				.get()
				.getUserEntity()
				.getId());
	}

	public String getAddress(int userId)
	{
		UserEntity ue = userRepository.findById(userId)
				.get();
		return ue.getAddress().ToString();
	}
}
