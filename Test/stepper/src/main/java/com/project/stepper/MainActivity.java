package com.project.stepper;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.project.stepper.fragments.FormFragment;
import com.project.stepper.fragments.Instruction;
import com.project.stepper.fragments.TextFragment;
import java.util.ArrayList;
import java.util.List;
import ivb.com.materialstepper.progressMobileStepper;

public class MainActivity extends progressMobileStepper {

    List<Class> stepperFragmentList = new ArrayList<>();

    @Override
    public List<Class> init() {
        TextFragment t = new TextFragment();
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(FormFragment.class);
        stepperFragmentList.add(Instruction.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(FormFragment.class);

        return stepperFragmentList;
    }

    @Override
    public void onStepperCompleted() {
        showCompletedDialog();
    }

    protected void showCompletedDialog(){
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
                MainActivity.this);

        alertDialogBuilder.setTitle("Hooray");
        alertDialogBuilder
                .setMessage("We've completed the stepper")
                .setCancelable(true)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
