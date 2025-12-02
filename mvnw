#!/usr/bin/env bash
# Simple mvnw shim: tries to run ./mvnw or mvn. If no mvn, instruct user to run bootstrap script.
if command -v mvn >/dev/null 2>&1; then
  mvn "$@"
else
  echo "Maven não encontrado. Execute 'mvnw-bootstrap.ps1' em PowerShell para baixar o wrapper, ou instale o Maven." 
  exit 1
fi
