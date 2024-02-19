package ecommerce.solutions.XindusTrade.EcommerceController;


import ecommerce.solutions.XindusTrade.Dto.WishListDto;
import ecommerce.solutions.XindusTrade.EcommerceEntity.WishlistItems;
import ecommerce.solutions.XindusTrade.EcommerceService.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WishListDto>> getUserWishlist(Authentication authentication) {
        // Retrieve user from Users
        var username = authentication.getName();
        List<WishListDto> wishlistItemsList = wishlistService.getUserWishlist(username);
        return new ResponseEntity(wishlistItemsList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addItemToWishlist(Authentication authentication, @RequestBody WishlistItems wishlistItem) {
        // Add item to user's wishlist
        String username = authentication.getName();
        String result = wishlistService.addItems(wishlistItem, username);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> removeItemFromWishlist(Authentication authentication, @PathVariable Integer productId) throws Exception {
        // Remove item from user's wishlist
        String username = authentication.getName();
        String result = wishlistService.removeItems(username, productId);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
