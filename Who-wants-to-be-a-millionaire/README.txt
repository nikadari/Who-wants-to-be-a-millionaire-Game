Who Wants to be a Millionaire ?
by: Nika & Claudia 

Read Me file :)

Things to lookout for:

- Categories:
	*Default category is Pop Culture. Categories can be changed at any time within the game. If changed, screen will refresh back to start up screen.

- Pop-up message:
	*If user is in the middle of a game (not start screen), and wishes to change category, a JOption Pop Up window will ask if user would like to restart the game.

- Sound On/Off:
	*Added a sound On & Off feature, where the user will be able to turn on and off the background music at any given time during the game. (See Menu Bar)

- Customized Features
	*We customized the JOptionPanes, buttons, cursor and menu bar. Prettiness has to be worth something right? (:o)
	*Bonus: very cute sound effects + gifs! :)

- Score < 5 rule:
	*If player plays through more than 5 questions then he/she will walk away with the amount listed on the side. However, if the player loses with less than 5 questions answered then the player will walk away with $0 (lose it all).

- Change of fonts in game:
	* We had to change the text sizes at different parts of the game because some questions and answers required more space than others. If text length is greater than a certain size, a smaller font is used. Text is not written to be centred. Inconsistency within the text fonts will occur. 

- 50/50 Blacked Out Boxes:
	*While coding the 50/50 features, we decided it would be easier if we would cover up the eliminated answers with filled black rectangles. However, due to time 	restraints, we were unable to take out the mouse clicked 50/50 option. Therefore, click on the blacked out boxes during 50/50 or else the game consider it an answer and you will lose the game because you would have picked the wrong answer.

- 50/50 & Audience Help
	*These were pretty difficult to figure out on our own so we are really proud that we actually figured it out and made it work. We were so close to giving up but we 	pushed through and here we are! :D

- Window Sizing
	*Since mine and Claudia's window sizes and pixels were different, there is a possibility that yours might be as well. This might change the sizing of the screen/buttons depending on whether this is an issue for you or not.

Known Bugs:

- Gifs and Sound Effects (winner screen & loser screen):
	*Java/JFrame has a bug when it comes to running a sound file and gif at the same time. The bug causes the sound file not to play thus, eliminating the sound effects for that particular screen. However, if you run the sound file without the gif then the sound will play. If you notice certain parts of the the code either the gif or the sound file is commented out. Feel free to mess around with uncommenting and commenting to see how we intended the program to run :)

- 50/50 after winning
	*50/50 Lifeline option works almost all the time. The option works when playing the game normally (first time) and then again (second time) when user has LOST and 	then selected new game option from MenuBar. There is only one instance where it does not work (Not sure why, possibly due to Graphics component). When user has WON the first time and selects new game option from MenuBar, 50/50 does not work. Works every other time (after losing & during first play).

Next Version Improvements: 
- More Category Options
- Fixed 50/50
