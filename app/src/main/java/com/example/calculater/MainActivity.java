package com.example.calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
public class MainActivity extends AppCompatActivity {

    TextView workingTV;
    TextView resultsTV;

    String workings = "";
    String formula="";
    String tempFormula ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();
    }
    private void  initTextView(){
        workingTV = (TextView)findViewById(R.id.workingTextView);
        resultsTV = (TextView)findViewById(R.id.resultTextView);
    }

    private  void  setWorking(String givenValue){
        workings = workings+ givenValue;
        workingTV.setText(workings);
    }
    public void ansOnClick(View view){
    Double result = null;
    ScriptEngine engine= new ScriptEngineManager().getEngineByName("rhino");
    checkForPowerOf();

    try {
    result = (double) engine.eval(formula);
    }catch (ScriptException e) {
        Toast.makeText(this,"Invalid Input ",Toast.LENGTH_SHORT).show();
}
    if(result!=null)
        resultsTV.setText(String.valueOf(result.doubleValue()));
}
private void checkForPowerOf(){
    ArrayList<Integer> indexOfPowers = new ArrayList<>();
    for(int i = 0;i<workings.length();i++) {
        if (workings.charAt(i) == '^') {
            indexOfPowers.add(i);
        }
    }
        formula = workings;
        tempFormula = workings;
        for(Integer index : indexOfPowers){
            changeFormula(index);
        }
        formula = tempFormula;
    }
    private void changeFormula(Integer index){
    String numberLeft = "";
    String numberRight = "";

    for(int i = index+1;i<workings.length();i++){
        if(isNumeric(workings.charAt(i)))
            numberRight = numberRight + workings.charAt(i);
        else
            break;;
    }
    for(int i=index-1;i>=0;i--){
        if(isNumeric(workings.charAt(i)))
            numberLeft = numberLeft + workings.charAt(i);
        else
            break;;
    }
    String original = numberLeft + "^" + numberRight;
    String changed = "Math.pow("+numberLeft+ ","+numberRight+ ")";
    tempFormula = tempFormula.replace(original ,changed);
    }
    private  boolean isNumeric(char c){
        if((c<='9' && c>='0') || c == '.')
            return true;

        return false;
    }


    public void clearOnClick(View view){
    workingTV.setText("");
    workings ="";
    resultsTV.setText("");
    leftBracket = true;
    }
    boolean leftBracket = true;
     public void bracketOnClick(View view){
    if(leftBracket) {
    setWorking("(");
    leftBracket = false;
    }else{
        setWorking(")");
        leftBracket = true;
    }
    }

     public void powOnClick(View view){
    setWorking("^");
    }
     public void divOnClick(View view){
         setWorking("/");
    }
     public void sevenOnClick(View view){
         setWorking("7");
    }
     public void eightOnClick(View view){
         setWorking("8");
    }
     public void nineOnClick(View view){
         setWorking("9");
    }
     public void mulOnClick(View view){
         setWorking("*");
    }
     public void fourOnClick(View view){
         setWorking("4");
    }
     public void fiveOnClick(View view){
         setWorking("5");
    }
     public void sixOnClick(View view){
         setWorking("6");
    }
     public void subOnClick(View view){
         setWorking("-");
    }
     public void oneOnClick(View view){
         setWorking("1");
    }
     public void twoOnClick(View view){
         setWorking("2");
    }
     public void threeOnClick(View view){
         setWorking("3");
    }
     public void addOnClick(View view){
         setWorking("+");
    }
    public void dotOnClick(View view){
        setWorking(".");
    }
    public void zeroOnClick(View view){
        setWorking("0");
    }



}