@echo off
setlocal

set ORACLE_HOME=C:\oracle\product\12.2.0\dbhome_1
set ORACLE_SID=E00197SV0203
set PATH=%ORACLE_HOME%\bin;%PATH%

set DUMP_DIR=C:\Users\sun.ykawakamit\Desktop\静岡銀行_S-plus\branches\新帰属入\050000_データ移行#JTAKU_LOAN\dumps
set DATE_STR=%DATE:~0,4%%DATE:~5,2%%DATE:~8,2%
set DUMP_FILE=JUTAKU_STRUCTURE_%DATE_STR%.dmp
set LOG_FILE=JUTAKU_EXPORT_%DATE_STR%.log

echo Exporting table structure...
echo ================================================
echo.
echo Source: E00197SV0203/SZH_SMS
echo Tables: 申込, 申込審査状況
echo Export Type: METADATA_ONLY (structure only, no data)
echo.
echo Dump File: %DUMP_DIR%\%DUMP_FILE%
echo Log File: %DUMP_DIR%\%LOG_FILE%
echo.
echo ================================================

REM Create dump directory if not exists
if not exist "%DUMP_DIR%" mkdir "%DUMP_DIR%"

REM Execute export
expdp userid=SZH_SMS/password@E00197SV0203 ^
    directory=DATA_PUMP_DIR ^
    dumpfile=%DUMP_FILE% ^
    logfile=%LOG_FILE% ^
    tables=申込,申込審査状況 ^
    content=METADATA_ONLY

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ================================================
    echo ERROR: Export failed with error code %ERRORLEVEL%
    echo ================================================
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo ================================================
echo Export completed successfully!
echo ================================================
echo.
echo Next step: Run import_structure.bat on target system
echo.
pause

endlocal
