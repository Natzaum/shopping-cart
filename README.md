Shopping Cart Application

This project implements a shopping cart system in Java with a MySQL database for storing products, users, and cart items.

Features

1- Add and remove products from the cart.

2- Display the total cart value based on the quantity and price of items.

3- Automatically update stock after purchases.

Requirements:

1- Java Development Kit (JDK) 8 or higher. 

2- MySQL Server.

3- Java dependencies for JDBC.

Setup and Execution Steps:

1. Clone the Repository
   
2. Configure the MySQL Database
   
Openthe ShoppingCartQuery.sql and execute in MySQL.

3. Set Up Database Connection
   
public static final String url = "jdbc:mysql://localhost:3306/shoppingcart";

public static final String user = "your_username";

public static final String password = "your_password";

4. Using the Program

You can generate new items using stockDao.insert(st); (Pre-defined items).
   
The program will display the list of available products and prompt for the name of the product you want to add to the cart.

Enter the product name and the desired quantity.

You can also enter:

"exit" to finish the purchase.

"remove" to remove an item from the cart. The program will ask for the name of the item to be removed.

When items are added or removed, the program will calculate and display the total cart value.
