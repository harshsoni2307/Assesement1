package com.harsh.studentapp.users.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.studentapp.users.models.ERole;
import com.harsh.studentapp.users.models.ESubject;
import com.harsh.studentapp.users.models.Role;
import com.harsh.studentapp.users.models.Subject;
import com.harsh.studentapp.users.models.User;
import com.harsh.studentapp.users.payload.request.LoginRequest;
import com.harsh.studentapp.users.payload.request.SignupRequest;
import com.harsh.studentapp.users.payload.response.JwtResponse;
import com.harsh.studentapp.users.payload.response.MessageResponse;
import com.harsh.studentapp.users.repository.RoleRepository;
import com.harsh.studentapp.users.repository.SubjectRepository;
import com.harsh.studentapp.users.repository.UserRepository;
import com.harsh.studentapp.users.security.jwt.JwtUtils;
import com.harsh.studentapp.users.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		Set<String> strSubjects = signUpRequest.getSubject();
		Set<Subject> subjects = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);

		// Setting Subjects for User

		if (strSubjects == null) {
			Subject userSubject = subjectRepository.findByName(ESubject.Maths)
					.orElseThrow(() -> new RuntimeException("Error: Subject is not found."));
			subjects.add(userSubject);
		} else {
			strSubjects.forEach(subject -> {
				switch (subject) {
				case "Maths":
					Subject Maths = subjectRepository.findByName(ESubject.Maths)
							.orElseThrow(() -> new RuntimeException("Error: Maths subject not found."));
					subjects.add(Maths);

					break;
				case "English":
					Subject English = subjectRepository.findByName(ESubject.English)
							.orElseThrow(() -> new RuntimeException("Error: English subject not found."));
					subjects.add(English);

					break;
				default:
					Subject Hindi = subjectRepository.findByName(ESubject.Hindi)
							.orElseThrow(() -> new RuntimeException("Error: Hindi subject not found."));
					subjects.add(Hindi);
				}
			});
		}

		user.setSubject(subjects);

		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}