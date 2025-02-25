# InventoryManagementSystem
**Description of project**
In this project, we will establish relationships among the product table, customer table, order table, and category table to monitor the orders placed by customers. 
  The **Product table** includes a primary key, product ID, along with attributes such as name, price, quantity, and category ID. The category table features a primary key, category ID, and its corresponding name. 
  The **Customer table** contains a primary key, customer ID, as well as fields for name, email, and phone number. In the order table, we have a primary key, order ID, and foreign keys for category ID, customer ID, and product ID. 
  Additionally, this table records the order quantity, which indicates the number of items ordered for each product, a timestamp for when the order was placed, the date of the order, the product name, product price, and total price, which is the aggregate of all products ordered by the customer. 
  The relationships among these tables are as follows: the customer and order tables exhibit a one-to-many relationship, as a single customer can place multiple orders; the order and product tables also demonstrate a one-to-many relationship, since one order can include several products; 
  finally, the relationship between the product and category tables is many-to-one, as multiple products can belong to a single category.

Here is the ER diagram of Inventory Management System:

![image](https://github.com/user-attachments/assets/29e3e0fa-e6ff-481f-a25d-8395b011201f)
