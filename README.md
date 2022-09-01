
# E-commerce 
Thực hiện các chức năng đơn giản với đề tài quản lý E-commerce
# Mục lục
#### [Tables](#tables)
- [Customer](#customer)
- [Product](#product)
- [Cart](#cart)
- [Cart_item](###cart_item)
#### 1. [Data](#data)
 - [Cusomer](#customer)
 - [Product](#product)
#### 2. [Viết api lấy tất cả sản phẩm với điều kiện(LESS_THAN, GREATER_THAN, EUQAL) ](#Câu2)
- [LESS_THAN](#lessthan)
- [GREATER_THAN](#greater_than)
- [EQUAL](#equal)
#### 3. [Viết một api với chức năng thêm cart bằng tham số `customer_id` của Customer](#Câu3)
#### 4. [Thực hiện gọi lại api số 3](#Câu4)
#### 5. [Viết một api lấy danh sách thoong tin của item theo **Cart** theo tham số `customer_id`](#Câu5)


# Tables
### 1. Customer

|    Column     |          Type          | Nullable |                  
|:-----------   |------------------------|----------|
| **`customer_id`**   | `integer  `              | not null | 
 |`customer_name` | `character varying(50)`  | not null |
 |`address`       | `character varying(100)` |          |
 |`phone_no`      | `character(20)`        | not null |
 |`cart_id`       | `integer`               |          |

### 2. Product
|    Column    |          Type           | Nullable |
|:-----------  |:---------------------|:-------|
|**`product_id`**   | `integer`               |not null |
|`name_product` | `character varying(100)`  | not null |
| `type`         | `character(5)`             |       |
| `size`         | `character(3)`           |          |
| `quantity`     | `integer`                  | not null |
| `price`        | `numeric`                   |          |

### 3. Cart
| Column  |  Type    | Nullable |    
|:--------|:-------|:-----------|       
|**`cart_id`** | `integer` | not null |

### 4. Cart_item
 |    Column      |            Type            | Nullable | 
|:--------------|:--------------------------|:--------|
 |**`cart_id`**         | `integer`                    | not null |
| **`product_id`**     | `integer`                    | not null |
 |`quantity_wished` | `integer`                      | not null |
 |`date_added`      | `timestamp`  | not null | 
 |`total_amount`    | `numeric`                     | not null |






