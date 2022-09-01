# E-commerce

Thực hiện các chức năng đơn giản với đề tài quản lý E-commerce
# Mục lục
#### [Tables](#tables)
- [Customer](#1-customer)
- [Product](#2-product)
- [Cart](#3-cart)
- [Cart_item](#4-cart_item)
#### 1. [Data](#data)
- [Cusomer data](#customer-data)
- [Product data](#product-data)
#### 2. [Viết api lấy tất cả sản phẩm với điều kiện(LESS_THAN, GREATER_THAN, EUQAL) ](#Câu-2)
- [LESS_THAN](#less-than)
- [GREATER_THAN](#greater_than)
- [EQUAL](#equal)
- [Exception](#exception)
#### 3. [Viết một api với chức năng thêm cart bằng tham số `customer_id` của Customer](#Câu-3)
#### 4. [Thực hiện gọi lại api số 3](#Câu-4)
#### 5. [Viết một api lấy danh sách thoong tin của item theo **Cart** theo tham số `customer_id`](#Câu-5)
# Tables
### 1. Customer
| Column            | Type                     | Nullable |
| :---------------- | ------------------------ | -------- |
| **`customer_id`** | `integer `               | not null |
| `customer_name`   | `character varying(50)`  | not null |
| `address`         | `character varying(100)` |          |
| `phone_no`        | `character(20)`          | not null |
| `cart_id`         | `integer`                |          |
### 2. Product
| Column           | Type                     | Nullable |
| :--------------- | :----------------------- | :------- |
| **`product_id`** | `integer`                | not null |
| `name_product`   | `character varying(100)` | not null |
| `type`           | `character(5)`           |          |
| `size`           | `character(3)`           |          |
| `quantity`       | `integer`                | not null |
| `price`          | `numeric`                |          |
### 3. Cart
| Column        | Type      | Nullable |
| :------------ | :-------- | :------- |
| **`cart_id`** | `integer` | not null |
### 4. Cart_item
| Column            | Type        | Nullable |
| :---------------- | :---------- | :------- |
| **`cart_id`**     | `integer`   | not null |
| **`product_id`**  | `integer`   | not null |
| `quantity_wished` | `integer`   | not null |
| `date_added`      | `timestamp` | not null |
| `total_amount`    | `numeric`   | not null |
## Data
## Customer data
| customer_id | customer_name   | address    | phone_no   | cart_id |
| ----------- | --------------- | ---------- | ---------- | ------- |
| 1           | Nguyen Duc Long | Quang Binh | 0906592672 |         |
| 2           | Nguyen Van A    | TP HCM     | 0912345678 |         |
| 3           | Nguyen Thi B    | Ha Noi     | 0908070605 |         |
| 4           | Le Thi D        | Da Nang    | 0903221122 |         |
| 5           | Tran Van C      | Hai Phong  | 0911223344 |         |
## Product data
| product_id | name_product  | type | size | quantity | price  |
| ---------- | ------------- | ---- | ---- | -------- | ------ |
| 1          | ao phong      | ao   | L    | 3        | 500000 |
| 2          | ao thun       | ao   | XL   | 35       | 55000  |
| 3          | ao phong2     | ao   | S    | 12       | 70000  |
| 4          | quan xanh     | quan | L    | 44       | 120000 |
| 5          | quan tay      | quan | S    | 12       | 100000 |
| 6          | quan den      | quan | S    | 55       | 170000 |
| 7          | giay fake     | giay | 36   | 44       | 120000 |
| 8          | giay real     | giay | 40   | 12       | 400000 |
| 9          | giay nai ki   | giay | 39   | 0        | 300000 |
| 10         | giay the thao | giay | 39   | 20       | 170000 |

# Câu 2
## LESS_THAN
    http://localhost:8080/api/products/?price=100000&condition=LESS_THAN
### Kết quả
```json
{
    "httpStatus": "OK",
    "message": "Query data successfully",
    "data": [
        {
            "product_id": 3,
            "name_product": "ao phong2",
            "type": "ao   ",
            "size": "S  ",
            "quantity": 12,
            "price": 70000
        },
        {
            "product_id": 2,
            "name_product": "ao thun",
            "type": "ao   ",
            "size": "XL ",
            "quantity": 31,
            "price": 55000
        }
    ]
}
```

## GREATER_THAN
    http://localhost:8080/api/products/?price=490000&condition=GREATER_THAN
### Kết quả
```json
{
    "httpStatus": "OK",
    "message": "Query data successfully",
    "data": [
        {
            "product_id": 1,
            "name_product": "ao phong",
            "type": "ao   ",
            "size": "L  ",
            "quantity": 0,
            "price": 500000
        }
    ]
}
```
## EQUAL
    http://localhost:8080/api/products/?price=120000&condition=EQUAL
### Kết quả
```json
{
    "httpStatus": "OK",
    "message": "Query data successfully",
    "data": [
        {
            "product_id": 4,
            "name_product": "quan xanh",
            "type": "quan ",
            "size": "L  ",
            "quantity": 44,
            "price": 120000
        },
        {
            "product_id": 7,
            "name_product": "giay fake",
            "type": "giay ",
            "size": "36 ",
            "quantity": 44,
            "price": 120000
        }
    ]
}
```
## Exception
### 1. Không tìm thấy
    http://localhost:8080/api/products/?price=1000&condition=EQUAL
#### Kết quả
```json
{
    "httpStatus": "NOT_FOUND",
    "data": "Product not exists"
}
```
### 2. Giá tiền khôn hợp lệ
    http://localhost:8080/api/products/?price=-1&condition=EQUAL
#### Kết quả
```json
{
    "httpStatus": "BAD_REQUEST",
    "data": "price = -1 argument invalid"
}
```
### 3. Điều kiện không hợp lệ
    http://localhost:8080/api/products/?price=10000&condition=EQUALL
#### Kết quả
```json
{
    "httpStatus": "BAD_REQUEST",
    "data": "Condition = EQUALL argument invalid"
}
```
