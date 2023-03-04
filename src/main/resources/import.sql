-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequen ce'), 'field-3');


insert into main.job_position (id, title, salary, created_at) values('9828137a-aaeb-4081-b061-1519c590e1b9','chef', 5000000, now());
insert into main.job_position (id, title, salary, created_at) values('a390afcf-0e41-4ff0-b25d-cb6c78fbe1cf','waitress', 3500000, now());
insert into main.job_position (id, title, salary, created_at) values('03edcb3e-7584-4d81-b802-01935646be65','cashier', 4000000, now());
insert into main.job_position (id, title, salary, created_at) values('2b57cccd-d4e2-4bbe-b387-5448838d630d','manager', 7000000, now());
insert into main.job_position (id, title, salary, created_at) values('971212c2-2f30-490e-8162-6e5db35355d1','janitor', 3000000, now());
insert into main.job_position (id, title, salary, created_at) values('4ad16f76-76d9-4112-99a3-d57ed4f7ad65','dishwasher', 2500000, now());
insert into main.job_position (id, title, salary, created_at) values('d7f848d3-fc12-4bbf-bdd5-c6237640e37a','barista', 4500000, now());

insert into main.last_education (id, name, created_at) values('74578342-5361-4ea1-9a21-056d51637c74', 'SD', now());
insert into main.last_education (id, name, created_at) values('7727a093-70c5-4479-8959-0e5084845620', 'SMP', now());
insert into main.last_education (id, name, created_at) values('716a2eee-5db0-4437-a94c-8f62e0fa688c', 'SMA', now());
insert into main.last_education (id, name, created_at) values('f36b366d-712a-4962-ae47-9d9cdf42aa49', 'D3', now());
insert into main.last_education (id, name, created_at) values('23c5da14-49c4-471c-8a60-19246b5413cc', 'S1/D4', now());
insert into main.last_education (id, name, created_at) values('2ab07d69-efd5-4129-8a26-19b522e4be1b', 'S2', now());
insert into main.last_education (id, name, created_at) values('d1b2944f-6795-4f04-8edd-1fc89113b60a', 'S3', now());

insert into main.payment_type (id, name, code, created_at) values('9466902e-8d2e-4e2d-aa29-b2b7025c896d', 'credit', 'CR', now());
insert into main.payment_type (id, name, code, created_at) values('7963edcf-87d4-4e0f-9660-1a1e7ae9bcdb', 'debit', 'DE', now());
insert into main.payment_type (id, name, code, created_at) values('1f93abe4-cc45-4870-a49f-bb527197753f', 'cashless', 'CL', now());
insert into main.payment_type (id, name, code, created_at) values('0eaa5213-3bd7-4622-ad6b-40aa539ea3fb', 'cash', 'CA', now());

