-- Создание баз данных
-- Не забываем при подключении писать все с маленькой буквы!!!

CREATE USER AuthService;
ALTER USER AuthService WITH ENCRYPTED PASSWORD '12345';
CREATE DATABASE AuthServiceDB;
GRANT ALL PRIVILEGES ON DATABASE AuthServiceDB TO AuthService;

CREATE USER ProjectService;
ALTER USER ProjectService WITH ENCRYPTED PASSWORD '12345';
CREATE DATABASE ProjectServiceDB;
GRANT ALL PRIVILEGES ON DATABASE ProjectServiceDB TO ProjectService;

CREATE USER CalendarService;
ALTER USER CalendarService WITH ENCRYPTED PASSWORD '12345';
CREATE DATABASE CalendarServiceDB;
GRANT ALL PRIVILEGES ON DATABASE CalendarServiceDB TO CalendarService;

CREATE USER DocumentService;
ALTER USER DocumentService WITH ENCRYPTED PASSWORD '12345';
CREATE DATABASE DocumentServiceDB;
GRANT ALL PRIVILEGES ON DATABASE DocumentServiceDB TO DocumentService;

CREATE USER CommsHubService;
ALTER USER CommsHubService WITH ENCRYPTED PASSWORD '12345';
CREATE DATABASE CommsHubServiceDB;
GRANT ALL PRIVILEGES ON DATABASE CommsHubServiceDB TO CommsHubService;

CREATE USER NotificationService;
ALTER USER NotificationService WITH ENCRYPTED PASSWORD '12345';
CREATE DATABASE NotificationServiceDB;
GRANT ALL PRIVILEGES ON DATABASE NotificationServiceDB TO NotificationService;

CREATE USER AnalyticsService;
ALTER USER AnalyticsService WITH ENCRYPTED PASSWORD '12345';
CREATE DATABASE AnalyticsServiceDB;
GRANT ALL PRIVILEGES ON DATABASE AnalyticsServiceDB TO AnalyticsService;

CREATE USER InvitationService;
ALTER USER InvitationService WITH ENCRYPTED PASSWORD '12345';
CREATE DATABASE InvitationServiceDB;
GRANT ALL PRIVILEGES ON DATABASE InvitationServiceDB TO InvitationService;