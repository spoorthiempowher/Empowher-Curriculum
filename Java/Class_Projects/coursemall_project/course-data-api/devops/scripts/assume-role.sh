#!/bin/bash
role_arn=$1
role_session_name=$2

read AWS_ACCESS_KEY_ID AWS_SECRET_ACCESS_KEY AWS_SESSION_TOKEN <<< \
    $(aws sts assume-role \
         --role-arn $role_arn \
         --role-session-name $role_session_name --output text | \
          awk '/^CREDENTIALS/ { print $2, $4, $5 }')
          
export AWS_ACCESS_KEY_ID
export AWS_SECRET_ACCESS_KEY
export AWS_SESSION_TOKEN