package ecommerce.solutions.XindusTrade.EcommerceService;

import ecommerce.solutions.XindusTrade.Dto.WishListDto;
import ecommerce.solutions.XindusTrade.EcommerceEntity.Users;
import ecommerce.solutions.XindusTrade.EcommerceEntity.WishlistItems;
import ecommerce.solutions.XindusTrade.EcommerceRepository.UserRepository;
import ecommerce.solutions.XindusTrade.EcommerceRepository.WishlistRepository;
import ecommerce.solutions.XindusTrade.Exception.UserNotFoundException;
import ecommerce.solutions.XindusTrade.Exception.WishlistItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WishlistRepository wishlistRepository;


    // Retrieve user's wishlist by userId
    public List<WishListDto> getUserWishlist(String username) {
        // Find user by userId
        Optional<Users> optionalUsers = userRepository.findByUsername(username);
        if (optionalUsers.isPresent()) {
            // If user exists, retrieve their wishlist items

            Users users = optionalUsers.get();
            List<WishListDto> wishList = new ArrayList<>();
            for(WishlistItems item: users.getWishlistItemsList()){
                wishList.add(WishListDto.builder().name(item.getName()).price(item.getPrice()).build());
            }
            return wishList;
        } else {
            // If user not found, return null or throw an exception based on your requirement
            throw new UserNotFoundException("please enter the correct username");
        }
    }

    // Add an item to user's wishlist
    public String addItems(WishlistItems wishlistItem, String username) {
        // Find user by userId
        Optional<Users> optionalUsers = userRepository.findByUsername(username);
        if (optionalUsers.isPresent()) {
            // If user exists, add the item to their wishlist
            Users users = optionalUsers.get();
            users.getWishlistItemsList().add(wishlistItem);
            wishlistItem.setUser(users);
            userRepository.save(users); // Save the user entity to reflect changes
            return "Wishlist item has been added to your wishlist";
        } else {
            throw new UserNotFoundException("please enter the correct username"); // Handle case where user is not found
        }
    }

    // Remove an item from user's wishlist
    public String removeItems(String username,Integer productId) throws Exception {
        // Find user by userId
        Optional<Users> optionalUsers = userRepository.findByUsername(username);
        if (optionalUsers.isPresent()) {
            // If user exists, find the wishlist item by productId
            Users users = optionalUsers.get();
            Optional<WishlistItems> optionalWishlistItems = wishlistRepository.findById(productId);
            if (optionalWishlistItems.isPresent()) {
                // If wishlist item exists, remove it from user's wishlist
                wishlistRepository.deleteById(productId);
                return "Wishlist item has been removed from your wishlist";
            } else {
                throw new WishlistItemNotFoundException("Wishlist item not found"); // Handle case where wishlist item is not found
            }
        } else {
            throw new UserNotFoundException("please enter the correct username"); // Handle case where user is not found
        }
    }
}
