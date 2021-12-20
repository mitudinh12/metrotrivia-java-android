# Android App Project
## Variables - Use for coding to avoid checking the layout
### Activity_main
`greeting_text_view` - Main text : heading that greets \
`opening_word_text_view` - Secondary text : short moto \
\
`next_button` - Button to start the game \
`profile_page_button` - Button that leads to profile/leaderboard \

### activity_chose_mode
`chose_mode_text_view` - "Choose mode" text \
\
{`easy_button`
`medium_button`
`hard_button`} - Buttons for choosing the difficulty \

## activity_profile_screen
`textView{3-5}` - Scores for easy difficulty. \
`textView{7-9}` - Scores for medium difficulty. \
`textView{11-13}` - Scores for hard difficulty.

## activity_the_game
`answer_{1-4}_button` - buttons for answer variants. \
`progress_bar` - progress bar \
`next_button` - button to check the question and move on to the next one. \


## Logic for the "next" button in the game activity
1. Depending on the chosen difficulty, parse a question, right answer and incorrect answers from the json. Add those to the QuestionData class. (Duplicate questions will be eliminated by comparing the question number to the previous one).
2. Check if the player has answered correctly: \
if yes --> Light up the button green, update the progress bar and wait 5 seconds. Switch the questions (Step 1)
   \
if no --> Light up the button green and make the right one green. Go to step 1.


## App logic
Main menu (Main activity) --> Choose mode activity --> Save the choice and pass to next activity --> Start the game with getting the difficulty --> Once answered, make a correct button green and incorrect red --> Go to the last activity.

## Question extraction logic
Questions to be found in /q/file.json

Each block has variable 'question', 'incorrect_answers' and 'correct_answer'. They all have a number at the end like so:
```json
"question34": "Which of the following is a class in the game &quot;Hearthstone&quot;?",
"correct_answer34": "Priest",
"incorrect_answers34": [
          "Sage",
          "Cleric",
          "Monk"
]
```

1. Generate 5 numbers within 1-50 range (5 questions per game)
2. Extract results['question[number]'] and results['incorrect_answers[number]'] and so on.
3. if-statement to check if there are more than one *incorrect_answer*. If so, it is a 4 choice question. If only one - True or False.
