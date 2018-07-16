#!/bin/sh

git init

git add -A

cd ..
chmod -R 777 "{{ cookiecutter.repo_name }}"

echo "Project setup completed -> Happy coding"
