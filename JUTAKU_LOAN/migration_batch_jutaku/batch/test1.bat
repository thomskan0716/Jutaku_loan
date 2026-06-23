:: test1.bat
java -Dfile.encoding=UTF-8 -cp "%~dp0..\build\classes;%~dp0..\libs\*;%~dp0..\lib\*" migration.MigrationBatchApplication --migration.process.id=1