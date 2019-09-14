cd ./telegrambotinfrastructure/

sbt assembly

cp ./bin/TelegramBotInfrastructure-0.0.1.jar ../richardPHJBensonBot/lib/
mv ./bin/TelegramBotInfrastructure-0.0.1.jar ../calandroBot/lib/

cd ..

ls -l ./calandroBot//lib/ #to see the content of the lib folder
ls -l ./richardPHJBensonBot/lib/ #to see the content of the lib folder


(cd ./calandroBot/; sbt test) &
(cd ./richardPHJBensonBot/; sbt test) &
(cd ./calandroBot/; sbt run) &
(cd ./djJacobBot/; sbt run) &
(cd ./richardPHJBensonBot/; sbt run)
