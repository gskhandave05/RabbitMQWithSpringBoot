# RabbitMQWithSpringBoot

Author : Gaurav Khandave

Version : 1.0.0

## Install RabbitMQ on MacOSx using Homebrew
1. A brew for RabbitMQ server is available from Homebrew.
2. First make sure you update the homebrew using "brew update".
3. Install RabbitMQ server with "brew install rabbitmq". It will install rabbitmq at "/usr/local/sbin". 
4. Add this to your path variable in .bash_profile.
5. To have launchd start rabbitmq now and restart at login:
    brew services start rabbitmq 
  Or, if you don't want/need a background service you can just run:
    rabbitmq-server 
  Server starts on port 15672 by default.
6. Management Plugin enabled by default at http://localhost:15672
    The broker creates a user "guest" with password "guest" for you.

## Things to be checked before starting the application:

1.  Update the application.properties file MongoDB configurations.
2.  Create a new database in mongoDB as rabbitmq
3.  Start rabbitmq server and correctly configure in application.properties (default is localhost:15672) )
