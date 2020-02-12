create table if not exists roles
(
    id          uuid not null
        constraint roles_pkey
            primary key,
    description varchar(255),
    name        varchar(255)
);

create table if not exists users_roles
(
    user_id  uuid not null
        constraint fk2o0jvgh89lemvvo17cbqvdxaa
            references users,
    roles_id uuid not null
        constraint uk_60loxav507l5mreo05v0im1lq
            unique
        constraint fka62j07k5mhgifpp955h37ponj
            references roles
);