#!/bin/bash

APP_NAME="{{ cookiecutter.app_name }}"
USE_ANALYTICS="{{ cookiecutter.firebase_analytics }}"
USE_FB_MESSAGING="{{ cookiecutter.firebase_messaging }}"

echo "*) Project ($APP_NAME) created"

git init > /dev/null
git add .

cd ..

echo "*) GIT setup completed"

if [[ $USE_ANALYTICS == "yes" || $USE_FB_MESSAGING == "yes" ]]
then
    if [[ $USE_ANALYTICS == "yes"  ]]
    then
        echo "*) Firebase Analytics setup successful"
    fi
    if [[ $USE_FB_MESSAGING == "yes"  ]]
    then
        echo "*) Firebase Messaging setup successful"
    fi

    echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
    echo "Please do not forget to modify google-services.json file!!!!"
    echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
fi


if [[ $USE_FB_MESSAGING != "yes" ]]
then
    echo "*) Unused FCM folders and files removed"
    cd {{ cookiecutter.repo_name }}/app
    if [[ $USE_ANALYTICS != "yes" ]]
    then
        rm google-services.json
    fi

    cd src/main/java/{{ cookiecutter.package_name }}/features
    rm -rf fcm
fi

echo -e "\nProject setup completed -> Happy coding\n\n"
