@echo off
REM Simple mvnw shim for Windows: tries to run mvn, otherwise instructs to run bootstrap.
where mvn >nul 2>&1
if %errorlevel%==0 (
  mvn %*
) else (
  echo Maven nao encontrado. Abra PowerShell e execute .\mvnw-bootstrap.ps1 para baixar o wrapper, ou instale o Maven.
  exit /b 1
)
