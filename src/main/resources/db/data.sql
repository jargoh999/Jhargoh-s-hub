truncate table users cascade;
truncate table media cascade;


insert into users(user_id, email, user_password, time_created) values
(200,'jargoh@gmail,com','2017','2024-06-04T15:03:03.792009700'),
(201,'jargoh1@gmail,com','2017','2024-06-04T15:03:03.792009700'),
(202,'jargoh2@gmail,com','2017','2024-06-04T15:03:03.792009700'),
(203,'jargoh3@gmail,com','2017','2024-06-04T15:03:03.792009700'),
(204,'jargoh4@gmail,com','2017','2024-06-04T15:03:03.792009700'),
(205,'jargoh5@gmail,com','2017','2024-06-04T15:03:03.792009700');



insert into media(id, category,user_user_id, description, url, time_created) values
(100, 'ACTION', 200, 'nice','https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700'),
(101,  'ACTION',201, 'nice','https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700'),
(102, 'ACTION', 202, 'nice','https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700'),
(103, 'ACTION', 203,'nice' ,'https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700'),
(104, 'ACTION', 204, 'nice','https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700'),
(105, 'ACTION', 205,'nice' ,'https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700');
