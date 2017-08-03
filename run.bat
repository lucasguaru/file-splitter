set LOG_LEVEL_RUN=
if "%1"=="" GOTO RUN
	set LOG_LEVEL_RUN=-DlogLevel=%1
:RUN
run_batch uy UY0001 EmailBanking 10 100 "C:\\MBB_App\\DFS" "C:\\MBB_App\\DFS" "C:\\MBB_App\\DFS\logs\\" %LOG_LEVEL_RUN%