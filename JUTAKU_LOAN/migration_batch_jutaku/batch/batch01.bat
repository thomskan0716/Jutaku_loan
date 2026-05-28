@echo off
setlocal

set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_45
set PROJECT_HOME=C:\Users\sun.ykawakamit\Desktop\静岡銀行_S-plus\branches\新帰属入\050000_データ移行#JTAKU_LOAN\migration_batch_JUTAKU

cd /d "%PROJECT_HOME%"

set CLASSPATH=bin\main
for %%f in (libs\*.jar) do call set CLASSPATH=%%CLASSPATH%%;%%f

echo Starting batch instance 01/15...

"%JAVA_HOME%\bin\java.exe" ^
    -Xms512m ^
    -Xmx2048m ^
    -Dspring.profiles.active=production ^
    -Dfile.encoding=UTF-8 ^
    -Dbatch.instance=01 ^
    -classpath "%CLASSPATH%" ^
    migration.MigrationBatchApplication

if %ERRORLEVEL% NEQ 0 (
    echo Batch 01 failed: %ERRORLEVEL%
    pause
    exit /b %ERRORLEVEL%
)

echo Batch 01 completed
pause
