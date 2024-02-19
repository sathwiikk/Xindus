package ecommerce.solutions.XindusTrade.EcommerceRepository;

import ecommerce.solutions.XindusTrade.EcommerceEntity.WishlistItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WishlistRepository extends JpaRepository<WishlistItems,Integer> {
}
