create table application_logins
(
    id                  uuid not null
        constraint application_logins_pkey
            primary key,
    application_version varchar(255),
    country_code        varchar(255),
    first_time_seen     timestamp,
    last_time_seen      timestamp,
    login_counts        bigint,
    selected_language   varchar(255)
);

create table feedbacks
(
    id               uuid      not null
        constraint feedbacks_pkey
            primary key,
    base64log_file   text,
    date             timestamp not null,
    email            varchar(255),
    feedback_message text,
    user_uuid        uuid      not null
);

create table images
(
    id               uuid not null
        constraint images_pkey
            primary key,
    delete_image_url varchar(255),
    image_url        varchar(255)
);

create table feedbacks_images
(
    feedback_id uuid not null
        constraint fkf4vpdi7snt9fi47lt92t8cfhu
            references feedbacks,
    images_id   uuid not null
        constraint uk_92mm44927fdn8s5p1jnbp7qqu
            unique
        constraint fk4yujtda8dmr69tl2hch0xuvu4
            references images
);

