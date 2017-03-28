@echo off

set PROGRAM_DIR=c:\Code\Projects\Utils\RenameEbooks

java -cp "%PROGRAM_DIR%\RenameEbooks.jar" com.humidmail.utils.ebooks.RenameEbooks %1

echo.

pause
