INSERT INTO users (username, password, enabled)
    values ('buyer', 'buyer', true);

INSERT INTO users (username, password, enabled)
    values ('seller', 'seller', true);

INSERT INTO users (username, password, enabled)
    values ('admin', 'admin', true);

INSERT INTO authorities (username, authority)
    values ('buyer', 'ROLE_BUYER');

INSERT INTO authorities (username, authority)
    values ('seller', 'ROLE_SELLER');

INSERT INTO authorities (username, authority)
    values ('admin', 'ROLE_ADMIN');
