# Data inserting into mysql server
## build backend it will create LMS database in mysql
## login to mysql server
- sudo mysql -u root -p
- password: Qwerty@123
### insert dummy data to database
- **SHOW DATABASE;**
- **USE LMS;**
- **SHOW TABLES;**
- INSERT INTO user (id, email, username, password, roles,img)
VALUES
('1', 'jessie@digital-edify.com', 'jessie', '$2a$10$HHb8UqUNaLB4bWAkdWzKs.WoDuAiWm5QHhQq00kPiWLJpkHxRKQRa', 'superadmin',null),
('2', 'praneeth@digital-edify.com', 'praneeth', '$2a$10$Xp.ZX88BX2G/WcBe0wWZo.LtClA.DwRSjDCiOzWckihUobkh872m.', 'user,admin',null),
('3', 'varun@digital-edify.com', 'varun', '$2a$10$mcvZYPO9HSL/luoIPFajU.AlEzRWBA58iy3kelx4oaeTnLRSkDBri', 'user',null),
('4', 'raju@digital-edify.com', 'raju', '$2a$10$lbqUCzxPwn.TNb7umLTmBeCAwdx/1hOOIQLTxEXxabjJv257oENm6', 'admin',null),
('5', 'ravi@digital-edify.com', 'ravi', '$2a$10$c9rtpTX8DOGoFi65hKF2Se5SADmiJQNRKb0mGzsMCidRj56sP9OfS', 'admin',null),
('6', 'farhath@gmail.com', 'farhath', '$2a$10$CMpmYyJIMcySe1yP57D55uTqzrqpmFHL2numt4wD4lkd3n0BQiLCy', 'user',null),
('7', 'nithin@gmail.com', 'nithin', '$2a$10$DGCAWkzJbSMM2.pLhYH4wOnbzg2DQGES/vlQ8v3CD/tT8mXvjn8ky', 'user',null);
- **to check the data**
- **SELECT * FROM user;**
## Rebuild the Backend
## Build Frontend and check connectivity
