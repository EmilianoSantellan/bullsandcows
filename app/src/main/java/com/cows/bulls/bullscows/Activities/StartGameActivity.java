package com.cows.bulls.bullscows.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import java.util.List;

import com.cows.bulls.bullscows.Fragments.AddPlayerFragment;
import com.cows.bulls.bullscows.R;

import java.util.ArrayList;

import static com.cows.bulls.bullscows.R.id.add_edit_lawyer_container;

public class StartGameActivity extends AppCompatActivity {

    // GUI ELEMENTS
    TextView numberDisplay, debbugNumber;
    EditText inputNumber;
    Button submitButton;
    Vibrator vibes;
    ListView tryList;
    ArrayAdapter<String> attemptsAdapter;

    // LOGIC ELEMENTS
    int [] numberToGuess = new int[4];
    int bulls = 0;
    int cows = 0;
    int tries = 0;
    boolean start = false;
    ArrayList history = new ArrayList();
    int [] currentGuess = new int[4];
    String numberFinal;
    String numberGuessed;
    boolean valid;
    List<String> attempts;

    // LOGIC METHOD
    // This method forms a string consisting of 4 random int (between 1-9) and puts it in a string numberFinal
    public void numberFormation(){
        int a,b,c,d;
        do {
            a = random();
            b = random();
            c = random();
            d = random();
        } while (a == 0 || a == b || a == c || a == d || b == c || b == d || c == d);

        numberToGuess[0] = a;
        numberToGuess[1] = b;
        numberToGuess[2] = c;
        numberToGuess[3] = d;
        numberFinal = toString(a) + toString(b) + toString(c) + toString(d);
    }

    // convert int to string
    private String toString(int OutputValue) {
        StringBuffer buf = new StringBuffer();
        buf.append(OutputValue);
        return buf.toString();
    }

    // This method takes a string and converts into an int type array
    public void convert(String string, int[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++ ){
            for(int z = 0; z < 10; z++){
                if( string.substring(i,i+1).equals(toString(z)) == true)
                    count++;
            }
        }
        if(count == 4){
            valid = true;
            for (int i = 0; i < arr.length; i++ ){
                arr[i] = Integer.parseInt(string.substring(i,i+1));
            }
        }
    }

    // method responsible for generation of random integers
    public int random(){
        double random = Math.random(); 		// random number is generated between 0.0 to 0.99
        random *= 9; 						// random number is multiplied by 100
        //random++; 							// 1 is added to random number
        int dieValue = (int)random; 		// random number is converted from double to int hence rounding "down" the number
        return dieValue;
    }

    // This method takes the input string that was converted to array and checks it with the final array
    public void checkForBulls(int [] ar, int [] arr){
        for(int i = 0; i < arr.length; i++){
            if(ar[i] != arr[i]){
                if(ar[i] == arr[0] || ar[i] == arr[1] || ar[i] == arr[2] || ar[i] == arr[3]){
                    bulls++;
                }
            }
        }
    }

    // This method takes checks the current given ans with the arraylist to see if the ans already exists
    public boolean checkHistory(ArrayList arr){
        for (int i = 0; i < history.size(); i++){
            if (numberGuessed.equals((String) history.get(i))){
                return true;
            }
        }
        return false;
    }

    // This methods checks for cows
    public void checkForCows(int [] ar, int [] arr){
        for (int i = 0; i < arr.length ; i++){
            if(ar[i] == arr[i]){
                cows++;
            }
        }
    }

    // This method checks for input errors, if no errors it returns FALSE
    public boolean checkForErrors(String string){
        if (string.length() == 4 && string.isEmpty() == false ){
            convert(numberGuessed, currentGuess);

            for(int i = 0; i < currentGuess.length ;i++) {
                switch (i) {
                    case 0:
                        if (currentGuess[i] == currentGuess[1] || currentGuess[i] == currentGuess[2] || currentGuess[i] == currentGuess[3])
                            return true;
                        break;
                    case 1:
                        if (currentGuess[i] == currentGuess[2] || currentGuess[i] == currentGuess[3])
                            return true;
                        break;
                    case 2:
                        if (currentGuess[i] == currentGuess[3])
                            return true;
                        break;
                    case 3:
                        if (currentGuess[0] == 0)
                            return true;
                        break;
                }
            }
            return false;
        }
        else
            return true;
    }

    // This method will run everytime the submit button is pressed
    public void submitNumber(View view){
        bulls = 0;
        cows = 0;
        numberGuessed = inputNumber.getText().toString();

        if(checkForErrors(numberGuessed) == false){
            if(checkHistory(history) == false){
                history.add(numberGuessed);

                convert(numberGuessed,currentGuess);

                tries++;
                checkForBulls(currentGuess,numberToGuess);
                //duckCount.setText(toString(bulls));

                checkForCows(currentGuess,numberToGuess);
                //dragonCount.setText(toString(cows));

                //triesText.setText(toString(tries));
                showMessage("Veo que usted se está acercando! :)");

                //
                addAttempts();

                // when we have 4 cows that means the game is over
                if(cows == 4){
                    showMessage("Usted ha adivinado el número en " + tries + " intentos, impresionante!" );
                    inputNumber.setEnabled(false);
                    submitButton.setEnabled(false);
                    numberDisplay.setText(numberFinal);
                    vibes.vibrate(400);
                    newPlayer();
                }
            }
            else{
                checkForBulls(currentGuess,numberToGuess);
                checkForCows(currentGuess,numberToGuess);
                showMessage("Ya probaste esto y el resultado fue:  " + bulls + " Bulls, " + cows + " Cows.");
                vibes.vibrate(200);
            }
        }
        else{
            //Display error to the user
            showMessage("Verifique el número ingresado.");
            vibes.vibrate(200);
        }
    }

    private void newPlayer(){
        Intent i = new Intent(getApplicationContext(), AddPlayerActivity.class);
        i.putExtra("key",toString(tries));
        startActivity(i);
    }

    private void showMessage(String msj) {
        Context context = getApplicationContext();
        CharSequence text = msj;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void addAttempts() {
        // add log attempts
        attempts.add(numberGuessed + ": " + bulls + "B" + cows + "C");
        // refresh data log
        //attemptsAdapter.clear();
        //attemptsAdapter.addAll(attempts);
        attemptsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Give values to Views
        numberDisplay = (TextView) findViewById(R.id.finalNumber);
        debbugNumber = (TextView) findViewById(R.id.debbugNumber);
        inputNumber = (EditText) findViewById(R.id.input_number);
        submitButton = (Button) findViewById(R.id.button_submit);
        tryList = (ListView) findViewById(R.id.try_list);
        attempts = new ArrayList<String>();
        attemptsAdapter = new ArrayAdapter<String>(this, R.layout.list_item_attempt, R.id.textViewLog, attempts);
        vibes = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Form the number that will be guessed by the user
        numberFormation();

        // right now we are displaying the text for debugging purpose, but we don't want to that later on
        numberDisplay.setText("????");
        debbugNumber.setText(numberFinal);

        //
        tryList.setAdapter(attemptsAdapter);
    }
}
