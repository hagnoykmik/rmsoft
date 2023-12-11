package rmsoft.library.user.service;

import rmsoft.library.user.dto.CreateUserRequest;
import rmsoft.library.user.dto.CreateUserResponse;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);  // 회원가입
}
