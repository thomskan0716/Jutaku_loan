@echo off
setlocal

set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_45
set PROJECT_HOME=C:\Users\sun.ykawakamit\Desktop\静岡銀行_S-plus\branches\新帰属入\050000_データ移行#JTAKU_LOAN\migration_batch_JUTAKU

cd /d %PROJECT_HOME%

if not exist bin\main mkdir bin\main

set CLASSPATH=src\main\resources
for %%f in (libs\*.jar) do call set CLASSPATH=%%CLASSPATH%%;%%f

echo Compiling...
echo Using Shift-JIS encoding for Japanese characters...
echo.

"%JAVA_HOME%\bin\javac.exe" -encoding Shift-JIS -d bin\main -classpath "%CLASSPATH%" ^
    src\main\java\migration\MigrationBatchApplication.java ^
    src\main\java\migration\batch\*.java ^
    src\main\java\migration\common\szh_sms\*.java ^
    src\main\java\migration\config\*.java ^
    src\main\java\migration\domain\source\*.java ^
    src\main\java\migration\domain\target\*.java ^
    src\main\java\migration\mapper\source\*.java ^
    src\main\java\migration\mapper\target\*.java ^
    src\main\java\migration\service\*.java

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Compilation failed
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
echo Compilation complete
echo Output: bin\main\
echo.
pause
