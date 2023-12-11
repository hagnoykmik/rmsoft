package rmsoft.library.user.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
