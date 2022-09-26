alter table wallet
    drop column if exists balance;
alter table wallet
    drop column if exists full_consump;
alter table wallet
    drop column if exists full_income;
alter table users
    drop column if exists full_balance;
alter table users
    drop column if exists full_consump;
alter table users
    drop column if exists full_income;
alter table operation
    drop column if exists operation_name;