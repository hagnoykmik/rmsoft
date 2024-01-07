package rmsoft.library.book.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UpdateBookRequest {

    @NotNull
    private String title;

    @NotNull
    private boolean isBorrow;
}
