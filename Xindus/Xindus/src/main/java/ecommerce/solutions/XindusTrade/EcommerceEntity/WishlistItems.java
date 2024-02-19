package ecommerce.solutions.XindusTrade.EcommerceEntity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WishlistItems {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String name;

    private Integer price;

    @ManyToOne
    @JoinColumn
    private Users user;
}
