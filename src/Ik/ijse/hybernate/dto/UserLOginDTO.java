package Ik.ijse.hybernate.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLOginDTO {
    private String userID;
    private String userName;
    private String password;
}
