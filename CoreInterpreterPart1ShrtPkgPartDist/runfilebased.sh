#!/bin/bash

if [ $# != 2 ]; then
    echo "USAGE: runfilebased.sh Runfile testFilesFolder"
    echo "For example:"
    echo "./runfilebased.sh Runfile data"
    exit
fi

#Command to run
TO_RUN=$(tr -d '\12\15\32' < $1)

#Location of the data folder
FILE_DIR=$2

echo "Running command:" $TO_RUN
echo "With files in folder:" $2

for FILE_NAME in $(ls $FILE_DIR/ | grep -v 'output'); do
    echo 'file:' $FILE_NAME
#    ACTUAL=$($1 $FILE_DIR/$FILE_NAME)
#    echo $ACTUAL
#    EXPECTED=$(tr -d '\15\32' < $FILE_DIR/${FILE_NAME}expected-output)
#    echo $EXPECTED
    #echo $FILE_DIR/${FILE_NAME}expected-output
    diff <($TO_RUN $FILE_DIR/$FILE_NAME)  <(tr -d '\15\32' < $FILE_DIR/${FILE_NAME}expected-output)
done

