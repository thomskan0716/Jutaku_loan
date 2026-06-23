:: test7.bat
java -Dfile.encoding=UTF-8 -cp "%~dp0..\build\classes;%~dp0..\libs\*;%~dp0..\lib\*" migration.MigrationBatchApplication --spring.profiles.active=test --migration.process.id=7
