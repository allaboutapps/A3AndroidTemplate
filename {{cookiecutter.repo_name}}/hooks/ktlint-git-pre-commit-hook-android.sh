#!/bin/sh

# <https://github.com/pinterest/ktlint> pre-commit hook

# On Linux xargs must be told to do nothing on no input. On MacOS (linux distribution "Darwin") this is default behavior
# and the xargs flag "--no-run-if-empty" flag does not exists
if [ "$(uname -s)" != "Darwin" ]; then
    no_run_if_empty='--no-run-if-empty'
fi

git --no-pager diff --name-only --cached --relative -- '*.kt' '*.kts' |
    xargs $no_run_if_empty ktlint --android --relative
