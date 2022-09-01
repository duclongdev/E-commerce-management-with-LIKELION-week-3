create table if not exists cart(
  cart_id serial primary key
);

create table if not exists customer (
    customer_id serial primary key ,
    customer_name varchar(50) not null,
    address varchar(100),
    phone_no char(20) not null,
    cart_id int,
    foreign key (cart_id) references cart(cart_id)
);
create table if not exists product (
    product_id serial primary key ,
    name_product varchar(100) not null,
    type char(5),
    size char(3),
    quantity int not null,
    price numeric
);
create table if not exists cart_item (
    cart_id int not null,
    product_id int not null,
    quantity_wished int not null,
    date_added timestamp not null default now(),
    total_amount numeric not null,
    foreign key (cart_id)
        references cart (cart_id),
    foreign key (product_id) references product (product_id)
)