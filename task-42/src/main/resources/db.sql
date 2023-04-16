CREATE TABLE users (
  id VARCHAR(36),
  name VARCHAR(255) NOT NULL,
  CONSTRAINT pk_user primary key (id)
);

CREATE TABLE devices (
  id VARCHAR(36),
  name VARCHAR(255) NOT NULL,
  technology VARCHAR(10) NOT NULL,
  fonoId VARCHAR(255),
  CONSTRAINT pk_device primary key (id),
  CONSTRAINT chk_device_technology CHECK (technology IN ('T2G', 'T3G', 'T4G'))
);

CREATE TABLE bookings (
  id VARCHAR(36),
  userId VARCHAR(36) NOT NULL,
  deviceId VARCHAR(36) NOT NULL,
  startTime TIMESTAMP WITH TIME ZONE NOT NULL,
  endTime TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT pk_booking primary key (id),
  CONSTRAINT fk_booking_device FOREIGN KEY (deviceId) REFERENCES devices(id),
  CONSTRAINT fk_booking_user FOREIGN KEY (userId) REFERENCES users(id)
);

insert into users(id, name) values( '1', 'User1');
insert into users(id, name) values( '2', 'User2');
insert into users(id, name) values( '3', 'User3');

// said 10 mobile devices but given 9, added 10th out of blue
insert into devices values( '1', 'Samsung Galaxy S9', 'T2G', 'f1_external_1');
insert into devices values( '2', '2x Samsung Galaxy S8', 'T3G', 'f1_external_2');
insert into devices values( '3', 'Motorola Nexus 6', 'T4G', 'f1_external_3');
insert into devices values( '4', 'Oneplus 9', 'T2G', 'f1_external_4');
insert into devices values( '5', 'Apple iPhone 13', 'T3G', 'f1_external_5');
insert into devices values( '6', 'Apple iPhone 12', 'T4G', 'f1_external_6');
insert into devices values( '7', 'Apple iPhone 11', 'T2G', 'f1_external_7');
insert into devices values( '8', 'iPhone X', 'T3G', 'f1_external_8');
insert into devices values( '9', 'Nokia 3310', 'T4G', 'f1_external_9');
insert into devices values( '10', 'Mysterious Phone Model', 'T4G', 'f1_external_10');

INSERT INTO bookings (id, userId, deviceId, startTime, endTime)
VALUES ('1', '1', '1', '2023-04-10 12:00:00.000000-07:00', '2023-04-23 13:00:00.000000-07:00');
INSERT INTO bookings (id, deviceId, userId, startTime, endTime)
VALUES ('2', '4', '3', '2023-04-10 12:00:00.000000-07:00', '2023-04-23 13:00:00.000000-07:00');




