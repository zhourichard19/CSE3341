#!/bin/bash

if [ $# != 1 ]; then
    echo "USAGE: runwith.sh dataFile"
    echo "For example:"
    echo "./runwith.sh data"
    exit
fi

#Command to run
TO_RUN=$(tr -d '\12\15\32' < Runfile)

#Data file
FILE_NAME=$1

# echo "Running command:" $TO_RUN
# echo "With file:" $1

$TO_RUN $FILE_NAME

