package rmsoft.library.book.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CreateBookRequest {

    @NotNull
    private String title;

    @NotNull
    private boolean isBororow;

}
