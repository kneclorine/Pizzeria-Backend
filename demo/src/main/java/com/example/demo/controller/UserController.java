package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.application.userApplication.CreateUserDTO;
import com.example.demo.application.userApplication.UpdateUserDTO;
import com.example.demo.application.userApplication.UserApplication;
import com.example.demo.application.userApplication.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserApplication userApplication;

    @Autowired
    public UserController(final UserApplication userApplication){
        this.userApplication = userApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserDTO dto){
        Mono<UserDTO> userDTO = this.userApplication.add(dto);

        return ResponseEntity.status(201).body(getJWTToken(userDTO.block().getName()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,  path = "/{id}")
    public Mono<ResponseEntity<UserDTO>> get(@Valid @PathVariable UUID id) {
        Mono<UserDTO> userDTO = this.userApplication.get(id);
        return userDTO.map(user -> ResponseEntity.ok(user)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public Mono<ResponseEntity<UserDTO>> update(@Valid @PathVariable UUID id, @Valid @RequestBody UpdateUserDTO dto) {
        Mono<UserDTO> userDTO = this.userApplication.update(id, dto);
        return userDTO.map(user -> ResponseEntity.ok(user)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<Void>> delete(@Valid @PathVariable UUID id) {
        return this.userApplication.delete(id).map( r -> ResponseEntity.ok().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(
        @RequestParam(required = false) String email,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.status(200).body(this.userApplication.getAll(email, page, size));
    }

    private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}