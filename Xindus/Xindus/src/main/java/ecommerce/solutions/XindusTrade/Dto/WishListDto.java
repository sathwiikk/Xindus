package ecommerce.solutions.XindusTrade.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishListDto {
    private String name;
    private Integer price;
}
