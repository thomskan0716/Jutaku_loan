@echo off
setlocal

set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_45
set PROJECT_HOME=C:\Users\sun.ykawakamit\Desktop\静岡銀行_S-plus\branches\新帰属入\050000_データ移行#JTAKU_LOAN\migration_batch_JUTAKU

cd /d "%PROJECT_HOME%"

if not exist "%JAVA_HOME%\bin\java.exe" (
    echo ERROR: Java not found
    pause
    exit /b 1
)

if not exist "bin\main" (
    echo ERROR: bin\main not found
    pause
    exit /b 1
)

if not exist "libs" (
    echo ERROR: libs not found
    pause
    exit /b 1
)

set CLASSPATH=bin\main
for %%f in (libs\*.jar) do call set CLASSPATH=%%CLASSPATH%%;%%f

echo Starting migration...

"%JAVA_HOME%\bin\java.exe" ^
    -Xms512m ^
    -Xmx2048m ^
    -Dspring.profiles.active=production ^
    -Dfile.encoding=UTF-8 ^
    -classpath "%CLASSPATH%" ^
    migration.MigrationBatchApplication

if %ERRORLEVEL% NEQ 0 (
    echo Migration failed: %ERRORLEVEL%
    pause
    exit /b %ERRORLEVEL%
)

echo Migration completed
pause
