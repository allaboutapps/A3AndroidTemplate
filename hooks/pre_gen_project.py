import re
import shutil
import sys


VALID_APP_NAME_REGEX: str = r'^[A-Za-z0-9 -]+$'

app_name: str = '{{ cookiecutter.app_name }}'

if re.fullmatch(VALID_APP_NAME_REGEX, app_name) is None:
    print(f'\nInvalid app name "{app_name}"')
    print('Only alphanumeric characters, spaces and dashes are allowed\n')

    sys.exit(1)


VALID_PACKAGE_NAME_REGEX: str = r'^[a-z][a-z0-9]*(\.[a-z][a-z0-9]*)*$'

package_name: str = '{{ cookiecutter.package_name }}'

if re.fullmatch(VALID_PACKAGE_NAME_REGEX, package_name) is None:
    print(f'\nInvalid package name "{package_name}"')
    print('Only lowercase letters and digits are allowed (separated by single periods)\n')

    sys.exit(1)


msg: str = '\n'
msg += ('#' * shutil.get_terminal_size().columns) + '\n\n'
msg += f'Setting up new project "{app_name}"...\n'
msg += '\nCopying files...'
print(msg, end='')
