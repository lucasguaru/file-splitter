@echo off
set EMAIL_BANKING_HOME=C:\MBB_App
set BASE_LOG_PATH=%EMAIL_BANKING_HOME%\logs
set JAR_FILE=file-splitter-0.0.1-SNAPSHOT.jar
set JAR_FILE=file-splitter-0.0.1-SNAPSHOT-jar-with-dependencies.jar
set LOG_LEVEL=

if "%9"=="" GOTO RUN
	set LOG_LEVEL=-DlogLevel=%9
:RUN

java -DlabelSystem=%1 -DprofileName=%2 -DprojectName=%3 -DnumberOfProfiles=%4 -DnumberOfRecords=%5 -DinputPath=%6 -DoutputPath=%7 -DBASE_LOG_PATH="%BASE_LOG_PATH%" -DrunningMode=prod %LOG_LEVEL% -jar %JAR_FILE% 