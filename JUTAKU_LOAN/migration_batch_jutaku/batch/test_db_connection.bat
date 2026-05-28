@echo off
setlocal

set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_45
set PROJECT_HOME=C:\work\migration_batch_JUTAKU

cd /d %PROJECT_HOME%

echo Compiling Database Connection Test
echo.

if not exist bin\test mkdir bin\test

set CLASSPATH=
for %%f in (libs\*.jar) do call set CLASSPATH=%%CLASSPATH%%;%%f

"%JAVA_HOME%\bin\javac.exe" -encoding Shift-JIS -d bin\test -classpath "%CLASSPATH%" ^
    src\main\java\migration\test\DatabaseConnectionTest.java

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Compilation failed
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo Compilation successful!
echo.
echo Running Database Connection Test
echo.

"%JAVA_HOME%\bin\java.exe" -cp "bin\test;%CLASSPATH%" migration.test.DatabaseConnectionTest

echo.
echo ========================================
pause
