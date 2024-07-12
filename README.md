# ShopService Documentation 

##  Front End 
npm install

ng serve
## Back End
### IDENTITY

POST - localhost:8080/auth/register
{
    "name":{
        "firstName":"myName",
        "lastName": "mylast"
    },
    "address":{
        "city":"NY",
        "street":"street",
        "number": 5,
        "zipcode":"1234"
    },
    "email":"myEmail@gmail.com",
    "pass":"test123",
    "role":"ADMIN",
    "phoneNumber":"1234567890"

}

POST - localhost:8080/auth/login

{
  "email":"myEmail@gmail.com",
    "password":"test123"
}

GET - localhost:8080/auth/admin/users
Enter the Bearer Token 

PUT - localhost:8080/admin/users/{userId}

DELETE - localhost:8080/admin/users/{userId}

### PRODUCT

GET - localhost:8080/product/products
returns all the product 

POST - localhost:8080/product/admin/products
put in token 
{
    "name": "TV",
    "description": "Brand new TV",
    "price": 99.99,
    "category": "Electronic",
    "imageUrl": "image1.png"
}

GET - localhost:8080/product/products/{id}

PUT - localhost:8080/product/admin/products/{id}
update the product (include token)

DELETE - localhost:8080/product/admin/products/{id}

GET - localhost:8080/product/products/categories/{category}

GET - localhost:8080/product/products/categories

POST - localhost:8080/product/admin/products/bulk-upload

### CART
POST - localhost:8080/cart/{userId}/add
use param , include token
key - productId
value - number

GET - localhost:8080/cart/{userId}

DELETE - localhost:8080/{userId}/remove/{productId}

PUT - localhost:8080/cart/1/update
[{
    "productId":1,
    "quantity":40
}]

### DISCOUNT
POST - localhost:8080/discount/admin/add
{
    "saving": 20,
    "code": "SUMMER"
}

POST - localhost:8080/discount/code
param
key - code
value - SUMMER

### ORDERS
POST - 	localhost:8080/orders/{userId}
need to init from cart first

GET - localhost:8080/orders/{userId}

GET - localhost:8080/orders/detail/{orderId}


### WISHLIST
POST - localhost:8080/wishlist/{userId}
param
key - productId
value - num

GET - localhost:8080/wishlist/{userId}

DELETE - localhost:8080/wishlist/{userId}/remove/{productId}






# ShopServiceApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.2.12.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.
