#!/bin/bash

RED=$(tput setaf 1)
GREEN=$(tput setaf 2)
BLUE=$(tput setaf 4)
YELLOW=$(tput setaf 3)
NORMAL=$(tput sgr0)

error() {
  echo "${RED}ERROR    => $1 ${NORMAL}" >&2
  echo >&2
  exit 1
}

info() {
  echo "${BLUE}   => $1 ${NORMAL}"
}

success() {
  echo "${GREEN}SUCCESS => $1 ${NORMAL}"
}

# Check if jq is installed
if ! command -v jq &> /dev/null; then
    error "jq is required but it's not installed. Please install jq (https://stedolan.github.io/jq/download/) and try again."
fi

base_url="http://127.0.0.1:3000/metabase"

info "Getting Metabase setup token..."
setup_token=$(curl --fail --insecure "${base_url}/api/session/properties" \
                -H 'Accept: application/json' \
                -H 'Cache-Control: no-cache' \
                -H 'Content-Type: application/json' | jq -r '.["setup_token"]')
export setup_token

info "Calling main setup endpoint..."
body=$(envsubst <./init-metabase.json)
session_token=$(curl --fail --insecure "${base_url}/api/setup" \
                  -H 'Accept: application/json' \
                  -H 'Cache-Control: no-cache' \
                  -H 'Content-Type: application/json' \
                  --data-raw "$body" | jq -r '.id')

info "Configuring settings with admin session token..."
curl --fail --insecure "${base_url}/api/setting/site-url" \
     -X 'PUT' \
     -H 'Accept: application/json' \
     -H 'Cache-Control: no-cache' \
     -H 'Content-Type: application/json' \
     --data-raw '{"value":"http://127.0.0.1:3000/metabase/"}'

curl --fail --insecure "${base_url}/api/setting/enable-public-sharing" \
     -X 'PUT' \
     -H 'Accept: application/json' \
     -H 'Cache-Control: no-cache' \
     -H 'Content-Type: application/json' \
     -H "X-Metabase-Session: $session_token" \
     --data-raw '{"value":true}'

curl --fail --insecure "${base_url}/api/setting/enable-embedding" \
     -X 'PUT' \
     -H 'Accept: application/json' \
     -H 'Cache-Control: no-cache' \
     -H 'Content-Type: application/json' \
     -H "X-Metabase-Session: $session_token" \
     --data-raw '{"value":true}'

info "Setting up embedding secret key..."
embedding_secret_key=$(curl --fail --insecure "${base_url}/api/util/random_token" \
                          -H 'Accept: application/json' \
                          -H 'Cache-Control: no-cache' \
                          -H 'Content-Type: application/json' \
                          -H "X-Metabase-Session: $session_token" | jq -r ".token")

curl --fail --insecure "${base_url}/api/setting/embedding-secret-key" \
     -X 'PUT' \
     -H 'Accept: application/json' \
     -H 'Cache-Control: no-cache' \
     -H 'Content-Type: application/json' \
     -H "X-Metabase-Session: $session_token" \
     --data-raw "{\"value\":\"$embedding_secret_key\"}"

success "Embedding secret key: $embedding_secret_key"
info "This key can also be obtained from the Metabase admin panel"

success "Metabase setup complete"