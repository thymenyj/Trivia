TRIVIA 

CREATOR: Thymen van de Lagemaat 

APP DESCRIPTION: 
The app is a quiz game. There is a singleplayer mode (highest non wrong answer streak)
and multiplayer mode (who answered the most questions

ACTIVITIES: The app contains the following activities:

- LOGIN: this activities asks for a email and password to log the user in, using a Firebase authentication.
         From the loginactivity, the user can go to the REGISTER activity or the MENU activity (after login).
       
- REGISTER: this activity asks for a name, email and password to create a new user in Firebase. After a registration 
            is successful a new 'user' is added under the child(Id of the user)to the database containing a 
            highscore(starting highscore = 0) and name (editText input).
            
- MENU: this activities contains 4 buttons to go to other activities:
           - SINGLEPLAYER 
           - MULTIPLAYER
           - HIGHSCORE
           - LOGOUT (back to login)
          
- SINGLEPLAYER: The activity starts a singleplayer game. The activity asks for a JSONobject from jservice.io.
                The object is a object from a random category containing serval question objects. A random question with correct
                answer is loaded and two answers from two other question objects from the category.
                When the answer is correct a new question is asked. When the answer is incorrect (or the player clicks the quit button) the user returns to the menu. If the score 
                was higher than the score in the Firebase, the highscore in the database is updated. 
         
- MULTIPLAYER(2player): The activity starts a startmultiplayeractivity where the amount of questions is asked. This number is 
                via intent transferd to the next multiplayergameactivity.
                GAMEACTIVITY: the activity starts a game with a counter(amount from startactivity). After each question the playerturn
                is switched. When the amountcount is zero the user is redirected to the MULITPLAYERSCORE activity. This activity shows
                who won and the score for player 1 and player 2.
                
- HIGHSCORE: the activity is a listview of the highscores in the Firebase database. The activity calls the firebase to request a list of 
             the highscores of the users. The arraylist is sorted to sort the list based on highscore. 
            
                
           
                

                 
