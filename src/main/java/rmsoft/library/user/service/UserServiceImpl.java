package rmsoft.library.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmsoft.library.user.dto.CreateUserRequest;
import rmsoft.library.user.dto.CreateUserResponse;
import rmsoft.library.user.entity.User;
import rmsoft.library.user.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        // db에 저장
        User user = User.create(request);
        userRepository.save(user);

        // 응답 dto로 반환
        CreateUserResponse response = CreateUserResponse.create(user);
        return response;
    }
}
