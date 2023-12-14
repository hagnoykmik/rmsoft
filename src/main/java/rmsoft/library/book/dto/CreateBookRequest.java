package rmsoft.library.book.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
public class CreateBookRequest {

    @NotNull
    private String title;

//    private boolean isBororow; // 초기 설정은 false

}
