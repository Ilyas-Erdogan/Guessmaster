package com.example.guessmasteractivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GuessMaster extends AppCompatActivity {
    // Instance Variables
    private TextView entityName;
    private TextView ticketsum;
    private Button guessButton;
    private EditText userIn;
    private Button btnclearContent;
    private String user_input;
    private ImageView entityImage;
    String answer;

    // Imported from original guess master
    private int numOfEntities;
    private Entity[] entities;
    private int awardedTickets = 0;
    private int entityid = 0;
    private String entName;

    // Entity objects
    private final Singer dion = new Singer("Celine Dion", new Date("March", 30, 1961), "Female", "La voix du bon Dieu", new Date("November", 6, 1981), 0.5);
    private final Person myCreator = new Person("myCreator", new Date("September", 1, 2000), "Female", 1);
    private final Country usa = new Country("United States", new Date("July", 4, 1776), "Washington D.C.", 0.1);
    private final Politician trudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), "Male", "Liberal", 0.25);

    // Context instance variable (for toasts)
    public Context context;

    public GuessMaster() {
        numOfEntities = 0;
        entities = new Entity[100];
        awardedTickets = 0;
    }

    /**
     * Adds entity to the array of existing entities
     *
     * @param entity entity Entity to append to the array
     */
    public void addEntity(Entity entity) {
        entities[numOfEntities++] = entity.clone();
    }

    /**
     * Begin playing the guessing game according to the given entity ID
     *
     * @param entityId index to use within entity list to challenge player with
     */
    public void playGame(int entityId) {
        Entity entity = entities[entityId];
        playGame(entity);
    }

    /**
     * Begin playing guessing game based on given entity object.
     * User will continually be asked to guess the birthday of the given entity until correct,
     * and will be awarded points accordingly.
     *
     * @param entity entity to challenge player with
     */
    public void playGame(Entity entity) {
        // Convert user input from textbox into java string object
        answer = userIn.getText().toString();
        answer = answer.replace("\n", " ").replace("\r", " ");

        // Check if user input is empty, provide warning toast if true
        if (answer.isEmpty()) {
            Toast.makeText(context, "Please type something!", Toast.LENGTH_SHORT).show();
        } else {
            Date date = new Date(answer);
            if (date.precedes(entity.getBorn())) {
                // Create new alert pop-up box for user telling them to try a later date
                AlertDialog.Builder precedesmessage = new AlertDialog.Builder(context);
                precedesmessage.setTitle("Incorrect");
                precedesmessage.setMessage("Try a later date");
                precedesmessage.setCancelable(false);

                // Create toast once user has pressed negative button
                precedesmessage.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Retrying...", Toast.LENGTH_SHORT).show();
                    }
                });

                // Show the created dialog
                AlertDialog dialog = precedesmessage.create();
                dialog.show();
            } else if (entity.getBorn().precedes(date)) {
                // Create new alert pop-up box for user telling them to try an earlier date 
                AlertDialog.Builder succeedsmessage = new AlertDialog.Builder(context);
                succeedsmessage.setTitle("Incorrect");
                succeedsmessage.setMessage("Try an earlier date");
                succeedsmessage.setCancelable(false);

                // Create toast once user has pressed negative button
                succeedsmessage.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Retrying...", Toast.LENGTH_SHORT).show();
                    }
                });
                // Show the created dialog
                AlertDialog dialog = succeedsmessage.create();
                dialog.show();
            } else {
                // Award appropriate number of tickets to the user
                awardTickets(entity.getAwardedTicketNumber());

                // Create new alert pop-up box for user telling them they won as well as provide entity details
                AlertDialog.Builder continuemessage = new AlertDialog.Builder(context);
                continuemessage.setTitle("You won");
                continuemessage.setMessage("BINGO! " + entity.closingMessage());
                continuemessage.setCancelable(false);

                // Create toast once user has pressed negative button
                continuemessage.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Tickets Awarded: " + entity.getAwardedTicketNumber(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Show the created dialog
                AlertDialog dialog = continuemessage.create();
                dialog.show();

                // Update the current number of accumulate tickets on the user's screen and the continue the game
                updateTicketText();
                ContinueGame();
            }
        }

    }

    /**
     * Begin playing guessing game with randomly generated indexes to be used with given entity array.
     */
    public void playGame() {
        playGame(entityid);
    }

    /**
     * Generates random integer index to be used with entity array.
     *
     * @return randomly generated integer index
     */
    public int genRandomEntityId() {
        Random randomNumber = new Random();
        return randomNumber.nextInt(numOfEntities);
    }

    /**
     * Accumulates user tickets based on given parameter.
     *
     * @param numOfTicketsToAdd number of tickets to add to user
     */
    public void awardTickets(int numOfTicketsToAdd) {
        setAwardedTickets(awardedTickets + numOfTicketsToAdd);
    }

    // Acessor (Getter)

    /**
     * @return Return number of tickets player has accumulated (if any)
     */
    public int getAwardedTickets() {
        return awardedTickets;
    }

    // Mutator (Setter)

    /**
     * Reset number of user-accumulated tickets according to given parameter.
     *
     * @param tickets Number of tickets to set for user
     */
    public void setAwardedTickets(int tickets) {
        awardedTickets = tickets;
    }

    /**
     * Welcomes the user to the game using a pop-up
     *
     * @param entity entity to use for application start-up welcome message
     */
    public void welcomeToGame(Entity Entity) {
        // Create new alert pop-up box for user welcoming them to the game as well indicating the entity type
        AlertDialog.Builder welcomealert = new AlertDialog.Builder(context);
        welcomealert.setTitle("GuessMaster Game v3");
        welcomealert.setMessage(Entity.welcomeMessage());
        welcomealert.setCancelable(false);

        // Create toast once user has pressed negative button
        welcomealert.setNegativeButton("START GAME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Game is Starting... Enjoy", Toast.LENGTH_SHORT).show();
            }
        });
        // Show the newly created dialog
        AlertDialog dialog = welcomealert.create();
        dialog.show();
    }

    /**
     * Set the image resource to be displayed on the screen in accordance with the current entity name
     */
    public void ImageSetter() {
        switch (entName) {
            case "Justin Trudeau":
                entityImage.setImageResource(R.drawable.justint);
                break;
            case "Celine Dion":
                entityImage.setImageResource(R.drawable.celidion);
                break;
            case "United States":
                entityImage.setImageResource(R.drawable.usaflag);
                break;
            case "myCreator":
                entityImage.setImageResource(R.drawable.mycreator);
                break;
        }
    }

    /**
     * Clears current user input and changes entity
     */
    public void changeEntity() {
        userIn.getText().clear();
        ContinueGame();
    }

    /**
     * Generate and set-up randomly generated entity
     */
    public void ContinueGame() {
        entityid = genRandomEntityId();
        Entity entity = entities[entityid];

        entName = entity.getName();

        // Call the ImageSetter method
        ImageSetter();

        // Print the name of the entity to be guessed
        // in entityName textview
        entityName.setText(entName);
        // Clear Previous Entry
        userIn.getText().clear();
    }

    /**
     * Update current amount of tickets displayed to the user in accordance with current accumulated tickcets
     */
    public void updateTicketText() {
        String text = "Tickets: " + getAwardedTickets();
        ticketsum.setText(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final GuessMaster gm = new GuessMaster();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_activity);
        // Specify the guess button in the view
        guessButton = (Button) findViewById(R.id.btnGuess);
        // Specify the change button in the view
        btnclearContent = (Button) findViewById(R.id.btnClear);
        // EditText for user input
        userIn = (EditText) findViewById(R.id.guessinput);
        // TextView for total tickets
        ticketsum = (TextView) findViewById(R.id.ticket);
        // ImageView of person in the view
        entityImage = (ImageView) findViewById(R.id.entityImage);
        // Specify entity name in the view
        entityName = (TextView) findViewById(R.id.entityName);

        // Add all entities to the entity list
        addEntity(dion);
        addEntity(myCreator);
        addEntity(trudeau);
        addEntity(usa);

        // Create context reference for alert dialog
        context = this;

        // Change entity at startup
        changeEntity();

        // Create welcome message specific to current entity displayed to user
        welcomeToGame(entities[entityid]);

        // OnClick Listener action for clear button
        btnclearContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeEntity();
            }
        });

        // OnClick Listener action for guess button
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame();
            }
        });

    }
}
