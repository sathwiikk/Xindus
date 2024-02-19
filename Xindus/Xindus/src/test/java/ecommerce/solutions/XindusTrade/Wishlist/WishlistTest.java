package ecommerce.solutions.XindusTrade.Wishlist;

import ecommerce.solutions.XindusTrade.EcommerceController.WishlistController;
import ecommerce.solutions.XindusTrade.EcommerceEntity.Users;
import ecommerce.solutions.XindusTrade.EcommerceEntity.WishlistItems;
import ecommerce.solutions.XindusTrade.EcommerceRepository.UserRepository;
import ecommerce.solutions.XindusTrade.EcommerceRepository.WishlistRepository;
import ecommerce.solutions.XindusTrade.Exception.UserNotFoundException;
import ecommerce.solutions.XindusTrade.Exception.WishlistItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class WishlistTest {

    @Autowired
    private WishlistController wishlistController;
    @Mock
    private Authentication authentication;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private WishlistRepository wishlistRepository;

    @BeforeEach
    public void setup() {
        Mockito.when(authentication.getName()).thenReturn("abc");
    }

    @Test
    public void shouldReturnInvalidUser_whenGetWishList() throws Exception {
        // when
        var runtimeException = Assertions.assertThrows(UserNotFoundException.class,
                () -> wishlistController.getUserWishlist(authentication));

        // then
        Assertions.assertEquals("please enter the correct username", runtimeException.getMessage());
    }

    @Test
    public void shouldReturnEmptyWishlist_whenGetWishList() {
        // given
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(getDummyUsers()));

        // when
        var response = wishlistController.getUserWishlist(authentication);

        // then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(getDummyWishList().getName(), response.getBody().get(0).getName());

    }

    @Test
    public void shouldReturnUserNotFound_whenAddWishList() {
        // when
        var response = Assertions.assertThrows(UserNotFoundException.class,
                () -> wishlistController.addItemToWishlist(authentication, getDummyWishList()));

        // then
        Assertions.assertEquals("please enter the correct username", response.getMessage());
    }

    @Test
    public void shouldReturnSuccess_whenAddWishList() {

        // given
        Mockito.when(userRepository.findByUsername(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(getDummyUsers()));

        // when
        var response = wishlistController.addItemToWishlist(authentication, getDummyWishList());

        // then
        Assertions.assertEquals("Wishlist item has been added to your wishlist", response.getBody().toString());
    }

    @Test
    public void shouldReturnUserNotFoundException_whenDeleteWishList() throws Exception {
        // when
        var response = Assertions.assertThrows(UserNotFoundException.class,
                () -> wishlistController.removeItemFromWishlist(authentication, 1));

        // then
        Assertions.assertEquals("please enter the correct username", response.getMessage());
    }

    @Test
    public void shouldReturnWishListNotFound_whenDeleteWistList() {
        // given
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(getDummyUsers()));

        // when
        var response = Assertions.assertThrows(WishlistItemNotFoundException.class,
                () -> wishlistController.removeItemFromWishlist(authentication, 1));

        // then
        Assertions.assertEquals("Wishlist item not found", response.getMessage());
    }

    @Test
    public void shouldDeleteWistListSuccessfully() throws Exception {
        // given
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(getDummyUsers()));
        Mockito.when(wishlistRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getDummyWishList()));

        // when
        var response = wishlistController.removeItemFromWishlist(authentication, 1);

        // then
        Assertions.assertEquals("Wishlist item has been removed from your wishlist", response.getBody().toString());
    }

    private Users getDummyUsers() {
        List<WishlistItems> wishList = new ArrayList<>();
        wishList.add(getDummyWishList());
        Users users = Users.builder().username("abc").email("dummy@mail.com").wishlistItemsList(wishList).build();
        return users;
    }

    private WishlistItems getDummyWishList() {
        return WishlistItems.builder().name("bag").price(100).build();
    }
}
