INSERT INTO user_role (id, role_name) VALUES (1, 'ADMIN_ROLE');
INSERT INTO user_role (id, role_name) VALUES (2, 'NON_ADMIN_ROLE');


INSERT INTO user_details
        (id, first_name, last_name, phone_number, street_number, zip_code, country)
VALUES (1, 'Lysovit', 'Dell', '123456789', '7788', '46', 'San Marino');

INSERT INTO users(id, user_name, password, user_details_id, user_role_id)
VALUES (1, 'lysovit', 'password', 1, 1)
