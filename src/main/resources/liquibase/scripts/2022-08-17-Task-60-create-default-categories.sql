-- TEST-USER FOR USER ID
insert into users (id, email, full_balance, full_income, full_consump, created_at)
values (1, 'test-user@mail.ru', 0, 0, 0, CURRENT_DATE);

-- INCOMES
insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('salary', 1, 'INCOME', 13, 4, true, false, CURRENT_DATE);

insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('side job', 1, 'INCOME', 13, 4, true, false, CURRENT_DATE);

insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('gifts', 1, 'INCOME', 17, 4, true, false, CURRENT_DATE);

insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('capitalization', 1, 'INCOME', 21, 4, true, false, CURRENT_DATE);

--CONSUMPTIONS
insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('cafes and restaurants', 1, 'CONSUMPTION', 4, 1, true, false, CURRENT_DATE);

insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('supermarket', 1, 'CONSUMPTION', 15, 7, true, false, CURRENT_DATE);

insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('gym', 1, 'CONSUMPTION', 10, 9, true, false, CURRENT_DATE);

insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('public transport', 1, 'CONSUMPTION', 27, 10, true, false, CURRENT_DATE);

insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('medicine', 1, 'CONSUMPTION', 5, 4, true, false, CURRENT_DATE);

insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('petrol', 1, 'CONSUMPTION', 12, 2, true, false, CURRENT_DATE);

insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('rent', 1, 'CONSUMPTION', 19, 1, true, false, CURRENT_DATE);

insert into category (category_name, user_id, operation_type, icon_id, colour_id, is_default, is_deleted, created_at)
values ('vacation', 1, 'CONSUMPTION', 2, 8, true, false, CURRENT_DATE);