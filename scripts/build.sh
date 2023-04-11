#!/bin/bash
SOURCE=${BASH_SOURCE[0]}
while [ -L "$SOURCE" ]; do
    DIR=$(cd -P "$(dirname "$SOURCE")" >/dev/null 2>&1 && pwd)
    SOURCE=$(readlink "$SOURCE")
    [[ $SOURCE != /* ]] && SOURCE=$DIR/$SOURCE 
done
DIR=$(cd -P "$(dirname "$SOURCE")" >/dev/null 2>&1 && pwd)

if [ $# -eq 0 ]; then
    echo -e "[\033[1;31m Erro \033[00m] Informa um parâmetro de stage [ dev ou prod ]"
    exit 1
fi

stage="$1"
if command mvn "-P${stage}" clean package; then
    echo -e "[\033[1;32m OK \033[00m] Build executado com sucesso para o stage: $stage"   
else
    echo -e "[\033[1;31m ERRO \033[00m] Não foi possível realizar o processo de build..."
    exit 1
fi