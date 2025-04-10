# Product_Discount
A Spring Boot REST API to manage discounts (flat, percentage, and seasonal) on products in an e-commerce system.

==========================================================================================================================

📦 Technologies Used
Java 17+
Spring Boot
Spring Web
Spring Data JPA
PostgreSQL
Lombok
Swagger (API Documentation)
SLF4J (Logging)
Postman / cURL (API Testing)
============================================================================================================================

▶️ How to Run the Application
1. Clone the Repository
git clone https://github.com/your-username/product-discount-management.git
cd product-discount-management

### 2. Configure PostgreSQL
Create a PostgreSQL database named product_db.

Update src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/product_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
server.port=8080

### 3. Build and Run the Application
>>>>>>>>Option 1: Using Terminal
bash
./mvnw spring-boot:run
>>>>>>>>Option 2: Using Eclipse or IntelliJ
Right-click ProductDiscountApplication.java → Run.

=========================================================================================================================================

🌐 Server URL, Port, and Documentation
Name	URL
Server Base URL	http://localhost:8080
Swagger UI (API Doc)	http://localhost:8080/swagger-ui.html
index.html (if added to resources/static)	http://localhost:8080/index.html
📝 index.html can be your homepage/landing page if you create it inside src/main/resources/static/

===========================================================================================================================================


📮 API Endpoints
         ✅ 1. Apply Discount to Product
         POST /product/discount
        ➡️ Full URL: http://localhost:8080/product/discount
 Sample Request Body json
{
  "productId": "P101",
  "productPrice": 50000,
  "quantity": 5,
  "discountType": "percentage",
  "discountValue": 10,
  "seasonalDiscountActive": true
}
Sample Response
{
  "productId": "P101",
  "productPrice": 50000,
  "discountType": "percentage",
  "discountValue": 10,
  "seasonalDiscountActive": true,
  "finalPrice": 32500.0
}

cURL Example bash
curl -X POST http://localhost:8080/product/discount \
-H "Content-Type: application/json" \
-d '{
  "productId": "P101",
  "productName": "Smartphone",
  "productPrice": 50000,
  "quantity": 5,
  "discountType": "percentage",
  "discountValue": 10,
  "seasonalDiscountActive": true
}'
         🔍 2. Get Product by ID
         GET /product/{id}
         ➡️ Example: http://localhost:8080/product/P101

cURL Example
curl http://localhost:8080/product/P101
🚨 Exception Handling (Custom)
Scenario	Custom Exception Class	HTTP Code
Product not found	ProductNotFoundException	404
Product is out of stock	OutOfStockException	400
Invalid discount type	InvalidDiscountTypeException	400
Discount > product price	DiscountExceedsPriceException	400
Missing/invalid fields in input	MethodArgumentNotValidException	400

========================================================================================================================================

🧪 Testing With Postman
Open Postman

Create a POST request to: http://localhost:8080/product/discount

Set body to raw JSON and copy the sample request

Set header: Content-Type: application/json

Test GET /product/{id} endpoint too

==========================================================================================================================================

📖 Swagger API Docs
If you've added Swagger, go to:

http://localhost:8080/swagger-ui.html
It will list all APIs with live testing support!

To enable Swagger, add the dependency in pom.xml:

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>



=============================================================================================================================================
🚨 Exception Handling (Custom)
Scenario	Custom Exception Class	HTTP Code
Product not found	ProductNotFoundException	404
Product is out of stock	OutOfStockException	400
Invalid discount type	InvalidDiscountTypeException	400
Discount > product price	DiscountExceedsPriceException	400
Missing/invalid fields in input	MethodArgumentNotValidException	400

=================================================================================================================================================

 Output Examples for Discount Cases
📌 1. Flat Discount Only
✅ Input:
{
  "productId": "P101",
  "productPrice": 1000,
  "discountType": "flat",
  "discountValue": 200,
  "seasonalDiscountActive": false,
  "quantity": 3
}
✅ Output:
{
  "finalPrice": 800.0
}


📌 2. Percentage Discount Only
✅ Input:
{
  "productId": "P102",
  "productPrice": 2000,
  "discountType": "percentage",
  "discountValue": 10,
  "seasonalDiscountActive": false,
  "quantity": 2
}
✅ Output:
{
  "finalPrice": 1800.0
}


📌 3. Flat + Seasonal Discount
✅ Input:
{
  "productId": "P103",
  "productPrice": 1000,
  "discountType": "flat",
  "discountValue": 100,
  "seasonalDiscountActive": true,
  "quantity": 5
}
✅ Output:
{
  "finalPrice": 650.0
}


📌 4. Percentage + Seasonal Discount
✅ Input:
{
  "productId": "123",
  "productPrice": 1000,
  "discountType": "percentage",
  "discountValue": 10,
  "seasonalDiscountActive": true,
  "quantity": 5
}
✅ Output:
{
  "finalPrice": 650.0
}


📌 5. No Discount
✅ Input:
{
  "productId": "P105",
  "productPrice": 1000,
  "discountType": "none",
  "discountValue": 0,
  "seasonalDiscountActive": false,
  "quantity": 1
}
✅ Output:
{
  Invalid discount type: none
}
