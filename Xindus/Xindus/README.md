## Setup Instructions:
  1.Clone the Repository:
  
  2.Open the Project in IDE:
  
  3.Install Dependencies

  4.Configure Application Properties:
    I am using MySQL database,so you have to create a database with name "ecommerce".
     
    We have to add database username and password in application.properties file.
    
    spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce?createTableIfNotExists=true
    spring.datasource.username=root
    spring.datasource.password=xxxxxxxx
    spring.jpa.hibernate.ddl-auto=update
  
  5.Run the Application.
  
  6.Access the Application using Postman.


## API Details:

## Sign-Up API:

HTTP Method: POST
Endpoint: /user/signup

Description: Allows users to create a new account by providing their username, email, password, and other relevant information.

Request Body: JSON object containing user registration details (e.g., username, email, password).

Response: A message indicating the success or failure of the sign-up process.

## Login API:
HTTP Method: POST

Endpoint: /user/login

Description: Allows registered users to log in to their account by providing their username/email and password.

Request Body: JSON object containing user credentials (e.g., username/email, password).

Response: Upon successful authentication, "User login successfully!..."

## For all the below end point users have to add Basic Authentication Details i.e username and password used for signup.



## Get User Wishlist Methods:

HTTP Method: GET

Endpoint: /api/wishlists/get

Description: Retrieves the wishlist items of the authenticated user.

Request Body: None

Response: A list of wishlist items belonging to the authenticated user.

Authentication: Requires authentication .

## Add Item to Wishlist:

HTTP Method: POST

Endpoint: /api/wishlists/add

Description: Adds an item to the wishlist of the authenticated user.

Request Body: JSON object representing the wishlist item to be added.

Response: A message indicating the success or failure of the operation.

Authentication: Requires authentication .

## Remove Item from Wishlist:

HTTP Method: DELETE

Endpoint: /api/wishlists/delete/{productId}

Description: Removes an item from the wishlist of the authenticated user.

Request Parameters: productId (Path variable) - ID of the product to be removed from the wishlist.

Response: A message indicating the success or failure of the operation.

Authentication: Requires authentication .


## TEST CASES:

Test Case Documentation for Wishlist Controller
## shouldReturnInvalidUser_whenGetWishList
Description:
Verifies that the controller method throws a UserNotFoundException when an invalid user attempts to access their wishlist.

Test Steps:

1.Set up the mock authentication to return an invalid username.

2.Execute the controller method to get the wishlist for the authenticated user.

3.Verify that a UserNotFoundException is thrown.
## shouldReturnEmptyWishlist_whenGetWishList
Description:
Tests that the controller returns an empty wishlist when a valid user with no wishlist items requests their wishlist.

Test Steps:

1.Mock the UserRepository to return a user with no wishlist items.

2.Execute the controller method to get the wishlist for the authenticated user.

3.Verify that the response contains an empty wishlist.
## shouldReturnUserNotFound_whenAddWishList
Description:
Ensures that the controller method throws a UserNotFoundException when attempting to add a wishlist item for an invalid user.

Test Steps:

1.Execute the controller method to add a wishlist item for the authenticated user.

2.Verify that a UserNotFoundException is thrown.
## shouldReturnSuccess_whenAddWishList
Description:
Tests that the controller successfully adds a wishlist item for a valid user.

Test Steps:

1.Mock the UserRepository to return a valid user.

2.Execute the controller method to add a wishlist item for the authenticated user.

3.Verify that the response indicates the item was added successfully.
## shouldReturnUserNotFoundException_whenDeleteWishList
Description:
Verifies that the controller method throws a UserNotFoundException when attempting to delete a wishlist item for an invalid user.

Test Steps:

1.Execute the controller method to delete a wishlist item for the authenticated user.

2.Verify that a UserNotFoundException is thrown.
## shouldReturnWishListNotFound_whenDeleteWistList
Description:
Ensures that the controller method throws a WishlistItemNotFoundException when attempting to delete a non-existent wishlist item.

Test Steps:

1.Mock the UserRepository to return a valid user.

2.Execute the controller method to delete a wishlist item for the authenticated user.

3.Verify that a WishlistItemNotFoundException is thrown.
## shouldDeleteWistListSuccessfully
Description:
Tests that the controller successfully deletes an existing wishlist item for a valid user.

Test Steps:

1.Mock the UserRepository to return a valid user.

2.Mock the WishlistRepository to return an existing wishlist item.

3.Execute the controller method to delete the wishlist item for the authenticated user.

4.Verify that the response indicates the item was deleted successfully.


These test cases cover various scenarios related to accessing, adding, and deleting wishlist items for users in the system. They ensure that the WishlistController behaves as expected under different conditions.




