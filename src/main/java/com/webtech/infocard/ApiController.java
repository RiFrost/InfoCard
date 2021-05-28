package com.webtech.infocard;

import com.webtech.database.model.EnumRole;
import com.webtech.database.model.Role;
import com.webtech.database.model.Topic;
import com.webtech.database.model.User;
import com.webtech.database.repository.RoleRepository;
import com.webtech.database.repository.UserRepository;
import com.webtech.database.service.IndexCardService;
import com.webtech.database.service.TopicService;
import com.webtech.database.service.UserService;
import com.webtech.security.jwt.JwtUtils;
import com.webtech.security.model.JwtResponse;
import com.webtech.security.model.LoginRequest;
import com.webtech.security.model.MessageResponse;
import com.webtech.security.model.RegisterRequest;
import com.webtech.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private IndexCardService indexCardService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public String home() {
        return "InfoCard - Your new Way of learning!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getLastname(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(registerRequest.getFirstname(),
                registerRequest.getLastname(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()));

        Set<String> reqRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            reqRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/topics/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TopicResponse> addNewTopic(@Valid @RequestBody TopicRequest topicRequest, @PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(topicService.addTopic(userId, topicRequest));
    }

    @GetMapping("/topics/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<TopicResponse> getTopicListFromUser(@Valid @PathVariable(name = "userId") String userId) {
        return topicService.getAllTopicsFromUser(userId);
    }


    @PostMapping("/topics")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteSelectedTopics(@Valid @RequestBody List<TopicRequest> requestTopicList) {
        topicService.deleteTopics(requestTopicList);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/topics")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TopicRequest> updateTopicName(@Valid @RequestBody() TopicRequest topicRequest) {
        return ResponseEntity.ok(topicService.renameTopic(topicRequest));
    }

    @PostMapping("/indexcards/{topicId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<IndexCardResponse> addNewIndexCard(@Valid @RequestBody() IndexCardResponse indexCardResponse, @PathVariable(name = "topicId") Long topicId) {
        return ResponseEntity.ok(indexCardService.addIndexCard(topicId, indexCardResponse));
    }

    @GetMapping("/indexcards/{topicId}")
    @PreAuthorize("hasRole('USER')")
    public List<IndexCardResponse> getIndexCardListFromTopic(@Valid @PathVariable(name = "topicId") Long topicId) {
        return indexCardService.getAllIndexCardsFromTopic(topicId);
    }

    @PostMapping("/indexcards")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteSelectedIndexCards(@Valid @RequestBody() List<IndexCardRequest> indexCardRequestList) {
        indexCardService.deleteIndexCard(indexCardRequestList);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/indexcards")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<IndexCardResponse> updateIndexCard(@Valid @RequestBody() IndexCardResponse indexCardResponse) {
        return ResponseEntity.ok(indexCardService.renameIndexCard(indexCardResponse));
    }

    @GetMapping("/showUsers")
    //@PreAuthorize("hasRole('USER')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/showUsersNoAuth")
    public List<User> getAllUsersNoAuth() {
        return userService.getAllUsers();
    }


}
