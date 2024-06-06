truncate table users cascade;
truncate table media cascade;


insert into users(user_id, email, user_password, time_created) values
(200,'jargoh@gmail,com','2017','2024-06-04T15:03:03.792009700'),
(201,'jargoh1@gmail,com','2017','2024-06-04T15:03:03.792009700'),
(202,'jargoh2@gmail,com','2017','2024-06-04T15:03:03.792009700'),
(203,'jargoh3@gmail,com','2017','2024-06-04T15:03:03.792009700'),
(204,'jargoh4@gmail,com','2017','2024-06-04T15:03:03.792009700'),
(205,'jargoh5@gmail,com','2017','2024-06-04T15:03:03.792009700');



insert into media(id, category,description, url, time_created, uploader_user_id) values
(100, 'ACTION',  'nice','https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700',200),
(101,  'ACTION', 'nice','https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700',200),
(102, 'ACTION',  'nice','https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700',202),
(103, 'ACTION', 'nice' ,'https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700',200),
(104, 'ACTION',  'nice','https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700',204),
(105, 'ACTION', 'nice' ,'https://www.cloudinary.com/media1', '2024-06-04T15:03:03.792009700',205);
