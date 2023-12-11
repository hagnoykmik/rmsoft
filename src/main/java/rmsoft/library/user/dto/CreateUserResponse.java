package rmsoft.library.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import rmsoft.library.user.entity.User;

@Data
@Builder
@AllArgsConstructor
public class CreateUserResponse {

    private Long userId;
    private String name;
    private String email;
    private String password;

    //==생성 메서드==//
    public static CreateUserResponse create(User user) {
        return CreateUserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
