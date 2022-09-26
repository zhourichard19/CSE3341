#!/bin/bash

if [ $# != 1 ]; then
    echo "USAGE: runenhancedwith.sh dataFile"
    echo "For example:"
    echo "./runenhancedwith.sh data"
    exit
fi

#Command to run
TO_RUN=$(tr -d '\12\15\32' < RunfileEnhanced)

#Data file
FILE_NAME=$1

# echo "Running command:" $TO_RUN
# echo "With file:" $1

$TO_RUN $FILE_NAME

