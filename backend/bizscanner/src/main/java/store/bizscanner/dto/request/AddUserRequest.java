package store.bizscanner.dto.request;

import lombok.Data;

@Data
public class AddUserRequest {

    private String email;
    private String password;
}
