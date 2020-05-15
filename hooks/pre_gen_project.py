import re
import sys

# Check the app name

APP_NAME_REGEX = r'[^A-Za-z0-9 ]'

app_name = '{{ cookiecutter.app_name }}'

if re.search(APP_NAME_REGEX, app_name):
    print('ERROR: please avoid using any special characters in your app name!')
    print('Include only alphanumeric characters and spaces.')

    # Exits with status 1 to indicate failure
    sys.exit(1)

# Check the package name

PACKAGE_REGEX = r'[^A-Za-z0-9.]'

package_name = '{{ cookiecutter.package_name }}'

if re.search(PACKAGE_REGEX, package_name):
    print('ERROR: %s is not a valid Android package name!' % package_name)
    print('Avoid using any special characters. Only alphanumeric characters are allowed.')

    # Exits with status 1 to indicate failure
    sys.exit(1)


print ('\n\n###############################')
print ('Setup for "{}" started.'.format(app_name))
print ('Please wait a few seconds...........\n')