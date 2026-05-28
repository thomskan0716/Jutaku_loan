@echo off
setlocal enabledelayedexpansion

set PROJECT_HOME=C:\Users\sun.ykawakamit\Desktop\静岡銀行_S-plus\branches\新帰属入\050000_データ移行#JTAKU_LOAN\migration_batch_JUTAKU

cd /d "%PROJECT_HOME%"

echo Generating 15 batch scripts...

for /L %%i in (1,1,15) do (
    set NUM=0%%i
    set NUM=!NUM:~-2!
    
    (
        echo @echo off
        echo setlocal
        echo.
        echo set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_45
        echo set PROJECT_HOME=C:\Users\sun.ykawakamit\Desktop\静岡銀行_S-plus\branches\新帰属入\050000_データ移行#JTAKU_LOAN\migration_batch_JUTAKU
        echo.
        echo cd /d "%%PROJECT_HOME%%"
        echo.
        echo set CLASSPATH=bin\main
        echo for %%%%f in ^(libs\*.jar^) do call set CLASSPATH=%%%%CLASSPATH%%%%;%%%%f
        echo.
        echo echo Starting batch instance %%i/15...
        echo.
        echo "%%JAVA_HOME%%\bin\java.exe" ^
        echo     -Xms512m ^
        echo     -Xmx2048m ^
        echo     -Dspring.profiles.active=production ^
        echo     -Dfile.encoding=UTF-8 ^
        echo     -Dbatch.instance=%%i ^
        echo     -classpath "%%CLASSPATH%%" ^
        echo     migration.MigrationBatchApplication
        echo.
        echo if %%ERRORLEVEL%% NEQ 0 ^(
        echo     echo Batch %%i failed: %%ERRORLEVEL%%
        echo     pause
        echo     exit /b %%ERRORLEVEL%%
        echo ^)
        echo.
        echo echo Batch %%i completed
        echo pause
    ) > batch!NUM!.bat
)

echo.
echo Generated: batch01.bat - batch15.bat
echo Ready for parallel execution
pause
