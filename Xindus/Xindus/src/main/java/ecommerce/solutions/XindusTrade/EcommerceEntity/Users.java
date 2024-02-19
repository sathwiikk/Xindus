package ecommerce.solutions.XindusTrade.EcommerceEntity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true)
    private String username;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String role;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<WishlistItems> wishlistItemsList=new ArrayList<>();
}
