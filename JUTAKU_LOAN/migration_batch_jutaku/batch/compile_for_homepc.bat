@echo off
setlocal

REM Home PC Compile Script
REM This version uses the D: drive path for home PC testing

set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_45
set PROJECT_HOME=d:\SunTrust\tanaka\module\MIGRATION_BATCH_C\JUTAKU_LOAN\migration_batch_jutaku

cd /d %PROJECT_HOME%

if not exist bin\main mkdir bin\main

set CLASSPATH=src\main\resources
for %%f in (libs\*.jar) do call set CLASSPATH=%%CLASSPATH%%;%%f

echo Compiling for Home PC...
echo Project: %PROJECT_HOME%
echo Using Shift-JIS encoding for Japanese characters...
echo.

"%JAVA_HOME%\bin\javac.exe" -encoding Shift-JIS -d bin\main -classpath "%CLASSPATH%" ^
    src\main\java\migration\MigrationBatchApplication.java ^
    src\main\java\migration\common\*.java ^
    src\main\java\migration\common\szh_sms\*.java ^
    src\main\java\migration\config\*.java ^
    src\main\java\migration\domain\source\*.java ^
    src\main\java\migration\domain\target\*.java ^
    src\main\java\migration\mapper\source\*.java ^
    src\main\java\migration\mapper\target\*.java ^
    src\main\java\migration\service\*.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ========================================
    echo ERROR: Compilation failed
    echo ========================================
    pause
    exit /b %ERRORLEVEL%
)

echo Copying resources...
xcopy /E /I /Y src\main\resources\* bin\main\

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to copy resources
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo ========================================
echo Compilation complete!
echo Output: %PROJECT_HOME%\bin\main\
echo ========================================
echo.
pause
