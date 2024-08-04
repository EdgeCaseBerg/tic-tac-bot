## Tic Tak Bot

This is a discord bot that will facilitate a game of Tic Tac Toe between 
users in a discord server. 

Create a token.txt file and place it next to the executable, include your
Bot token from your [application dashboard] inside the file and don't
share it with anyone!

Run the program, and then add the bot to your discord server and it will
play games with people via the `!tt` commands.

[application dashboard]:https://discord.com/developers/applications


## Building an exe

Run gradle `shadowJar` then

```
jpackage \
-t app-image \
-i build \
-n tictacbot \
--main-jar libs/PeetsBot1-1.0-SNAPSHOT-all.jar \
--main-class space.peetseater.bot.Main \
--verbose \
--description "Plays Tic Tac toe with people" \
--win-console
```