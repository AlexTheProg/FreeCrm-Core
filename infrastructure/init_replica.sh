#!/bin/bash

if [ -z "$(ls -A /var/lib/postgresql/data)" ]; then
    until pg_basebackup --pgdata=/var/lib/postgresql/data -R --slot=replication_slot --host=postgres_primary --port=5432
    do
        echo 'Waiting for primary to connect...'
        sleep 2s
    done

    echo 'Backup done, starting replica...'
    chmod 0700 /var/lib/postgresql/data
    postgres
else
    echo "/var/lib/postgresql/data directory is not empty. Using existing data"
fi