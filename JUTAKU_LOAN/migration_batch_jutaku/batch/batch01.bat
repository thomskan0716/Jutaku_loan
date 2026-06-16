@echo off
cd /d "%~dp0"
set CLASSPATH="..\build\classes;..\libs\*;..\lib\*"
java.exe -Xmx512m -Xms512m -Dfile.encoding=UTF-8 -Dspring.profiles.active=local -classpath %CLASSPATH% migration.MigrationBatchApplication --spring.output.ansi.enabled=always --migration.process.id=01
