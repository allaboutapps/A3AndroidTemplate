#!/bin/sh

set -o errexit
set -o nounset

# enabling POSIX-compliant behavior for GNU programs
export POSIXLY_CORRECT=yes POSIX_ME_HARDER=yes

package_name_dir="{{ cookiecutter.package_name_dir }}"
string_tool="{{ cookiecutter.string_tool }}"
use_fb_crashlytics="{{ cookiecutter.firebase_crashlytics }}"
use_fb_analytics="{{ cookiecutter.firebase_analytics }}"
use_fb_messaging="{{ cookiecutter.firebase_messaging }}"

printf ' Done.\n\n'

if [ "$use_fb_analytics" = 'yes' ] || [ "$use_fb_messaging" = 'yes' ] || [ "$use_fb_crashlytics" = 'yes' ]; then
    if [ "$use_fb_analytics" = 'yes' ]; then
        printf '(*) Using [Firebase Analytics]\n'
    fi

    if [ "$use_fb_messaging" = 'yes' ]; then
        printf '(*) Using [Firebase Messaging]\n'
    fi

    if [ "$use_fb_crashlytics" = 'yes' ]; then
        printf '(*) Using [Firebase Crashlytics]\n'
    fi
else
    printf '(*) Using no Firebase services\n'
fi

if [ "$use_fb_messaging" != 'yes' ]; then
    if [ "$use_fb_analytics" != 'yes' ] && [ "$use_fb_crashlytics" != 'yes' ]; then
        rm -- 'app/google-services.json'
    fi

    rm -r -- "app/src/main/java/$package_name_dir/features/fcm" \
             "app/src/main/java/$package_name_dir/di/FirebaseModule.kt"
fi

case "$string_tool" in
    ('none')
        rm -- 'app/texterify.json'
        printf '(*) Using no string tool\n'
        ;;
    ('texterify')
        printf '(*) Using [Texterify] as string tool\n'
        ;;
    (*)
        printf 'Unknown string tool: %s\n' "$string_tool" >&2
        exit 1
        ;;
esac

if [ "$use_fb_analytics" = 'yes' ] || [ "$use_fb_messaging" = 'yes' ] || [ "$use_fb_crashlytics" = 'yes' ]; then
    clr_red=''
    clr_reset=''

    if [ -t 1 ] &&
       [ -z "${NO_COLOR-}" ] && # <https://no-color.org>
       command -v tput > '/dev/null' &&
       tput setaf 3 > '/dev/null' 2> '/dev/null' &&
       tput sgr0 > '/dev/null' 2> '/dev/null'; then

        clr_red="$(tput setaf 3 || true)"
        clr_reset="$(tput sgr0 || true)"
    fi

    printf '\n%s' "$clr_red"
    printf '    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n'
    printf "    !!! Do not forget to modify the file 'app/google-services.json' !!!\\n"
    printf '    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!'
    printf '%s\n' "$clr_reset"
fi

git init --quiet --initial-branch='master'
git add --all

printf '\nProject setup completed. Happy coding! :)\n'
